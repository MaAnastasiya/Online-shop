package by.my.onlineShop.dao.mapper;

public final class Column { // столбцы в таблицах
    public static final String ID = "id";

    // для таблицы ролей (roles)
    public static final String ROLE_ID = "role_id";
    public static final String ROLE_NAME = "role";

    // для таблицы категорий (categories)
    public static final String CATEGORY_ID = "category_id";
    public static final String CATEGORY_NAME = "category";

    // для таблицы пользователей (user)
    public static final String USER_ID = "user_id";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";

    // для таблицы информации от пользователей (userinformation)
    public static final String USER_INFORMATION_ID = "user_information_id";
    public static final String USER_INFORMATION_NAME = "name";
    public static final String USER_INFORMATION_SURNAME = "surname";
    public static final String USER_INFORMATION_PATRONYMIC = "patronymic";
    public static final String USER_INFORMATION_PHONE = "phone";

    // для таблицы пользовательских заказов (userorders)
    public static final String USER_ORDER_ID = "userOrder_id";
    public static final String USER_ORDER_ADDRESS = "address";
    public static final String USER_ORDER_DATE = "order_date";
    public static final String USER_ORDER_DELIVERY_DATE = "delivery_date";
    public static final String USER_ORDER_STATUS = "status";

    // для таблицы акций (promotion)
    public static final String PRODUCT_ID = "product_id";
    public static final String PRODUCT_NAME = "name";
    public static final String PRODUCT_DESCRIPTION = "description";
    public static final String PRODUCT_PRICE = "price";
    public static final String PRODUCT_STATUS = "status";
    public static final String PRODUCT_PHOTO = "photo";

    public static final String ORDER_NUMBER = "number";
    public static final String ORDERS_NUMBER = "orders_number";

    // для таблицы продуктов (product)
    public static final String PROMOTION_ID = "promotion_id";
    public static final String PROMOTION_NAME = "name";
    public static final String PROMOTION_DESCRIPTION = "description";
    public static final String PROMOTION_DISCOUNT = "discount";
    public static final String PROMOTION_BEGINNING_DATE = "beginning_date";
    public static final String PROMOTION_EXPIRATION_DATE = "expiration_date";
    public static final String PROMOTION_PHOTO = "photo";

    // для таблицы банковских карт (bankcards)
    public static final String BANK_CARD_NUMBER = "card_number";
    public static final String BANK_CARD_OWNER = "card_owner";
    public static final String BANK_CARD_EXPIRATION_MONTH = "expiration_month";
    public static final String BANK_CARD_EXPIRATION_YEAR = "expiration_year";
    public static final String BANK_CARD_BALANCE = "balance";
    public static final String BANK_CARD_CVV = "cvv";
}
