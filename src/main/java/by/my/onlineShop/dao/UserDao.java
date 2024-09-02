package by.my.onlineShop.dao;

import by.my.onlineShop.entity.User;
import by.my.onlineShop.exeptions.DaoException;

import java.util.Optional;

public interface UserDao extends Dao<User> { // Dao пользователей

    // получить пользователя по электронной почте и паролю из базы данных
    //  На вход - электронная почта пользователя и пароль
    // На выходе - Optional пользователь
    Optional<User> findByEmailAndPassword(String email, String password) throws DaoException;

    // получить пользователя по электронной почте из базы данных
    // На вход - электронная почта пользователя
    // На выход - Optional пользователь
    Optional<User> findByEmail(String email) throws DaoException;
}
