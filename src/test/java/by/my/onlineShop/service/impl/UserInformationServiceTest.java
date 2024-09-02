package by.my.onlineShop.service.impl;

import by.my.onlineShop.entity.User;
import by.my.onlineShop.entity.UserInformation;
import by.my.onlineShop.exeptions.ServiceException;
import by.my.onlineShop.service.UserInformationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserInformationServiceTest {
    private final UserInformationService userInformationService;

    UserInformationServiceTest(){
        userInformationService = Mockito.mock(UserInformationServiceImpl.class);
    }
    // возвращает пользователя по id, если данные введены корректно
    @Test
    public void testRetrieveUserInformationById_ShouldReturnTrue_WhenDataIsCorrect() throws ServiceException {
        Optional <UserInformation> userInformation = Optional.of(new UserInformation(1, "Анастасия","Крюковская","Алексеевна",89118330761L));
        Mockito.when(userInformationService.retrieveUserInformationById(1)).thenReturn(userInformation);
        Optional<UserInformation> actual = userInformationService.retrieveUserInformationById(1);
        assertTrue(actual.isPresent());
    }

    // возвращает пользователя по id, если данные введены корректно
    @Test
    public void testRetrieveUserInformationById_ShouldReturnTrue_WhenDataIsNotCorrect() throws ServiceException {
        Optional <UserInformation> userInformation = Optional.empty();
        Mockito.when(userInformationService.retrieveUserInformationById(100)).thenReturn(userInformation);
        Optional<UserInformation> actual = userInformationService.retrieveUserInformationById(100);
        assertFalse(actual.isPresent());
    }

    @Test
    public void testRetrieveCategories_ShouldReturnCategories_WhenDataIsNotNull() throws ServiceException {

        List<User> users = new LinkedList<>();
        users.add(new User(1,1,"i.nastya897@gmail.com","Nastya09042002",2));
        users.add(new User(2,2,"victor.petrov@gmail.com","Victor123",1));
        users.add(new User(3,3,"17ekaterina17@mail.ru","12345678",1));

        UserInformation userInformationFirst = new UserInformation(1,"Анастасия","Крюковская","Алексеевна",89118330761L);
        UserInformation userInformationSecond = new UserInformation(2,"Виктор","Петров","Андреевич",84448770101L);
        UserInformation userInformationThird = new UserInformation(3,"Екатерина","Козлова","Романовна",89347779900L);

        List<UserInformation> expected = new LinkedList<>();
        expected.add(userInformationFirst);
        expected.add(userInformationSecond);
        expected.add(userInformationThird);
        Mockito.when(userInformationService.getUserInformationFromUsers(users)).thenReturn(expected);
        List<UserInformation> actual = userInformationService.getUserInformationFromUsers(users);

        assertEquals(expected, actual);
    }

}
