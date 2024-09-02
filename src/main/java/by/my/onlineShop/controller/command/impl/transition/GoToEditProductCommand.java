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
import by.my.onlineShop.service.ServiceFactory;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class GoToEditProductCommand implements Command { // команда изменить продукт
    private static final String PAGE = "WEB-INF/view/editProduct.jsp";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String CATEGORIES = "categories";
    private static final String PRODUCT_ID = "productId";
    private static final String PRODUCT = "product";
    private static final String CATEGORY = "category";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();// формируем контекст запроса

        try {
            // формируем список доступных категорий
            CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();
            List<Category> categories = categoryService.retrieveCategories();
            // передаем в контекст категории
            requestContext.addRequestAttribute(CATEGORIES, categories);

            long productId = Long.parseLong(requestContext.getRequestParameter(PRODUCT_ID));
            ProductService productService = ServiceFactory.getInstance().getProductService();
            // извлекаем продукт по его id
            Optional<Product> product = productService.retrieveProductById(productId);
            if (product.isPresent()) {
                requestContext.addRequestAttribute(PRODUCT, product.get());
                Optional<Category> category = categoryService.retrieveCategoryBtId(product.get().getCategoryId());
                if (category.isPresent()) {
                    // записывает в контекст продукт и его категорию
                    requestContext.addRequestAttribute(PRODUCT, product.get());
                    requestContext.addRequestAttribute(CATEGORY, category.get());
                }
            }
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.FORWARD);
    }
}
