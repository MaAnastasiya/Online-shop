package by.my.onlineShop.dao;

import by.my.onlineShop.entity.UserOrder;
import by.my.onlineShop.exeptions.DaoException;

import java.util.List;

public interface UserOrderDao extends Dao<UserOrder> { // Dao заказов пользователя

    // получение заказов пользователей по статусу из базы данных
    // На вход - статус заказа пользователя
    // На выходе - список пользовательских заказов
    List<UserOrder> findByStatus(String status) throws DaoException;

    // обновление статуса в порядке пользователя по идентификатору в базе данных
    // Идентификатор пользователя для обновления, новый статус заказа пользователя
    void updateStatusById(long id, String status) throws DaoException;

}
