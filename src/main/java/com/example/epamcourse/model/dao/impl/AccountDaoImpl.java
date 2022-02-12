package com.example.epamcourse.model.dao.impl;

import com.example.epamcourse.model.dao.AccountDao;
import com.example.epamcourse.model.dao.mapper.impl.AccountResultSetHandler;
import com.example.epamcourse.model.entity.Account;
import com.example.epamcourse.model.exception.DaoException;
import com.example.epamcourse.model.exception.TransactionException;
import com.example.epamcourse.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

import static com.example.epamcourse.model.dao.TableColumn.ROLE;
import static com.example.epamcourse.model.dao.TableColumn.STATUS;

public class AccountDaoImpl implements AccountDao {
    private static final Logger logger = LogManager.getLogger();
    private static AccountDao instance = new AccountDaoImpl();

    private JdbcTemplate<Account> jdbcTemplate;

    private static final String FIND_ACCOUNT_STATUS_BY_LOGIN = """
            SELECT status
            FROM accounts
            WHERE login = ?
            """;

    public static final String FIND_ACCOUNT_BY_IP = """
            SELECT account_id, login, password, role, email, status, ip, image_path
            FROM accounts
            WHERE ip = ?
            """;

    private static final String UPDATE_ACCOUNT = """
            UPDATE accounts SET  
            status = ?, image_path = ?
            WHERE account_id = ?
            """;

    private static final String FIND_ACCOUNT_BY_ID = """
            SELECT account_id, login, password, role, email, status, ip, image_path 
            FROM accounts
            WHERE account_id = ?
            """;
    private static final String FIND_ACCOUNTS_FOR_PAGE = """
            SELECT account_id, login, password, role, email, status, ip, image_path 
            FROM accounts
            LIMIT ?, ?
            """;

    private static final String FIND_ACCOUNT_BY_LOGIN = """
            SELECT account_id, login, password, role, email, status, ip, image_path 
            FROM accounts
            WHERE login = ?
            """;

    private static final String FIND_ACCOUNT_BY_LOGIN_AND_PASSWORD = """
            SELECT account_id, login, password, role, email, status, ip, image_path
            FROM accounts
            WHERE login = ? and password = ?
            """;

    private static final String FIND_ACCOUNT_ROLE_BY_ID = """
            SELECT role 
            FROM accounts
            WHERE account_id = ?        
            """;

    private static final String FIND_ALL_ACCOUNTS = """
            SELECT account_id, login, password, role, email, status, ip, image_path
            FROM accounts       
            """;

    private static final String FIND_IMAGE_PATH_BY_LOGIN = """
            SELECT image_path
            FROM accounts 
            WHERE login = ?      
            """;

    private static final String DELETE_ACCOUNT = """
              DELETE FROM accounts 
              WHERE account_id = ?
            """;

    private static final String ADD_ACCOUNT = """
            INSERT INTO accounts (login, password, role, ip, email)
            VALUE (?, ?, ?, ?, ?)
            """;

    private static final String UPDATE_PASSWORD = """
            UPDATE accounts SET password = ?
            WHERE account_id = ?
            """;
    private static final String UPDATE_STATUS = """
            UPDATE accounts SET status = ?
            WHERE account_id = ?
            """;

    private static final String UPDATE_ROLE = """
            UPDATE accounts SET role = ?
            
            WHERE account_id = ?
            """;

    private static final String FIND_ACCOUNT_ID_BY_LOGIN = """
            SELECT account_id
            FROM accounts
            WHERE login = ? 
            """;

    private AccountDaoImpl() {
        this.jdbcTemplate = new JdbcTemplate<>(new AccountResultSetHandler());
    }

    @Override
    public Optional<Account> findEntityById(Long id) throws DaoException {
        Optional<Account> account = null;
        try {
            account = jdbcTemplate.executeSelectQueryForObject(FIND_ACCOUNT_BY_ID, id);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding account with id {}.", id);
            throw new DaoException("Error when finding account with id ".concat(id.toString()), e);
        }

        return account;
    }

    @Override
    public Optional<Account> findAccountByLogin(String login) throws DaoException {
        Optional<Account> account = null;
        try {
            account = jdbcTemplate.executeSelectQueryForObject(FIND_ACCOUNT_BY_LOGIN, login);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding account with login {}.", login);
            throw new DaoException("Error when finding account with login " + login, e);
        }

        return account;
    }

    @Override
    public Optional<Account> findAccountByIp(String ip) throws DaoException {
        Optional<Account> account;
        try {
            account = jdbcTemplate.executeSelectQueryForObject(FIND_ACCOUNT_BY_IP, ip);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding account with ip {}. {}", ip, e);
            throw new DaoException("Error when finding account with ip " + ip, e);
        }

        return account;
    }

    @Override
    public Optional<Account> findAccountByLoginAndPassword(String login, String password) throws DaoException {
        Optional<Account> account = null;
        try {
            account = jdbcTemplate
                    .executeSelectQueryForObject(FIND_ACCOUNT_BY_LOGIN_AND_PASSWORD, login, password);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding account with login {} and password {}.",
                    login, password);
            throw new DaoException("Error when finding account with login and password: " + login + " " + password, e);
        }

        return account;
    }

    @Override
    public List<Map<String, Object>> getAccountRoleById(Long accountId) throws DaoException {
        List<Map<String, Object>> role = null;
        try {
            role = jdbcTemplate.executeSelectSomeFields(FIND_ACCOUNT_ROLE_BY_ID, Set.of(ROLE), accountId);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when getting account id = {} role. {}", accountId, e);
            throw new DaoException("Error when getting account id = " + accountId + " role", e);
        }

        return role;
    }

    @Override
    public boolean updateRole(Account account, int role) throws DaoException {
        try {
            jdbcTemplate.executeInsertQuery(UPDATE_ROLE,
                    role,
                    account.getAccountId());
        } catch (TransactionException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public List<Account> findAccountsPage(int accountsSkip, int accountsGet) throws DaoException {
        List<Account> accounts = null;
        try {
            accounts = jdbcTemplate.executeSelectQuery(FIND_ACCOUNTS_FOR_PAGE, accountsSkip, accountsGet);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding accounts", e);
            throw new DaoException("Error when finding all accounts", e);
        }
        return accounts;
    }

    @Override
    public String getAccountStatusByLogin(String login) throws DaoException {
        List<Map<String, Object>> fields;
        try {
            fields = jdbcTemplate.executeSelectSomeFields(FIND_ACCOUNT_STATUS_BY_LOGIN, Set.of(STATUS), login);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding account status with login {}.", login, e);
            throw new DaoException("Error when finding account status with login " + login, e);
        }

        return fields.get(0).get(STATUS).toString();
    }

    @Override
    public List<Map<String, Object>> findImagePathByLogin(String login) throws DaoException {
        List<Map<String, Object>> imagePath;
        try {
            imagePath = jdbcTemplate.executeSelectSomeFields(FIND_IMAGE_PATH_BY_LOGIN, Set.of(login));
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when getting image path", e);
            throw new DaoException("Error when getting image path", e);
        }

        return imagePath;
    }

    @Override
    public List<Account> findAll() throws DaoException {
        List<Account> accounts = null;
        try {
            accounts = jdbcTemplate.executeSelectQuery(FIND_ALL_ACCOUNTS);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding all accounts", e);
            throw new DaoException("Error when finding all accounts", e);
        }

        return accounts;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ACCOUNT)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error when deleting account with Id {}. {}", id, e);
            throw new DaoException("Error when account with Id " + id, e);
        }

        return true;
    }

    @Override
    public Long add(Account account) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(Account account) throws DaoException {
        try {
            System.out.println(account);
            jdbcTemplate.executeUpdateDeleteFields(UPDATE_ACCOUNT,
                    account.getStatus().toString(),
                    account.getImagePath(),
                    account.getAccountId());
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when updating account.", e);
            throw new DaoException("Error when updating account", e);
        }

        return true;
    }

    @Override
    public Long add(Account account, String password) throws DaoException {
        long accountId = 0;
        try {
            accountId = jdbcTemplate.executeInsertQuery(ADD_ACCOUNT,
                    account.getLogin(),
                    password,
                    account.getRole().toString(),
                    account.getIp(),
                    account.getEmail());
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when adding account.", e);
            throw new DaoException("Error when adding account.", e);
        }

        return accountId;
    }

    @Override
    public boolean update(Account account, String hashPassword) throws DaoException {
        try {
            jdbcTemplate.executeInsertQuery(UPDATE_PASSWORD,
                    hashPassword,
                    account.getAccountId());
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when updating password.", e);
            throw new DaoException("Error when updating password", e);
        }

        return true;
    }



    public static AccountDao getInstance() {
        return instance;
    }
}
