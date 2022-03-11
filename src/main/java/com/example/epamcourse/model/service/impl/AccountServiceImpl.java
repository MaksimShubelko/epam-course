package com.example.epamcourse.model.service.impl;

import com.example.epamcourse.model.dao.AccountDao;
import com.example.epamcourse.model.dao.AdministratorDao;
import com.example.epamcourse.model.dao.ApplicantDao;
import com.example.epamcourse.model.dao.impl.AccountDaoImpl;
import com.example.epamcourse.model.dao.impl.AdministratorDaoImpl;
import com.example.epamcourse.model.dao.impl.ApplicantDaoImpl;
import com.example.epamcourse.model.dao.impl.TransactionManager;
import com.example.epamcourse.model.entity.Account;
import com.example.epamcourse.model.exception.DaoException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.exception.TransactionException;
import com.example.epamcourse.model.service.AccountService;
import com.example.epamcourse.model.service.MailingService;
import com.example.epamcourse.model.validator.AccountValidator;
import com.example.epamcourse.model.validator.impl.AccountValidatorImpl;
import com.example.epamcourse.util.EmailMessages;
import com.example.epamcourse.util.FilePropertyReader;
import com.example.epamcourse.util.HashGenerator;
import com.example.epamcourse.util.impl.FilePropertyReaderImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * class AccountServiceImpl
 *
 * @author M.Shubelko
 */
public class AccountServiceImpl implements AccountService {

    /**
     * The logger
     */
    private static final Logger logger = LogManager.getLogger();

    /**
     * The upload directory for images
     */
    private static final String UPLOAD_DIR = "C:\\images\\";

    /**
     * The string params for image
     */
    private static final String STRING_PARAMS = "data:image/jpeg;base64,";

    /**
     * The instance
     */
    private static final AccountService instance = new AccountServiceImpl();

    /**
     * The transaction manager
     */
    private final TransactionManager transactionManager = TransactionManager.getInstance();

    /**
     * The account dao
     */
    private final AccountDao accountDao = AccountDaoImpl.getInstance();

    /**
     * The applicant dao
     */
    private final ApplicantDao applicantDao = ApplicantDaoImpl.getInstance();

    /**
     * The administrator dao
     */
    private final AdministratorDao administratorDao = AdministratorDaoImpl.getInstance();

    /**
     * The private constructor
     */
    private AccountServiceImpl() {
    }

    /**
     * The getting of instance
     *
     * @return instance the instance
     */
    public static AccountService getInstance() {
        return instance;
    }

    /**
     * The authentication
     *
     * @param login    the login
     * @param password the password
     * @return accountOptional the account optional
     * @throws ServiceException the service exception
     */
    @Override
    public Optional<Account> authenticate(String login, String password) throws ServiceException {
        AccountValidator validator = AccountValidatorImpl.getInstance();
        Optional<Account> accountOptional = Optional.empty();

        if (validator.isLoginValid(login) && validator.isPasswordValid(password)) {
            String hashPassword = HashGenerator.hashPassword(password);
            try {
                transactionManager.initTransaction();
                accountOptional = accountDao.findAccountByLoginAndPassword(login, hashPassword);
                transactionManager.commit();
            } catch (DaoException | TransactionException e) {
                logger.log(Level.ERROR, "Error when authenticating account", e);
                throw new ServiceException("Error when authenticating account", e);
            } finally {
                transactionManager.endTransaction();
            }
        }

        return accountOptional;
    }

    /**
     * Checking the existing of account login
     *
     * @param login the login
     * @return the isAccount present
     * @throws ServiceException the service exception
     */
    @Override
    public boolean isAccountLoginExist(String login) throws ServiceException {
        int countOfAccount;
        try {
            transactionManager.initTransaction();
            countOfAccount = accountDao.getCountOfAccountsByLogin(login);
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when getting the count of accounts by login", e);
            throw new ServiceException("Error when getting the count of accounts by login", e);
        } finally {
            transactionManager.endTransaction();
        }
        return countOfAccount != 0;
    }

    /**
     * Checking the existing of ip
     *
     * @param ip the ip
     * @return the isAccount present
     * @throws ServiceException the service exception
     */
    @Override
    public boolean isIpPresent(String ip) throws ServiceException {
        Optional<Account> accountOptional;
        try {
            transactionManager.initTransaction();
            accountOptional = accountDao.findAccountByIp(ip);
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when checking the presentation of ip", e);
            throw new ServiceException("Error when checking the presentation of ip", e);
        } finally {
            transactionManager.endTransaction();
        }

        return accountOptional.isPresent();
    }

    /**
     * Checking the existing of if
     *
     * @param login the login
     * @return image path
     * @throws ServiceException the service exception
     */
    @Override
    public String loadImage(String login) throws ServiceException {
        Optional<Account> accountOptional;
        String newImage = "";
        try {
            transactionManager.initTransaction();
            accountOptional = accountDao.findAccountByLogin(login);
            if (accountOptional.isPresent()) {
                String imagePath = accountOptional.get().getImagePath();
                File image = new File(imagePath);
                System.out.println(imagePath);
                byte[] byteContent = Files.readAllBytes(image.toPath());
                StringBuilder stringBuilderParams = new StringBuilder(STRING_PARAMS);
                byte[] encodingImg = Base64.encodeBase64(byteContent, false);
                String imageString = StringUtils.newStringUtf8(encodingImg);
                stringBuilderParams.append(imageString);
                newImage = stringBuilderParams.toString();
            }
            transactionManager.commit();
        } catch (DaoException | TransactionException | IOException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when loading image", e);
            throw new ServiceException("Error when loading image", e);
        } finally {
            transactionManager.endTransaction();
        }

        return newImage;
    }

    /**
     * The adding of account
     *
     * @param login              the login
     * @param password           the password
     * @param confirmingPassword the confirming password
     * @param email              the email
     * @param ip                 the ip
     * @return true if account is added
     * @throws ServiceException the service exception
     */
    @Override
    public boolean addAccount(String login, String password,
                              String confirmingPassword, String email,
                              String ip) throws ServiceException {
        boolean isAccountAdded = false;
        if (validateRegistrationData(login, password, confirmingPassword, email)) {
            Account account = new Account.AccountBuilder()
                    .setLogin(login)
                    .setEmail(email)
                    .setIp(ip)
                    .setRole(Account.Role.APPLICANT)
                    .createAccount();
            String hashPassword = HashGenerator.hashPassword(password);
            try {
                transactionManager.initTransaction();
                accountDao.add(account, hashPassword);
                transactionManager.commit();
                isAccountAdded = true;
            } catch (DaoException | TransactionException e) {
                transactionManager.rollback();
                logger.log(Level.ERROR, "Error when adding account ", e);
                throw new ServiceException("Error when adding account", e);
            } finally {
                transactionManager.endTransaction();
            }
        }

        return isAccountAdded;
    }

    /**
     * The validation on account's data
     *
     * @param login              the login
     * @param password           the password
     * @param confirmingPassword the confirming password
     * @param email              the email
     * @return true if account's data is valid
     * @throws ServiceException the service exception
     */
    public boolean validateRegistrationData(String login, String password,
                                            String confirmingPassword, String email) throws ServiceException {
        boolean isDataValid;
        AccountValidator validator = AccountValidatorImpl.getInstance();
        isDataValid = (validator.isLoginValid(login)
                && validator.isPasswordValid(password))
                && !isAccountLoginExist(login)
                && validator.isEmailValid(email)
                && validator.passwordCheck(password, confirmingPassword);
        return isDataValid;
    }

    /**
     * The validation on account's data
     *
     * @param login              the login
     * @param password           the password
     * @param confirmingPassword the confirming password
     * @return true if account's data is valid
     * @throws ServiceException the service exception
     */
    public boolean validateRegistrationData(String login, String password,
                                            String confirmingPassword) throws ServiceException {
        boolean isDataValid;
        AccountValidator validator = AccountValidatorImpl.getInstance();
        isDataValid = (validator.isLoginValid(login)
                && validator.isPasswordValid(password))
                && !isAccountLoginExist(login)
                && validator.passwordCheck(password, confirmingPassword);
        return isDataValid;
    }

    /**
     * The adding of administrator
     *
     * @param login              the login
     * @param password           the password
     * @param confirmingPassword the confirming password
     * @return true id admin is added
     * @throws ServiceException the service exception
     */
    @Override
    public boolean addAdminAccount(String login, String password, String confirmingPassword) throws ServiceException {
        boolean isAccountAdded = false;
        if (validateRegistrationData(login, password, confirmingPassword)) {
            Account account = new Account.AccountBuilder()
                    .setLogin(login)
                    .setPassword(password)
                    .setRole(Account.Role.ADMIN)
                    .createAccount();
            String hashPassword = HashGenerator.hashPassword(password);
            try {
                transactionManager.initTransaction();
                accountDao.add(account, hashPassword);
                transactionManager.commit();
                isAccountAdded = true;
            } catch (DaoException | TransactionException e) {
                transactionManager.rollback();
                logger.log(Level.ERROR, "Error when adding admin with login {} and password {}, {}", login, password, e);
                throw new ServiceException("Error when adding admin with login " + login + " and password " + password, e);
            } finally {
                transactionManager.endTransaction();
            }
        }
        return isAccountAdded;
    }

    /**
     * The getting of account's status
     *
     * @param login the login
     * @return accountStatus the account status
     * @throws ServiceException the service exception
     */
    @Override
    public String getAccountStatusByLogin(String login) throws ServiceException {
        String accountStatus;
        try {
            transactionManager.initTransaction();
            accountStatus = accountDao.getAccountStatusByLogin(login);
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when finding account with login {}. {}.", login, e);
            throw new ServiceException("Error when finding account with login {}. {}" + login, e);
        } finally {
            transactionManager.endTransaction();
        }
        return accountStatus;
    }

    /**
     * The finding of account by id
     *
     * @param accountId the account id
     * @return accountOptional the account optional
     * @throws ServiceException the service exception
     */
    @Override
    public Optional<Account> findAccountById(Long accountId) throws ServiceException {
        Optional<Account> accountOptional;
        try {
            transactionManager.initTransaction();
            accountOptional = accountDao.findEntityById(accountId);
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when finding account by id", e);
            throw new ServiceException("Error when finding account by id", e);
        } finally {
            transactionManager.endTransaction();
        }

        return accountOptional;
    }

    /**
     * The getting of account id by login
     *
     * @param login the login
     * @return accountId the accountId
     * @throws ServiceException the serviceException
     */
    @Override
    public Long getAccountIdByLogin(String login) throws ServiceException {
        Optional<Account> accountOptional;
        Long accountId;
        try {
            transactionManager.initTransaction();
            accountOptional = accountDao.findAccountByLogin(login);
            accountId = accountOptional.orElseThrow(IllegalArgumentException::new).getAccountId();
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when finding account with login {}. {}.", login, e);
            throw new ServiceException("Error when finding account with login {}. {}" + login, e);
        } finally {
            transactionManager.endTransaction();
        }

        return accountId;
    }

    /**
     * The getting of account role by login
     *
     * @param login the login
     * @return role the role
     * @throws ServiceException the serviceException
     */
    @Override
    public Account.Role getAccountRoleByLogin(String login) throws ServiceException {
        Account.Role role;
        try {
            transactionManager.initTransaction();
            Optional<Account> accountOptional = accountDao.findAccountByLogin(login);
            Account account = accountOptional.orElseThrow(IllegalArgumentException::new);
            role = account.getRole();
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when finding account role by login", e);
            throw new ServiceException("Error when finding account role by login", e);
        } finally {
            transactionManager.endTransaction();
        }

        return role;
    }

    /**
     * The getting of account by account id
     *
     * @param accountId the account id
     * @throws ServiceException the serviceException
     */
    @Override
    public void deleteAccount(Long accountId) throws ServiceException {
        try {
            transactionManager.initTransaction();
            accountDao.delete(accountId);
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when deleting account", e);
            throw new ServiceException("Error when deleting account", e);
        } finally {
            transactionManager.endTransaction();
        }

    }

    /**
     * The changing of account status
     *
     * @param accountOptional the account optional
     * @throws ServiceException the service exception
     */
    @Override
    public void changeStatus(Optional<Account> accountOptional) throws ServiceException {
        Account account;
        try {
            transactionManager.initTransaction();
            if (accountOptional.isPresent()) {
                account = accountOptional.get();
                switch (account.getStatus()) {
                    case ACTIVE -> {
                        account.setStatus(Account.Status.BLOCKED);
                    }
                    case BLOCKED -> {
                        account.setStatus(Account.Status.ACTIVE);
                    }
                    default -> {
                        throw new UnsupportedOperationException();
                    }
                }
                accountDao.update(account);
                transactionManager.commit();
            }
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when updating account status", e);
            throw new ServiceException("Error when updating account status", e);
        }


    }

    /**
     * The getting of count of accounts
     *
     * @return the count of applicants
     * @throws ServiceException the service exception
     */
    @Override
    public int getCountOfAccount() throws ServiceException {
        int countOfAccount;
        try {
            transactionManager.initTransaction();
            countOfAccount = accountDao.getCountOfAccounts();
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when getting the count of accounts", e);
            throw new ServiceException("Error when getting the count of accounts", e);
        } finally {
            transactionManager.endTransaction();
        }
        return countOfAccount;
    }

    @Override
    public int getCountOfAccountsByLogin(String login) throws ServiceException {
        int countOfAccount;
        try {
            transactionManager.initTransaction();
            countOfAccount = accountDao.getCountOfAccountsByLogin(login);
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when getting the count of accounts by login", e);
            throw new ServiceException("Error when getting the count of accounts by login", e);
        } finally {
            transactionManager.endTransaction();
        }
        return countOfAccount;
    }

    /**
     * The uploading of image
     *
     * @param content  the content
     * @param fileName the file name
     * @param login    the login
     * @throws ServiceException the service exception
     */
    @Override
    public void uploadImage(InputStream content, String fileName, String login) throws ServiceException {
        try (content) {
            final int MAX_SIZE_IMAGE = 1024 * 1024;
            String imagePathString = UPLOAD_DIR + fileName;
            File file = new File(imagePathString);
            System.out.println(imagePathString);
            try (FileOutputStream outputStream = new FileOutputStream(file, true)) {
                int read;
                byte[] bytes = new byte[MAX_SIZE_IMAGE];
                System.out.println(bytes.length);
                while ((read = content.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            }
            transactionManager.initTransaction();
            Optional<Account> accountOptional = accountDao.findAccountByLogin(login);
            Account account;
            if (accountOptional.isPresent()) {
                account = accountOptional.get();
                account.setImagePath(imagePathString);
                accountDao.update(account);
            }
            transactionManager.commit();
        } catch (DaoException | TransactionException | IOException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when uploading image for account", e);
            throw new ServiceException("Error when uploading image for account", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    /**
     * The checking of existing personal information
     *
     * @param login the login
     * @return true if the personal information is existing
     * @throws ServiceException the service exception
     */
    @Override
    public boolean isPersonalInformationExist(String login) throws ServiceException {
        boolean isPersonalInformationPresent;
        try {
            transactionManager.initTransaction();
            Account account = accountDao.findAccountByLogin(login)
                    .orElseThrow(IllegalArgumentException::new);
            Account.Role role = account.getRole();
            long accountId = account.getAccountId();
            switch (role) {
                case APPLICANT -> isPersonalInformationPresent = applicantDao
                        .findApplicantByAccountId(accountId)
                        .isPresent();
                case ADMIN -> isPersonalInformationPresent = administratorDao
                        .findAdministratorByAccountId(accountId)
                        .isPresent();
                default -> throw new UnsupportedOperationException();
            }
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when finding personal information", e);
            throw new ServiceException("Error when finding personal information", e);
        } finally {
            transactionManager.endTransaction();
        }

        return isPersonalInformationPresent;
    }

    /**
     * The finding of all account for page
     *
     * @param currentPageNumber the current page number
     * @return account the accounts
     * @throws ServiceException the service exception
     */
    @Override
    public List<Account> findAccountsInPage(int currentPageNumber) throws ServiceException {
        List<Account> accounts;
        try {
            final int recordsPerPage = 5;
            transactionManager.initTransaction();
            int accountsSkip = (currentPageNumber - 1) * recordsPerPage;
            accounts = accountDao.findAccountsPage(accountsSkip, recordsPerPage);
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when getting accounts", e);
            throw new ServiceException("Error when getting accounts", e);
        } finally {
            transactionManager.endTransaction();
        }
        return accounts;
    }
}
