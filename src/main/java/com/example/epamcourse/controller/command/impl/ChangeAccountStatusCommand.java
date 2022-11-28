package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.entity.Account;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.AccountService;
import com.example.epamcourse.model.service.impl.AccountServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * class ChangeAccountStatusCommand
 *
 * @author M.Shubelko
 */
public class ChangeAccountStatusCommand implements Command {

    /** The logger. */
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
        HttpSession session = request.getSession();
        AccountService accountService = AccountServiceImpl.getInstance();
        int page = Integer.parseInt(request.getParameter(RequestParameter.PAGE));
        Router router = new Router(PagePath.SHOW_ACCOUNTS_PAGE_REDIRECT + page);
        try {
            Long accountId = Long.valueOf(request.getParameter(RequestParameter.ACCOUNT_ID));
            Optional<Account> accountOptional = accountService.findAccountById(accountId);
            accountService.changeStatus(accountOptional);
            session.setAttribute(SessionAttribute.MESSAGE_RESULT, LocaleMessageKey.ACCOUNT_STATUS_CHANGED);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Changing account status failed", e);
            throw new CommandException("Changing account status failed", e);
        }
        return router;
    }
}
