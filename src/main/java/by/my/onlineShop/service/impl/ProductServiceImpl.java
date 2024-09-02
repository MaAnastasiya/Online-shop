package by.my.onlineShop.service.impl;

import by.my.onlineShop.dao.CategoryDao;
import by.my.onlineShop.dao.DaoFactory;
import by.my.onlineShop.dao.ProductDao;
import by.my.onlineShop.dao.PromotionDao;
import by.my.onlineShop.entity.Category;
import by.my.onlineShop.entity.Order;
import by.my.onlineShop.entity.Product;
import by.my.onlineShop.entity.Promotion;
import by.my.onlineShop.exeptions.DaoException;
import by.my.onlineShop.exeptions.ServiceException;
import by.my.onlineShop.service.ProductService;
import by.my.onlineShop.service.validator.Validator;
import by.my.onlineShop.service.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LogManager.getLogger();

    // извлекаем товары по id категории
    @Override
    public List<Product> retrieveProductsByCategory(long categoryId) throws ServiceException {
        try {
            ProductDao productDao = DaoFactory.getInstance().getProductDao();
            List<Product> result = null;
            result = productDao.findByCategory(categoryId);
            return result;
        } catch (DaoException e) {
            logger.error("Unable to retrieve products by category!");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    // извлекаем товар по его id
    @Override
    public Optional<Product> retrieveProductById(long productId) throws ServiceException {
        try {
            ProductDao productDao = DaoFactory.getInstance().getProductDao();
            Optional<Product> result;
            result = productDao.findById(productId);
            return result;
        } catch (DaoException e) {
            logger.error("Unable to retrieve product by id!");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    // получать Товары Из Заказов
    @Override
    public List<Product> getProductsFromOrders(List<Order> orders) throws ServiceException {
        List<Product> products = new LinkedList<>();

        for (Order order : orders) {
            // получение продукта из заказа по id
            Optional<Product> product = retrieveProductById(order.getProductId());
            if (product.isPresent()) {
                if (!products.contains(product.get())) {
                    products.add(product.get()); // добавляем продукт в список
                }
            }
        }
        return products;
    }

    @Override
    public boolean addNewProduct(String productName, String photo, String priceString, String categoryName, boolean status, String description) throws ServiceException {
        System.out.println("Я здесь добавлю");
        System.out.println(photo);
        if (productName == null || photo == null || categoryName == null || description == null) {
            return false;
        }

        Validator priceValidator = ValidatorFactory.getInstance().getPriceValidator();
        // корректность строки цены
        if (!priceValidator.isValid(priceString)) {
            return false;
        }

        try {
            ProductDao productDao = DaoFactory.getInstance().getProductDao();
            // существует ли продукт в БД
            Optional<Product> productExist = productDao.findByName(productName);
            if (productExist.isPresent()) {
                return false;
            }

            // взяли категорию данного продукта
            long categoryId = getCategoryId(categoryName);

            // перевели цену в число
            double price = Double.parseDouble(priceString);
            // создали продукт
            Product product = buildProduct(categoryId, productName, description, price, status, photo);
            // сохранили продукт в БД
            productDao.save(product);

            return true;
        } catch (DaoException e) {
            logger.error("Unable to add product!");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    // обновление Информации о Продукте
    @Override
    public boolean updateProductInformation(String productIdString, String productName, String photo, String priceString, String categoryName, boolean status, String description, String promotionIdString) throws ServiceException {
        // проверили что все параметры переданы
        if (productIdString == null || productName == null || photo == null || priceString == null ||
                categoryName == null || description == null || promotionIdString == null) {
            return false;
        }

        // проверка корректности строки цены
        Validator priceValidator = ValidatorFactory.getInstance().getPriceValidator();
        if (!priceValidator.isValid(priceString)) {
            return false;
        }

        // проверка корректности id акции и id продукта
        Validator idValidator = ValidatorFactory.getInstance().getIdValidator();
        if (!(idValidator.isValid(promotionIdString) || idValidator.isValid(productIdString))) {
            return false;
        }

        long categoryId = getCategoryId(categoryName);
        long promotionId = Long.parseLong(promotionIdString);
        long productId = Long.parseLong(productIdString);
        try {
            // взяли акции из БД
            PromotionDao promotionDao = DaoFactory.getInstance().getPromotionDao();
            // взяли нужную акцию по id
            Optional<Promotion> promotion = promotionDao.findById(promotionId);
            if (!(promotion.isPresent() || promotionId == 0)) {
                return false;
            }

            double price = Double.parseDouble(priceString);
            ProductDao productDao = DaoFactory.getInstance().getProductDao();
            // формируем новый продукт
            Product product = buildProduct(categoryId, productName, description, price, status, photo);
            // меняем акцию в продукте
            product.setPromotionId(promotionId);
            // обновление продукта по идентификатору в базе данных
            productDao.updateById(productId, product);

            if (promotionId == 0) {
                // удаления акции по id продукта в базе данных у данного продукта
                productDao.removePromotionById(productId);
            } else {
                // обновление акции по id продукта в базе данных
                productDao.updatePromotionById(productId, promotionId);
            }

            return true;
        } catch (DaoException e) {
            logger.error("Unable to update product!");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    // создание продукта
    private Product buildProduct(long categoryId, String name, String description, double price,
                                 boolean status, String photo) {
        Product product = new Product();
        product.setCategoryId(categoryId);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStatus(status);
        product.setPhoto(photo);
        return product;
    }

    // Получить id категории по имени категории
    private long getCategoryId(String categoryName) throws ServiceException {
        try {
            // взяли все категории из БД
            CategoryDao categoryDao = DaoFactory.getInstance().getCategoryDao();
            // взяли нужную категорию из БД
            Optional<Category> categoryExist = categoryDao.findByName(categoryName);

            long categoryId;
            if (categoryExist.isPresent()) {
                categoryId = categoryExist.get().getId();
            } else {
                Category category = new Category();
                category.setCategoryName(categoryName);
                categoryId = categoryDao.save(category);
            }
            return categoryId;
        } catch (DaoException e) {
            logger.error("Unable to get category id!");
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
