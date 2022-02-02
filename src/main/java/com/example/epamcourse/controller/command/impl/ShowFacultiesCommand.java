package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.entity.Applicant;
import com.example.epamcourse.model.entity.Faculty;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.ApplicantService;
import com.example.epamcourse.model.service.BillService;
import com.example.epamcourse.model.service.FacultyService;
import com.example.epamcourse.model.service.impl.ApplicantServiceImpl;
import com.example.epamcourse.model.service.impl.BillServiceImpl;
import com.example.epamcourse.model.service.impl.FacultyServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.example.epamcourse.controller.command.PagePath.*;

public class ShowFacultiesCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        final int recordsPerPage = 5;
        int page = 1;
        HttpSession session = request.getSession();
        Router router = new Router(SHOW_FACULTIES_PAGE);
        ApplicantService applicantService = ApplicantServiceImpl.getInstance();
        FacultyService facultyService = FacultyServiceImpl.getInstance();
        try {
            long noOfPages = (long) Math.ceil(facultyService.findAllFaculties().size() * 1.0 / recordsPerPage);
            if (request.getParameter(RequestParameter.PAGE) != null) {
                page = Integer.parseInt(request.getParameter(RequestParameter.PAGE));
            }
            List<Faculty> faculties = facultyService.findFaculties(page);
            request.setAttribute(RequestAttribute.FACULTIES, faculties);
            request.setAttribute(RequestAttribute.CURRENT_PAGE, page);
            request.setAttribute(RequestAttribute.COUNT_PAGES, noOfPages);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Finding applicants failed", e);
            throw new CommandException("Finding applicants failed", e);
        }
        session.setAttribute(SessionAttribute.CURRENT_PAGE, router.getPage());
        return router;
    }
}