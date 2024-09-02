package by.my.onlineShop.service;

import by.my.onlineShop.entity.Order;
import by.my.onlineShop.entity.UserOrder;
import by.my.onlineShop.exeptions.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserOrderService { // Услуга о заказах пользователей

    // получение заказов пользователей по id
    // На входе - Идентификатор пользователя для получения заказов
    // На выходе - необязателен для заказа пользователя
    Optional<UserOrder> retrieveUserOrderById(long userOrderId) throws ServiceException;


    // Получение заказов пользователей по статусу
    // На входе - статус заказа пользователя
    // На выходе - список пользовательских заказов
    List<UserOrder> retrieveUserOrderByStatus(String status) throws ServiceException;


    // Обновление статуса в порядке пользователя по id
    // На вход - id пользователя для обновления
    // На выходе - true, если статус заказа пользователя был успешно обновлен
    boolean updateStatusAtUserOrderById(long userOrderId, String status) throws ServiceException;


    // Добавление нового пользовательского заказа
    // На вход - заказы пользователя, адрес доставки, дата доставки, имя владельца карты
    // CVV, номер карты, месяц карты, год карты, итоговая цена
    // На выходе - true, если заказ пользователя был успешно добавлен
    boolean addNewUserOrder(List<Order> orders, String address, String deliveryDateString, String cardholderName,
                            String cvvString, String cardNumberString, String monthString, String yearString, double totalPrice) throws ServiceException;


    // Получение пользовательских заказов из заказов
    // На вход - Список заказов
    // На выходе - список пользовательских заказов
    List<UserOrder> getUserOrdersFromOrders(List<Order> orders) throws ServiceException;
}
