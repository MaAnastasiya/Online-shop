package by.my.onlineShop.dao.impl;

import by.my.onlineShop.dao.AbstractDao;
import by.my.onlineShop.dao.PromotionDao;
import by.my.onlineShop.dao.Table;
import by.my.onlineShop.dao.mapper.RowMapperFactory;
import by.my.onlineShop.entity.Promotion;
import by.my.onlineShop.exeptions.DaoException;

import java.util.Optional;

public class PromotionDaoImpl extends AbstractDao<Promotion> implements PromotionDao {
    // запрос на сохранение промоакции
    private static final String SAVE_PROMOTION_QUERY = "INSERT INTO " + Table.PROMOTION +
            " (name, description, discount, beginning_date, expiration_date, photo) VALUES (?, ?, ?, ?, ?, ?)";
    // запрос на поиск промоакции по ее имени
    private static final String FIND_PROMOTION_BY_NAME_QUERY = "SELECT * FROM " + Table.PROMOTION + " WHERE name=?";

    public PromotionDaoImpl() {
        super(RowMapperFactory.getInstance().getPromotionRowMapper(), Table.PROMOTION);
    }

    // сохраняем новую сущность АКЦИЯ
    @Override
    public long save(Promotion promotion) throws DaoException {
        return executeInsertQuery(SAVE_PROMOTION_QUERY, promotion.getName(), promotion.getDescription(),
                promotion.getDiscount(), promotion.getBeginningDate(), promotion.getExpirationDate(), promotion.getPhoto());
    }

    // поиск акции по ее имени
    @Override
    public Optional<Promotion> findByName(String name) throws DaoException {
        return executeQueryForSingleResult(FIND_PROMOTION_BY_NAME_QUERY, name);
    }
}
