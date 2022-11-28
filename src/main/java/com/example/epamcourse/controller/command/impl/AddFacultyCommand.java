package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.FacultyService;
import com.example.epamcourse.model.service.impl.FacultyServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;

/**
 * class AddFacultyCommand
 *
 * @author M.Shubelko
 */
public class AddFacultyCommand implements Command {

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
        FacultyService facultyService = FacultyServiceImpl.getInstance();
        Router router = new Router(PagePath.ADD_FACULTY_PAGE);
        router.setType(Router.RouterType.REDIRECT);
        try {
            String facultyName = request.getParameter(RequestParameter.FACULTY_NAME);
            int recruitmentPlanFree = Integer.parseInt(request.getParameter(RequestParameter.RECRUITMENT_PLAN_FREE));
            int recruitmentPlanCanvas = Integer.parseInt(request.getParameter(RequestParameter.RECRUITMENT_PLAN_CANVAS));
            if (facultyService.isFacultyNameExist(facultyName)) {
                session.setAttribute(SessionAttribute.MESSAGE, LocaleMessageKey.FACULTY_NAME_EXIST_ERROR);
            } else {
                if (facultyService.addFaculty(facultyName, recruitmentPlanFree, recruitmentPlanCanvas)) {
                    router.setPage(PagePath.MAIN_PAGE_ADMINISTRATOR);
                    session.setAttribute(SessionAttribute.MESSAGE_RESULT, LocaleMessageKey.FACULTY_ADDED);
                }
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Adding faculty failed", e);
            throw new CommandException("Adding faculty failed", e);
        }
        return router;
    }
}
