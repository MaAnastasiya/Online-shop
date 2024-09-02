package by.my.onlineShop.service.validator.impl;

import by.my.onlineShop.service.validator.AbstractValidator;

// проверка допустимых символов в cvv карты
// кол-во цифр 3
public class CvvValidatorImpl extends AbstractValidator {
    private static final String CVV_REGEX = "^[0-9]{3}$";

    @Override
    protected String getRegex() {
        return CVV_REGEX;
    }
}
