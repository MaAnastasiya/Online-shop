package by.my.onlineShop.dao.impl;

import by.my.onlineShop.dao.CategoryDao;
import by.my.onlineShop.dao.connection.ConnectionPool;
import by.my.onlineShop.entity.Category;
import by.my.onlineShop.exeptions.ConnectionException;
import by.my.onlineShop.exeptions.DaoException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CategoryDaoTest {
    CategoryDao categoryDao = new CategoryDaoImpl();

    // инициализировать пул соединения
    @BeforeAll
    static void initializeConnectionPool() throws ConnectionException {
        ConnectionPool.getInstance().initialize();
    }

    // нахождение категории по имени, когда данные корректны
    @Test
    public void testFindByName_ShouldReturnCategory_WhenDataIsCorrect() throws DaoException {
        Optional<Category> actual = categoryDao.findByName("Ноутбуки");
        Category expected = new Category();
        expected.setId(1);
        expected.setCategoryName("Ноутбуки");
        assertEquals(expected, actual.get());
    }

    // нахождение категории по имени, когда данные некорректны
    @Test
    public void testFindById_ShouldReturnCategory_WhenDataIsCorrect() throws DaoException {
        Optional<Category> actual = categoryDao.findById(1);
        Category expected = new Category();
        expected.setId(1);
        expected.setCategoryName("Ноутбуки");
        assertEquals(expected, actual.get());
    }
}