package com.example.epamcourse.controller.command.impl.go;

import com.example.epamcourse.controller.command.Command;
import com.example.epamcourse.controller.command.Router;
import com.example.epamcourse.controller.command.SessionAttribute;
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

import static com.example.epamcourse.controller.command.PagePath.APPLICANT_ADD_PERSONAL_INF;
import static com.example.epamcourse.controller.command.PagePath.APPLICANT_ADD_PERSONAL_INF_REDIRECT;
import static com.example.epamcourse.controller.command.RequestAttribute.FACULTIES;

public class GoToMainPageApplicantCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        FacultyService facultyService = FacultyServiceImpl.getInstance();
        Router router = new Router(APPLICANT_ADD_PERSONAL_INF);
        router.setType(Router.RouterType.REDIRECT);
        HttpSession session = request.getSession();
        try {
            List<Faculty> faculties = facultyService.findAllFaculties();
            session.setAttribute(SessionAttribute.FACULTIES, faculties);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Go to main page applicant failed.", e);
            throw new CommandException("Go to main page applicant failed.", e);
        }
        session.setAttribute(SessionAttribute.CURRENT_PAGE, router.getPage());
        return router;
    }
}
