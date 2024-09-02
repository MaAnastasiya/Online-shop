package by.my.onlineShop.service.validator.impl;

import by.my.onlineShop.service.validator.AbstractValidator;

// проверка допустимых символов в id
public class IdValidatorImpl extends AbstractValidator {
    private static final String ID_REGEX = "(?<![-.])\\b[0-9]+\\b(?!\\.[0-9])";

    @Override
    protected String getRegex() {
        return ID_REGEX;
    }
}
