package by.my.onlineShop.service;

import by.my.onlineShop.entity.Order;
import by.my.onlineShop.entity.User;
import by.my.onlineShop.exeptions.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService { // Обслуживание пользователей

    // Аутентификации пользователя
    // На вход - почта, пароль
    // На выходе - необязательно пользователя
    Optional<User> login(String email, String password) throws ServiceException;


    // Регистрация нового пользователя
    // На вход - имя, фамилия, отчество, почта, номер телефона, пароль
    // На выходе - результат регистрации
    boolean register(String name, String surname, String patronymic, String email, String phoneString, String password) throws ServiceException;


    // получение пользователя по id
    // На вход - идентификатор пользователя для извлечения
    // На выходе - необязательно пользователя
    Optional<User> retrieveUserById(long userId) throws ServiceException;


    // получения пользователей из заказов
    // На вход - Список заказов
    // На выходе - список пользователей
    List<User> getUsersFromOrders(List<Order> orders) throws ServiceException;
}
