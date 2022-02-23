package com.example.epamcourse.model.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import static com.example.epamcourse.model.pool.DatabasePropertyReader.CONNECTION_POOL_SIZE;

/**
 * class ConnectionPool
 *
 * @author M.Shubelko
 */
public class ConnectionPool {

    /**
     * The logger
     */
    private static final Logger logger = LogManager.getLogger();

    /**
     * The variable isCreated for monitoring the pool's state
     */
    private static final AtomicBoolean isCreated = new AtomicBoolean(false);

    /**
     * The instance
     */
    private static ConnectionPool instance;

    /**
     * The locker
     */
    private static final ReentrantLock locker = new ReentrantLock(true);

    /**
     * The queue of free connections
     */
    private final BlockingQueue<ProxyConnection> freeConnections;

    /**
     * The queue of busy connections
     */
    private final BlockingQueue<ProxyConnection> busyConnection;

    /**
     * The boolean destroyingPool is true, when pool is destroying
     */
    private boolean destroyingPool;

    /**
     * The private constructor uses for pool creation
     */
    private ConnectionPool() {
        freeConnections = new LinkedBlockingQueue<>(CONNECTION_POOL_SIZE);
        busyConnection = new LinkedBlockingQueue<>(CONNECTION_POOL_SIZE);
        Connection connection;

        for (int i = 0; i < CONNECTION_POOL_SIZE; i++) {
            try {
                connection = ConnectionFactory.createConnection();
                boolean isAdded = freeConnections.add(new ProxyConnection(connection));
                logger.log(Level.DEBUG, "Is connection added: {}", isAdded);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Connection wasn't created because database access error", e);
            }
        }

        if (freeConnections.isEmpty()) {
            logger.log(Level.FATAL, "No one connection was created");
            throw new RuntimeException("No one connection was created");
        } else {
            if (freeConnections.size() < CONNECTION_POOL_SIZE) {
                logger.log(Level.WARN, "Connection pool is not full");
                for (int i = freeConnections.size(); i < CONNECTION_POOL_SIZE; i++) {
                    try {
                        connection = ConnectionFactory.createConnection();
                        freeConnections.add(new ProxyConnection(connection));
                    } catch (SQLException e) {
                        logger.log(Level.FATAL, "Connection wasn't added.", e);
                        throw new RuntimeException("Connection pool can't be full", e);
                    }
                }
            }
        }

        logger.log(Level.INFO, "Connection is successfully created");
    }

    /**
     * The getting of instance
     *
     * @return ConnectionPool the connection pool
     */
    public static ConnectionPool getInstance() {
        if (!isCreated.get()) {
            locker.lock();
            try {
                if (!isCreated.get()) {
                    instance = new ConnectionPool();
                    isCreated.set(true);
                }
            } finally {
                locker.unlock();
            }
        }
        return instance;
    }

    /**
     * The getting of connection
     *
     * @return Connection the connection
     */
    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            busyConnection.put(connection);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Current thread was interrupted", e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    /**
     * The realisation of connection
     *
     * @return true when connection is realised
     */
    public boolean releaseConnection(Connection connection) {
        boolean result = false;
        if (connection instanceof ProxyConnection && !destroyingPool && busyConnection.remove(connection)) {
            try {
                freeConnections.put((ProxyConnection) connection);
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "Current thread was interrupted", e);
                Thread.currentThread().interrupt();
            }
            result = true;
        } else {
            logger.log(Level.WARN, "Given connection isn't ProxyConnection or it can't be removed");
        }
        return result;
    }

    /**
     * The destroying of pool
     *
     * @return true when pool is destroyed
     */
    public void destroyPool() {
        destroyingPool = true;
        for (int i = 0; i < CONNECTION_POOL_SIZE; i++) {
            try {
                freeConnections.take().close();
            } catch (InterruptedException e) {
                logger.log(Level.INFO, "Try to destroy connection into pool was failed", e);
                Thread.currentThread().interrupt();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Try to destroy connection into pool was failed", e);
            }
        }
        deregisterDriver();
    }

    /**
     * The closing of connection
     */
    private void closeConnection(BlockingQueue<ProxyConnection> connections) {
        while (!connections.isEmpty()) {
            try {
                connections.take().realClose();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Connection isn't close due to database access error.", e);
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "Connection isn't close due to current thread was interrupted", e);
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * The deregistration of driver
     */
    private void deregisterDriver() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Driver isn't deregistered due to database access error.", e);
            }
        });
    }

}
