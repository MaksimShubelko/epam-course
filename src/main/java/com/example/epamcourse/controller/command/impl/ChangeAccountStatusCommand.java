package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.entity.Account;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.AccountService;
import com.example.epamcourse.model.service.impl.AccountServiceImpl;
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
        Account account;
        AccountService accountService = AccountServiceImpl.getInstance();
        int page = Integer.parseInt(request.getParameter(RequestParameter.PAGE));
        Router router = new Router(PagePath.SHOW_ACCOUNTS_PAGE_REDIRECT + page);
        router.setType(Router.RouterType.REDIRECT);
        try {
            Long accountId = Long.valueOf(request.getParameter(RequestParameter.ACCOUNT_ID));
            Optional<Account> accountOptional = accountService.findAccountById(accountId);
            if (accountOptional.isPresent()) { // todo move to service
                account = accountOptional.get();
                switch (account.getStatus()) {
                    case ACTIVE -> {
                        account.setStatus(Account.Status.BLOCKED);
                    }
                    case BLOCKED -> {
                        account.setStatus(Account.Status.ACTIVE);
                    }
                    default -> {
                        throw new UnsupportedOperationException();
                    }
                }
                accountService.updateAccount(account);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Edition faculty failed", e);
            throw new CommandException("Edition faculty failed", e);
        }
        return router;
    }
}
