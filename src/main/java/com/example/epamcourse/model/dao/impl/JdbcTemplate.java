package com.example.epamcourse.model.dao.impl;

import com.example.epamcourse.model.dao.mapper.ResultSetHandler;
import com.example.epamcourse.model.entity.BaseEntity;
import com.example.epamcourse.model.exception.DaoException;
import com.example.epamcourse.model.exception.TransactionException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;


/**
 * class JdbcTemplate<T extends BaseEntity>
 *
 * @author M.Shubelko
 */
public class JdbcTemplate<T extends BaseEntity> {

    /**
     * The logger
     */
    private static final Logger logger = LogManager.getLogger();

    /**
     * The constant GENERATED_KEY
     **/
    private static final String GENERATED_KEY = "GENERATED_KEY";

    /**
     * The resultSetHandler
     */
    private final ResultSetHandler<T> resultSetHandler;

    /**
     * The transactionManager
     */
    private final TransactionManager transactionManager;

    /**
     * The constructor
     *
     * @param resultSetHandler rhe result set handler
     */
    public JdbcTemplate(ResultSetHandler<T> resultSetHandler) {
        this.resultSetHandler = resultSetHandler;
        this.transactionManager = TransactionManager.getInstance();
    }

    /**
     * Execute select query
     *
     * @param sqlQuery   the query
     * @param parameters the parameters
     * @return list the list of data
     * @throws TransactionException the TransactionException
     */
    public List<T> executeSelectQuery(String sqlQuery, Object... parameters) throws TransactionException {
        List<T> list = new ArrayList<>();
        Connection connection = transactionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            setParametersInPreparedStatement(statement, parameters);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                T entity = resultSetHandler.resultToObject(resultSet);
                list.add(entity);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error when finding all elements.");
        }

        return list;
    }

    /**
     * Execute select query for object
     *
     * @param sqlQuery   the query
     * @param parameters the parameters
     * @return object the object
     * @throws TransactionException the TransactionException
     */
    public Optional<T> executeSelectQueryForObject(String sqlQuery, Object... parameters) throws TransactionException {
        Optional<T> result = Optional.empty();
        List<T> list;
        list = executeSelectQuery(sqlQuery, parameters);

        if (!list.isEmpty()) {
            result = Optional.of(list.get(0));
        } else {
            logger.log(Level.WARN, "Element isn't found");
        }
        return result;
    }

    /**
     * Execute select count
     *
     * @param sql        the query
     * @param parameters the parameters
     * @return object the object
     * @throws TransactionException the TransactionException
     */
    public int executeSelectCountQuery(String sql,
                                       Object... parameters) throws TransactionException {
        Connection connection = transactionManager.getConnection();
        int count = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            setParametersInPreparedStatement(statement, parameters);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            count = resultSet.getInt(1);
            resultSet.close();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database error. Elements wasn't extracted.");
        }
        return count;
    }

    /**
     * Execute select some fields
     *
     * @param sql         the query
     * @param columnNames the column names
     * @param parameters  the parameters
     * @return object the object
     * @throws TransactionException the TransactionException
     */
    public List<Map<String, Object>> executeSelectSomeFields(String sql,
                                                             Set<String> columnNames,
                                                             Object... parameters) throws TransactionException {
        List<Map<String, Object>> extractedValues = new ArrayList<>();

        Connection connection = transactionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            setParametersInPreparedStatement(statement, parameters);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> rowValues = new HashMap<>();
                for (String name : columnNames) {
                    rowValues.put(name, resultSet.getObject(name));
                }
                extractedValues.add(rowValues);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database error. Elements wasn't extracted.");
        }
        return extractedValues;
    }

    /**
     * Execute update/delete fields
     *
     * @param sqlQuery   the query
     * @param parameters the parameters
     * @return true the true
     * @throws TransactionException the TransactionException
     */
    public boolean executeUpdateDeleteFields(String sqlQuery, Object... parameters) throws TransactionException {
        Connection connection = transactionManager.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            setParametersInPreparedStatement(statement, parameters);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Element isn't updated or deleted.");
        }

        return true;
    }

    /**
     * Execute insert query
     *
     * @param sqlQuery   the query
     * @param parameters the parameters
     * @return object the object
     * @throws TransactionException the TransactionException
     */
    public long executeInsertQuery(String sqlQuery, Object... parameters) throws TransactionException {
        long generatedId = 0;
        Connection connection = transactionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
            setParametersInPreparedStatement(statement, parameters);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                generatedId = resultSet.getLong(GENERATED_KEY);
                logger.log(Level.DEBUG, "Generated id is {}", generatedId);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error when insert element.");
        }

        return generatedId;
    }

    /**
     * Set parameters in prepared statement
     *
     * @param statement  the prepared statement
     * @param parameters the parameters
     * @throws TransactionException the TransactionException
     */
    private void setParametersInPreparedStatement(PreparedStatement statement, Object... parameters) throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            statement.setObject(i + 1, parameters[i]);
        }
    }
}