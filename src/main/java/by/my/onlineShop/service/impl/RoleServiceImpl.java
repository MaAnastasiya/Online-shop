package by.my.onlineShop.service.impl;

import by.my.onlineShop.dao.DaoFactory;
import by.my.onlineShop.dao.RoleDao;
import by.my.onlineShop.entity.Role;
import by.my.onlineShop.exeptions.DaoException;
import by.my.onlineShop.exeptions.ServiceException;
import by.my.onlineShop.service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class RoleServiceImpl implements RoleService {
    private static final Logger logger = LogManager.getLogger();

    // получить роль пользователя по id
    @Override
    public Optional<Role> retrieveRoleById(long roleId) throws ServiceException {
        try {
            // взяли роли из БД
            RoleDao roleDao = DaoFactory.getInstance().getRoleDao();
            Optional<Role> result;
            // вернули роль по id пользователя
            result = roleDao.findById(roleId);
            return result;
        } catch (DaoException e) {
            logger.error("Unable to retrieve role by id!");
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
