package by.my.onlineShop.controller.command.impl.transition;

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

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GoToAddOrderCommand implements Command { // команда добавить заказ ( оформление заказа)
    private static final String PAGE = "WEB-INF/view/addOrder.jsp";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String USER = "user";
    private static final String TOTAL_COST = "totalCost";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();

        User user = (User) requestContext.getSessionAttribute(USER);
        long userId = user.getId();

        try {

            OrderService orderService = ServiceFactory.getInstance().getOrderService();
            // берем список покупок по id пользователя
            List<Order> orders = orderService.retrieveOrdersByUserWhereProductStatusTrue(userId);
            // формируем новую цену
            double totalPrice = orderService.calculateTotalCost(orders);
            requestContext.addRequestAttribute(TOTAL_COST, totalPrice);
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }
        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.FORWARD);
    }
}