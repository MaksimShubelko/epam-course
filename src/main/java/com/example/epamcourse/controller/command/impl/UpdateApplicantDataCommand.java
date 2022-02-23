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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 * class UpdateApplicantDataCommand
 *
 * @author M.Shubelko
 */
public class UpdateApplicantDataCommand implements Command {

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
            if (applicantService.editApplicantPersonalInformation(applicantId, name, surname, lastname)) {
                router.setPage(PagePath.MAIN_PAGE_APPLICANT_REDIRECT);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Updating applicant secure information failed", e);
            throw new CommandException("Updating applicant secure information failed", e);
        }
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.MAIN_PAGE_APPLICANT_REDIRECT);
        return router;
    }
}
