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
import java.util.List;

public class EditFacultyCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        FacultyService facultyService = FacultyServiceImpl.getInstance();
        Router router = new Router(PagePath.EDIT_FACULTY_PAGE);
        try {
            Long applicantId = (Long) request.getSession().getAttribute(SessionAttribute.APPLICANT_ID);
            List<Faculty> faculties = facultyService.findAllFaculties();
            request.setAttribute(RequestAttribute.FACULTIES, faculties);
            Long facultyId = Long.valueOf(request.getParameter(RequestParameter.FACULTY_ID));
            String facultyName = request.getParameter(RequestParameter.FACULTY_NAME);
            int recruitmentPlanFree = Integer.parseInt(request.getParameter(RequestParameter.RECRUITMENT_PLAN_FREE));
            int recruitmentPlanCanvas = Integer.parseInt(request.getParameter(RequestParameter.RECRUITMENT_PLAN_CANVAS));
            if (facultyService.editFaculty(facultyId, facultyName, recruitmentPlanFree, recruitmentPlanCanvas)) {
                router.setPage(PagePath.MAIN_PAGE_ADMINISTRATOR);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Edition faculty failed", e);
            throw new CommandException("Edition faculty failed", e);
        }
        return router;
    }
}
