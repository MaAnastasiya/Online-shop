package by.my.onlineShop.dao.impl;

import by.my.onlineShop.dao.AbstractDao;
import by.my.onlineShop.dao.Table;
import by.my.onlineShop.dao.UserDao;
import by.my.onlineShop.dao.mapper.RowMapperFactory;
import by.my.onlineShop.entity.User;
import by.my.onlineShop.exeptions.DaoException;

import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    // запрос на нахождение пользователя по электронной почте и паролю
    private static final String FIND_USER_BY_EMAIL_AND_PASSWORD_QUERY = "SELECT * FROM " + Table.USER + " WHERE email=? and password=SHA1(?)";
    // запрос на нахождение пользователя по электронной почте
    private static final String FIND_USER_BY_EMAIL_QUERY = "SELECT * FROM " + Table.USER + " WHERE email=?";
    // запрос на сохранение пользователя в БД
    private static final String SAVE_USER_QUERY = "INSERT INTO " + Table.USER + " (email, password, role_id, userInformation_id) VALUES (?, ?, ?, ?)";
    // запрос на удаление пользователя в БД
    private static final String DELETE_USER_QUERY = "DELETE FROM " + Table.USER + " WHERE id=?";

    public UserDaoImpl() {
        super(RowMapperFactory.getInstance().getUserRowMapper(), Table.USER);
    }

    // нахождение пользователя по электронной почте и паролю
    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) throws DaoException {
        return executeQueryForSingleResult(FIND_USER_BY_EMAIL_AND_PASSWORD_QUERY, email, password);
    }

    // сохранение сущности ПОЛЬЗОВАТЕЛЬ в БД
    @Override
    public long save(User user) throws DaoException {
        return executeInsertQuery(SAVE_USER_QUERY, user.getEmail(), user.getPassword(),
                user.getRoleId(), user.getUserInformationId());
    }

    // Нахождение пользователя по email в БД
    @Override
    public Optional<User> findByEmail(String email) throws DaoException {
        return executeQueryForSingleResult(FIND_USER_BY_EMAIL_QUERY, email);
    }

    // удаление пользователя по id из БД
    @Override
    public void removeById(long id) throws DaoException {
        executeUpdateQuery(DELETE_USER_QUERY, id);
    }
}
