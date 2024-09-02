package by.my.onlineShop.service.impl;

import by.my.onlineShop.dao.DaoFactory;
import by.my.onlineShop.dao.impl.CategoryDaoImpl;
import by.my.onlineShop.entity.Category;
import by.my.onlineShop.exeptions.DaoException;
import by.my.onlineShop.exeptions.ServiceException;
import by.my.onlineShop.service.CategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {
    private static final Logger logger = LogManager.getLogger();

    // извлечение категорий (список категорий)
    @Override
    public List<Category> retrieveCategories() throws ServiceException {
        try {
            // извлекание категорий из dao
            CategoryDaoImpl categoryDao = DaoFactory.getInstance().getCategoryDao();
            List<Category> result = null;
            // извлечение всех сущностей категорий
            result = categoryDao.findAll();
            return result;
        } catch (DaoException e) {
            logger.error("Unable to retrieve categories!");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    // получить индификатор категории
    @Override
    public Optional<Category> retrieveCategoryBtId(long categoryId) throws ServiceException {
        try {
            // извлекание категорий из dao
            CategoryDaoImpl categoryDao = DaoFactory.getInstance().getCategoryDao();
            Optional<Category> result;
            // извлечение нужной категории по id
            result = categoryDao.findById(categoryId);
            return result;
        } catch (DaoException e) {
            logger.error("Unable to retrieve category by id!");
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
