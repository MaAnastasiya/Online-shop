package by.my.onlineShop.service.impl;

import by.my.onlineShop.dao.DaoFactory;
import by.my.onlineShop.dao.PromotionDao;
import by.my.onlineShop.dao.impl.PromotionDaoImpl;
import by.my.onlineShop.entity.Product;
import by.my.onlineShop.entity.Promotion;
import by.my.onlineShop.exeptions.DaoException;
import by.my.onlineShop.exeptions.ServiceException;
import by.my.onlineShop.service.PromotionService;
import by.my.onlineShop.service.validator.Validator;
import by.my.onlineShop.service.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PromotionServiceImpl implements PromotionService {
    private static final Logger logger = LogManager.getLogger();

    private static final int HUNDRED_PERCENT = 100;// сто процентов

    // получить список всех акций
    @Override
    public List<Promotion> retrievePromotions() throws ServiceException {
        try {
            // взяли все акции из БД
            PromotionDaoImpl promotionDao = DaoFactory.getInstance().getPromotionDao();
            List<Promotion> result = null;
            // вернули в список все акции
            result = promotionDao.findAll();
            return result;
        } catch (DaoException e) {
            logger.error("Unable to retrieve promotions!");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    // получить определенную акцию по id
    @Override
    public Optional<Promotion> retrievePromotionById(long promotionId) throws ServiceException {
        try {
            // взяли все акции из БД
            PromotionDaoImpl promotionDao = DaoFactory.getInstance().getPromotionDao();
            Optional<Promotion> result;
            // вернули акцию по ее id
            result = promotionDao.findById(promotionId);
            // проверим актуальность акуии по времени
            if (!checkRelevanceOfPromotion(result)) {
                // вернем пустой объект
                return Optional.empty();
            }
            return result;
        } catch (DaoException e) {
            logger.error("Unable to retrieve promotion by id!");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    // пересчет новой цены с акцией
    @Override
    public Double calculateNewPrice(double price, int discount) {
        return price * (HUNDRED_PERCENT - discount) / HUNDRED_PERCENT;
    }

    // получите новые цены
    @Override
    public Map<String, Double> getNewPrices(List<Product> products) throws ServiceException {
        Map<String, Double> newPrices = new HashMap<>();

        // проходимся по всем продуктам
        for (Product product : products) {
            // если у них есть скидочная акция
            if (product.getPromotionId() != 0) {
                // берем их скидочную акцию
                Optional<Promotion> promotion = retrievePromotionById(product.getPromotionId());
                // проверяем актуальность акции
                if (checkRelevanceOfPromotion(promotion)) {
                    // пересчитываем цену вместе с акцией
                    Double newPrice = calculateNewPrice(product.getPrice(), promotion.get().getDiscount());
                    double scale = Math.pow(10, 2);
                    newPrice = Math.ceil(newPrice * scale) / scale;
                    // map (имя продукта - цена)
                    newPrices.put(product.getName(), newPrice);
                }
            }
        }
        return newPrices;
    }

    // добавление новой акции
    @Override
    public boolean addNewPromotion(String promotionName, String photo, String beginningDateString, String expirationDateString,
                                   String description, String discountString) throws ServiceException {
        // переданы ли все параметры
        if (promotionName == null || photo == null || beginningDateString == null || expirationDateString == null ||
                description == null || discountString == null) {
            return false;
        }

        try {
            // взяли акции из БД
            PromotionDao promotionDao = DaoFactory.getInstance().getPromotionDao();
            // взяли по переданному имени акцию
            Optional<Promotion> existPromotion = promotionDao.findByName(promotionName);
            // проверили есть ли акция с этим именем
            if (existPromotion.isPresent()) {
                return false;
            }

            // установили время акции
            Date beginningDate = new SimpleDateFormat("yyyy-MM-dd").parse(beginningDateString);
            Date expirationDate = new SimpleDateFormat("yyyy-MM-dd").parse(expirationDateString);

            // проверили корректность дат
            if (!isPromotionDatesCorrect(beginningDate, expirationDate)) {
                return false;
            }

            // проверили корректность скидки, в плане символов
            Validator discountValidator = ValidatorFactory.getInstance().getDiscountValidator();
            if (!discountValidator.isValid(discountString)) {
                return false;
            }

            byte discount = Byte.parseByte(discountString);

            // сделали акцию новую
            Promotion promotion = buildPromotion(promotionName, photo, beginningDate, expirationDate, discount, description);
            // сохранили ее
            promotionDao.save(promotion);

            return true;
        } catch (DaoException | ParseException e) {
            logger.error("Unable to add product!");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    // проверьте актуальности акции по дате ее действия относительно сегоднешей даты
    @Override
    public boolean checkRelevanceOfPromotion(Optional<Promotion> promotion) {
        if (promotion.isPresent()) {
            Date currentDate = new Date();
            return promotion.get().getExpirationDate().compareTo(currentDate) >= 0;
        }
        return false;
    }

    // проверьте актуальности акции по дате ее действия относительно сегоднешей даты
    private boolean isPromotionDatesCorrect(Date beginningDate, Date expirationDate) {
        Date currentDate = new Date();
        if (!(beginningDate.compareTo(currentDate) >= 0)) {
            return false;
        }

        if (!(expirationDate.compareTo(beginningDate) >= 0)) {
            return false;
        }

        return true;
    }

    // создание новой акции
    private Promotion buildPromotion(String name, String photo, Date beginningDate, Date expirationDate,
                                     byte discount, String description) {
        Promotion promotion = new Promotion();
        promotion.setName(name);
        promotion.setPhoto(photo);
        promotion.setBeginningDate(beginningDate);
        promotion.setExpirationDate(expirationDate);
        promotion.setDiscount(discount);
        promotion.setDescription(description);
        return promotion;
    }
}
