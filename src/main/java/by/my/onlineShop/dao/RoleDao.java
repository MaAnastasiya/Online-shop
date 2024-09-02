package by.my.onlineShop.dao;

import by.my.onlineShop.entity.Role;
import by.my.onlineShop.exeptions.DaoException;

import java.util.Optional;

public interface RoleDao extends Dao<Role> { // Dao ролей

    // Получение роли по имени из базы данных
    // На вход - имя роли
    // Optional роль
    Optional<Role> findByName(String name) throws DaoException;
}
