package com.example.epamcourse.model.dao.impl;

import com.example.epamcourse.model.dao.Table;
import com.example.epamcourse.model.dao.mapper.ResultSetHandler;
import com.example.epamcourse.model.entity.BaseEntity;
import com.example.epamcourse.model.exception.DaoException;
import com.example.epamcourse.model.exception.TransactionException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;

public class JdbcTemplate<T extends BaseEntity> {
    private static Logger logger = LogManager.getLogger();
    private static final String GENERATED_KEY = "GENERATED_KEY";
    private ResultSetHandler<T> resultSetHandler;
    private TransactionManager transactionManager;

    public JdbcTemplate(ResultSetHandler<T> resultSetHandler) {
        this.resultSetHandler = resultSetHandler;
        this.transactionManager = TransactionManager.getInstance();
    }

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

    public Optional<T> executeSelectQueryForObject(String sqlQuery, Object... parameters) throws TransactionException {
        Optional<T> result = Optional.empty();
        List<T> list = null;
        list = executeSelectQuery(sqlQuery, parameters);

        if (!list.isEmpty()) {
            result = Optional.of(list.get(0));
        } else {
            logger.log(Level.WARN, "Element isn't found");
        }
        return result;
    }

    public List<Map<String, Object>> executeSelectForList(String sql, Set<String> columnNames, Object... parameters)
            throws DaoException, TransactionException { // todo
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

    public List<Map<String, Object>> executeSelectSomeFields(String sql, Set<String> columnNames, Object... parameters)
            throws DaoException, TransactionException {
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

    public boolean executeUpdateDeleteFields(String sqlQuery, Object... parameters) throws
            DaoException, TransactionException {
        Connection connection = transactionManager.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            setParametersInPreparedStatement(statement, parameters);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Element isn't updated or deleted.");
        }

        return true;
    }

    public long executeInsertQuery(String sqlQuery, Object... parameters) throws
            DaoException, TransactionException {
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

    public long executeSelectCalculation(String sqlQuery, Object... parameters) throws TransactionException {
        long length = 0;

        Connection connection = transactionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            setParametersInPreparedStatement(statement, parameters);
            ResultSet resultSet = statement.executeQuery();
            length = resultSet.getFetchSize();

        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error when finding value.");
        }

        return length;
    }

    public void insertBatch(String sql, List<Object[]> batchArguments) throws
            DaoException, TransactionException {
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

    private void setParametersInPreparedStatement(PreparedStatement statement, Object... parameters) throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            statement.setObject(i + 1, parameters[i]);
        }
    }
}