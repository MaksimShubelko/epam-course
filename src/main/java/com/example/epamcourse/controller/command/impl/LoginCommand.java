package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.LocaleMessageKey;
import com.example.epamcourse.controller.command.RequestAttribute;
import com.example.epamcourse.controller.command.Router;
import com.example.epamcourse.controller.command.SessionAttribute;
import com.example.epamcourse.model.entity.Account;
import com.example.epamcourse.model.entity.Faculty;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.AccountService;
import com.example.epamcourse.model.service.FacultyService;
import com.example.epamcourse.model.service.impl.AccountServiceImpl;
import com.example.epamcourse.model.service.impl.FacultyServiceImpl;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.example.epamcourse.controller.command.LocaleMessageKey.LOGIN_WELCOME_MASSAGE;
import static com.example.epamcourse.controller.command.PagePath.*;
import static com.example.epamcourse.controller.command.RequestAttribute.FACULTIES;

public class LoginCommand extends AbstractCommand {
    @Override
    public Router execute(HttpServletRequest request) throws ServiceException {
        initCommand(request);
        Router router = new Router(LOGIN);
        router.setType(Router.RouterType.REDIRECT);
        AccountService accountService = AccountServiceImpl.getInstance();
        FacultyService facultyService = FacultyServiceImpl.getInstance();
        try {
            if (accountService.authenticate(content).isEmpty()) {
                request.setAttribute(RequestAttribute.ERROR_MASSAGE,
                        LocaleMessageKey.LOGIN_ERROR_MESSAGE);
            } else {
                List<Faculty> faculties = facultyService.findAllFaculties(content);
                System.out.println(faculties);
                request.getSession().setAttribute(FACULTIES, faculties);
                Long accountId = accountService.getAccountIdByLogin(content);
                content.addSessionAttribute(SessionAttribute.ACCOUNT_ID, accountId);
                if (accountService.getAccountRoleByLogin(content) == Account.Role.APPLICANT) {
                    if (!accountService.isPersonalInformationExist(content)) {
                        router.setPage(APPLICANT_ADD_PERSONAL_INF);
                    } else {
                        router.setPage(MAIN_PAGE_APPLICANT);
                    }
                } else {
                    if (accountService.getAccountRoleByLogin(content) == Account.Role.ADMIN) {
                        if (!accountService.isPersonalInformationExist(content)) {
                            router.setPage(ADMIN_ADD_PERSONAL_INF);
                        } else {
                            router.setPage(MAIN_PAGE_APPLICANT);
                        }
                    }
                }
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Login account failed. {}", e.getMessage());
            throw new ServiceException("Login account failed", e);
        }
        content.insertValues(request);

        return router;
    }
}
