package by.my.onlineShop.service;

import by.my.onlineShop.entity.Order;
import by.my.onlineShop.entity.UserOrder;
import by.my.onlineShop.exeptions.ServiceException;

import java.util.List;

public interface OrderService { // заказ услуг

    // Метод получения заказов по id пользователя, ! где пользовательский заказ равен нулю !
    // На вход - Идентификатор пользователя
    // Возвращает список заказов по id
   List<Order> retrieveOrdersByUserWithoutUserOrder(long userId) throws ServiceException;


    // Способ получения заказов по идентификатору пользователя
    // На вход - Идентификатор пользователя
    // Возвращает список заказов по id
    List<Order> retrieveOrdersByUser(long userId) throws ServiceException;


    // Метод получения заказов по идентификатору пользователя, где заказ пользователя равен нулю, а статус равен true
    // На вход - Идентификатор пользователя
    // Возвращает список заказов по id
    List<Order> retrieveOrdersByUserWhereProductStatusTrue(long userId) throws ServiceException;


    // Способ получения заказов по заказу пользователя
    // На вход - Идентификатор пользователя
    // Возвращает список заказов по id
    List<Order> retrieveOrdersByUserOrder(long userOrderId) throws ServiceException;


    // Способ удаления заказа по идентификатору
    // Возвращает true, если заказ был успешно удален, false - исключение
    boolean removeOrderById(long orderId) throws ServiceException;

    // Добавления нового заказа
    // На вход - id пользователя, id продукта, количество продуктов
    // Возвращает true, если заказ был успешно добавлен, false - исключение
    boolean addNewOrder(long userId, long productId, int number) throws ServiceException;

    // Расчета общей стоимости заказов (корзины)
    // На вход - список заказов
    // Возвращает стоимость корзины
    double calculateTotalCost(List<Order> orders) throws ServiceException;

    // Способ получения заказов от заказов пользователей
    // На вход - Список пользовательских заказов
    // Возвращает список заказов
    List<Order> getOrdersFromUserOrders(List<UserOrder> userOrders) throws ServiceException;
}
