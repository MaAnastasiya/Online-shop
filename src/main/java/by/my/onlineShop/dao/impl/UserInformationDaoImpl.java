package by.my.onlineShop.dao.impl;

import by.my.onlineShop.dao.AbstractDao;
import by.my.onlineShop.dao.Table;
import by.my.onlineShop.dao.mapper.RowMapperFactory;
import by.my.onlineShop.entity.UserInformation;
import by.my.onlineShop.exeptions.DaoException;

public class UserInformationDaoImpl extends AbstractDao<UserInformation> {
    // запрос на сохранение информации о пользователе
    private static final String SAVE_USER_INFORMATION_QUERY = "INSERT INTO " + Table.USER_INFORMATION +
            " (name, surname, patronymic, phone) VALUES (?, ?, ?, ?)";

    public UserInformationDaoImpl() {
        super(RowMapperFactory.getInstance().getUserInformationRowMapper(), Table.USER_INFORMATION);
    }

    // сохранение новой сущности ИНФОРМАЦИИ О ПОЛЬЗОВАТЕЛЕ в БД
    @Override
    public long save(UserInformation userInformation) throws DaoException {
        return executeInsertQuery(SAVE_USER_INFORMATION_QUERY, userInformation.getName(),
                userInformation.getSurname(), userInformation.getPatronymic(), userInformation.getPhone());
    }
}
