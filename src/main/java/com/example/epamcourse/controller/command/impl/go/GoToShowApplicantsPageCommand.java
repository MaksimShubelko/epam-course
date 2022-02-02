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

import static com.example.epamcourse.controller.command.PagePath.*;
import static com.example.epamcourse.controller.command.PagePath.APPLICANT_ADD_PERSONAL_INF;
import static com.example.epamcourse.controller.command.RequestAttribute.FACULTIES;

public class GoToShowApplicantsPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        FacultyService facultyService = FacultyServiceImpl.getInstance();
        ApplicantService applicantService = ApplicantServiceImpl.getInstance();
        Router router = new Router(SHOW_APPLICANTS_PAGE);
        List<Faculty> faculties = null;
        try {
            faculties = facultyService.findAllFaculties();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Go to show applicants failed", e);
            throw new CommandException("Go to show applicants failed", e);
        }
        request.setAttribute(FACULTIES, faculties);
        return router;
    }
}
