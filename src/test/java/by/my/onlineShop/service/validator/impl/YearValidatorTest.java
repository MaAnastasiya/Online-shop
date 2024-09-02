package by.my.onlineShop.service.validator.impl;

import by.my.onlineShop.service.validator.AbstractValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class YearValidatorTest {
    private final AbstractValidator validator = new YearValidatorImpl();
    private static final String CORRECT_YEAR = "2021";
    private static final String INCORRECT_YEAR = "20211";

    // тестирование валидатора на корректность формы года
    @Test
    public void testYearValidator_ShouldReturnTrue_WhenDataIsCorrect() {
        boolean actual = validator.isValid(CORRECT_YEAR);
        assertTrue(actual);
    }

    // тестирование валидатора на некорректность формы года
    @Test
    public void testYearValidator_ShouldReturnFalse_WhenDataIsNotCorrect() {
        boolean actual = validator.isValid(INCORRECT_YEAR);
        assertFalse(actual);
    }
}