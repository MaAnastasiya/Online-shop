package by.my.onlineShop.dao.impl;

import by.my.onlineShop.dao.AbstractDao;
import by.my.onlineShop.dao.OrderDao;
import by.my.onlineShop.dao.Table;
import by.my.onlineShop.dao.mapper.RowMapperFactory;
import by.my.onlineShop.entity.Order;
import by.my.onlineShop.exeptions.DaoException;

import java.util.List;

public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {
    // запрос на поиск заказов по id пользователя и id заказа пользователя
    private static final String FIND_ORDERS_BY_USER_ID_AND_USER_ORDER_ID_WITHOUT_USER_ORDER_QUERY =
            "SELECT * FROM " + Table.ORDER + " WHERE user_id=? AND product_id=? AND userOrder_id IS NULL";
    // запрос на поиск заказов по id пользователя
    private static final String FIND_ORDERS_BY_USER_ID_WITHOUT_USER_ORDER_QUERY = "SELECT * FROM " + Table.ORDER +
            " WHERE user_id=? AND userOrder_id IS NULL ORDER BY id DESC";
    // запрос на поиск заказов по id заказа пользователя
    private static final String FIND_ORDERS_BY_USER_ORDER_ID_QUERY = "SELECT * FROM " + Table.ORDER + " WHERE userOrder_id=?";
    // запрос на поиск заказов по id заказа пользователя
    private static final String FIND_ORDERS_BY_USER_ID_QUERY = "SELECT * FROM " + Table.ORDER + " WHERE user_id=? ORDER BY id DESC";
    // запрос на обновление заказа по id пользователя
    private static final String UPDATE_USER_ORDER_QUERY = "UPDATE " + Table.ORDER + " SET userOrder_id=? WHERE id=?";
    // запрос к БД на сохранение заказа
    private static final String SAVE_ORDER_QUERY = "INSERT INTO " + Table.ORDER + " (product_id, user_id, number) VALUES (?, ?, ?)";

    public OrderDaoImpl() {
        super(RowMapperFactory.getInstance().getOrderRowMapper(), Table.ORDER);
    }

    // поиск заказов по id пользователя
    @Override
    public List<Order> findByUser(long userId) throws DaoException {
        return executeQuery(FIND_ORDERS_BY_USER_ID_QUERY, userId);
    }

    // поиск заказов по id заказа пользователя
    @Override
    public List<Order> findByUserOrder(long userOrderId) throws DaoException {
        return executeQuery(FIND_ORDERS_BY_USER_ORDER_ID_QUERY, userOrderId);
    }

    // поиск заказов по id пользователя
    @Override
    public List<Order> findByUserWithoutUserOrder(long userId) throws DaoException {
        return executeQuery(FIND_ORDERS_BY_USER_ID_WITHOUT_USER_ORDER_QUERY, userId);
    }

    // найти заказы по id пользователя и id заказа пользователя
    @Override
    public List<Order> findByUserAndProductWithoutUserOrder(long userId, long productId) throws DaoException {
        return executeQuery(FIND_ORDERS_BY_USER_ID_AND_USER_ORDER_ID_WITHOUT_USER_ORDER_QUERY, userId, productId);
    }

    // обновление заказа по id пользователя
    @Override
    public void updateUserOrder(long id, long userOrderId) throws DaoException {
        executeUpdateQuery(UPDATE_USER_ORDER_QUERY, userOrderId, id);
    }

    // сохранение заказа в БД
    @Override
    public long save(Order order) throws DaoException {
        return executeInsertQuery(SAVE_ORDER_QUERY, order.getProductId(), order.getUserId(), order.getNumber());
    }
}
