package by.my.onlineShop.service.validator.impl;

import by.my.onlineShop.service.validator.AbstractValidator;

// проверка допустимых символов в номере карты
// кол-во цифр 16
public class CardNumberValidatorImpl extends AbstractValidator {
    private static final String CARD_NUMBER_REGEX = "^[0-9]{16}$";

    @Override
    protected String getRegex() {
        return CARD_NUMBER_REGEX;
    }
}
