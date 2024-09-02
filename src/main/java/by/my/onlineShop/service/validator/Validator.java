package by.my.onlineShop.service.validator;

public interface Validator {

    // проверка информации
    // на вход - информация о выражении в виде строки для проверки
    // на выходе - результат проверки
    boolean isValid(String expression);
}
