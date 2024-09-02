package by.my.onlineShop.service.validator.impl;

import by.my.onlineShop.service.validator.AbstractValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailValidatorTest {
    private final AbstractValidator validator = new EmailValidatorImpl();
    private static final String CORRECT_EMAIL = "test.email@gmail.com";
    private static final String INCORRECT_EMAIL = "test.email@com";

    // тестирование валидатора на корректность email
    @Test
    public void testDiscountValidator_ShouldReturnTrue_WhenDataIsCorrect() {
        boolean actual = validator.isValid(CORRECT_EMAIL);
        assertTrue(actual);
    }

    // тестирование валидатора на некорректность email
    @Test
    public void testDiscountValidator_ShouldReturnFalse_WhenDataIsNotCorrect() {
        boolean actual = validator.isValid(INCORRECT_EMAIL);
        assertFalse(actual);
    }
}