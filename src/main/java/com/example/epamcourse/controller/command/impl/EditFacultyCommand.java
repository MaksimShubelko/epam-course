package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.entity.Faculty;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.ApplicantService;
import com.example.epamcourse.model.service.FacultyService;
import com.example.epamcourse.model.service.impl.ApplicantServiceImpl;
import com.example.epamcourse.model.service.impl.FacultyServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;

import java.util.*;

/**
 * class EditFacultyCommand
 *
 * @author M.Shubelko
 */
public class EditFacultyCommand implements Command {

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
        FacultyService facultyService = FacultyServiceImpl.getInstance();
        Router router = new Router(PagePath.EDIT_FACULTY_PAGE);
        router.setType(Router.RouterType.REDIRECT);
        HttpSession session = request.getSession();
        try {
            List<Faculty> faculties = facultyService.findAllFaculties();
            request.setAttribute(RequestAttribute.FACULTIES, faculties);
            Long facultyId = Long.valueOf(request.getParameter(RequestParameter.FACULTY_ID));
            String facultyName = request.getParameter(RequestParameter.FACULTY_NAME);
            int recruitmentPlanFree = Integer.parseInt(request.getParameter(RequestParameter.RECRUITMENT_PLAN_FREE));
            int recruitmentPlanCanvas = Integer.parseInt(request.getParameter(RequestParameter.RECRUITMENT_PLAN_CANVAS));
            if (facultyService.isFacultyNameExist(facultyName, facultyId)) {
                session.setAttribute(SessionAttribute.MESSAGE, LocaleMessageKey.FACULTY_NAME_EXIST_ERROR);
            } else{
                if (facultyService.editFaculty(facultyId, facultyName, recruitmentPlanFree, recruitmentPlanCanvas)) {
                    session.setAttribute(SessionAttribute.MESSAGE_RESULT, LocaleMessageKey.FACULTY_EDITED);
                    router.setPage(PagePath.SHOW_FACULTIES_PAGE_REDIRECT);
                }
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Edition faculty failed", e);
            throw new CommandException("Edition faculty failed", e);
        }
        return router;
    }
}
