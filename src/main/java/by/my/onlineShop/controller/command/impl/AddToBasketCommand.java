package by.my.onlineShop.controller.command.impl;

import by.my.onlineShop.controller.command.Command;
import by.my.onlineShop.controller.command.CommandResult;
import by.my.onlineShop.controller.command.CommandResultType;
import by.my.onlineShop.controller.context.RequestContext;
import by.my.onlineShop.controller.context.RequestContextHelper;
import by.my.onlineShop.entity.Product;
import by.my.onlineShop.entity.User;
import by.my.onlineShop.exeptions.ServiceException;
import by.my.onlineShop.service.OrderService;
import by.my.onlineShop.service.ProductService;
import by.my.onlineShop.service.ServiceFactory;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AddToBasketCommand implements Command { // Команда Добавить В Корзину
    private static final String PAGE = "command=catalog";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String CATEGORY_ID_PARAMETER = "&categoryId=";
    private static final String MESSAGE_PARAMETER = "&message=";
    private static final String USER = "user";
    private static final String PRODUCT_ID = "productId";
    private static final String QUANTITY = "quantity";
    private static final String OK = "ok";
    private static final String ERROR = "error";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext(); // получаем контекст запроса

        User user = (User) requestContext.getSessionAttribute(USER);
        long userId = user.getId();
        try { // разбираем параметр запроса
            long productId = Long.parseLong(requestContext.getRequestParameter(PRODUCT_ID)); // id продукта
            int quantity = Integer.parseInt(requestContext.getRequestParameter(QUANTITY)); // количество
            OrderService orderService = ServiceFactory.getInstance().getOrderService();
            String message = OK;
            boolean result = orderService.addNewOrder(userId, productId, quantity); // добавляем новый товар
            if (!result) message = ERROR;

            ProductService productService = ServiceFactory.getInstance().getProductService();
            // извлекаем продукт по id
            Optional<Product> product = productService.retrieveProductById(productId);

            long categoryId = 0;
            if (product.isPresent()) { // берем категорию нашего продукта
                categoryId = product.get().getCategoryId();
            }

            helper.updateRequest(requestContext);
            // отправляем строку, если все оки
            return new CommandResult(PAGE + CATEGORY_ID_PARAMETER + categoryId
                    + MESSAGE_PARAMETER + message, CommandResultType.REDIRECT);
        } catch (ServiceException | NumberFormatException e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }
    }
}
