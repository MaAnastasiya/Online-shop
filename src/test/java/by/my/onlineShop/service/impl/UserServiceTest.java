package by.my.onlineShop.service.impl;

import by.my.onlineShop.entity.User;
import by.my.onlineShop.exeptions.ServiceException;
import by.my.onlineShop.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceTest {
    private final UserService userService;

    UserServiceTest(){
        userService = Mockito.mock(UserServiceImpl.class);
    }

    // Тест на залогинивание при корректных данных
    // если true, то вернем пользователя
    @Test
    public void testLogin_ShouldReturnUser_WhenDataIsCorrect() throws ServiceException {
        Optional <User> user = Optional.of(new User(3,3,"17ekaterina17@mail.ru","12345678",1));
        Mockito.when(userService.login("17ekaterina17@mail.ru", "12345678")).thenReturn(user);
        Optional<User> actual = userService.login("17ekaterina17@mail.ru", "12345678");
        assertTrue(actual.isPresent());
    }

    // не корректные данные
    @Test
    public void testLogin_ShouldReturnNull_WhenDataIsNotCorrect() throws ServiceException {
        Optional <User> user = Optional.empty();
        Mockito.when(userService.login("17ekaterina17@mail.ru", "111Kate")).thenReturn(user);
        Optional<User> actual = userService.login("17ekaterina17@mail.ru", "111Kate");
        assertFalse(actual.isPresent());
    }

}