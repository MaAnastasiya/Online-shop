package by.my.onlineShop.service.validator.impl;

import by.my.onlineShop.service.validator.AbstractValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DiscountValidatorTest {
    private final AbstractValidator validator = new DiscountValidatorImpl();
    private static final String CORRECT_DISCOUNT = "50";
    private static final String INCORRECT_DISCOUNT = "-50";

    // тестирование валидатора на корректность формы скидки
    @Test
    public void testDiscountValidator_ShouldReturnTrue_WhenDataIsCorrect() {
        boolean actual = validator.isValid(CORRECT_DISCOUNT);
        assertTrue(actual);
    }

    // тестирование валидатора на некорректность формы скидки
    @Test
    public void testDiscountValidator_ShouldReturnFalse_WhenDataIsNotCorrect() {
        boolean actual = validator.isValid(INCORRECT_DISCOUNT);
        assertFalse(actual);
    }
}