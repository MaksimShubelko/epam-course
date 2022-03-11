package com.example.epamcourse.model.dao.impl;

import com.example.epamcourse.model.dao.AccountDao;
import com.example.epamcourse.model.dao.mapper.impl.AccountResultSetHandler;
import com.example.epamcourse.model.entity.Account;
import com.example.epamcourse.model.exception.DaoException;
import com.example.epamcourse.model.exception.TransactionException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static com.example.epamcourse.model.dao.TableColumn.STATUS;

/**
 * class AccountDaoImpl
 *
 * @author M.Shubelko
 */
public class AccountDaoImpl implements AccountDao {

    /**
     * The logger
     */
    private static final Logger logger = LogManager.getLogger();

    /**
     * The instance
     */
    private static final AccountDao instance = new AccountDaoImpl();

    /**
     * The jdbcTemplate
     */
    private final JdbcTemplate<Account> jdbcTemplate;

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

    private static final String SELECT_COUNT_ACCOUNTS = """
            SELECT COUNT(account_id)
            FROM accounts
            """;

    private static final String SELECT_COUNT_ACCOUNTS_BY_LOGIN = """
            SELECT COUNT(account_id)
            FROM accounts
            WHERE login = ?
            """;

    /**
     * The private constructor
     */
    private AccountDaoImpl() {
        this.jdbcTemplate = new JdbcTemplate<>(new AccountResultSetHandler());
    }

    /**
     * Find by id
     *
     * @param id the id
     * @return accountOptional the account optional
     * @throws DaoException the DaoException
     */
    @Override
    public Optional<Account> findEntityById(Long id) throws DaoException {
        Optional<Account> accountOptional;
        try {
            accountOptional = jdbcTemplate.executeSelectQueryForObject(FIND_ACCOUNT_BY_ID, id);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding account with id {}.", id);
            throw new DaoException("Error when finding account with id ".concat(id.toString()), e);
        }

        return accountOptional;
    }

    /**
     * Find account by login
     *
     * @param login the login
     * @return accountOptional the account optional
     * @throws DaoException the DaoException
     */
    @Override
    public Optional<Account> findAccountByLogin(String login) throws DaoException {
        Optional<Account> accountOptional;
        try {
            accountOptional = jdbcTemplate.executeSelectQueryForObject(FIND_ACCOUNT_BY_LOGIN, login);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding account with login {}.", login);
            throw new DaoException("Error when finding account with login " + login, e);
        }

        return accountOptional;
    }

    /**
     * Get count of accounts by login
     *
     * @param login the login
     * @return T extends BaseEntity
     * @throws DaoException the DaoException
     */
    @Override
    public int getCountOfAccountsByLogin(String login) throws DaoException {
        int countOfAccountsWithLogin;
        try {
            countOfAccountsWithLogin = jdbcTemplate.executeSelectCountQuery(SELECT_COUNT_ACCOUNTS_BY_LOGIN, login);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding account with login {}.", login);
            throw new DaoException("Error when finding account with login " + login, e);
        }

        return countOfAccountsWithLogin;
    }

    /**
     * Find account by ip
     *
     * @param ip the ip
     * @return accountOptional the account optional
     * @throws DaoException the DaoException
     */
    @Override
    public Optional<Account> findAccountByIp(String ip) throws DaoException {
        Optional<Account> accountOptional;
        try {
            accountOptional = jdbcTemplate.executeSelectQueryForObject(FIND_ACCOUNT_BY_IP, ip);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding account with ip {}. {}", ip, e);
            throw new DaoException("Error when finding account with ip " + ip, e);
        }

        return accountOptional;
    }

    /**
     * Find account by login and password
     *
     * @param login the login
     * @return accountOptional the account optional
     * @throws DaoException the DaoException
     */
    @Override
    public Optional<Account> findAccountByLoginAndPassword(String login, String password) throws DaoException {
        Optional<Account> accountOptional;
        try {
            accountOptional = jdbcTemplate
                    .executeSelectQueryForObject(FIND_ACCOUNT_BY_LOGIN_AND_PASSWORD, login, password);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding account with login {} and password {}.",
                    login, password);
            throw new DaoException("Error when finding account with login and password: " + login + " " + password, e);
        }

        return accountOptional;
    }

    /**
     * Find accounts to page
     *
     * @param accountsSkip the accounts skip
     * @param accountsGet the accounts get
     * @return accounts the accounts
     * @throws DaoException the DaoException
     */
    @Override
    public List<Account> findAccountsPage(int accountsSkip, int accountsGet) throws DaoException {
        List<Account> accounts;
        try {
            accounts = jdbcTemplate.executeSelectQuery(FIND_ACCOUNTS_FOR_PAGE, accountsSkip, accountsGet);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding accounts", e);
            throw new DaoException("Error when finding all accounts", e);
        }
        return accounts;
    }

    /**
     * Get account's status by login
     *
     * @param login the login
     * @return fields the fields
     * @throws DaoException the DaoException
     */
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

    /**
     * Get account status by login
     *
     * @return accounts the accounts
     * @throws DaoException the DaoException
     */
    @Override
    public List<Account> findAll() throws DaoException {
        List<Account> accounts;
        try {
            accounts = jdbcTemplate.executeSelectQuery(FIND_ALL_ACCOUNTS);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding all accounts", e);
            throw new DaoException("Error when finding all accounts", e);
        }

        return accounts;
    }

    /**
     * Delete an account
     *
     * @param id the id
     * @return true
     * @throws DaoException the DaoException
     */
    @Override
    public boolean delete(Long id) throws DaoException {
        try {
            jdbcTemplate.executeUpdateDeleteFields(DELETE_ACCOUNT, id);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when deleting account.", e);
            throw new DaoException("Error when deleting account", e);
        }

        return true;
    }

    @Override
    public Long add(Account account) {
        throw new UnsupportedOperationException();
    }

    /**
     * Update an account
     *
     * @param account the account
     * @return true
     * @throws DaoException the DaoException
     */
    @Override
    public boolean update(Account account) throws DaoException {
        try {
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

    /**
     * Add an account
     *
     * @param account the account
     * @param password the password
     * @return accountId the accountId
     * @throws DaoException the DaoException
     */
    @Override
    public Long add(Account account, String password) throws DaoException {
        long accountId;
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
    public int getCountOfAccounts() throws DaoException {
        int count;
        try {
            count = jdbcTemplate.executeSelectCountQuery(SELECT_COUNT_ACCOUNTS);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding faculties", e);
            throw new DaoException("Error when finding all faculties", e);
        }
        return count;
    }


    public static AccountDao getInstance() {
        return instance;
    }
}
