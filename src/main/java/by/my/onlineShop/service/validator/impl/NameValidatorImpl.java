package by.my.onlineShop.service.validator.impl;

import by.my.onlineShop.service.validator.AbstractValidator;

// проверка допустимых символов в имени
public class NameValidatorImpl extends AbstractValidator {
    private static final String NAME_REGEX = "^[A-ZА-Я]{1}[A-zА-я]{2,29}$";

    @Override
    protected String getRegex() {
        return NAME_REGEX;
    }
}
