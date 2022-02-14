package com.example.epamcourse.controller.command.impl.go;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.entity.Faculty;
import com.example.epamcourse.model.entity.Recruitment;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.ApplicantService;
import com.example.epamcourse.model.service.FacultyService;
import com.example.epamcourse.model.service.RecruitmentService;
import com.example.epamcourse.model.service.impl.ApplicantServiceImpl;
import com.example.epamcourse.model.service.impl.FacultyServiceImpl;
import com.example.epamcourse.model.service.impl.RecruitmentServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;

import static com.example.epamcourse.controller.command.PagePath.*;
import static com.example.epamcourse.controller.command.RequestAttribute.FACULTIES;

public class GoToMainPageApplicantCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        FacultyService facultyService = FacultyServiceImpl.getInstance();
        RecruitmentService recruitmentService = RecruitmentServiceImpl.getInstance();
        Router router = new Router(MAIN_PAGE_APPLICANT);
        HttpSession session = request.getSession();
        try {
            Recruitment recruitment = recruitmentService.findRecruitment();
            List<Faculty> faculties = facultyService.findAllFaculties();
            request.setAttribute(RequestAttribute.FACULTIES, faculties);
            request.setAttribute(RequestAttribute.RECRUITMENT, recruitment);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Go to main page applicant failed.", e);
            throw new CommandException("Go to main page applicant failed.", e);
        }
        session.setAttribute(SessionAttribute.CURRENT_PAGE, MAIN_PAGE_APPLICANT_REDIRECT);
        return router;
    }
}
