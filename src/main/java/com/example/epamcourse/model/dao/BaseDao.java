package com.example.epamcourse.model.dao;

import com.example.epamcourse.model.entity.BaseEntity;
import com.example.epamcourse.model.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public interface BaseDao <T extends BaseEntity> {
    Logger LOGGER = LogManager.getLogger();

    List<T> findAll() throws DaoException;

    Optional<T> findEntityById(Long id) throws DaoException;

    boolean delete(Long id) throws DaoException;

    Long add(T t) throws DaoException;

    boolean update(T t, String hashPassword) throws DaoException;

    default void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Statement didn't close. ", e);
        }
    }

    default void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Connection didn't close. ", e);
        }
    }
}
