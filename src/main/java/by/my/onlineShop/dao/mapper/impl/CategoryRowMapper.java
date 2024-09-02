package by.my.onlineShop.dao.mapper.impl;

import by.my.onlineShop.dao.mapper.Column;
import by.my.onlineShop.dao.mapper.RowMapper;
import by.my.onlineShop.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
// Создаем сущность категории
// Отображение строк категорий
public class CategoryRowMapper implements RowMapper<Category> {

    @Override
    public Category map(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getLong(Column.ID));
        category.setCategoryName(resultSet.getString(Column.CATEGORY_NAME));
        return category;
    }
}
