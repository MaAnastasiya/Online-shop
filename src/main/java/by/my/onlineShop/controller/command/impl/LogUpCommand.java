package by.my.onlineShop.controller.command.impl;

import by.my.onlineShop.controller.command.Command;
import by.my.onlineShop.controller.command.CommandResult;
import by.my.onlineShop.controller.command.CommandResultType;
import by.my.onlineShop.controller.context.RequestContext;
import by.my.onlineShop.controller.context.RequestContextHelper;
import by.my.onlineShop.exeptions.ServiceException;
import by.my.onlineShop.service.ServiceFactory;
import by.my.onlineShop.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LogUpCommand implements Command { // зарегистрироваться
    private static final String LOG_UP_PAGE = "WEB-INF/view/logup.jsp";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String EMAIL = "email";
    private static final String PASSWORD_FIRST = "password-first";
    private static final String PASSWORD_SECOND = "password-second";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String PATRONYMIC = "patronymic";
    private static final String PHONE = "phone";
    private static final String MESSAGE = "message";
    private static final String ERROR = "error";
    private static final String OK = "ok";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();
        String message = ERROR;

        // введенная почта
        Optional<String> email = Optional.ofNullable(requestContext.getRequestParameter(EMAIL));
        // 1ый пароль
        Optional<String> passwordFirst = Optional.ofNullable(requestContext.getRequestParameter(PASSWORD_FIRST));
        // подтвержденный 2ой пароль
        Optional<String> passwordSecond = Optional.ofNullable(requestContext.getRequestParameter(PASSWORD_SECOND));
        // имя
        Optional<String> name = Optional.ofNullable(requestContext.getRequestParameter(NAME));
        // фамилия
        Optional<String> surname = Optional.ofNullable(requestContext.getRequestParameter(SURNAME));
        // отчество
        Optional<String> patronymic = Optional.ofNullable(requestContext.getRequestParameter(PATRONYMIC));
        // телефон
        Optional<String> phone = Optional.ofNullable(requestContext.getRequestParameter(PHONE));

        try {
            // если все параметры введены
            if (email.isPresent() && passwordFirst.isPresent() && passwordSecond.isPresent() &&
                    name.isPresent() && surname.isPresent() && patronymic.isPresent() && phone.isPresent()) {
                // если пароли совпадают
                if (passwordFirst.get().equals(passwordSecond.get())) {
                    UserService userService = ServiceFactory.getInstance().getUserService();
                    // регистрируем пользователя
                    boolean result = userService.register(name.get(), surname.get(), patronymic.get(), email.get(), phone.get(), DigestUtils.sha1Hex(passwordFirst.get()));
                    if (result) message = OK;
                }
            }
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        requestContext.addRequestAttribute(MESSAGE, message);
        helper.updateRequest(requestContext);
        return new CommandResult(LOG_UP_PAGE, CommandResultType.FORWARD);
    }
}
