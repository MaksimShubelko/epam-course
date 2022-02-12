package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.entity.Faculty;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.FacultyService;
import com.example.epamcourse.model.service.impl.FacultyServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

public class AddFacultyCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        FacultyService facultyService = FacultyServiceImpl.getInstance();
        Router router = new Router(PagePath.ADD_FACULTY_PAGE);
        try {
            String facultyName = request.getParameter(RequestParameter.FACULTY_NAME);
            int recruitmentPlanFree = Integer.parseInt(request.getParameter(RequestParameter.RECRUITMENT_PLAN_FREE));
            int recruitmentPlanCanvas = Integer.parseInt(request.getParameter(RequestParameter.RECRUITMENT_PLAN_CANVAS));
            if (facultyService.addFaculty(facultyName, recruitmentPlanFree, recruitmentPlanCanvas)) {
                router.setPage(PagePath.MAIN_PAGE_ADMINISTRATOR);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Adding faculty failed", e);
            throw new CommandException("Adding faculty failed", e);
        }
        return router;
    }
}
