package by.my.onlineShop.service;

import by.my.onlineShop.entity.Role;
import by.my.onlineShop.exeptions.ServiceException;

import java.util.Optional;

public interface RoleService {

    // Назначение роли по id
    // На вход - идентификатор роли
    // На выходе - необязательно роль
    Optional<Role> retrieveRoleById(long roleId) throws ServiceException;
}
