package by.my.onlineShop.dao;

import by.my.onlineShop.dao.connection.ConnectionPool;
import by.my.onlineShop.dao.connection.ProxyConnection;
import by.my.onlineShop.dao.mapper.RowMapper;
import by.my.onlineShop.entity.Identifiable;
import by.my.onlineShop.exeptions.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AbstractQueryExecutor<T extends Identifiable> {
    private static final Logger logger = LogManager.getLogger();

    private final RowMapper<T> rowMapper;

    public AbstractQueryExecutor(RowMapper<T> rowMapper) {
        this.rowMapper = rowMapper;
    }

    // Запрос на выполнение SELECT - извлечение информации из БД
    protected List<T> executeQuery(String query, Object... params) throws DaoException {
        List<T> entities = new ArrayList<>();
        try (PreparedStatement statement = createStatement(query, params);
             // возвращает набор значений SELECT
             ResultSet resultSet = statement.executeQuery()) {
            entities = createEntitiesList(resultSet);
        } catch (SQLException e) {
            logger.error("Unable to execute query", e);
            throw new DaoException(e.getMessage(), e);
        }
        return entities;
    }

    // Выполнение Запроса Для Одного Результата ! Вернуть должны не все сущности, а одну!
    // query - сама строка запроса к БД + параметры
    protected Optional<T> executeQueryForSingleResult(String query, Object... params) throws DaoException {
        // пытаемся выполнить запрос к БД на выполнение
        List<T> items = executeQuery(query, params);
        if (items.isEmpty()) {
            return Optional.empty();
        }

        if (!(items.size() == 1)) {
            return Optional.empty();
        }

        // возвращаем сущность
        return Optional.of(items.get(0));
    }

    // выполнить запрос вставки ( добавим что-нибудь в БД)
    protected long executeInsertQuery(String query, Object... params) throws DaoException {
        long result = 0;
        // пробуем создать соединение по запросу
        try (PreparedStatement statement = createStatement(query, params)) {
            // изменяем что-то в таблице (добавляем)
            statement.executeUpdate();
            // извлекает все сгенерированные ключи после запроса
            ResultSet generatedKey = statement.getGeneratedKeys();
            if (generatedKey.next()) {
                result = generatedKey.getLong(1);
            }
        } catch (SQLException e) {
            logger.error("Unable to execute insert query", e);
            throw new DaoException(e.getMessage(), e);
        }
        return result;
    }

    // выполнить запрос Обновления поля в БД
    protected void executeUpdateQuery(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Unable to execute update query", e);
            throw new DaoException(e.getMessage(), e);
        }
    }

    // создание коннекта для обращения к БД по запросу
    private PreparedStatement createStatement(String query, Object... params) throws DaoException {
        try {
            // берем соединение для БД
            ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
            // соединение обращается со строкой запроса query к БД
            PreparedStatement preparedStatement = proxyConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            // довавление параметров по запросу
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            // освобождаем соединение
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
            return preparedStatement;
        } catch (SQLException e) {
            logger.error("Unable to create statement!", e);
            throw new DaoException(e.getMessage(), e);
        }
    }

    // Создание списка объектов из БД (уже в виде сущностей)
    private List<T> createEntitiesList(ResultSet resultSet) throws DaoException {
        List<T> entities = new ArrayList<>();
        try {
            while (resultSet.next()) { // берем каждую строку
                // для нее вызываем map (обращения к полям)
                // формируем сущность
                T entity = rowMapper.map(resultSet);
                // добавляем в список сущностей
                entities.add(entity);
            }
        } catch (SQLException e) {
            logger.error("Unable to create entity list!", e);
            throw new DaoException(e.getMessage(), e);
        }
        return entities;
    }
}
