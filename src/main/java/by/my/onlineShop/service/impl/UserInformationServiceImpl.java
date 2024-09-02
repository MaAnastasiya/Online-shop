package by.my.onlineShop.service.impl;

import by.my.onlineShop.dao.DaoFactory;
import by.my.onlineShop.dao.impl.UserInformationDaoImpl;
import by.my.onlineShop.entity.User;
import by.my.onlineShop.entity.UserInformation;
import by.my.onlineShop.exeptions.DaoException;
import by.my.onlineShop.exeptions.ServiceException;
import by.my.onlineShop.service.UserInformationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserInformationServiceImpl implements UserInformationService {
    private static final Logger logger = LogManager.getLogger();

    // получение информации о пользователе по id
    @Override
    public Optional<UserInformation> retrieveUserInformationById(long userInformationId) throws ServiceException {
        try {
            // берем всю информацию о пользователях из БД
            UserInformationDaoImpl userInformationDao = DaoFactory.getInstance().getUserInformationDao();
            Optional<UserInformation> result;
            // возвращаем информацию определенного пользователя
            result = userInformationDao.findById(userInformationId);
            return result;
        } catch (DaoException e) {
            logger.error("Unable to retrieve user information by id!");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    // получить информацию о всех пользователях из переданного списка
    @Override
    public List<UserInformation> getUserInformationFromUsers(List<User> users) throws ServiceException {
        // создаем список, куда пишем всех пользователей
        List<UserInformation> userInformation = new LinkedList<>();
        try {
            for (User user : users) {
                // берем информацию поочередно о каждом пользователе из переданного списка
                Optional<UserInformation> information = retrieveUserInformationById(user.getUserInformationId());
                if (information.isPresent()) { // если информация существует
                    if (!userInformation.contains(information.get())) {
                        // добавляем информацию в созданный список
                        userInformation.add(information.get());
                    }
                }
            }
        } catch (ServiceException e) {
            logger.error("Unable to get user information from users!");
            throw new ServiceException(e.getMessage(), e);
        }

        return userInformation;
    }
}
