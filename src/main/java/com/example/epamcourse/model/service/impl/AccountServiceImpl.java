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
import com.example.epamcourse.model.validator.AccountValidator;
import com.example.epamcourse.model.validator.impl.AccountValidatorImpl;
import com.example.epamcourse.util.HashGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {
    private static final Logger logger = LogManager.getLogger();
    private static final String UPLOAD_DIR = "C:\\images\\";
    private static final String stringParams = "data:image/jpeg;base64,";
    private static final AccountServiceImpl instance = new AccountServiceImpl();
    private final TransactionManager transactionManager = TransactionManager.getInstance();
    private final AccountDao accountDao = AccountDaoImpl.getInstance();
    private final ApplicantDao applicantDao = ApplicantDaoImpl.getInstance();
    private final AdministratorDao administratorDao = AdministratorDaoImpl.getInstance();

    private AccountServiceImpl() {
    }

    public static AccountServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<Account> authenticate(String login, String password) throws ServiceException {
        AccountValidator validator = AccountValidatorImpl.getInstance();
        Optional<Account> account = Optional.empty();

        if (validator.isLoginValid(login) && validator.isPasswordValid(password)) {
            String hashPassword = HashGenerator.hashPassword(password);
            try {
                transactionManager.initTransaction();
                account = accountDao.findAccountByLoginAndPassword(login, hashPassword);
                transactionManager.commit();
            } catch (DaoException | TransactionException e) {
                logger.log(Level.ERROR, "Error when authenticating account with login {} and password {}. {}", login, password, e);
                throw new ServiceException("Error when authenticating account with login " + login + " and password " + password, e);
            } finally {
                transactionManager.endTransaction();
            }
        }

        return account;
    }

    @Override
    public boolean isAccountLoginExist(String login) throws ServiceException {
        Optional<Account> account;

        try {
            transactionManager.initTransaction();
            account = accountDao.findAccountByLogin(login);
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when finding account with login {}. {}.", login, e);
            throw new ServiceException("Error when finding account with login {}. {}" + login, e);
        } finally {
            transactionManager.endTransaction();
        }

        return account.isPresent();
    }

    @Override
    public boolean isIpPresent(String ip) throws ServiceException {
        Optional<Account> account;

        try {
            transactionManager.initTransaction();
            account = accountDao.findAccountByIp(ip);
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when finding account with login {}. {}.", ip, e);
            throw new ServiceException("Error when finding account with login {}. {}" + ip, e);
        } finally {
            transactionManager.endTransaction();
        }

        return account.isPresent();
    }

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
                byte[] byteContent = Files.readAllBytes(image.toPath());
                StringBuilder stringBuilderParams = new StringBuilder(stringParams);
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

    @Override
    public boolean addAccount(String login, String password, String passwordCheck, String email, String ip) throws ServiceException {
        boolean isAccountAdded = false;
        AccountValidator validator = AccountValidatorImpl.getInstance();
        System.out.println("add account");
        if ((validator.isLoginValid(login)
                && validator.passwordCheck(password, passwordCheck)
                && validator.isPasswordValid(password))
                && !isAccountLoginExist(login)) {

            Account account = new Account.AccountBuilder()
                    .setLogin(login)
                    .setPassword(password)
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
                logger.log(Level.ERROR, "Error when adding account with login {} and password {}, {}", login, password, e);
                throw new ServiceException("Error when adding account with login " + login + " and password " + password, e);
            } finally {
                transactionManager.endTransaction();
            }
        }

        return isAccountAdded;
    }

    public boolean validateRegistrationData(String login, String password, String passwordCheck, String email) throws ServiceException {
        boolean isDataValid;
        AccountValidator validator = AccountValidatorImpl.getInstance();
        isDataValid = (validator.isLoginValid(login)
                && validator.isPasswordValid(password))
                && !isAccountLoginExist(login)
                && validator.passwordCheck(password, passwordCheck);
        return isDataValid;
    }

    @Override
    public boolean updateAccount(Account account) throws ServiceException {
        boolean isAAccountUpdated;
        try {
            transactionManager.initTransaction();
            accountDao.update(account);
            transactionManager.commit();
            isAAccountUpdated = true;
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when updating account", e);
            throw new ServiceException("Error when updating account", e);
        } finally {
            transactionManager.endTransaction();
        }

        return isAAccountUpdated;
    }

    @Override
    public boolean addAdminAccount(String login, String password, String email, String passwordCheck) throws ServiceException {
        boolean isAccountAdded = false;
        AccountValidator validator = AccountValidatorImpl.getInstance();
        if (!(validator.isLoginValid(login) &&
                validator.isPasswordValid(password)) || isAccountLoginExist(login)) {
            // todo
        } else {
            if (!validator.passwordCheck(password, passwordCheck)) {
// todo
            } else {
                Account account = new Account.AccountBuilder()
                        .setLogin(login)
                        .setPassword(password)
                        .setEmail(email)
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
        }
        return isAccountAdded;
    }

    /*@Override
    public boolean updatePassword(SessionRequestContent content) throws ServiceException {
        return false;
    }*/

    @Override
    public String getAccountStatusByLogin(String login) throws ServiceException {
        String accountStatus;
        Long accountId;
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

    @Override
    public Long getAccountIdByLogin(String login) throws ServiceException {
        Optional<Account> account;
        Long accountId;
        try {
            transactionManager.initTransaction();
            account = accountDao.findAccountByLogin(login);
            accountId = account.orElseThrow(IllegalArgumentException::new).getAccountId();
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
            logger.log(Level.ERROR, "Error when finding account role by login, {}", e);
            throw new ServiceException("Error when finding account role by login", e);
        } finally {
            transactionManager.endTransaction();
        }

        return role;
    }

    @Override
    public boolean deleteAccount(Long accountId) throws ServiceException {
        boolean isAccountDeleted = false;
        try {
            transactionManager.initTransaction();
            accountDao.delete(accountId);
            transactionManager.commit();
            isAccountDeleted = true;
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when deleting account", e);
            throw new ServiceException("Error when deleting account", e);
        } finally {
            transactionManager.endTransaction();
        }

        return isAccountDeleted;
    }

    @Override
    public void uploadImage(InputStream content, String fileName, String login) throws ServiceException {
        boolean isPersonalInformationPresent;
        try (content) {
            String imagePathString = UPLOAD_DIR + fileName;
            Path imagePath = new File(imagePathString).toPath();
            long bytes = Files.copy(
                    content,
                    imagePath,
                    StandardCopyOption.REPLACE_EXISTING
            );
            transactionManager.initTransaction();
            Optional<Account> accountOptional = accountDao.findAccountByLogin(login);
            Account account;
            if (accountOptional.isPresent()) {
                System.out.println("PRESENT ACCOUNTS");
                account = accountOptional.get();
                account.setImagePath(imagePathString);
                accountDao.update(account);
            } else {
                System.out.println("NO ACCOUNTS");
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
                        .getApplicantByAccountId(accountId)
                        .isPresent();
                case ADMIN -> isPersonalInformationPresent = administratorDao
                        .getAdministratorByAccountId(accountId)
                        .isPresent();
                default -> throw new UnsupportedOperationException();
            }
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when finding personal information, {}", e);
            throw new ServiceException("Error when finding personal information", e);
        } finally {
            transactionManager.endTransaction();
        }

        return isPersonalInformationPresent;
    }

    @Override
    public List<Account> findAllAccounts() throws ServiceException {
        List<Account> accounts;
        try {
            transactionManager.initTransaction();
            accounts = accountDao.findAll();
            transactionManager.commit();
        } catch (DaoException | TransactionException | SQLException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when finding account role by login, {}", e);
            throw new ServiceException("Error when finding account role by login", e);
        } finally {
            transactionManager.endTransaction();
        }

        return accounts;
    }

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
