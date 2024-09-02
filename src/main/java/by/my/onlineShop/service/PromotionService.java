package by.my.onlineShop.service;

import by.my.onlineShop.entity.Product;
import by.my.onlineShop.entity.Promotion;
import by.my.onlineShop.exeptions.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PromotionService { //обслуживание акций

    // Получение рекламных акций
    // На выходе - список рекламных акций
    List<Promotion> retrievePromotions() throws ServiceException;


    // Получение акции по id
    // На вход - id акции
    // На выходе - желаемая акция
    Optional<Promotion> retrievePromotionById(long promotionId) throws ServiceException;


    // Метод расчета новой цены
    // На вход - цена и скидка
    // На выходе - новая цена
    Double calculateNewPrice(double price, int discount);


    // Способ получения новой цены на продукцию
    // На вход - Список продуктов
    // На выходе - возврата с названием товара и новой ценой
    Map<String, Double> getNewPrices(List<Product> products) throws ServiceException;


    // Добавление новой акции
    // На вход - название рекламной акции, фото акции, начальная дата акции, дата конца акции, скидка на акцию,
    // На выходе - true, если акция была успешно добавлена
    boolean addNewPromotion(String promotionName, String photo, String beginningDateString, String expirationDateString,
                            String description, String discountString) throws ServiceException;


    // проверить актуальность рекламной акции
    // На вход - определенная акция
    // На выходе - возврата с названием товара и новой ценой
    boolean checkRelevanceOfPromotion(Optional<Promotion> promotion);
}
