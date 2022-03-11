package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.entity.Faculty;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.FacultyService;
import com.example.epamcourse.model.service.impl.FacultyServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * class DeleteFacultyCommand
 *
 * @author M.Shubelko
 */
public class DeleteFacultyCommand implements Command {

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
        int page = Integer.parseInt(request.getParameter(RequestParameter.PAGE));
        Router router = new Router(PagePath.SHOW_FACULTIES_REDIRECT + page);
        try {
            Long facultyId = Long.valueOf(request.getParameter(RequestParameter.FACULTY_ID));
            facultyService.deleteFaculty(facultyId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Deleting faculty failed", e);
            throw new CommandException("Deleting faculty failed", e);
        }
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.SHOW_FACULTIES_REDIRECT);
        return router;
    }
}
