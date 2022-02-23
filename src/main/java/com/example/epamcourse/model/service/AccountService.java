package com.example.epamcourse.model.service;

import com.example.epamcourse.model.entity.Account;
import com.example.epamcourse.model.exception.ServiceException;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * interface AccountService
 *
 * @author M.Shubelko
 */
public interface AccountService {

    /**
     * The authentication
     *
     * @param login the login
     * @param password the password
     * @return Optional<Account>
     * @throws ServiceException the service exception
     */
    Optional<Account> authenticate(String login, String password) throws ServiceException;

    /**
     * The checking of existing of account's login
     *
     * @param login the login
     * @return true if account's login is existed
     * @throws ServiceException the service exception
     */
    boolean isAccountLoginExist(String login) throws ServiceException;

    /**
     * The checking of ip presentation
     *
     * @param ip the ip
     * @return true if ip is present
     * @throws ServiceException the service exception
     */
    boolean isIpPresent(String ip) throws ServiceException;

    /**
     * The loading of image
     *
     * @param accountLogin the account login
     * @return login the login
     * @throws ServiceException the service exception
     */
    String loadImage(String accountLogin) throws ServiceException;

    /**
     * The adding of account
     *
     * @param login the login
     * @param password the password
     * @param email the email
     * @param confirmingPassword the confirming password
     * @param ip the ip
     * @return true if account is added
     * @throws ServiceException the service exception
     */
    boolean addAccount(String login, String password, String email, String confirmingPassword, String ip) throws ServiceException;

    /**
     * The validation of account's data
     *
     * @param login the login
     * @param password the password
     * @param confirmingPassword the confirming password
     * @param email the email
     * @return true if email is valid
     * @throws ServiceException the service exception
     */
    boolean validateRegistrationData(String login, String password, String confirmingPassword, String email) throws ServiceException;


    /**
     * The updating of account
     *
     * @param account the account
     * @throws ServiceException the service exception
     */
    void updateAccount(Account account) throws ServiceException;


    /**
     * The adding of administrator
     *
     * @param login the login
     * @param password the password
     * @param email the email
     * @param confirmingPassword the confirming password
     * @return true if administrator is added
     * @throws ServiceException the service exception
     */
    boolean addAdminAccount(String login, String password, String email, String confirmingPassword) throws ServiceException;

    /**
     * The getting of account's status by login
     *
     * @param login the login
     * @return the status
     * @throws ServiceException the service exception
     */
    String getAccountStatusByLogin(String login) throws ServiceException;

    /**
     * The finding of account by id
     *
     * @param accountId the account id
     * @return the accountOptional
     * @throws ServiceException the service exception
     */
    Optional<Account> findAccountById(Long accountId) throws ServiceException;

    /**
     * The getting of account's id by login
     *
     * @param login the login
     * @return the account id
     * @throws ServiceException the service exception
     */
    Long getAccountIdByLogin(String login) throws ServiceException;

    /**
     * The uploading of image
     *
     * @param content the content
     * @param fileName the file name
     * @param login the login
     * @throws ServiceException the service exception
     */
    void uploadImage(InputStream content, String fileName, String login) throws ServiceException;

    /**
     * The getting of account role by login
     *
     * @param login the login
     * @return the role
     * @throws ServiceException the service exception
     */
    Account.Role getAccountRoleByLogin(String login) throws ServiceException;

    /**
     * The checking of personal information existing
     *
     * @param login the login
     * @return true if personal information is existed
     * @throws ServiceException the service exception
     */
    boolean isPersonalInformationExist(String login) throws ServiceException;

    /**
     * The finding of all accounts
     *
     * @return the accounts
     * @throws ServiceException the service exception
     */
    List<Account> findAllAccounts() throws ServiceException;

    /**
     * The finding of accounts for page
     *
     * @param page the page
     * @return the accounts
     * @throws ServiceException the service exception
     */
    List<Account> findAccountsInPage(int page) throws ServiceException;

    /**
     * The deleting of accounts
     *
     * @param accountId the account id
     * @throws ServiceException the service exception
     */
    void deleteAccount(Long accountId) throws ServiceException;

}
