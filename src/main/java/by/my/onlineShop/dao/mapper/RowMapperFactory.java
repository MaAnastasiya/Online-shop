package by.my.onlineShop.dao.mapper;

import by.my.onlineShop.dao.mapper.impl.CategoryRowMapper;
import by.my.onlineShop.dao.mapper.impl.OrderRowMapper;
import by.my.onlineShop.dao.mapper.impl.ProductRowMapper;
import by.my.onlineShop.dao.mapper.impl.PromotionRowMapper;
import by.my.onlineShop.dao.mapper.impl.RoleRowMapper;
import by.my.onlineShop.dao.mapper.impl.UserInformationRowMapper;
import by.my.onlineShop.dao.mapper.impl.UserOrderRowMapper;
import by.my.onlineShop.dao.mapper.impl.UserRowMapper;
import by.my.onlineShop.dao.mapper.impl.BankCardRowMapper;
import by.my.onlineShop.entity.Category;
import by.my.onlineShop.entity.Order;
import by.my.onlineShop.entity.Product;
import by.my.onlineShop.entity.Promotion;
import by.my.onlineShop.entity.Role;
import by.my.onlineShop.entity.User;
import by.my.onlineShop.entity.UserInformation;
import by.my.onlineShop.entity.UserOrder;
import by.my.onlineShop.entity.BankCard;

public class RowMapperFactory {
    // строки разных таблиц преобразованы в сущности
    private final RowMapper<Category> categoryRowMapper = new CategoryRowMapper();
    private final RowMapper<Order> orderRowMapper = new OrderRowMapper();
    private final RowMapper<Product> productRowMapper = new ProductRowMapper();
    private final RowMapper<Promotion> promotionRowMapper = new PromotionRowMapper();
    private final RowMapper<Role> roleRowMapper = new RoleRowMapper();
    private final RowMapper<UserInformation> userInformationRowMapper = new UserInformationRowMapper();
    private final RowMapper<UserOrder> userOrderRowMapper = new UserOrderRowMapper();
    private final RowMapper<User> userRowMapper = new UserRowMapper();
    private final RowMapper<BankCard> bankCardRowMapper = new BankCardRowMapper();

    public static RowMapperFactory getInstance() {
        return Holder.INSTANCE;
    }

    public RowMapper<Category> getCategoryRowMapper() {
        return categoryRowMapper;
    }

    public RowMapper<Order> getOrderRowMapper() {
        return orderRowMapper;
    }

    public RowMapper<Product> getProductRowMapper() {
        return productRowMapper;
    }

    public RowMapper<Promotion> getPromotionRowMapper() {
        return promotionRowMapper;
    }

    public RowMapper<Role> getRoleRowMapper() {
        return roleRowMapper;
    }

    public RowMapper<UserInformation> getUserInformationRowMapper() {
        return userInformationRowMapper;
    }

    public RowMapper<UserOrder> getUserOrderRowMapper() {
        return userOrderRowMapper;
    }

    public RowMapper<User> getUserRowMapper() {
        return userRowMapper;
    }

    public RowMapper<BankCard> getBankCardRowMapper() {
        return bankCardRowMapper;
    }

    private static class Holder {
        private static final RowMapperFactory INSTANCE = new RowMapperFactory();
    }
}
