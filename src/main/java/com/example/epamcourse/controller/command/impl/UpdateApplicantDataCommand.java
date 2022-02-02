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

import static com.example.epamcourse.controller.command.RequestAttribute.FACULTIES;

public class UpdateApplicantDataCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        ApplicantService applicantService = ApplicantServiceImpl.getInstance();
        FacultyService facultyService = FacultyServiceImpl.getInstance();
        Router router = new Router(PagePath.EDIT_APPLICANT_DATA);
        try {
            Long applicantId = (Long) request.getSession().getAttribute(SessionAttribute.APPLICANT_ID);
            List<Faculty> faculties = facultyService.findAllFaculties();
            request.setAttribute(RequestAttribute.FACULTIES, faculties);
            String name = request.getParameter(RequestParameter.NAME);
            String surname = request.getParameter(RequestParameter.SURNAME);
            String lastname = request.getParameter(RequestParameter.LASTNAME);
            if (applicantService.updateApplicantPersonalData(applicantId, name, surname, lastname)) {
                router.setPage(PagePath.MAIN_PAGE_APPLICANT);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Updating applicant secure information failed", e);
            throw new CommandException("Updating applicant secure information failed", e);
        }
        session.setAttribute(SessionAttribute.CURRENT_PAGE, router.getPage());
        return router;
    }
}
