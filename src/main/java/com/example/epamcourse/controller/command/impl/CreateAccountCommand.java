package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.exception.DaoException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.AccountService;
import com.example.epamcourse.model.service.impl.AccountServiceImpl;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;

public class CreateAccountCommand extends AbstractCommand {
    @Override
    public Router execute(HttpServletRequest request) throws ServiceException {
        initCommand(request);
        AccountService accountService = AccountServiceImpl.getInstance();
        Router router = new Router(PagePath.CREATION_ACCOUNT);
        router.setType(Router.RouterType.REDIRECT);
        try {
            if (accountService.addAccount(content)) {
                content.addRequestAttribute(RequestAttribute.ERROR_MASSAGE,
                        LocaleMessageKey.ACCOUNT_CREATION_SUCCESS);
                router.setPage(PagePath.LOGIN);
            } else {
                content.addSessionAttribute(SessionAttribute.SESSION_MESSAGE,
                        LocaleMessageKey.ACCOUNT_CREATION_ERROR);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Adding account failed. {}", e.getMessage());
            throw new ServiceException("Adding account failed", e);
        }

        return router;
    }

}
