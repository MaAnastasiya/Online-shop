package by.my.onlineShop.service.impl;

import by.my.onlineShop.dao.BankCardDao;
import by.my.onlineShop.dao.DaoFactory;
import by.my.onlineShop.entity.BankCard;
import by.my.onlineShop.exeptions.DaoException;
import by.my.onlineShop.exeptions.ServiceException;
import by.my.onlineShop.service.BankCardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class BankCardServiceImpl implements BankCardService {
    private static final Logger logger = LogManager.getLogger();

    // Существует ли банковская карта
    // на вход - параметры карты
    @Override
    public boolean isBankCardExist(long cardNumber, int expirationMonth, int expirationYear, int cvv) throws ServiceException {
        BankCardDao bankCardDao = DaoFactory.getInstance().getBankCardDao();
        try {
            Optional<BankCard> bankCard = bankCardDao.findByCardNumber(cardNumber);
            if (bankCard.isPresent()) { // если элемент существует
                // если параметры карты корректны (данные из базы данных совпадают с введенными)
                if (isBankCardInformationCorrect(bankCard.get(), cardNumber, expirationMonth, expirationYear, cvv)) {
                    return true;
                }
            }
        } catch (DaoException e) {
            logger.error("Unable to check card!");
            throw new ServiceException(e.getMessage(), e);
        }
        return false;
    }

    public boolean isBankCardExistWithoutCorrDate(long cardNumber, int expirationMonth, int expirationYear, int cvv) throws ServiceException {
        BankCardDao bankCardDao = DaoFactory.getInstance().getBankCardDao();
        try {
            Optional<BankCard> bankCard = bankCardDao.findByCardNumber(cardNumber);
            if (bankCard.isPresent()) { // если элемент существует
                // если параметры карты корректны (данные из базы данных совпадают с введенными)
             return true;
            }
        } catch (DaoException e) {
            logger.error("Unable to check card!");
            throw new ServiceException(e.getMessage(), e);
        }
        return false;
    }

    private BankCard buildBankCard(long cardNumber, int expirationMonth, int expirationYear, String cardOwner, int cvv,double balance) {
        BankCard card = new BankCard();
        card.setcardholderName(cardOwner);
        card.setCardNumber(cardNumber);
        card.setExpirationMonth(expirationMonth);
        card.setExpirationYear(expirationYear);
        card.setCvv(cvv);
        card.setBalance(balance);
        return card;
    }


    // произвести оплату
    @Override
    public boolean makePayment(long cardNumber, int expirationYear, int expirationMonth, String cardOwner, int cvv, double price) throws ServiceException {
        // проверка не существования карты
        if (!isBankCardExist(cardNumber, expirationMonth, expirationYear, cvv)) { //
            // карты нет тогда создадим новую
            if (!isBankCardExistWithoutCorrDate(cardNumber, expirationMonth, expirationYear, cvv)) { //
                // карты нет тогда создадим новую
                addNewBankCard(cardNumber, expirationYear,expirationMonth,cardOwner,cvv);
            }
            else {
                return false;
            }
        }

        BankCardDao bankCardDao = DaoFactory.getInstance().getBankCardDao();
        try {
            // найдем карту
            Optional<BankCard> bankCard = bankCardDao.findByCardNumber(cardNumber);
            if (bankCard.isPresent()) { // если нашли
                // хватит ли денег для оплаты
                if (!isEnoughMoney(bankCard.get().getBalance(), price)) {
                    return false;
                }

                double newBalance = calculateNewBalance(bankCard.get().getBalance(), price);
                double scale = Math.pow(10, 2);
                newBalance = Math.ceil(newBalance * scale) / scale;// округляем
                // отправляем новый баланс на карту
                bankCardDao.updateBalanceByCardNumber(cardNumber, newBalance);
                return true;
            }
            return false;
        } catch (DaoException e) {
            logger.error("Unable to pay by card!");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    // добавление новой банковской карты в БД
    private void addNewBankCard(long cardNumber, int expirationYear,int expirationMonth, String cardOwner , int cvv) {
        try {
            // взяли карты из БД
            BankCardDao bankDao = DaoFactory.getInstance().getBankCardDao();
            // генерируем рандомный баланс
            double piece = Math.ceil(Math.random()+30000);
            // создаем сущность карты
            BankCard card = buildBankCard(cardNumber,expirationMonth,expirationYear, cardOwner ,cvv,piece);
            // сохраняем карту
            bankDao.save(card);

        } catch (DaoException e) {
            logger.error("Unable to add product!");
        }
    }

    private boolean isBankCardInformationCorrect(BankCard bankCard, long cardNumber, int expirationMonth, int expirationYear, int cvv) {
        if (bankCard.getCardNumber() != cardNumber) return false;
        if (bankCard.getExpirationMonth() != expirationMonth) return false;
        if (bankCard.getExpirationYear() != expirationYear) return false;
        if (bankCard.getCvv() != cvv) return false;
        return true;
    }

    // хватит ли денег для оплаты
    // на вход - баланс карты, цена покупки
    private boolean isEnoughMoney(double balance, double price) {
        return price <= balance;
    }

    // пересчитаем новый баланс на карте
    private double calculateNewBalance(double balance, double price) {
        return balance - price;
    }
}