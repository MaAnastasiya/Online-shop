package by.my.onlineShop.service.validator.impl;

import by.my.onlineShop.service.validator.AbstractValidator;

// проверка допустимых символов в месяце
public class MonthValidatorImpl extends AbstractValidator {
    private static final String MONTH_REGEX = "^([1-9]|1[012])$";

    @Override
    protected String getRegex() {
        return MONTH_REGEX;
    }
}
