package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.LocaleMessageKey;
import com.example.epamcourse.controller.command.PagePath;
import com.example.epamcourse.controller.command.Router;
import com.example.epamcourse.controller.command.SessionAttribute;
import com.example.epamcourse.model.exception.ControllerException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.AccountService;
import com.example.epamcourse.model.service.impl.AccountServiceImpl;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand extends AbstractCommand {
    @Override
    public Router execute(HttpServletRequest request) throws ControllerException {
        initCommand(request);
        AccountService accountService = AccountServiceImpl.getInstance();
        try {
            if (accountService.authenticate(content).isEmpty()) {
                router.setPage(PagePath.LOGIN); // todo
                content.addSessionAttribute(SessionAttribute.SESSION_MESSAGE, LocaleMessageKey.ACCOUNT_CREATION_SUCCESS);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Adding account failed. {}", e.getMessage());
            throw new ControllerException("Adding account failed", e);
        }

        content.insertValues(request);

        return router;
    }
}
