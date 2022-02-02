package com.example.epamcourse.model.dao.impl;

import com.example.epamcourse.model.dao.AdministratorDao;
import com.example.epamcourse.model.dao.mapper.impl.AdministratorResultSetHandler;
import com.example.epamcourse.model.entity.Administrator;
import com.example.epamcourse.model.exception.DaoException;
import com.example.epamcourse.model.exception.TransactionException;
import com.example.epamcourse.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AdministratorDaoImpl implements AdministratorDao {
    private static final Logger logger = LogManager.getLogger();
    private static AdministratorDao instance = new AdministratorDaoImpl();

    private JdbcTemplate<Administrator> jdbcTemplate;

    private static final String FIND_ALL_ADMINISTRATORS = """
            SELECT administrator_id, firstname, lastname, surname, account_id 
            FROM administrators
            """;

    private static final String FIND_ADMINISTRATOR_BY_ID = """
            SELECT administrator_id, firstname, lastname, surname, account_id 
            WHERE administrator_id = ?
            """;

    private static final String DELETE_ADMINISTRATOR = """
              DELETE FROM administrators 
              WHERE administrator_id = ?
            """;

    private static final String ADD_ADMINISTRATOR = """
            INSERT INTO administrators (firstname, lastname, surname, account_id)
            VALUE (?, ?, ?, ?)
            """;

    private static final String FIND_ADMINISTRATOR_BY_ACCOUNT_ID = """
            SELECT administrator_id, firstname, lastname, surname, account_id
            FROM administrators 
            WHERE account_id = ?
            """;

    private static final String UPDATE_ADMINISTRATOR = """
            UPDATE administrators SET firstname = ?, lastname = ?, surname = ?
            WHERE administrator_id = ?
            """;


    private AdministratorDaoImpl() {
        this.jdbcTemplate = new JdbcTemplate<>(new AdministratorResultSetHandler());
    }

    @Override
    public List<Administrator> findAll() throws DaoException {
        List<Administrator> administrators = null;
        try {
            administrators = jdbcTemplate.executeSelectQuery(FIND_ALL_ADMINISTRATORS);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding all administrators {}", e);
            throw new DaoException("Error when finding all administrators", e);
        }

        return administrators;
    }

    @Override
    public Optional<Administrator> findEntityById(Long id) throws DaoException {
        Optional<Administrator> administrator = null;
        try {
            administrator = jdbcTemplate.executeSelectQueryForObject(FIND_ADMINISTRATOR_BY_ID, id);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding administrator by id = {}  {}", id, e);
            throw new DaoException("Error when finding administrator by id = " + id, e);
        }

        return administrator;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ADMINISTRATOR)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error when deleting administrator with Id {}. {}", id, e);
            throw new DaoException("Error when deleting administrator with Id " + id, e);
        }

        return true;
    }

    @Override
    public Long add(Administrator administrator) throws DaoException {
        long administratorId = 0;
        try {
            administratorId = jdbcTemplate.executeInsertQuery(ADD_ADMINISTRATOR,
                    administrator.getFirstname(),
                    administrator.getLastname(),
                    administrator.getSurname(),
                    administrator.getAccountId());
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when adding administrator.", e);
            throw new DaoException("Error when adding administrator", e);
        }

        return administratorId;
    }

    public boolean update(Administrator administrator) throws DaoException {
        try {
            jdbcTemplate.executeInsertQuery(UPDATE_ADMINISTRATOR,
                    administrator.getAdministratorId(),
                    administrator.getFirstname(),
                    administrator.getLastname(),
                    administrator.getSurname(),
                    administrator.getAccountId());
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when up administrator.", e);
            throw new DaoException("Error when adding administrator", e);
        }

        return true;

    }

    public static AdministratorDao getInstance() {
        return instance;
    }

    @Override
    public Optional<Administrator> getAdministratorByAccountId(Long id) throws DaoException {
        Optional<Administrator> administrator = null;
        try {
            administrator = jdbcTemplate.executeSelectQueryForObject(FIND_ADMINISTRATOR_BY_ACCOUNT_ID, id);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding administrator by account id = {}  {}", id, e);
            throw new DaoException("Error when finding administrator by account id = " + id, e);
        }

        return administrator;
    }
}
