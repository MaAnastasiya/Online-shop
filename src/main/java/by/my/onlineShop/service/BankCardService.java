package by.my.onlineShop.service;

import by.my.onlineShop.exeptions.ServiceException;

public interface BankCardService { // Обслуживание Банковских Карт

    // проверяем существование банковской карты.
    // На вход параметры карты.
    // На выход - true существует, false - не существует

    boolean isBankCardExist(long cardNumber, int expirationMonth, int expirationYear, int cvv) throws ServiceException;


    // Проведение оплаты заказа.
    // На вход параметры карты.
    // На выход - true платеж прошел, false - в противном случае исключение
    boolean makePayment(long cardNumber, int expirationMonth, int expirationYear, String cardholderName, int cvv, double price) throws ServiceException;
}
