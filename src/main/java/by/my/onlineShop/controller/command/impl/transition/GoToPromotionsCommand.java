package by.my.onlineShop.controller.command.impl.transition;

import by.my.onlineShop.controller.command.Command;
import by.my.onlineShop.controller.command.CommandResult;
import by.my.onlineShop.controller.command.CommandResultType;
import by.my.onlineShop.controller.context.RequestContext;
import by.my.onlineShop.controller.context.RequestContextHelper;
import by.my.onlineShop.entity.Category;
import by.my.onlineShop.entity.Promotion;
import by.my.onlineShop.exeptions.ServiceException;
import by.my.onlineShop.service.CategoryService;
import by.my.onlineShop.service.PromotionService;
import by.my.onlineShop.service.ServiceFactory;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GoToPromotionsCommand implements Command { // страница акций
    private static final String PAGE = "WEB-INF/view/promotions.jsp";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String PROMOTIONS = "promotions";
    private static final String CATEGORIES = "categories";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();

        try {
            // берем список акций
            PromotionService promotionService = ServiceFactory.getInstance().getPromotionService();
            List<Promotion> promotions = promotionService.retrievePromotions();
            requestContext.addRequestAttribute(PROMOTIONS, promotions);

            // берем список категорий
            CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();
            List<Category> categories = categoryService.retrieveCategories();
            requestContext.addRequestAttribute(CATEGORIES, categories);
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.FORWARD);
    }
}
