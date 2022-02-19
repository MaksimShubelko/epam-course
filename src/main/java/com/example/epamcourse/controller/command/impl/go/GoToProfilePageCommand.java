package com.example.epamcourse.controller.command.impl.go;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.entity.Account;
import com.example.epamcourse.model.entity.Administrator;
import com.example.epamcourse.model.entity.Applicant;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.AccountService;
import com.example.epamcourse.model.service.AdministratorService;
import com.example.epamcourse.model.service.ApplicantService;
import com.example.epamcourse.model.service.impl.AccountServiceImpl;
import com.example.epamcourse.model.service.impl.AdministratorServiceImpl;
import com.example.epamcourse.model.service.impl.ApplicantServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

/**
 * class GoToProfilePageCommand
 *
 * @author M.Shubelko
 */
public class GoToProfilePageCommand implements Command {

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
        AccountService accountService = AccountServiceImpl.getInstance();
        Router router = new Router(PagePath.SHOW_ACCOUNTS_PAGE_REDIRECT);
        AdministratorService administratorService = AdministratorServiceImpl.getInstance();
        ApplicantService applicantService = ApplicantServiceImpl.getInstance();
        Long accountId = Long.valueOf(request.getParameter(RequestParameter.ACCOUNT_ID));
        try {
            Optional<Account> accountOptional = accountService.findAccountById(accountId);
            if (accountOptional.isPresent()) {
                router.setPage(PagePath.PROFILE);
                Account account = accountOptional.get();
                String image = accountService.loadImage(account.getLogin());
                request.setAttribute(RequestAttribute.IMAGE, image);
                switch (account.getRole()) {
                    case ADMIN -> {
                        Optional<Administrator> administratorOptional = administratorService.getAdministratorByAccountId(accountId);
                        administratorOptional.ifPresent(administrator -> request.setAttribute(RequestAttribute.ADMINISTRATOR, administrator));
                    }
                    case APPLICANT -> {
                        Optional<Applicant> applicantOptional = applicantService.getApplicantByAccountId(accountId);
                        applicantOptional.ifPresent(applicant -> request.setAttribute(RequestAttribute.ADMINISTRATOR, applicant));
                    }
                    default -> throw new IllegalArgumentException();
                }
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Go to profile failed.", e);
            throw new CommandException("Go to profile failed.", e);
        }
        return router;
    }
}
