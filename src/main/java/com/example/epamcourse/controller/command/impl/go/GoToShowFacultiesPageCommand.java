package com.example.epamcourse.controller.command.impl.go;

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

import static com.example.epamcourse.controller.command.PagePath.SHOW_FACULTIES_PAGE;

/**
 * class GoToShowFacultiesPageCommand
 *
 * @author M.Shubelko
 */
public class GoToShowFacultiesPageCommand implements Command {

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
        final int recordsPerPage = 5;
        int page = 1;
        HttpSession session = request.getSession();
        Router router = new Router(SHOW_FACULTIES_PAGE);
        FacultyService facultyService = FacultyServiceImpl.getInstance();
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.SHOW_FACULTIES_REDIRECT);
        try {
            System.out.println(facultyService.getCountOfFaculties());
            long noOfPages = (long) Math.ceil(facultyService.getCountOfFaculties() * 1.0 / recordsPerPage);
            if (request.getParameter(RequestParameter.PAGE) != null) {
                page = Integer.parseInt(request.getParameter(RequestParameter.PAGE));
            }
            List<Faculty> faculties = facultyService.findFaculties(page);
            request.setAttribute(RequestAttribute.FACULTIES, faculties);
            request.setAttribute(RequestAttribute.PAGE, page);
            request.setAttribute(RequestAttribute.COUNT_PAGES, noOfPages);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Finding faculties failed", e);
            throw new CommandException("Finding faculties failed", e);
        }
        return router;
    }

}
