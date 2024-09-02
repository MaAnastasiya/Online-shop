package by.my.onlineShop.dao;

import by.my.onlineShop.entity.Product;
import by.my.onlineShop.exeptions.DaoException;

import java.util.List;
import java.util.Optional;

public interface ProductDao extends Dao<Product> { // Dao продукта

    // Способ поиска товара по категориям в базе данных
    // на вход - идентификатор категории
    // на выходе - список товаров по id
    List<Product> findByCategory(long categoryId) throws DaoException;

    // Способ получения продукта по названию из базы данных
    // На вход - название продукта
    // На выходе - Optional товар
    Optional<Product> findByName(String name) throws DaoException;

    // обновление продукта по идентификатору в базе данных
    // На вход - id продукта для обновления, информация о новом продукте
    void updateById(long id, Product product) throws DaoException;

    // Способ обновления акции по id продукта в базе данных
    // На вход - id продукта для обновления, id акции
    void updatePromotionById(long id, long promotionId) throws DaoException;

    // удаления акции по id продукта в базе данных
    // На вход - id продукта для обновления
    void removePromotionById(long id) throws DaoException;
}
