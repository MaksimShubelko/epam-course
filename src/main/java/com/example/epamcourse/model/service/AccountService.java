package com.example.epamcourse.model.service;

import com.example.epamcourse.model.entity.Account;
import com.example.epamcourse.model.exception.ServiceException;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface AccountService {
    Optional<Account> authenticate(String login, String password) throws ServiceException;

    boolean isAccountLoginExist(String login) throws ServiceException;

    boolean isIpPresent(String ip) throws ServiceException;

    String loadImage(String accountLogin) throws ServiceException;

    boolean addAccount(String login, String password, String email, String passwordCheck, String ip) throws ServiceException;

    boolean validateRegistrationData(String login, String password, String passwordCheck, String email) throws ServiceException;

    boolean updateAccount(Account account) throws ServiceException;

    boolean addAdminAccount(String login, String password, String email, String passwordCheck) throws ServiceException;

    String getAccountStatusByLogin(String login) throws ServiceException;

    Optional<Account> findAccountById(Long accountId) throws ServiceException;

    Long getAccountIdByLogin(String login) throws ServiceException;

    void uploadImage(InputStream content, String fileName, String login) throws ServiceException;

    Account.Role getAccountRoleByLogin(String login) throws ServiceException;

    boolean isPersonalInformationExist(String login) throws ServiceException;

    List<Account> findAllAccounts() throws ServiceException;

    List<Account> findAccountsInPage(int page) throws ServiceException;

    boolean deleteAccount(Long accountId) throws ServiceException;

}
