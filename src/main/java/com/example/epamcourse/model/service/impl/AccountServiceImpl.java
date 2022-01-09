package com.example.epamcourse.model.service.impl;

import com.example.epamcourse.controller.command.LocaleMessageKey;
import com.example.epamcourse.controller.command.RequestParameter;
import com.example.epamcourse.controller.command.SessionAttribute;
import com.example.epamcourse.controller.command.SessionRequestContent;
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
import com.example.epamcourse.util.HashGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.example.epamcourse.controller.command.RequestParameter.*;

public class AccountServiceImpl implements AccountService {
    private static final Logger logger = LogManager.getLogger();
    private static final AccountServiceImpl instance = new AccountServiceImpl();
    private final AccountValidator validator = AccountValidator.getInstance();
    private final TransactionManager transactionManager = TransactionManager.getInstance();
    private final AccountDao accountDao = AccountDaoImpl.getInstance();
    private final ApplicantDao applicantDao = ApplicantDaoImpl.getInstance();
    private final AdministratorDao administratorDao = AdministratorDaoImpl.getInstance();

    private AccountServiceImpl() {
    }

    public static AccountServiceImpl getInstance() {
        return instance;
    }

    public Optional<Account> authenticate(SessionRequestContent content) throws ServiceException {
        Optional<Account> account = Optional.empty();
        String login = content.getRequestParameter(LOGIN);
        String password = content.getRequestParameter(PASSWORD);

        if (validator.isLoginValid(login) && validator.isPasswordValid(password)) {
            String hashPassword = HashGenerator.hashPassword(password);

            try {
                transactionManager.initTransaction();
                account = accountDao.findAccountByLoginAndPassword(login, hashPassword);
                transactionManager.commit();
            } catch (DaoException | TransactionException e) {
                logger.log(Level.ERROR, "Error when authenticating account with login {} and password {}. {}", login, password, e.getMessage());
                throw new ServiceException("Error when authenticating account with login " + login + " and password " + password, e);
            } finally {
                transactionManager.endTransaction();
            }
        }

        return account;
    }

    @Override
    public boolean isAccountLoginExist(SessionRequestContent content) throws ServiceException {
        String login = content.getRequestParameter(LOGIN);
        Optional<Account> account;
        boolean isAccountExist;

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
    public boolean addAccount(SessionRequestContent content) throws ServiceException {
        boolean isAccountAdded = false;
        String login = content.getRequestParameter(RequestParameter.LOGIN);
        String password = content.getRequestParameter(RequestParameter.PASSWORD);
        String email = content.getRequestParameter(RequestParameter.EMAIL);
        String passwordChecker = content.getRequestParameter(PASSWORD_CHECK);
        AccountValidator validator = AccountValidator.getInstance();
        if (!(validator.isLoginValid(login) &&
                validator.isPasswordValid(password)) || isAccountLoginExist(content)) {
            content.addSessionAttribute(SessionAttribute.SESSION_MESSAGE, LocaleMessageKey.ACCOUNT_CREATION_ERROR);
        } else {
            if (!validator.passwordCheck(password, passwordChecker)) {
                content.addSessionAttribute(SessionAttribute.SESSION_MESSAGE, LocaleMessageKey.PASSWORD_DOUBLE_CHECK_ERROR);
            } else {
                Account account = new Account.AccountBuilder()
                        .setLogin(login)
                        .setPassword(password)
                        .setEmail(email)
                        .createAccount();
                String hashPassword = HashGenerator.hashPassword(password);
                try {
                    transactionManager.initTransaction();
                    long accountId = accountDao.add(account, hashPassword);
                    //account.setAccountId(accountId);
                    transactionManager.commit();
                    isAccountAdded = true;
                } catch (DaoException | TransactionException e) {
                    transactionManager.rollback();
                    logger.log(Level.ERROR, "Error when adding account with login {} and password {}, {}", login, password, e.getMessage());
                    throw new ServiceException("Error when adding account with login " + login + " and password " + password, e);
                } finally {
                    transactionManager.endTransaction();
                }
            }
        }
        return isAccountAdded;
    }

    @Override
    public boolean updateAccountByAdmin(SessionRequestContent content) throws ServiceException {
        boolean isRoleUpdated = false;
        Account account = (Account) content.getSessionAttribute(SessionAttribute.ACCOUNT);
        long accountId = account.getAccountId();

        String roleRequest = content.getRequestParameter(ROLE);
        int role = Account.Role.valueOf(roleRequest).ordinal();
        try {
                transactionManager.initTransaction();
                accountDao.updateRole(account, role);
                transactionManager.commit();
                isRoleUpdated = true;
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when updating role, {}", e.getMessage());
            throw new ServiceException("Error when updating role", e);
        } finally {
            transactionManager.endTransaction();
        }

        return isRoleUpdated;
    }

    @Override
    public boolean updatePassword(SessionRequestContent content) throws ServiceException {
        boolean isPasswordUpdated = false;
        Account account = (Account) content.getSessionAttribute(SessionAttribute.ACCOUNT);
        long accountId = account.getAccountId();

        String newPassword = content.getRequestParameter(PASSWORD);
        String passwordChecker = content.getRequestParameter(PASSWORD_CHECK);
        try {
            if (validator.isPasswordValid(newPassword) && validator.passwordCheck(newPassword, passwordChecker)) {
                String hashPassword = HashGenerator.hashPassword(newPassword);
                transactionManager.initTransaction();
                accountDao.update(account, hashPassword);
                transactionManager.commit();
                isPasswordUpdated = true;
            }
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when updating password, {}", e.getMessage());
            throw new ServiceException("Error when updating password", e);
        } finally {
            transactionManager.endTransaction();
        }

        return isPasswordUpdated;
    }

    @Override
    public Long getAccountIdByLogin(SessionRequestContent content) throws ServiceException {
        String login = content.getRequestParameter(LOGIN);
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
    public Account.Role getAccountRoleByLogin(SessionRequestContent content) throws ServiceException {
        String login = content.getRequestParameter(LOGIN);
        Account.Role role = null;
        try {
            transactionManager.initTransaction();
            Optional<Account> accountOptional = accountDao.findAccountByLogin(login);
            System.out.println(login);
            Account account = accountOptional.orElseThrow(IllegalArgumentException::new);
            role = account.getRole();
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when finding account role by login, {}", e.getMessage());
            throw new ServiceException("Error when finding account role by login", e);
        } finally {
            transactionManager.endTransaction();
        }

        return role;
    }

    @Override
    public boolean deleteAccount(SessionRequestContent content) throws ServiceException {
        boolean isAccountDeleted = false;
        Account account = (Account) content.getSessionAttribute(SessionAttribute.ACCOUNT);
        long accountId = account.getAccountId();

        try {
                transactionManager.initTransaction();
                accountDao.delete(accountId);
                transactionManager.commit();
                isAccountDeleted = true;
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when deleting account, {}", e.getMessage());
            throw new ServiceException("Error when deleting account", e);
        } finally {
            transactionManager.endTransaction();
        }

        return isAccountDeleted;
    }

    @Override
    public boolean finishRegistration(SessionRequestContent content) throws ServiceException {
        return false;
    }

    public boolean isPersonalInformationExist(SessionRequestContent content) throws ServiceException {
        boolean isPersonalInformationPresent;
        try {
            transactionManager.initTransaction();
            Account account = accountDao.findAccountByLogin(content.getRequestParameter(LOGIN))
                    .orElseThrow(IllegalArgumentException::new);
            Account.Role role = account.getRole();
            System.out.println(role);
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
            logger.log(Level.ERROR, "Error when finding personal information, {}", e.getMessage());
            throw new ServiceException("Error when finding personal information", e);
        } finally {
            transactionManager.endTransaction();
        }

        return isPersonalInformationPresent;
    }

}
