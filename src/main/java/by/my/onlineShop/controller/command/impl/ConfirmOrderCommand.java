package by.my.onlineShop.controller.command.impl;

import by.my.onlineShop.controller.command.Command;
import by.my.onlineShop.controller.command.CommandResult;
import by.my.onlineShop.controller.command.CommandResultType;
import by.my.onlineShop.controller.context.RequestContext;
import by.my.onlineShop.controller.context.RequestContextHelper;
import by.my.onlineShop.entity.Order;
import by.my.onlineShop.entity.User;
import by.my.onlineShop.exeptions.ServiceException;
import by.my.onlineShop.service.OrderService;
import by.my.onlineShop.service.ServiceFactory;
import by.my.onlineShop.service.UserOrderService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class ConfirmOrderCommand implements Command { // Команда Подтверждения Заказа
    private static final String ADD_ORDER_PAGE = "WEB-INF/view/addOrder.jsp";
    private static final String MY_ORDERS_PAGE = "command=myOrders";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String ADDRESS = "address";
    private static final String DELIVERY_DATE = "delivery-date";
    private static final String CARDHOLDER_NAME = "cardholder-name";
    private static final String CVV = "cvv";
    private static final String CARD_NUMBER = "card-number";
    private static final String MONTH = "month";
    private static final String YEAR = "year";
    private static final String USER = "user";
    private static final String TOTAL_COST = "totalCost";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();

        // адрес заказа
        Optional<String> address = Optional.ofNullable(requestContext.getRequestParameter(ADDRESS));
        // дата доставки
        Optional<String> deliveryDate = Optional.ofNullable(requestContext.getRequestParameter(DELIVERY_DATE));
        // имя владельца карты
        Optional<String> cardholderName = Optional.ofNullable(requestContext.getRequestParameter(CARDHOLDER_NAME));
        // cvv карты
        Optional<String> cvvString = Optional.ofNullable(requestContext.getRequestParameter(CVV));
        // номер карты
        Optional<String> cardNumberString = Optional.ofNullable(requestContext.getRequestParameter(CARD_NUMBER));
        // месяц на карте
        Optional<String> monthString = Optional.ofNullable(requestContext.getRequestParameter(MONTH));
        // год на карте
        Optional<String> yearString = Optional.ofNullable(requestContext.getRequestParameter(YEAR));

        try {
            // взяли пользователя
            User user = (User) requestContext.getSessionAttribute(USER);
            // взяли id пользователя
            long userId = user.getId();
            OrderService orderService = ServiceFactory.getInstance().getOrderService();
            // список заказов данного пользователя
            List<Order> orders = orderService.retrieveOrdersByUserWhereProductStatusTrue(userId);
            //  цена заказа
            double totalPrice = orderService.calculateTotalCost(orders);

            // если все это есть
            if (isPresent(address, deliveryDate, cardholderName, cvvString, cardNumberString, monthString, yearString)) {

                UserOrderService userOrderService = ServiceFactory.getInstance().getUserOrderService();
                // добавляем новый пользовательский заказ
                boolean result = userOrderService.addNewUserOrder(orders, address.get(), deliveryDate.get(), cardholderName.get(),
                        cvvString.get(), cardNumberString.get(), monthString.get(), yearString.get(), totalPrice);
                if (result) {
                    helper.updateRequest(requestContext);
                    return new CommandResult(MY_ORDERS_PAGE, CommandResultType.REDIRECT);
                }
            }
            requestContext.addRequestAttribute(TOTAL_COST, totalPrice);
            requestContext.addRequestAttribute(ERROR_MESSAGE, true);
            helper.updateRequest(requestContext);
            return new CommandResult(ADD_ORDER_PAGE, CommandResultType.FORWARD);
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }
    }

    public boolean isPresent(Optional<String> address, Optional<String> deliveryDate, Optional<String> cardholderName,
                             Optional<String> cvv, Optional<String> cardNumber, Optional<String> month, Optional<String> year) {
        return address.isPresent() && deliveryDate.isPresent() && cardholderName.isPresent() && cvv.isPresent()
                && cardNumber.isPresent() && month.isPresent() && year.isPresent();
    }
}
