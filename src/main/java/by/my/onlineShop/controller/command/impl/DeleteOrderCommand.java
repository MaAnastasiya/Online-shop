package by.my.onlineShop.controller.command.impl;

import by.my.onlineShop.controller.command.Command;
import by.my.onlineShop.controller.command.CommandResult;
import by.my.onlineShop.controller.command.CommandResultType;
import by.my.onlineShop.controller.context.RequestContext;
import by.my.onlineShop.controller.context.RequestContextHelper;
import by.my.onlineShop.exeptions.ServiceException;
import by.my.onlineShop.service.OrderService;
import by.my.onlineShop.service.ServiceFactory;

import javax.servlet.http.HttpServletResponse;

public class DeleteOrderCommand implements Command { // Команда Удалить заказ
    private static final String PAGE = "command=basket";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String ORDER_ID = "orderId";


    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();

        try {
            // id заказа
            long orderId = Long.parseLong(requestContext.getRequestParameter(ORDER_ID));
            OrderService orderService = ServiceFactory.getInstance().getOrderService();
            // удаление заказа по индификатору
            orderService.removeOrderById(orderId);
        } catch (ServiceException | NumberFormatException e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.REDIRECT);
    }
}
