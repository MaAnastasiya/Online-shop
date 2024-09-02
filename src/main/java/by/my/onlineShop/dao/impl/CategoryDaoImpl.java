package by.my.onlineShop.dao.impl;

import by.my.onlineShop.dao.AbstractDao;
import by.my.onlineShop.dao.CategoryDao;
import by.my.onlineShop.dao.Table;
import by.my.onlineShop.dao.mapper.RowMapperFactory;
import by.my.onlineShop.entity.Category;
import by.my.onlineShop.exeptions.DaoException;

import java.util.Optional;

public class CategoryDaoImpl extends AbstractDao<Category> implements CategoryDao {
    // строка запроса к БД на сохранение новой категории
    private static final String SAVE_CATEGORY_QUERY = "INSERT INTO " + Table.CATEGORY + " (category) VALUES (?)";
    // строка запроса к БД на нахождение категории по ее имени из БД
    private static final String FIND_CATEGORY_BY_NAME_QUERY = "SELECT * FROM " + Table.CATEGORY + " WHERE category=?";

    public CategoryDaoImpl() {
        super(RowMapperFactory.getInstance().getCategoryRowMapper(), Table.CATEGORY);
    }

    // сохранение новой сущности-категории в БД
    @Override
    public long save(Category category) throws DaoException {
        return executeInsertQuery(SAVE_CATEGORY_QUERY, category.getCategoryName());
    }

    // поиск определенной сущности-категории из БД по имени
    @Override
    public Optional<Category> findByName(String name) throws DaoException {
        return executeQueryForSingleResult(FIND_CATEGORY_BY_NAME_QUERY, name);
    }
}
