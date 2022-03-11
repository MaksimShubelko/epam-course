package com.example.epamcourse.model.dao;

import com.example.epamcourse.model.entity.Account;
import com.example.epamcourse.model.exception.DaoException;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * interface AccountDao extends BaseDao<Account>
 *
 * @author M.Shubelko
 */
public interface AccountDao extends BaseDao<Account> {

    /**
     * Find an account by login
     *
     * @param login the login
     * @return T extends BaseEntity
     * @throws DaoException the DaoException
     */
    Optional<Account> findAccountByLogin(String login) throws DaoException;

    /**
     * Get count of accounts by login
     *
     * @param login the login
     * @return T extends BaseEntity
     * @throws DaoException the DaoException
     */
    int getCountOfAccountsByLogin(String login) throws DaoException;

    /**
     * Find an account by ip
     *
     * @param ip the ip
     * @return T extends BaseEntity
     * @throws DaoException the DaoException
     */
    Optional<Account> findAccountByIp(String ip) throws DaoException;


    /**
     * Find an account by login and password
     *
     * @param login the login
     * @param password the password
     * @return T extends BaseEntity
     * @throws DaoException the DaoException
     */
    Optional<Account> findAccountByLoginAndPassword(String login, String password) throws DaoException;


    /**
     * Find accounts to page
     *
     * @param accountsSkip the account to skip
     * @param accountsGet the accounts to get
     * @return T extends BaseEntity
     * @throws DaoException the DaoException
     */
    List<Account> findAccountsPage(int accountsSkip, int accountsGet) throws DaoException;


    /**
     * Get account status by login
     *
     * @param login the login
     * @return T extends BaseEntity
     * @throws DaoException the DaoException
     */
    String getAccountStatusByLogin(String login) throws DaoException;

    /**
     * Add an account
     *
     * @param account the account
     * @param password the hashed password
     * @return T extends BaseEntity
     * @throws DaoException the DaoException
     */
    Long add(Account account, String password) throws DaoException;

    /**
     * The getting of count tof accounts
     *
     * @return the count of accounts
     * @throws DaoException the DaoException
     */
    int getCountOfAccounts() throws DaoException;
}
