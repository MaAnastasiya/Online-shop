package by.my.onlineShop.controller.command.impl;

import by.my.onlineShop.controller.command.Command;
import by.my.onlineShop.controller.command.CommandResult;
import by.my.onlineShop.controller.command.CommandResultType;
import by.my.onlineShop.controller.context.RequestContext;
import by.my.onlineShop.controller.context.RequestContextHelper;
import by.my.onlineShop.exeptions.ServiceException;
import by.my.onlineShop.service.PromotionService;
import by.my.onlineShop.service.ServiceFactory;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ConfirmAddingPromotionCommand implements Command { // подтверждение добавления акции
    private static final String PAGE = "command=addPromotion";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String PROMOTION_NAME = "promotion-name";
    private static final String BEGINNING_DATE = "beginning-date";
    private static final String EXPIRATION_DATE = "expiration-date";
    private static final String DISCOUNT = "discount";
    private static final String PHOTO = "photo";
    private static final String DESCRIPTION = "description";
    private static final String MESSAGE_PARAMETER = "&message=";
    private static final String ERROR = "error";
    private static final String OK = "ok";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        // формируем контекст запроса
        RequestContext requestContext = helper.createContext();
        String message = ERROR;
        // имя акции
        Optional<String> promotionName = Optional.ofNullable(requestContext.getRequestParameter(PROMOTION_NAME));
        // фото акции
        Optional<String> photo = Optional.ofNullable(requestContext.getRequestParameter(PHOTO));
        // дата начала акции
        Optional<String> beginningDate = Optional.ofNullable(requestContext.getRequestParameter(BEGINNING_DATE));
        // дата конца акции
        Optional<String> expirationDate = Optional.ofNullable(requestContext.getRequestParameter(EXPIRATION_DATE));
        // описание акции
        Optional<String> description = Optional.ofNullable(requestContext.getRequestParameter(DESCRIPTION));
        // скидка
        Optional<String> discount = Optional.ofNullable(requestContext.getRequestParameter(DISCOUNT));

        try {
            // если все параметры на месте
            if (promotionName.isPresent() && photo.isPresent() && beginningDate.isPresent() && expirationDate.isPresent() &&
                    description.isPresent() && discount.isPresent()) {

                PromotionService promotionService = ServiceFactory.getInstance().getPromotionService();
                // добавляем новую акцию
                boolean result = promotionService.addNewPromotion(promotionName.get(), photo.get(), beginningDate.get(),
                        expirationDate.get(), description.get(), discount.get());
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
