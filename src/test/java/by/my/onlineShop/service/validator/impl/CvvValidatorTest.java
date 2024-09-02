package by.my.onlineShop.service.validator.impl;

import by.my.onlineShop.service.validator.AbstractValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CvvValidatorTest {
    private final AbstractValidator validator = new CvvValidatorImpl();
    private static final String CORRECT_CVV = "123";
    private static final String INCORRECT_CVV = "1234";

    // тестирование валидатора на корректность cvv карты
    @Test
    public void testCvvValidator_ShouldReturnTrue_WhenDataIsCorrect() {
        boolean actual = validator.isValid(CORRECT_CVV);
        assertTrue(actual);
    }

    // тестирование валидатора на некорректность cvv карты
    @Test
    public void testCvvValidator_ShouldReturnFalse_WhenDataIsNotCorrect() {
        boolean actual = validator.isValid(INCORRECT_CVV);
        assertFalse(actual);
    }
}