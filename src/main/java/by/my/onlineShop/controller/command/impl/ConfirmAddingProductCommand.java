package by.my.onlineShop.controller.command.impl;

import by.my.onlineShop.controller.command.Command;
import by.my.onlineShop.controller.command.CommandResult;
import by.my.onlineShop.controller.command.CommandResultType;
import by.my.onlineShop.controller.context.RequestContext;
import by.my.onlineShop.controller.context.RequestContextHelper;
import by.my.onlineShop.exeptions.ServiceException;
import by.my.onlineShop.service.ProductService;
import by.my.onlineShop.service.ServiceFactory;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
// Подтвердите Добавление Команды Продукта
public class ConfirmAddingProductCommand implements Command {
    private static final String PAGE = "command=addProduct";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String PRODUCT_NAME = "product-name";
    private static final String PHOTO = "photo";
    private static final String PRICE = "price";
    private static final String CATEGORY = "category";
    private static final String DESCRIPTION = "description";
    private static final String AVAILABILITY = "availability";
    private static final String MESSAGE_PARAMETER = "&message=";
    private static final String ERROR = "error";
    private static final String OK = "ok";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        // формируем контекст запроса
        RequestContext requestContext = helper.createContext();
        String message = ERROR;
        // имя продукта
        Optional<String> productName = Optional.ofNullable(requestContext.getRequestParameter(PRODUCT_NAME));
        // фото продукта
        Optional<String> photo = Optional.ofNullable(requestContext.getRequestParameter(PHOTO));
        // цена
        Optional<String> price = Optional.ofNullable(requestContext.getRequestParameter(PRICE));
        // категория
        Optional<String> category = Optional.ofNullable(requestContext.getRequestParameter(CATEGORY));
        // описание
        Optional<String> description = Optional.ofNullable(requestContext.getRequestParameter(DESCRIPTION));
        // есть ли в наличии
        Optional<String> availability = Optional.ofNullable(requestContext.getRequestParameter(AVAILABILITY));

        try {
            // если все параметры присутствуют
            if (productName.isPresent() && photo.isPresent() && price.isPresent() && category.isPresent() &&
                    description.isPresent()) {
                boolean status = false;
                if (availability.isPresent()) { // если товар доступен
                    status = true;
                }

                ProductService productService = ServiceFactory.getInstance().getProductService();
                // добавляем новый продукт
                boolean result = productService.addNewProduct(productName.get(),
                        photo.get(), price.get(), category.get(), status, description.get());
                if (result) {
                    message = OK;
                }
            }
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        helper.updateRequest(requestContext);
        return new CommandResult(PAGE + MESSAGE_PARAMETER + message, CommandResultType.REDIRECT);
    }
}
