package by.my.onlineShop.dao;

import by.my.onlineShop.entity.Category;
import by.my.onlineShop.exeptions.DaoException;

import java.util.Optional;

public interface CategoryDao extends Dao<Category> { // dao категорий

    // Получение категории по имени из базы данных
    // На вход - название категории
    // На выходе - Optional категория
    Optional<Category> findByName(String name) throws DaoException;
}
