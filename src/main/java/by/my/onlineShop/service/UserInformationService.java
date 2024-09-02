package by.my.onlineShop.service;

import by.my.onlineShop.entity.User;
import by.my.onlineShop.entity.UserInformation;
import by.my.onlineShop.exeptions.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserInformationService { //Служба информации о пользователях

    // Получение информации о пользователе по идентификатору
    // На входе - id по которому я могу получить информацию о пользователе
    // На выходе - необязательную информацию о пользователе
    Optional<UserInformation> retrieveUserInformationById(long userInformationId) throws ServiceException;

    // Получение пользовательской информации от пользователей
    // На входе - Список пользователей
    // На выходе - список информации о пользователях
    List<UserInformation> getUserInformationFromUsers(List<User> users) throws ServiceException;
}
