package by.my.onlineShop.dao.impl;

import by.my.onlineShop.dao.AbstractDao;
import by.my.onlineShop.dao.ProductDao;
import by.my.onlineShop.dao.Table;
import by.my.onlineShop.dao.mapper.RowMapperFactory;
import by.my.onlineShop.entity.Product;
import by.my.onlineShop.exeptions.DaoException;

import java.util.List;
import java.util.Optional;

public class ProductDaoImpl extends AbstractDao<Product> implements ProductDao {
    // ПОИСК ТОВАРОВ ПО ИДЕНТИФИКАТОРУ КАТЕГОРИИ
    private static final String FIND_PRODUCTS_BY_CATEGORY_ID_QUERY = "SELECT * FROM " + Table.PRODUCT + " WHERE category_id=?";
    // ПОИСК ТОВАРА ПО НАЗВАНИЮ
    private static final String FIND_PRODUCT_BY_NAME_QUERY = "SELECT * FROM " + Table.PRODUCT + " WHERE name=?";
    // СОХРАНИТЬ ПРОДУКТ
    private static final String SAVE_PRODUCT_QUERY = "INSERT INTO " + Table.PRODUCT +
            " (category_id, name, description, price, status, photo, orders_number) VALUES (?, ?, ?, ?, ?, ?, ?)";
    // ОБНОВЛЕНИЕ ПРОДУКТА
    private static final String UPDATE_PRODUCT_QUERY = "UPDATE " + Table.PRODUCT + " SET category_id=?, " +
            "name=?, description=?, price=?, status=?, photo=? WHERE id=?";
    // ОБНОВЛЕНИЕ АКЦИИ, КОТОРАЯ ДЕЙСТВУЕТ НА ПРОДУКТ
    private static final String UPDATE_PROMOTION_ID_QUERY = "UPDATE " + Table.PRODUCT + " SET promotion_id=? WHERE id=?";
    // УДАЛЕНИЕ АКЦИИ, КОТОРАЯ ДЕЙСТВУЕТ НА ПРОДУКТ
    private static final String DELETE_PROMOTION_ID_QUERY = "UPDATE " + Table.PRODUCT + " SET promotion_id=NULL WHERE id=?";

    public ProductDaoImpl() {
        super(RowMapperFactory.getInstance().getProductRowMapper(), Table.PRODUCT);
    }

    // найти товары, которые относятся к определенной категории по id
    @Override
    public List<Product> findByCategory(long categoryId) throws DaoException {
        return executeQuery(FIND_PRODUCTS_BY_CATEGORY_ID_QUERY, categoryId);
    }

    // найти определенный товар по его имени
    @Override
    public Optional<Product> findByName(String name) throws DaoException {
        return executeQueryForSingleResult(FIND_PRODUCT_BY_NAME_QUERY, name);
    }

    // обновление параметров продукта
    // на вход id продукта на обновление и новые параметры продукта
    @Override
    public void updateById(long id, Product product) throws DaoException {
        executeUpdateQuery(UPDATE_PRODUCT_QUERY, product.getCategoryId(),
                product.getName(), product.getDescription(), product.getPrice(), product.isStatus(),
                product.getPhoto(), id);
    }

    // обновление акции, которая действует на продукт
    // на вход - id товара, новая акция
    @Override
    public void updatePromotionById(long id, long promotionId) throws DaoException {
        executeUpdateQuery(UPDATE_PROMOTION_ID_QUERY, promotionId, id);
    }

    // удаление акции, которая действует на этот продукт
    // на вход id продукта
    @Override
    public void removePromotionById(long id) throws DaoException {
        executeUpdateQuery(DELETE_PROMOTION_ID_QUERY, id);
    }

    // сохранение новой сущности ПРОДУКТ в БД
    @Override
    public long save(Product product) throws DaoException {
        return executeInsertQuery(SAVE_PRODUCT_QUERY, product.getCategoryId(), product.getName(), product.getDescription(),
                product.getPrice(), product.isStatus(), product.getPhoto(), product.getOrdersNumber());
    }
}
