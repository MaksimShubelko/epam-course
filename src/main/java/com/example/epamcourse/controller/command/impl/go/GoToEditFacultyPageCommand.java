package com.example.epamcourse.controller.command.impl.go;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.entity.Account;
import com.example.epamcourse.model.entity.Applicant;
import com.example.epamcourse.model.entity.Faculty;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.AccountService;
import com.example.epamcourse.model.service.ApplicantService;
import com.example.epamcourse.model.service.FacultyService;
import com.example.epamcourse.model.service.impl.AccountServiceImpl;
import com.example.epamcourse.model.service.impl.ApplicantServiceImpl;
import com.example.epamcourse.model.service.impl.FacultyServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;

/**
 * class GoToEditFacultyPageCommand
 *
 * @author M.Shubelko
 */
public class GoToEditFacultyPageCommand implements Command {

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
        Router router = new Router(PagePath.EDIT_FACULTY_PAGE);
        int page = Integer.parseInt(request.getParameter(RequestParameter.PAGE));
        router.setType(Router.RouterType.REDIRECT);
        try {
            FacultyService facultyService = FacultyServiceImpl.getInstance();
            HttpSession session = request.getSession();
            Long facultyId = Long.valueOf(request.getParameter(RequestParameter.FACULTY_ID));
            Optional<Faculty> facultyOptional = facultyService.findFacultyById(facultyId);
            facultyOptional.ifPresent(faculty -> session.setAttribute(SessionAttribute.FACULTY, faculty));
            session.setAttribute(SessionAttribute.PAGE, page);
            session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.EDIT_FACULTY_REDIRECT + facultyId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Edition faculty's data failed.", e);
            throw new CommandException("Edition faculty's data failed.", e);
        }
        return router;
    }
}
