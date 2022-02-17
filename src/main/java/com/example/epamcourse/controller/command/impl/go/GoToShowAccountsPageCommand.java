package com.example.epamcourse.controller.command.impl.go;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.entity.Account;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.AccountService;
import com.example.epamcourse.model.service.ApplicantService;
import com.example.epamcourse.model.service.impl.AccountServiceImpl;
import com.example.epamcourse.model.service.impl.ApplicantServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;

import static com.example.epamcourse.controller.command.PagePath.SHOW_ACCOUNTS_PAGE;

public class GoToShowAccountsPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        int page = 1;
        final int recordsPerPage = 5;
        HttpSession session = request.getSession();
        Router router = new Router(SHOW_ACCOUNTS_PAGE);
        AccountService accountService = AccountServiceImpl.getInstance();
        try {
            long noOfPages = (long) Math.ceil(accountService.findAllAccounts().size() * 1.0 / recordsPerPage);
            if (request.getParameter(RequestParameter.PAGE) != null) {
                page = Integer.parseInt(request.getParameter(RequestParameter.PAGE));
            }
            List<Account> accounts = accountService.findAccountsInPage(page);
            request.setAttribute(RequestAttribute.ACCOUNTS, accounts);
            request.setAttribute(RequestAttribute.PAGE, page);
            request.setAttribute(RequestAttribute.COUNT_PAGES, noOfPages);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Finding accounts failed", e);
            throw new CommandException("Finding accounts failed", e);
        }
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.GO_TO_SHOW_ACCOUNT_REDIRECT);
        return router;
    }
}
