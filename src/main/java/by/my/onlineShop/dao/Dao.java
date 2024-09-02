package by.my.onlineShop.dao;

import by.my.onlineShop.exeptions.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    // Получение всех сущностей из таблицы
    // На выходе - список всех объектов в таблице
    List<T> findAll() throws DaoException;

    // получения объекта сущности из таблицы по id
    // На вход - Идентификатор объекта, который нужно найти
    // На выходе - объект сущности из таблицы (optional)
    Optional<T> findById(long id) throws DaoException;

    // сохранение сущности в таблицу
    // На вход объект сущности элемента для сохранения
    long save(T item) throws DaoException;

    // удаление сущности из таблицы по идентификатору
    // на вход - Идентификатор объекта для удаления
    void removeById(long id) throws DaoException;
}
