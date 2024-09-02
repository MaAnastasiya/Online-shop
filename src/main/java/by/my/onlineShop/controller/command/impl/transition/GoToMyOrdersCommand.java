package by.my.onlineShop.controller.command.impl.transition;

import by.my.onlineShop.controller.command.Command;
import by.my.onlineShop.controller.command.CommandResult;
import by.my.onlineShop.controller.command.CommandResultType;
import by.my.onlineShop.controller.context.RequestContext;
import by.my.onlineShop.controller.context.RequestContextHelper;
import by.my.onlineShop.entity.*;
import by.my.onlineShop.entity.*;
import by.my.onlineShop.exeptions.ServiceException;
import by.my.onlineShop.service.*;
import by.my.onlineShop.service.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GoToMyOrdersCommand implements Command { // добавленные заказы, которые ты ждешь
    private static final String PAGE = "WEB-INF/view/myOrders.jsp";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String USER_ORDERS = "userOrders";
    private static final String ORDERS = "orders";
    private static final String PRODUCTS = "products";
    private static final String CATEGORIES = "categories";
    private static final String USER = "user";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();

        User user = (User) requestContext.getSessionAttribute(USER);
        if (user == null) {
            helper.updateRequest(requestContext);
            return new CommandResult(PAGE, CommandResultType.FORWARD);
        }
        try {
            // берем список категорий
            CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();
            List<Category> categories = categoryService.retrieveCategories();
            requestContext.addRequestAttribute(CATEGORIES, categories);

            // берем список всех заказов
            long userId = user.getId();
            OrderService orderService = ServiceFactory.getInstance().getOrderService();
            List<Order> orders = orderService.retrieveOrdersByUser(userId);
            requestContext.addRequestAttribute(ORDERS, orders);

            // берем информацию о пользовательском заказе
            UserOrderService userOrderService = ServiceFactory.getInstance().getUserOrderService();
            List<UserOrder> userOrders = userOrderService.getUserOrdersFromOrders(orders);
            requestContext.addRequestAttribute(USER_ORDERS, userOrders);

            // берем продукты этого заказа
            ProductService productService = ServiceFactory.getInstance().getProductService();
            List<Product> products = productService.getProductsFromOrders(orders);
            requestContext.addRequestAttribute(PRODUCTS, products);
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.FORWARD);
    }
}
