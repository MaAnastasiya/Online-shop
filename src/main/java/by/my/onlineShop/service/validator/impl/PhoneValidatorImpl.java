package by.my.onlineShop.service.validator.impl;

import by.my.onlineShop.service.validator.AbstractValidator;

// проверка допустимых символов в телефоне
public class PhoneValidatorImpl extends AbstractValidator {
    private static final String PHONE_REGEX = "^[0-9]{10,15}$";

    @Override
    protected String getRegex() {
        return PHONE_REGEX;
    }
}
