package by.my.onlineShop.dao;

import by.my.onlineShop.dao.mapper.RowMapper;
import by.my.onlineShop.entity.Identifiable;
import by.my.onlineShop.exeptions.DaoException;

import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends Identifiable> extends AbstractQueryExecutor<T> implements Dao<T> {
    private final String tableName;

    public AbstractDao(RowMapper<T> rowMapper, String tableName) {
        super(rowMapper);
        this.tableName = tableName;
    }

    // извлечение всей информации из заданной таблицы БД
    @Override
    public List<T> findAll() throws DaoException {
        String query = "SELECT * FROM " + tableName;
        return executeQuery(query);
    }

    // извлечение определенной строки (1 сущность) из заданной таблицы БД по id
    @Override
    public Optional<T> findById(long id) throws DaoException {
        String query = "SELECT * FROM " + tableName + " WHERE id=?";
        return executeQueryForSingleResult(query, id);
    }

    // удаление определенной строки (1 сущность) из заданной таблицы БД по id
    @Override
    public void removeById(long id) throws DaoException {
        String deleteQuery = "DELETE FROM " + tableName + " WHERE id=?";
        executeUpdateQuery(deleteQuery, id);
    }
}
