package by.my.onlineShop.dao.mapper.impl;

import by.my.onlineShop.dao.mapper.Column;
import by.my.onlineShop.dao.mapper.RowMapper;
import by.my.onlineShop.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

// создаем сущность роли
// Отображение строк ролей
public class RoleRowMapper implements RowMapper<Role> {

    @Override
    public Role map(ResultSet resultSet) throws SQLException {
        Role role = new Role();
        role.setId(resultSet.getLong(Column.ID));
        role.setName(resultSet.getString(Column.ROLE_NAME));
        return role;
    }
}