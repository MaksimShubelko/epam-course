package com.example.epamcourse.model.dao.impl;

import com.example.epamcourse.model.exception.DaoException;
import com.example.epamcourse.model.exception.TransactionException;
import com.example.epamcourse.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * class TransactionManager
 *
 * @author M.Shubelko
 */
public class TransactionManager {

    /**
     * The logger
     */
    private static final Logger logger = LogManager.getLogger();

    /**
     * The instance
     */
    private static final TransactionManager instance = new TransactionManager();

    /**
     * The threadConnection
     */
    private ThreadLocal<Connection> threadConnection = new ThreadLocal<>();

    /**
     * The private constructor
     */
    private TransactionManager() {
    }

    /**
     * Get instance
     *
     * @return instance
     */
    public static TransactionManager getInstance() {
        return instance;
    }

    /**
     * Init transaction
     *
     * @throws TransactionException the TransactionException
     */
    public void initTransaction() throws TransactionException {
        if (threadConnection.get() == null) {
            try {
                Connection connection = ConnectionPool.getInstance().getConnection();
                if (connection == null) {
                    throw new TransactionException("This thread was interrupted and connection is null");
                }
                threadConnection.set(connection);
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Failed to change disable autocommit. A database access error occurs", e);
                throw new TransactionException("Failed to change disable autocommit. A database access error occurs", e);
            }
        }
    }

    /**
     * Get connection
     *
     * @return connection the connection
     * @throws TransactionException the TransactionException
     */
    public Connection getConnection() throws TransactionException {
        Connection connection = threadConnection.get();
        if (connection != null) {
            return connection;
        } else {
            throw new TransactionException("Failed to get connection. The transaction not started.");
        }
    }


    /**
     * End transaction
     */
    public void endTransaction() {
        Connection connection = threadConnection.get();
        if (connection != null) {
            try {
                threadConnection.remove();
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Failed to set commit true. A database access error occurs: ", e);
            } finally {
                close(connection);
            }
        }
    }

    /**
     * Commit connection
     *
     * @throws TransactionException the TransactionException
     */
    public void commit() throws TransactionException {
        Connection connection = threadConnection.get();
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Failed to commit. A database access error occurs: ", e);
                throw new TransactionException("Failed to commit. A database access error occurs: ", e);
            }
        }
    }

    /**
     * Rollback connection
     */
    public void rollback() {
        Connection connection = threadConnection.get();
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Failed to rollback. A database access error occurs: ", e);
            }
        }
    }

    /**
     * Close connection
     */
    private void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Connection didn't close. ", e);
        }
    }
}
