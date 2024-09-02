package by.my.onlineShop.controller.command.impl.transition;

import by.my.onlineShop.controller.command.Command;
import by.my.onlineShop.controller.command.CommandResult;
import by.my.onlineShop.controller.command.CommandResultType;
import by.my.onlineShop.controller.context.RequestContext;
import by.my.onlineShop.controller.context.RequestContextHelper;
import by.my.onlineShop.entity.Category;
import by.my.onlineShop.entity.Product;
import by.my.onlineShop.exeptions.ServiceException;
import by.my.onlineShop.service.CategoryService;
import by.my.onlineShop.service.ProductService;
import by.my.onlineShop.service.PromotionService;
import by.my.onlineShop.service.ServiceFactory;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class GoToCatalogCommand implements Command { // переход в определенный каталог
    private static final String PAGE = "WEB-INF/view/catalog.jsp";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String CATEGORY_ID = "categoryId";
    private static final String CATEGORIES = "categories";
    private static final String PRODUCTS = "products";
    private static final String NEW_PRICES = "newPrices";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();

        try {
            // берем список категорий
            CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();
            List<Category> categories = categoryService.retrieveCategories();
            requestContext.addRequestAttribute(CATEGORIES, categories);

            // берем id категории
            long categoryId = Long.parseLong(requestContext.getRequestParameter(CATEGORY_ID));
            ProductService productService = ServiceFactory.getInstance().getProductService();
            // берем продукты по id категории
            List<Product> products = productService.retrieveProductsByCategory(categoryId);
            requestContext.addRequestAttribute(PRODUCTS, products);

            // берем акции
            PromotionService promotionService = ServiceFactory.getInstance().getPromotionService();
            // вычисляем новую цену относительно акций
            Map<String, Double> newPrices = promotionService.getNewPrices(products);
            requestContext.addRequestAttribute(NEW_PRICES, newPrices);
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.FORWARD);
    }
}
