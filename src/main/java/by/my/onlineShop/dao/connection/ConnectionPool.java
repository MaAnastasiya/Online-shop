package by.my.onlineShop.dao.connection;

import by.my.onlineShop.exeptions.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool { // соединение с базой данных
    private static final Logger logger = LogManager.getLogger();
    private static final String POOL_SIZE = "db.default-pool-size";
    private static final String DB_CONNECTION_PATH = "connection/dbConnection.properties";

    // блокирующие очереди
    public BlockingQueue <ProxyConnection> availableConnections; // свободные соединения
    private BlockingQueue <ProxyConnection> usedConnections; // используемые соединения

    public static ConnectionPool getInstance() {
        return Holder.INSTANCE;
    }

    private ConnectionPool() {}



    public void initialize() throws ConnectionException {
        try {
            Properties dbProperties = new Properties();
            dbProperties.load(ConnectionFactory.class.getClassLoader().getResourceAsStream(DB_CONNECTION_PATH));
            // максимальное количество возможных соединений
            int poolSize = Integer.parseInt(dbProperties.getProperty(POOL_SIZE));
            availableConnections = new ArrayBlockingQueue<>(poolSize);
            usedConnections = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                // соединяем соединения в БД и кладем в очередь
                ProxyConnection connection = ConnectionFactory.createConnection(dbProperties);
                availableConnections.add(connection);
            }
        } catch (IOException e) {
            logger.error("Unable to load DB properties!", e);
            throw new ConnectionException(e.getMessage(), e);
        }

        logger.info("Connection pool initialized");
    }
    // освободить соединение
    public void releaseConnection(ProxyConnection proxyConnection) throws ConnectionException {
        if (proxyConnection != null) {
            usedConnections.remove(proxyConnection);
            try {
                availableConnections.put(proxyConnection);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Unable to release connection!", e);
                throw new ConnectionException(e.getMessage(), e);
            }
        }
    }

    // берем новое соединение, добавляем новое соединение в используемые
    public ProxyConnection getConnection() throws ConnectionException {
        ProxyConnection connection;
        try {
            connection = availableConnections.take();
            usedConnections.put(connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Unable to get connection!", e);
            throw new ConnectionException(e.getMessage(), e);
        }
        return connection;
    }

    // уничтожаем все соединения
    public void destroy() throws ConnectionException {
        try {
            for (ProxyConnection connection : availableConnections) {
                connection.closeConnection();
            }
            for (ProxyConnection connection : usedConnections) {
                connection.closeConnection();
            }
        } catch (SQLException e) {
            logger.error("Unable to close all connections!", e);
            throw new ConnectionException(e.getMessage(), e);
        }

        logger.info("Connection pool closed");
    }

    private static class Holder {
        static final ConnectionPool INSTANCE = new ConnectionPool();
    }
}
