package by.my.onlineShop.controller.command.impl;

import by.my.onlineShop.controller.command.Command;
import by.my.onlineShop.controller.command.CommandResult;
import by.my.onlineShop.controller.command.CommandResultType;
import by.my.onlineShop.controller.context.RequestContext;
import by.my.onlineShop.controller.context.RequestContextHelper;
import by.my.onlineShop.entity.Product;
import by.my.onlineShop.exeptions.ServiceException;
import by.my.onlineShop.service.ProductService;
import by.my.onlineShop.service.ServiceFactory;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ConfirmProductChangeCommand implements Command { // Команда Изменения продукта
    private static final String PAGE = "command=catalog";
    private static final String CATEGORY_PARAMETER = "&categoryId=";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String PRODUCT_NAME = "product-name";
    private static final String PHOTO = "photo";
    private static final String PRICE = "price";
    private static final String CATEGORY = "category";
    private static final String PROMOTION = "promotion";
    private static final String DESCRIPTION = "description";
    private static final String AVAILABILITY = "availability";
    private static final String PRODUCT_ID = "productId";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();

        // имя продукта
        Optional<String> productName = Optional.ofNullable(requestContext.getRequestParameter(PRODUCT_NAME));
        // фото продукта
        Optional<String> photo = Optional.ofNullable(requestContext.getRequestParameter(PHOTO));
        // цена продукта
        Optional<String> price = Optional.ofNullable(requestContext.getRequestParameter(PRICE));
        // категория продукта
        Optional<String> category = Optional.ofNullable(requestContext.getRequestParameter(CATEGORY));
        // описание продукта
        Optional<String> description = Optional.ofNullable(requestContext.getRequestParameter(DESCRIPTION));
        // есть ли продукт в доступе
        Optional<String> availability = Optional.ofNullable(requestContext.getRequestParameter(AVAILABILITY));
        // акция для продукта
        Optional<String> promotion = Optional.ofNullable(requestContext.getRequestParameter(PROMOTION));
        // индификатор продукта
        Optional<String> productId = Optional.ofNullable(requestContext.getRequestParameter(PRODUCT_ID));

        try {
            ProductService productService = ServiceFactory.getInstance().getProductService();
            long categoryId = 0;

            if (productName.isPresent() && photo.isPresent() && price.isPresent() && category.isPresent() &&
                    description.isPresent()) {
                // если все параметры на месте
                boolean status = false;
                if (availability.isPresent()) { // если продукт в доступе
                    status = true;
                }

                // обновляем параметры продукта
                productService.updateProductInformation(productId.get(), productName.get(),
                        photo.get(), price.get(), category.get(), status, description.get(), promotion.get());
            }
            Optional<Product> product = productService.retrieveProductById(Long.parseLong(productId.get()));
            if (product.isPresent()) {
                categoryId = product.get().getCategoryId();
                helper.updateRequest(requestContext);
                return new CommandResult(PAGE + CATEGORY_PARAMETER + categoryId, CommandResultType.REDIRECT);
            }
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }
    }
}
