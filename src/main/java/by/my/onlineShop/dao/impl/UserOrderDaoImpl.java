package by.my.onlineShop.dao.impl;

import by.my.onlineShop.dao.AbstractDao;
import by.my.onlineShop.dao.Table;
import by.my.onlineShop.dao.UserOrderDao;
import by.my.onlineShop.dao.mapper.RowMapperFactory;
import by.my.onlineShop.entity.UserOrder;
import by.my.onlineShop.exeptions.DaoException;

import java.util.List;

public class UserOrderDaoImpl extends AbstractDao<UserOrder> implements UserOrderDao {
    // запрос на нахождение пользовательских заказов по статусу
    private static final String FIND_USER_ORDERS_BY_STATUS_QUERY = "SELECT * FROM " + Table.USER_ORDER + " WHERE status=?";
    // запрос на обновление статуса заказа пользователя по id
    private static final String UPDATE_USER_ORDER_STATUS_BY_ID_QUERY = "UPDATE " + Table.USER_ORDER + " SET status=? WHERE id=?";
    // сохранить заказ пользователя
    private static final String SAVE_USER_ORDER_QUERY = "INSERT INTO " + Table.USER_ORDER + " (address, order_date, delivery_date, status) VALUES (?, ?, ?, ?)";

    public UserOrderDaoImpl() {
        super(RowMapperFactory.getInstance().getUserOrderRowMapper(), Table.USER_ORDER);
    }

    // нахождение списка пользовательских заказов по статусу
    @Override
    public List<UserOrder> findByStatus(String status) throws DaoException {
        return executeQuery(FIND_USER_ORDERS_BY_STATUS_QUERY, status);
    }

    // обновление статуса заказа пользователя по id
    // на вход - id заказа и новый статус заказа
    @Override
    public void updateStatusById(long id, String status) throws DaoException {
        executeUpdateQuery(UPDATE_USER_ORDER_STATUS_BY_ID_QUERY, status, id);
    }

    // сохранение нового заказа в БД
    @Override
    public long save(UserOrder userOrder) throws DaoException {
        return executeInsertQuery(SAVE_USER_ORDER_QUERY, userOrder.getAddress(), userOrder.getOrderDate(),
                userOrder.getDeliveryDate(), userOrder.getStatus());
    }
}
