package by.my.onlineShop.service;

import by.my.onlineShop.service.impl.CategoryServiceImpl;
import by.my.onlineShop.service.impl.OrderServiceImpl;
import by.my.onlineShop.service.impl.PromotionServiceImpl;
import by.my.onlineShop.service.impl.UserServiceImpl;
import by.my.onlineShop.service.impl.ProductServiceImpl;
import by.my.onlineShop.service.impl.RoleServiceImpl;
import by.my.onlineShop.service.impl.UserInformationServiceImpl;
import by.my.onlineShop.service.impl.UserOrderServiceImpl;
import by.my.onlineShop.service.impl.BankCardServiceImpl;

public class ServiceFactory { // общий сервисный центр
    private final UserService userService = new UserServiceImpl(); // Обслуживание пользователей
    private final PromotionService promotionService = new PromotionServiceImpl(); // Обслуживание акций
    private final CategoryService categoryService = new CategoryServiceImpl();// // Обслуживание категорий
    private final OrderService orderService = new OrderServiceImpl();// // Обслуживание заказов
    private final ProductService productService = new ProductServiceImpl(); // Обслуживание продаваемых продуктов
    private final RoleService roleService = new RoleServiceImpl();// Обслуживание определения ролей
    private final UserInformationService userInformationService = new UserInformationServiceImpl(); //Служба информации о пользователях
    private final UserOrderService userOrderService = new UserOrderServiceImpl();// обслуживание заказов пользователей
    private final BankCardService bankCardService = new BankCardServiceImpl();// сервис банковских карт

    public ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return Holder.INSTANCE;
    }

    public UserService getUserService() {
        System.out.println(userService);
        return userService;
    }

    public PromotionService getPromotionService() {
        return promotionService;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public BankCardService getBankCardService() {
        return bankCardService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public UserInformationService getUserInformationService() {
        return userInformationService;
    }

    public UserOrderService getUserOrderService() {
        return userOrderService;
    }

    private static class Holder {
        static final ServiceFactory INSTANCE = new ServiceFactory();
    }
}
