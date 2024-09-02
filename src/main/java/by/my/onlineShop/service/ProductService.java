package by.my.onlineShop.service;

import by.my.onlineShop.entity.Order;
import by.my.onlineShop.entity.Product;
import by.my.onlineShop.exeptions.ServiceException;

import java.util.List;
import java.util.Optional;

public interface ProductService { //Обслуживание продукта

    // Способ извлечения товаров по категориям
    // На вход - id категории
    // На выход - список товаров
    List<Product> retrieveProductsByCategory(long categoryId) throws ServiceException;


    // Способ извлечения продукта по id
    //На вход - id продукта для извлечения
    // На выход - товара необязательно
    Optional<Product> retrieveProductById(long productId) throws ServiceException;


    // Способ получения товаров из заказов
    //На вход - Список заказов
    // На выход - список товаров
    List<Product> getProductsFromOrders(List<Order> orders) throws ServiceException;


    boolean addNewProduct(String productName, String photo, String priceString, String categoryName,
                          boolean status, String description) throws ServiceException;


    boolean updateProductInformation(String productIdString, String productName, String photo, String priceString, String categoryName,
                                     boolean status, String description, String promotionIdString) throws ServiceException;
}
