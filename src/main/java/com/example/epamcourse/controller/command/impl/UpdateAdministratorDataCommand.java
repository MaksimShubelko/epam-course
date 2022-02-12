package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.entity.Faculty;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.AdministratorService;
import com.example.epamcourse.model.service.ApplicantService;
import com.example.epamcourse.model.service.FacultyService;
import com.example.epamcourse.model.service.impl.AdministratorServiceImpl;
import com.example.epamcourse.model.service.impl.ApplicantServiceImpl;
import com.example.epamcourse.model.service.impl.FacultyServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;

public class UpdateAdministratorDataCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        AdministratorService administratorService = AdministratorServiceImpl.getInstance();
        Router router = new Router(PagePath.EDIT_ADMINISTRATOR_DATA);
        try {
            Long administratorId = (Long) request.getSession().getAttribute(SessionAttribute.ADMINISTRATOR_ID);
            String name = request.getParameter(RequestParameter.NAME);
            String surname = request.getParameter(RequestParameter.SURNAME);
            String lastname = request.getParameter(RequestParameter.LASTNAME);
            if (administratorService.editAdministratorPersonalInformation(name, surname, lastname, administratorId)) {
                router.setPage(PagePath.MAIN_PAGE_ADMINISTRATOR);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Updating administrator secure information failed", e);
            throw new CommandException("Updating administrator secure information failed", e);
        }
        session.setAttribute(SessionAttribute.CURRENT_PAGE, router.getPage());
        return router;
    }
}
