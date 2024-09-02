package by.my.onlineShop.service.validator.impl;

import by.my.onlineShop.service.validator.AbstractValidator;

// проверка допустимых символов в годе
public class YearValidatorImpl extends AbstractValidator {
    private static final String YEAR_REGEX = "^[0-9]{4}$";

    @Override
    protected String getRegex() {
        return YEAR_REGEX;
    }
}
