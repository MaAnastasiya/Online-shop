package by.my.onlineShop.dao.mapper.impl;

import by.my.onlineShop.dao.mapper.Column;
import by.my.onlineShop.dao.mapper.RowMapper;
import by.my.onlineShop.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

// Создаем сущность продукта
// Отображение строк продукта
public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product map(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getLong(Column.ID));
        product.setCategoryId(resultSet.getLong(Column.CATEGORY_ID));
        product.setPromotionId(resultSet.getLong(Column.PROMOTION_ID));
        product.setName(resultSet.getString(Column.PRODUCT_NAME));
        product.setDescription(resultSet.getString(Column.PRODUCT_DESCRIPTION));
        product.setPrice(resultSet.getDouble(Column.PRODUCT_PRICE));
        product.setStatus(resultSet.getBoolean(Column.PRODUCT_STATUS));
        product.setPhoto(resultSet.getString(Column.PRODUCT_PHOTO));
        product.setOrdersNumber(resultSet.getLong(Column.ORDERS_NUMBER));
        return product;
    }
}
