package by.my.onlineShop.dao.mapper.impl;

import by.my.onlineShop.dao.mapper.Column;
import by.my.onlineShop.dao.mapper.RowMapper;
import by.my.onlineShop.entity.UserInformation;

import java.sql.ResultSet;
import java.sql.SQLException;

// создаем сущность информации пользователя
// Отображение строк информации пользователя
public class UserInformationRowMapper implements RowMapper<UserInformation> {
    @Override
    public UserInformation map(ResultSet resultSet) throws SQLException {
        UserInformation userInformation = new UserInformation();
        userInformation.setId(resultSet.getLong(Column.ID));
        userInformation.setName(resultSet.getString(Column.USER_INFORMATION_NAME));
        userInformation.setSurname(resultSet.getString(Column.USER_INFORMATION_SURNAME));
        userInformation.setPatronymic(resultSet.getString(Column.USER_INFORMATION_PATRONYMIC));
        userInformation.setPhone(resultSet.getLong(Column.USER_INFORMATION_PHONE));
        return userInformation;
    }
}
