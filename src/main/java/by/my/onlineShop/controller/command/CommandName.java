package by.my.onlineShop.controller.command;

public final class CommandName { // допустимые команды
    private static final String DEFAULT_COMMAND = "default";
    private static final String MAIN_COMMAND = "main";
    private static final String PROFILE_COMMAND = "profile";
    private static final String CONTACTS_COMMAND = "contacts";
    private static final String PROMOTIONS_COMMAND = "promotions";
    private static final String BASKET_COMMAND = "basket";
    private static final String MY_ORDERS_COMMAND = "myOrders";
    private static final String REGISTRATION_COMMAND = "registration";
    private static final String LOG_IN_COMMAND = "logIn";
    private static final String CATALOG_COMMAND = "catalog";
    private static final String CHECK_LOGIN_COMMAND = "checkLogin";
    private static final String LOG_OUT_COMMAND = "logOut";
    private static final String LOG_UP_COMMAND = "logUp";
    private static final String ADD_PRODUCT_COMMAND = "addProduct";
    private static final String ADD_PROMOTION_COMMAND = "addPromotion";
    private static final String VIEW_ORDERS_COMMAND = "viewOrders";
    private static final String ADD_ORDER_COMMAND = "addOrder";
    private static final String DELETE_ORDER_COMMAND = "deleteOrder";
    private static final String COMPLETE_USER_ORDER_COMMAND = "completeUserOrder";
    private static final String ADD_TO_BASKET_COMMAND = "addToBasket";
    private static final String CONFIRM_ORDER_COMMAND = "confirmOrder";
    private static final String CONFIRM_ADDING_PRODUCT_COMMAND = "confirmAddingProduct";
    private static final String DELETE_USER_ORDER_COMMAND = "deleteUserOrder";
    private static final String EDIT_PRODUCT_COMMAND = "editProduct";
    private static final String CONFIRM_EDIT_PRODUCT = "confirmEditProduct";
    private static final String CONFIRM_ADDING_PROMOTION_COMMAND = "confirmAddingPromotion";

    public String getDefaultCommand(){
        return DEFAULT_COMMAND;
    }

    public String getConfirmAddingPromotionCommand(){
        return CONFIRM_ADDING_PROMOTION_COMMAND;
    }

    public String getConfirmEditProduct(){
        return CONFIRM_EDIT_PRODUCT;
    }


    public String getEditProductCommand(){
        return EDIT_PRODUCT_COMMAND;
    }

    public String getDeleteUserOrderCommand(){
        return DELETE_USER_ORDER_COMMAND;
    }

    public String getConfirmAddingProductCommand(){
        return CONFIRM_ADDING_PRODUCT_COMMAND;
    }

    public String getConfirmOrderCommand(){
        return CONFIRM_ORDER_COMMAND;
    }

    public String getAddToBasketCommand(){
        return ADD_TO_BASKET_COMMAND;
    }

    public String getCompleteUserOrderCommand(){
        return COMPLETE_USER_ORDER_COMMAND;
    }

    public String getDeleteOrderCommand(){
        return DELETE_ORDER_COMMAND;
    }

    public String getAddOrderCommand(){
        return ADD_ORDER_COMMAND;
    }

    public String getViewOrdersCommand(){
        return VIEW_ORDERS_COMMAND;
    }

    public String getAddProductCommand(){
        return ADD_PRODUCT_COMMAND;
    }

    public String getLogUpCommand(){
        return LOG_UP_COMMAND;
    }

    public String getAddPromotionCommand(){
        return ADD_PROMOTION_COMMAND;
    }

    public String getLogOutCommand(){
        return LOG_OUT_COMMAND;
    }

    public String getMainCommandCommand(){
        return MAIN_COMMAND;
    }

    public String getProfileCommand(){
        return PROFILE_COMMAND;
    }

    public String getContactsCommand(){
        return CONTACTS_COMMAND;
    }

    public String getPromotionsCommand(){
        return PROMOTIONS_COMMAND;
    }

    public String getBasketCommand(){
        return BASKET_COMMAND;
    }

    public String getMyOrdersCommand(){
        return MY_ORDERS_COMMAND;
    }

    public String getRegistrationCommand(){
        return REGISTRATION_COMMAND;
    }

    public String getLogInCommand(){
        return LOG_IN_COMMAND;
    }

    public String getCatalogCommand(){
        return CATALOG_COMMAND;
    }

    public String getCheckLoginCommand(){
        return CHECK_LOGIN_COMMAND;
    }
}
