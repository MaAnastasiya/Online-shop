package by.my.onlineShop.dao.connection;

import by.my.onlineShop.exeptions.ConnectionException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConnectionPoolTest {

    // инициализировать пул соединения
    @BeforeAll
    static void initializeConnectionPool() throws ConnectionException {
        ConnectionPool.getInstance().initialize();
    }

    // тест на то, что getInstance возвращает один и тот же класс
    @Test
    void testGetInstance_ShouldReturnTheSameClass_Always() {
        ConnectionPool poolFirst = ConnectionPool.getInstance();
        ConnectionPool poolSecond = ConnectionPool.getInstance();

        assertEquals(poolFirst, poolSecond);
    }

    // тест получения соединения
    // вернет True, когда соединение действует 10 сек.
    @Test
    void testGetConnection_ShouldReturnTrue_WhenConnectionValidTenSeconds() throws SQLException {
        assertTrue(ConnectionPool.getInstance().getConnection().isValid(10));
    }
}