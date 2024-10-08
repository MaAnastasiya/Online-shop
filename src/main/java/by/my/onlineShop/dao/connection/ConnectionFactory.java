package by.my.onlineShop.dao.connection;

import by.my.onlineShop.exeptions.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory { // создает соединение с БД
    private static final Logger logger = LogManager.getLogger();
    private static final String DB_URL = "db.url";
    private static final String DB_USER = "db.user";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_DRIVER = "db.driver";

    static ProxyConnection createConnection(Properties dbProperties) throws ConnectionException {
        // создание соединения
        ProxyConnection proxyConnection = null;
        try {
            // возвращает строку по ключу из connection properties
            String dbUrl = dbProperties.getProperty(DB_URL);
            String dbUser = dbProperties.getProperty(DB_USER);
            String dbPassword = dbProperties.getProperty(DB_PASSWORD);
            Class.forName(dbProperties.getProperty(DB_DRIVER));
            System.out.println("Здесь: " +dbUrl+" " + dbUser+" " +dbPassword);
            // создание соединения с БД
            proxyConnection = new ProxyConnection(DriverManager.getConnection(dbUrl, dbUser, dbPassword));
        } catch (SQLException e) {
            logger.error("Unable to connect to DB!", e);
            throw new ConnectionException(e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            logger.error("MySQL JDBC driver not found!", e);
            throw new ConnectionException(e.getMessage(), e);
        }
        return proxyConnection;
    }
}