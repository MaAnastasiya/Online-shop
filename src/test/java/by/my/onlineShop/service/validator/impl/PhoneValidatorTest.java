package by.my.onlineShop.service.validator.impl;

import by.my.onlineShop.service.validator.AbstractValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PhoneValidatorTest {
    private final AbstractValidator validator = new PhoneValidatorImpl();
    private static final String CORRECT_PHONE = "375291234567";
    private static final String INCORRECT_PHONE = "37529";

    // тестирование валидатора на корректность номера телефона
    @Test
    public void testPhoneValidator_ShouldReturnTrue_WhenDataIsCorrect() {
        boolean actual = validator.isValid(CORRECT_PHONE);
        assertTrue(actual);
    }

    // тестирование валидатора на некорректность номера телефона
    @Test
    public void testPhoneValidator_ShouldReturnFalse_WhenDataIsNotCorrect() {
        boolean actual = validator.isValid(INCORRECT_PHONE);
        assertFalse(actual);
    }
}