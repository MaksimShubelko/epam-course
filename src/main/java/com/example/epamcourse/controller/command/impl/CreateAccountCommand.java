package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.LocaleMessageKey;
import com.example.epamcourse.controller.command.PagePath;
import com.example.epamcourse.controller.command.Router;
import com.example.epamcourse.controller.command.SessionAttribute;
import com.example.epamcourse.model.dao.AccountDao;
import com.example.epamcourse.model.dao.impl.AccountDaoImpl;
import com.example.epamcourse.model.entity.Account;
import com.example.epamcourse.model.exception.DaoException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.AccountService;
import com.example.epamcourse.model.service.impl.AccountServiceImpl;
import com.example.epamcourse.model.validator.AccountValidator;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.File;

import static com.example.epamcourse.controller.command.RequestParameter.*;
import static com.example.epamcourse.controller.command.SessionAttribute.ACCOUNT;

public class CreateAccountCommand extends AbstractCommand {
    @Override
    public Router execute(HttpServletRequest request) throws DaoException, ServiceException {
        initCommand(request);

        AccountValidator accountValidator = AccountValidator.getInstance(); // todo account roles
        AccountService accountService = AccountServiceImpl.getInstance();
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String email = request.getParameter(EMAIL);
        content.addRequestAttribute(LOGIN, login);
        content.addRequestAttribute(PASSWORD, password);
        content.addRequestAttribute(EMAIL, email);
        try {
            if (accountService.addAccount(content)) {
                router.setPage(PagePath.LOGIN);
                content.addSessionAttribute(SessionAttribute.SESSION_MESSAGE,
                        LocaleMessageKey.ACCOUNT_CREATION_SUCCESS);
            } else {
                content.addSessionAttribute(SessionAttribute.SESSION_MESSAGE_ERROR,
                        LocaleMessageKey.ACCOUNT_CREATION_ERROR);

            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Adding account failed. {}", e.getMessage());
            throw new ServiceException("Adding account failed", e);
        }

        return router;
    }

}
