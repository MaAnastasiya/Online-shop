package by.my.onlineShop.dao.impl;

import by.my.onlineShop.dao.AbstractDao;
import by.my.onlineShop.dao.BankCardDao;
import by.my.onlineShop.dao.Table;
import by.my.onlineShop.dao.mapper.RowMapperFactory;
import by.my.onlineShop.entity.BankCard;
import by.my.onlineShop.exeptions.DaoException;

import java.util.Optional;

public class BankCardDaoImpl extends AbstractDao<BankCard> implements BankCardDao {

    // поиск банковской карты по номеру из БД по запросу
    private static final String FIND_BANK_CARD_BY_NUMBER_QUERY = "SELECT * FROM " + Table.BANK_CARD + " WHERE card_number=?";
    // строка запроса на сохранение банковской карты в таблицу БД
    private static final String SAVE_BANK_CARD_QUERY = "INSERT INTO " + Table.BANK_CARD +
            " (card_number, expiration_year, expiration_month, card_owner, balance, cvv) VALUES (?, ?, ?, ?, ?, ?)";
    // строка запроса на обновление баланса банковской карты в таблицу БД
    private static final String UPDATE_BALANCE_BY_CARD_NUMBER_QUERY = "UPDATE " + Table.BANK_CARD + " SET balance=? WHERE card_number=?";

    public BankCardDaoImpl() {
        super(RowMapperFactory.getInstance().getBankCardRowMapper(), Table.BANK_CARD);
    }

    // нахождение сущности BankCard по номеру по номеру карты
    @Override
    public Optional<BankCard> findByCardNumber(long cardNumber) throws DaoException {
        return executeQueryForSingleResult(FIND_BANK_CARD_BY_NUMBER_QUERY, cardNumber);
    }

    // обновление баланса сущности BankCard по номеру карты
    @Override
    public void updateBalanceByCardNumber(long cardNumber, double newBalance) throws DaoException {
        executeUpdateQuery(UPDATE_BALANCE_BY_CARD_NUMBER_QUERY, newBalance, cardNumber);
    }

    // сохранение новой сущности BankCard в БД
    @Override
    public long save(BankCard bankCard) throws DaoException {
        return executeInsertQuery(SAVE_BANK_CARD_QUERY, bankCard.getCardNumber(), bankCard.getExpirationYear(),
                bankCard.getExpirationMonth(), bankCard.getCardholderName(), bankCard.getBalance(), bankCard.getCvv());
    }
}
