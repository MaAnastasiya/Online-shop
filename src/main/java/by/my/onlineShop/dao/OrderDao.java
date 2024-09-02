package by.my.onlineShop.dao;

import by.my.onlineShop.entity.Order;
import by.my.onlineShop.exeptions.DaoException;

import java.util.List;

public interface OrderDao extends Dao<Order> { // Dao заказов

    // получение заказов по id пользовательского заказа из базы данных
    // на вход - идентификатор заказа пользователя
    // на выходе - список заказов
    List<Order> findByUserOrder(long userOrderId) throws DaoException;

    // получения заказов по идентификатору пользователя из базы данных
    // на вход - идентификатор пользователя
    // на выход - список заказов
    List<Order> findByUser(long userId) throws DaoException;


    //получение заказов по идентификатору пользователя, где пользовательский заказ равен нулю из базы данных
    // на вход - идентификатор пользователя
    // на выходе - список заказов
    List<Order> findByUserWithoutUserOrder(long userId) throws DaoException;


    // получение заказов по идентификатору пользователя и идентификатору продукта,
    // где пользовательский заказ равен нулю из базы данных
    // на входе - id пользователя, id продукта
    // на выход - список заказов
    List<Order> findByUserAndProductWithoutUserOrder(long userId, long productId) throws DaoException;

    // Способ обновления порядка пользователей в порядке по идентификатору и идентификатору пользователя в базе данных
    //Идентификатор объекта для обновления ,id заказа пользователя, который будет обновлен
    void updateUserOrder(long id, long userOrderId) throws DaoException;
}
