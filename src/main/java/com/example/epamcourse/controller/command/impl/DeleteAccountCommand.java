package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.Command;
import com.example.epamcourse.controller.command.PagePath;
import com.example.epamcourse.controller.command.RequestParameter;
import com.example.epamcourse.controller.command.Router;
import com.example.epamcourse.model.entity.Account;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.AccountService;
import com.example.epamcourse.model.service.FacultyService;
import com.example.epamcourse.model.service.impl.AccountServiceImpl;
import com.example.epamcourse.model.service.impl.FacultyServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;

/**
 * class DeleteAccountCommand
 *
 * @author M.Shubelko
 */
public class DeleteAccountCommand implements Command {

    /** The logger */
    private static final Logger logger = LogManager.getLogger();

    /**
     * Execute
     *
     * @param request the request
     * @return the router
     * @throws CommandException the command exception
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        AccountService accountService = AccountServiceImpl.getInstance();
        int page = Integer.parseInt(request.getParameter(RequestParameter.PAGE));
        Router router = new Router(PagePath.SHOW_ACCOUNTS_PAGE_REDIRECT + page);
        try {
            Long accountId = Long.valueOf(request.getParameter(RequestParameter.ACCOUNT_ID));
            accountService.deleteAccount(accountId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Deleting account failed", e);
            throw new CommandException("Deleting account failed", e);
        }
        return router;
    }
}
