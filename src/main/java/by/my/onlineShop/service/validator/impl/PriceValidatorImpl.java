package by.my.onlineShop.service.validator.impl;

import by.my.onlineShop.service.validator.AbstractValidator;

// проверка допустимых символов в цене
public class PriceValidatorImpl extends AbstractValidator {
    private static final String PRICE_REGEX = "^\\d+\\.\\d{0,2}$";

    @Override
    protected String getRegex() {
        return PRICE_REGEX;
    }
}
