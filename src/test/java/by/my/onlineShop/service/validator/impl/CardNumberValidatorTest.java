package by.my.onlineShop.service.validator.impl;

import by.my.onlineShop.service.validator.AbstractValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CardNumberValidatorTest {
    private final AbstractValidator validator = new CardNumberValidatorImpl();
    private static final String CORRECT_CARD_NUMBER = "1111222233334444";
    private static final String INCORRECT_CARD_NUMBER = "111122223333";

    // тестирование валидатора на корректный номер карты
    @Test
    public void testCardNumberValidator_ShouldReturnTrue_WhenDataIsCorrect() {
        boolean actual = validator.isValid(CORRECT_CARD_NUMBER);
        assertTrue(actual); // проверка истинности
    }

    // тестирование валидатора на некорректный номер карты
    @Test
    public void testCardNumberValidator_ShouldReturnFalse_WhenDataIsNotCorrect() {
        boolean actual = validator.isValid(INCORRECT_CARD_NUMBER);
        assertFalse(actual); // проверка ложности
    }
}