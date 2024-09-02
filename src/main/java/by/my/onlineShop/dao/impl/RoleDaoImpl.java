package by.my.onlineShop.dao.impl;

import by.my.onlineShop.dao.AbstractDao;
import by.my.onlineShop.dao.RoleDao;
import by.my.onlineShop.dao.Table;
import by.my.onlineShop.dao.mapper.RowMapperFactory;
import by.my.onlineShop.entity.Role;
import by.my.onlineShop.exeptions.DaoException;

import java.util.Optional;

public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
    // сохранение роли в таблице
    private static final String SAVE_ROLE_QUERY = "INSERT INTO " + Table.ROLE + " (role) VALUES (?)";
    // нахождение роли пользователя
    private static final String FIND_ROLE_BY_NAME_QUERY = "SELECT * FROM " + Table.ROLE + " WHERE role=?";

    public RoleDaoImpl() {
        super(RowMapperFactory.getInstance().getRoleRowMapper(), Table.ROLE);
    }

    // сохранение роли в БД
    @Override
    public long save(Role role) throws DaoException {
        return executeInsertQuery(SAVE_ROLE_QUERY, role.getName());
    }

    // нахождение сущности РОЛИ по имени пользователя
    @Override
    public Optional<Role> findByName(String name) throws DaoException {
        return executeQueryForSingleResult(FIND_ROLE_BY_NAME_QUERY, name);
    }
}
