package com.example.epamcourse.model.service;

import com.example.epamcourse.controller.command.SessionRequestContent;
import com.example.epamcourse.model.entity.Account;
import com.example.epamcourse.model.exception.ServiceException;

import java.util.Optional;

public interface AccountService {
    Optional<Account> authenticate (SessionRequestContent content) throws ServiceException;

    boolean isAccountLoginExist(SessionRequestContent content) throws ServiceException;

    boolean addAccount(SessionRequestContent content) throws ServiceException;

    boolean updateAccountByAdmin(SessionRequestContent content) throws ServiceException;

    boolean updatePassword(SessionRequestContent content) throws ServiceException;



    boolean deleteAccount(SessionRequestContent content) throws ServiceException;

    boolean finishRegistration(SessionRequestContent content) throws ServiceException;
}
