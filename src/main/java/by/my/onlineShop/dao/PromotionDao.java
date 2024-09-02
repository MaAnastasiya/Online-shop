package by.my.onlineShop.dao;

import by.my.onlineShop.entity.Promotion;
import by.my.onlineShop.exeptions.DaoException;

import java.util.Optional;

public interface PromotionDao extends Dao<Promotion> { // Dao акций

    // получения акции по имени из базы данных
    // на вход - название акции
    // на выходе - Optional акция
    Optional<Promotion> findByName(String name) throws DaoException;
}
