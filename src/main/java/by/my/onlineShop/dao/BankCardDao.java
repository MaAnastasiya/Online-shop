package by.my.onlineShop.dao;

import by.my.onlineShop.entity.BankCard;
import by.my.onlineShop.exeptions.DaoException;

import java.util.Optional;

public interface BankCardDao { // dao банковских карт

    // Получение банковской карты по номеру карты из базы данных
    // На вход - номер карты
    // На выходе - банковскую карту Optional
    Optional<BankCard> findByCardNumber(long cardNumber) throws DaoException;

    // Обновление баланса по номеру банковской карты в базе данных
    // На вход - номер карты, новый баланс
    void updateBalanceByCardNumber(long cardNumber, double newBalance) throws DaoException;

    long save(BankCard bankCard) throws DaoException;
}
