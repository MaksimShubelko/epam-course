package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.entity.Faculty;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.ApplicantService;
import com.example.epamcourse.model.service.FacultyService;
import com.example.epamcourse.model.service.impl.ApplicantServiceImpl;
import com.example.epamcourse.model.service.impl.FacultyServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AddApplicantSecureInformationCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String name = request.getParameter(RequestParameter.NAME);
        String surname = request.getParameter(RequestParameter.SURNAME);
        String lastname = request.getParameter(RequestParameter.LASTNAME);
        Long accountId = (Long) request.getSession().getAttribute(SessionAttribute.ACCOUNT_ID);
        ApplicantService applicantService = ApplicantServiceImpl.getInstance();
        FacultyService facultyService = FacultyServiceImpl.getInstance();
        Router router = new Router(PagePath.APPLICANT_ADD_PERSONAL_INF);
        router.setType(Router.RouterType.REDIRECT);
        try {
            if (applicantService.addPersonalInformation(name, surname, lastname, accountId)) {
                List<Faculty> faculties = facultyService.findAllFaculties();
                request.setAttribute(RequestAttribute.FACULTIES, faculties);
                Long applicantId = applicantService.getApplicantIdByAccountId(accountId);
                session.setAttribute(SessionAttribute.APPLICANT_ID, applicantId);
                router.setPage(PagePath.MAIN_PAGE_APPLICANT);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Adding administrator secure information failed.", e);
            throw new CommandException("Adding administrator secure information failed", e);
        }
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.APPLICANT_ADD_PERSONAL_INF_REDIRECT);
        return router;
    }
}
