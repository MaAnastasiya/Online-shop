package by.my.onlineShop.service.validator.impl;

import by.my.onlineShop.service.validator.AbstractValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NameValidatorTest {
    private final AbstractValidator validator = new NameValidatorImpl();
    private static final String CORRECT_NAME = "Test";
    private static final String INCORRECT_NAME = "test";

    // тестирование валидатора на корректность имени
    @Test
    public void testNameValidator_ShouldReturnTrue_WhenDataIsCorrect() {
        boolean actual = validator.isValid(CORRECT_NAME);
        assertTrue(actual);
    }
    // тестирование валидатора на некорректность месяца
    @Test
    public void testNameValidator_ShouldReturnFalse_WhenDataIsNotCorrect() {
        boolean actual = validator.isValid(INCORRECT_NAME);
        assertFalse(actual);
    }
}