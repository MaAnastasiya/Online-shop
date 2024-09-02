package by.my.onlineShop.controller.command.impl.transition;

import by.my.onlineShop.controller.command.Command;
import by.my.onlineShop.controller.command.CommandResult;
import by.my.onlineShop.controller.command.CommandResultType;
import by.my.onlineShop.controller.context.RequestContext;
import by.my.onlineShop.controller.context.RequestContextHelper;

import javax.servlet.http.HttpServletResponse;

public class DefaultCommand implements Command {
    // если пользователь сделал недопустимую команду - ошибка
    private static final String PAGE = "WEB-INF/view/error.jsp";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        // приняли контекст запроса + сам запрос
        RequestContext requestContext = helper.createContext(); // создаем контекст запроса

        helper.updateRequest(requestContext); // обновили запрос
        return new CommandResult(PAGE, CommandResultType.FORWARD);//переадресация на страницу ошибки
    }
}
