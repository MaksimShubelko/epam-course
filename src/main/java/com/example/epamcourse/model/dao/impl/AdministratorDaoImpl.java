package com.example.epamcourse.model.dao.impl;

import com.example.epamcourse.model.dao.AdministratorDao;
import com.example.epamcourse.model.dao.mapper.impl.AdministratorResultSetHandler;
import com.example.epamcourse.model.entity.Administrator;
import com.example.epamcourse.model.exception.DaoException;
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

    private JdbcTemplates<Administrator> jdbcTemplate;

    private static final String FIND_ALL_ADMINISTRATORS = """
            SELECT administrator_id, firstname, lastname, surname, account_id 
            FROM administrators
            """;

    private static final String FIND_ADMINISTRATOR_BY_ID = """
            SELECT administrator_id, firstname, lastname, surname, account_id 
            WHERE account_id = ?
            """;

    private static final String DELETE_ADMINISTRATOR = """
              DELETE FROM administrators 
              WHERE administrator_id = ?
            """;

    private static final String ADD_ADMINISTRATOR = """
            INSERT INTO administrators (firstname, lastname, surname, account_id)
            VALUE (?, ?, ?, ?)
            """;

    private static final String UPDATE_ADMINISTRATOR = """
            UPDATE administrators SET firstname = ?, lastname = ?, surname = ?
            WHERE administrator_id = ?
            """;



    private AdministratorDaoImpl() {
        this.jdbcTemplate = new JdbcTemplates<>(new AdministratorResultSetHandler());
    }

    @Override
    public List<Administrator> findAll() throws DaoException {
        List<Administrator>  administrators = jdbcTemplate.executeSelectQuery(FIND_ALL_ADMINISTRATORS);

        return administrators;
    }

    @Override
    public Optional<Administrator> findEntityById(Long id) throws DaoException {
        Optional<Administrator> administrator = jdbcTemplate.executeSelectQueryForObject(FIND_ADMINISTRATOR_BY_ID, id);

        return administrator;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ADMINISTRATOR)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error when deleting administrator with Id {}. {}", id, e.getMessage());
            throw new DaoException("Error when deleting administrator with Id " + id, e);
        }

        return true;
    }

    @Override
    public Long add(Administrator administrator) throws DaoException {
        long administratorId = jdbcTemplate.executeInsertQuery(ADD_ADMINISTRATOR,
                administrator.getAdministratorId(),
                administrator.getFirstname(),
                administrator.getLastname(),
                administrator.getSurname(),
                administrator.getAccount_id());

        return administratorId;
    }

    @Override
    public boolean update(Administrator administrator, String hashPassword) throws DaoException {
        jdbcTemplate.executeInsertQuery(UPDATE_ADMINISTRATOR,
                administrator.getAdministratorId(),
                administrator.getFirstname(),
                administrator.getLastname(),
                administrator.getSurname(),
                administrator.getAccount_id());

        return true;

    }
}
