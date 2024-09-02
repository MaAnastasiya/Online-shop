package by.my.onlineShop.service.impl;

import by.my.onlineShop.dao.DaoFactory;
import by.my.onlineShop.dao.OrderDao;
import by.my.onlineShop.dao.UserOrderDao;
import by.my.onlineShop.entity.Order;
import by.my.onlineShop.entity.UserOrder;
import by.my.onlineShop.exeptions.DaoException;
import by.my.onlineShop.exeptions.ServiceException;
import by.my.onlineShop.service.BankCardService;
import by.my.onlineShop.service.ServiceFactory;
import by.my.onlineShop.service.UserOrderService;
import by.my.onlineShop.service.validator.Validator;
import by.my.onlineShop.service.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserOrderServiceImpl implements UserOrderService {
    private static final Logger logger = LogManager.getLogger();

    // получить заказ пользователя по id
    @Override
    public Optional<UserOrder> retrieveUserOrderById(long userOrderId) throws ServiceException {
        try {
            // достаем все пользовательские заказы из БД
            UserOrderDao userOrderDao = DaoFactory.getInstance().getUserOrderDao();
            Optional<UserOrder> result;
            // берем заказ пользователя по id пользователя
            result = userOrderDao.findById(userOrderId);
            return result;
        } catch (DaoException e) {
            logger.error("Unable to retrieve user order by id!");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    // получение списка заказов пользователей по статусу
    @Override
    public List<UserOrder> retrieveUserOrderByStatus(String status) throws ServiceException {
        try {
            // берем заказы из БД
            UserOrderDao userOrderDao = DaoFactory.getInstance().getUserOrderDao();
            List<UserOrder> result = null;
            // возвращаем все заказы с переданным статусом
            result = userOrderDao.findByStatus(status);
            return result;
        } catch (DaoException e) {
            logger.error("Unable to retrieve promotions!");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    // обновление статуса пользовательского заказа по id
    @Override
    public boolean updateStatusAtUserOrderById(long userOrderId, String status) throws ServiceException {
        try {
            // берем заказы из БД
            UserOrderDao userOrderDao = DaoFactory.getInstance().getUserOrderDao();
            // заказ по id заказа
            Optional<UserOrder> userOrder = userOrderDao.findById(userOrderId);
            if (!userOrder.isPresent()) { // проверяем его существование
                return false;
            }
            // обновляем статус заказа
            userOrderDao.updateStatusById(userOrderId, status);
            return true;
        } catch (DaoException e) {
            logger.error("Unable to update user order status!");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    // добавляем новый пользовательский заказ
    @Override
    public boolean addNewUserOrder(List<Order> orders, String address, String deliveryDateString, String cardholderName,
                                   String cvvString, String cardNumberString, String monthString, String yearString, double totalPrice) throws ServiceException {
        try {
            // проверяем, что все параметры для нового заказа переданы
            if (address == null || deliveryDateString == null || orders.size() < 1 || cardholderName == null ||
                    cvvString == null || cardNumberString == null || monthString == null || yearString == null) {
                return false;
            }

            Date deliveryDate = new SimpleDateFormat("yyyy-MM-dd").parse(deliveryDateString);
            Date currentDate = new Date();
            // проверяем корректность даты доставки относительно текущей даты
            if (!isDateValid(deliveryDate, currentDate)) {
                return false;
            }

            // проверка корректности всех введенных данных
            Validator cardNumberValidator = ValidatorFactory.getInstance().getCardNumberValidator();
            Validator cvvValidator = ValidatorFactory.getInstance().getCvvValidator();
            Validator monthValidator = ValidatorFactory.getInstance().getMonthValidator();
            Validator yearValidator = ValidatorFactory.getInstance().getYearValidator();

            if (!(cardNumberValidator.isValid(cardNumberString) && cvvValidator.isValid(cvvString)
                    && monthValidator.isValid(monthString) && yearValidator.isValid(yearString))) {
                return false;
            }

            // преобразуем все параметры карты в int
            long cardNumber = Long.parseLong(cardNumberString);
            int cvv = Integer.parseInt(cvvString);
            int month = Integer.parseInt(monthString);
            int year = Integer.parseInt(yearString);
            // действительна ли относительно сегодняшней даты карта
            if (!isCardDateValid(month, year)) {
                return false;
            }

            // проводим оплату по карте
            BankCardService bankCardService = ServiceFactory.getInstance().getBankCardService();
            if (!bankCardService.makePayment(cardNumber, year,month, cardholderName, cvv, totalPrice)) {
                return false;
            }

            // создаем новый заказ со статусом ожидается
            UserOrder userOrder = buildUserOrder(address, deliveryDate, currentDate, "ожидается");
            UserOrderDao userOrderDao = DaoFactory.getInstance().getUserOrderDao();
            // сохраняем этот заказ в базе данных в таблицу пользовательский заказ
            long userOrderId = userOrderDao.save(userOrder);

            OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
            // берем из БД всевозможные заказы и обновляем
            for (Order order : orders) {
                orderDao.updateUserOrder(order.getId(), userOrderId);
            }
            return true;
        } catch (ParseException | DaoException e) {
            logger.error("Unable to add new user order!");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    // получать пользовательские заказы Из Заказов
    @Override
    public List<UserOrder> getUserOrdersFromOrders(List<Order> orders) throws ServiceException {
        List<UserOrder> userOrders = new LinkedList<>();

        // идем по пользовательским заказам
        for (Order order : orders) {
            // получаем заказ пользователя по id
            Optional<UserOrder> userOrder = retrieveUserOrderById(order.getUserOrderId());
            if (userOrder.isPresent()) { // если таковой существует
                // проверяем существует Ли Заказ
                boolean checkIfOrderExistFlag = false;
                for (UserOrder value : userOrders) {
                    if (value.getId() == userOrder.get().getId()) {
                        checkIfOrderExistFlag = true;
                    }
                }
                if (!checkIfOrderExistFlag) { // если заказ не существует, то добавляем его
                    userOrders.add(userOrder.get());
                }
            }
        }

        return userOrders;
    }

    // проверка, что дата доставки должна быть больше текущей даты
    private boolean isDateValid(Date deliveryDate, Date currentDate) {
        return deliveryDate.compareTo(currentDate) > 0;
    }

    // не кончился ли срок действия карты
    private boolean isCardDateValid(int month, int year) {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int currentMonth = cal.get(Calendar.MONTH);
        int currentYear = cal.get(Calendar.YEAR);

        if (year < currentYear) return false;
        if (year == currentYear) {
            if (month <= currentMonth) {
                return false;
            }
        }
        return true;
    }

    // создаем новый пользовательский заказ
    private UserOrder buildUserOrder(String address, Date deliveryDate, Date orderDate, String status) {
        UserOrder userOrder = new UserOrder();
        userOrder.setAddress(address);
        userOrder.setOrderDate(orderDate);
        userOrder.setDeliveryDate(deliveryDate);
        userOrder.setStatus(status);
        return userOrder;
    }
}
