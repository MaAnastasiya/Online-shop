package by.my.onlineShop.controller.command.impl;

import by.my.onlineShop.controller.command.Command;
import by.my.onlineShop.controller.command.CommandResult;
import by.my.onlineShop.controller.command.CommandResultType;
import by.my.onlineShop.controller.context.RequestContext;
import by.my.onlineShop.controller.context.RequestContextHelper;
import by.my.onlineShop.entity.Role;
import by.my.onlineShop.entity.User;
import by.my.onlineShop.exeptions.ServiceException;
import by.my.onlineShop.service.RoleService;
import by.my.onlineShop.service.ServiceFactory;
import by.my.onlineShop.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LogInCommand implements Command { // вход в систему
    private static final String PROFILE_PAGE = "command=profile";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String LOGIN_PAGE = "WEB-INF/view/login.jsp";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String EMAIL_PARAMETER = "email";
    private static final String USER = "user";
    private static final String ROLE = "role";
    private static final String ERROR_MESSAGE = "errorMessage";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();
        // взяли пароль и почту
        String password = requestContext.getRequestParameter(PASSWORD_PARAMETER);
        String login = requestContext.getRequestParameter(EMAIL_PARAMETER);
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            // Аутентификация пользователя
            Optional<User> optionalResult = userService.login(login, password);

            if (optionalResult.isPresent()) { // если аутендефикацию прошли
                requestContext.addSessionAttribute(USER, optionalResult.get());

                RoleService roleService = ServiceFactory.getInstance().getRoleService();
                // взяли роль данного пользователя
                Optional<Role> role = roleService.retrieveRoleById(optionalResult.get().getRoleId());
                role.ifPresent(value -> requestContext.addSessionAttribute(ROLE, value));

                helper.updateRequest(requestContext);
                return new CommandResult(PROFILE_PAGE, CommandResultType.REDIRECT);
            }
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        // если проблемы с почтой или паролем - скажем об этом
        requestContext.addRequestAttribute(ERROR_MESSAGE, true);
        helper.updateRequest(requestContext);
        return new CommandResult(LOGIN_PAGE, CommandResultType.FORWARD);
    }
}