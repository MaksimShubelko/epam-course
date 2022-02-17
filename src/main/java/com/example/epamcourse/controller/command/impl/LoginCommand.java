package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.entity.Account;
import com.example.epamcourse.model.entity.Faculty;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.AccountService;
import com.example.epamcourse.model.service.AdministratorService;
import com.example.epamcourse.model.service.ApplicantService;
import com.example.epamcourse.model.service.FacultyService;
import com.example.epamcourse.model.service.impl.AccountServiceImpl;
import com.example.epamcourse.model.service.impl.AdministratorServiceImpl;
import com.example.epamcourse.model.service.impl.ApplicantServiceImpl;
import com.example.epamcourse.model.service.impl.FacultyServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import static com.example.epamcourse.controller.command.PagePath.*;
import static com.example.epamcourse.controller.command.RequestAttribute.FACULTIES;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);
        HttpSession session = request.getSession();
        Router router = new Router(LOGIN);
        AccountService accountService = AccountServiceImpl.getInstance();
        ApplicantService applicantService = ApplicantServiceImpl.getInstance();
        AdministratorService administratorService = AdministratorServiceImpl.getInstance();
        try {
            if (accountService.authenticate(login, password).isEmpty()) {
                request.setAttribute(RequestAttribute.ERROR_MASSAGE,
                        LocaleMessageKey.LOGIN_ERROR_MESSAGE);
            } else {
                if (accountService.getAccountStatusByLogin(login)
                        .equals(Account.Status.BLOCKED.toString())) {
                    router.setPage(PagePath.BLOCKED_ACCOUNT_PAGE);
                } else {
                    Long accountId = accountService.getAccountIdByLogin(login);
                    Account.Role role = accountService.getAccountRoleByLogin(login);
                    session.setAttribute(SessionAttribute.ROLE, role);
                    session.setAttribute(SessionAttribute.ACCOUNT_ID, accountId);
                    if (role == Account.Role.APPLICANT) {
                        if (!accountService.isPersonalInformationExist(login)) {
                            router.setPage(APPLICANT_ADD_PERSONAL_INF);
                        } else {
                            Long applicantId = applicantService.getApplicantIdByAccountId(accountId);
                            session.setAttribute(SessionAttribute.APPLICANT_ID, applicantId);
                            router.setPage(MAIN_PAGE_APPLICANT_REDIRECT);
                        }
                    } else {
                        if (role == Account.Role.ADMIN) {
                            if (!accountService.isPersonalInformationExist(login)) {
                                router.setPage(ADMIN_ADD_PERSONAL_INF);
                            } else {
                                Long administratorId = administratorService.getAdministratorIdByAccountId(accountId);
                                session.setAttribute(SessionAttribute.ADMINISTRATOR_ID, administratorId);
                                router.setPage(MAIN_PAGE_ADMINISTRATOR);
                            }
                        }
                    }
                }
            }
            request.getSession().setAttribute(SessionAttribute.CURRENT_PAGE, router.getPage());
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Login account failed", e);
            throw new CommandException("Login account failed", e);
        }

        return router;
    }
}
