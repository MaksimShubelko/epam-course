package com.example.epamcourse.model.dao.impl;

import com.example.epamcourse.model.dao.AccountDao;
import com.example.epamcourse.model.dao.mapper.impl.AccountResultSetHandler;
import com.example.epamcourse.model.entity.Account;
import com.example.epamcourse.model.exception.DaoException;
import com.example.epamcourse.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static com.example.epamcourse.model.dao.Table.ROLE;

public class AccountDaoImpl implements AccountDao {
    private static final Logger logger = LogManager.getLogger();
    private static AccountDao instance = new AccountDaoImpl();

    private JdbcTemplates<Account> jdbcTemplate;

    private static final String FIND_ACCOUNT_BY_ID = """
            SELECT account_id, login, password, role, email FROM accounts
            WHERE account_id = ?
            """;
    private static final String FIND_ACCOUNT_BY_LOGIN = """
            SELECT account_id, login, password, role, email FROM accounts
            WHERE login = ?
            """;

    private static final String FIND_ACCOUNT_BY_LOGIN_AND_PASSWORD = """
            SELECT account_id, login, password, role, email
            FROM accounts
            WHERE login = ? and password = ?
            """;

    private static final String GET_ACCOUNT_ROLE_BY_ID = """
            SELECT role 
            FROM accounts
            WERE account_id = ?        
            """;

    private static final String FIND_ALL_ACCOUNTS = """
            SELECT account_id, login, password, role, email
            FROM accounts       
            """;

    private static final String DELETE_ACCOUNT = """
              DELETE FROM accounts 
              WHERE account_id = ?
            """;

    private static final String ADD_ACCOUNT = """
            INSERT INTO accounts (login, password, email)
            VALUE (?, ?, ?)
            """;

    private static final String UPDATE_PASSWORD = """
            UPDATE accounts SET password = ?
            WHERE account_id = ?
            """;

    private static final String UPDATE_ROLE = """
            UPDATE accounts SET role = ?
            WHERE account_id = ?
            """;

    private AccountDaoImpl() {
        this.jdbcTemplate = new JdbcTemplates<>(new AccountResultSetHandler());
    }

    @Override
    public Optional<Account> findEntityById(Long id) throws DaoException {
        Optional<Account> account = jdbcTemplate.executeSelectQueryForObject(FIND_ACCOUNT_BY_ID, id);

        return account;
    }

    @Override
    public Optional<Account> findAccountByLogin(String login) throws DaoException {
        Optional<Account> account = jdbcTemplate.executeSelectQueryForObject(FIND_ACCOUNT_BY_LOGIN, login);

        return account;
    }

    @Override
    public Optional<Account> findAccountByLoginAndPassword(String login, String password) throws DaoException {
        Optional<Account> account = jdbcTemplate.executeSelectQueryForObject(FIND_ACCOUNT_BY_LOGIN_AND_PASSWORD, login, password);

        return account;
    }

    @Override
    public List<Map<String, Object>> getAccountRoleById(Long accountId) throws DaoException {
        List<Map<String, Object>> role = jdbcTemplate.executeSelectSomeFields(GET_ACCOUNT_ROLE_BY_ID, Set.of(ROLE), accountId);

        return role;
    }

    @Override
    public boolean updateRole(Account account, int role) throws DaoException {
        jdbcTemplate.executeInsertQuery(UPDATE_PASSWORD,
                role,
                account.getAccountId());

        return true;
    }

    @Override
    public List<Account> findAll() throws DaoException {
        List<Account> accounts = jdbcTemplate.executeSelectQuery(FIND_ALL_ACCOUNTS);

        return accounts;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ACCOUNT)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error when deleting account with Id {}. {}", id, e.getMessage());
            throw new DaoException("Error when account with Id " + id, e);
        }

        return true;
    }

    @Override
    public Long add(Account account) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Long add(Account account, String password) throws DaoException {
        long accountId = jdbcTemplate.executeInsertQuery(ADD_ACCOUNT,
                account.getEmail(),
                account.getLogin(),
                password);

        return accountId;
    }

    @Override
    public boolean update(Account account, String hashPassword) throws DaoException {
        jdbcTemplate.executeInsertQuery(UPDATE_PASSWORD,
                hashPassword,
                account.getAccountId());

        return true;
    }



    public static AccountDao getInstance() {
        return instance;
    }
}
