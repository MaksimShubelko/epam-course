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
     * @param sqlQuery the query
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
     * @param sqlQuery the query
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
     * Execute select query for list
     *
     * @param sql the query
     * @param columnNames the column names
     * @param parameters the parameters
     * @return object the object
     * @throws TransactionException the TransactionException
     */
    public List<Map<String, Object>> executeSelectForList(String sql, Set<String> columnNames, Object... parameters) throws TransactionException { // todo
        Connection connection = transactionManager.getConnection();
        List<Map<String, Object>> extractedValues = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            setParametersInPreparedStatement(statement, parameters);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Map<String, Object> rowValues = new HashMap<>();
                T entity = resultSetHandler.resultToObject(resultSet);
                String key = entity.getClass().getSimpleName().toLowerCase();
                rowValues.put(key, entity);
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
     * Execute select some fields
     *
     * @param sql the query
     * @param columnNames the column names
     * @param parameters the parameters
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
     * @param sqlQuery the query
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
     * @param sqlQuery the query
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
     * Execute fields calculation
     *
     * @param sqlQuery the query
     * @param parameters the parameters
     * @return object the object
     * @throws TransactionException the TransactionException
     */
    public Number selectFieldsCalculation(String sqlQuery, String columnName, Object... parameters) throws TransactionException {
        Number totalValue = null;

        Connection connection = transactionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            setParametersInPreparedStatement(statement, parameters);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                totalValue = (Number) resultSet.getObject(columnName);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error when finding value. {}", e.getMessage());
            throw new TransactionException("Error when finding value.", e);
        }

        return totalValue;
    }

    /**
     * Execute insert batch arguments
     *
     * @param sql the query
     * @param batchArguments the batch arguments
     * @throws TransactionException the TransactionException
     */
    public void insertBatch(String sql, List<Object[]> batchArguments) throws TransactionException {
        Connection connection = transactionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Object[] sqlArgument : batchArguments) {
                setParametersInPreparedStatement(statement, sqlArgument);
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to insert batch data. A database access error occurs", e);
        }
    }

    /**
     * Set parameters in prepared statement
     *
     * @param statement the prepared statement
     * @param parameters the parameters
     * @throws TransactionException the TransactionException
     */
    private void setParametersInPreparedStatement(PreparedStatement statement, Object... parameters) throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            statement.setObject(i + 1, parameters[i]);
        }
    }
}