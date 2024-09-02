package by.my.onlineShop.controller.command.impl.transition;

import by.my.onlineShop.controller.command.Command;
import by.my.onlineShop.controller.command.CommandResult;
import by.my.onlineShop.controller.command.CommandResultType;
import by.my.onlineShop.controller.context.RequestContext;
import by.my.onlineShop.controller.context.RequestContextHelper;
import by.my.onlineShop.entity.Category;
import by.my.onlineShop.exeptions.ServiceException;
import by.my.onlineShop.service.CategoryService;
import by.my.onlineShop.service.ServiceFactory;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GoToMainCommand implements Command { // переход на главную страницу
    private static final String PAGE = "WEB-INF/view/main.jsp";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String CATEGORIES = "categories";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();// создаем контекст запроса

        try {
            // формируем список доступных категорий
            CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();
            List<Category> categories = categoryService.retrieveCategories();
            // добавляем категории в контекст запроса
            requestContext.addRequestAttribute(CATEGORIES, categories);
        } catch (ServiceException e) { // ошибка - уходим на страницу ошибки
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        helper.updateRequest(requestContext); // обновление запроса
        return new CommandResult(PAGE, CommandResultType.FORWARD);
    }
}
