package by.my.onlineShop.service.validator.impl;

import by.my.onlineShop.service.validator.AbstractValidator;

// проверка допустимых символов в скидке
public class DiscountValidatorImpl extends AbstractValidator {
    private static final String DISCOUNT_REGEX = "^[0-9][0-9]?$|^100$";

    @Override
    protected String getRegex() {
        return DISCOUNT_REGEX;
    }
}
