package com.example.epamcourse.model.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.example.epamcourse.model.pool.DatabasePropertyReader.*;

public class ConnectionFactory {
    private static final Logger logger = LogManager.getLogger();

    static {
        try {
            Class.forName(DATABASE_DRIVER);
        } catch (ClassNotFoundException e) {
            logger.log(Level.FATAL, "Driver class isn't found, it can't be registered", e);
            throw new RuntimeException("Driver class isn't found", e);
        }
    }

    private ConnectionFactory() {}

    static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
    }
}
