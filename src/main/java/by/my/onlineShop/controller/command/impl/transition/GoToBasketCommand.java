package by.my.onlineShop.controller.command.impl.transition;

import by.my.onlineShop.controller.command.Command;
import by.my.onlineShop.controller.command.CommandResult;
import by.my.onlineShop.controller.command.CommandResultType;
import by.my.onlineShop.controller.context.RequestContext;
import by.my.onlineShop.controller.context.RequestContextHelper;
import by.my.onlineShop.entity.Category;
import by.my.onlineShop.entity.Order;
import by.my.onlineShop.entity.Product;
import by.my.onlineShop.entity.User;
import by.my.onlineShop.exeptions.ServiceException;
import by.my.onlineShop.service.*;
import by.my.onlineShop.service.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class GoToBasketCommand implements Command { // переход в корзину
    private static final String PAGE = "WEB-INF/view/basket.jsp";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String CATEGORIES = "categories";
    private static final String ORDERS = "orders";
    private static final String USER = "user";
    private static final String PRODUCTS = "products";
    private static final String TOTAL_COST = "totalCost";
    private static final String NEW_PRICES = "newPrices";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();

        User user = (User) requestContext.getSessionAttribute(USER);
        if (user == null) {
            helper.updateRequest(requestContext);
            return new CommandResult(PAGE, CommandResultType.FORWARD);
        }
        try {
            CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();
            // берем список категорий
            List<Category> categories = categoryService.retrieveCategories();
            requestContext.addRequestAttribute(CATEGORIES, categories);

            // берем список покупок
            long userId = user.getId();
            OrderService orderService = ServiceFactory.getInstance().getOrderService();
            List<Order> orders = orderService.retrieveOrdersByUserWhereProductStatusTrue(userId);
            requestContext.addRequestAttribute(ORDERS, orders);

            // итоговую цену
            double totalPrice = orderService.calculateTotalCost(orders);
            requestContext.addRequestAttribute(TOTAL_COST, totalPrice);

            // список продуктов
            ProductService productService = ServiceFactory.getInstance().getProductService();
            List<Product> products = productService.getProductsFromOrders(orders);
            requestContext.addRequestAttribute(PRODUCTS, products);

            // акции на продукты
            PromotionService promotionService = ServiceFactory.getInstance().getPromotionService();
            // новая цена с акцией
            Map<String, Double> newPrices = promotionService.getNewPrices(products);
            requestContext.addRequestAttribute(NEW_PRICES, newPrices);
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.FORWARD);
    }
}
