package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.AccountService;
import com.example.epamcourse.model.service.impl.AccountServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;

public class AddAdminAccountCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    public Router execute(HttpServletRequest request) throws CommandException {
        AccountService accountService = AccountServiceImpl.getInstance();
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String passwordCheck = request.getParameter(RequestParameter.PASSWORD_CHECK);
        String email = request.getParameter(RequestParameter.EMAIL);
        Router router = new Router(PagePath.ADD_ADMIN_ACCOUNT_PAGE);
        router.setType(Router.RouterType.REDIRECT);
        try {
            if (accountService.addAdminAccount(login, password, email, passwordCheck)) {
                router.setPage(PagePath.MAIN_PAGE_ADMINISTRATOR);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Adding account failed.", e);
            throw new CommandException("Adding account failed", e);
        }
        return router;
    }
}
