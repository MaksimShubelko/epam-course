package com.example.epamcourse.controller.command.impl.go;

import com.example.epamcourse.controller.command.Router;
import com.example.epamcourse.controller.command.SessionAttribute;
import com.example.epamcourse.controller.command.impl.AbstractCommand;
import com.example.epamcourse.model.entity.Faculty;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.FacultyService;
import com.example.epamcourse.model.service.impl.FacultyServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import static com.example.epamcourse.controller.command.PagePath.*;
import static com.example.epamcourse.controller.command.RequestAttribute.FACULTIES;

public class GoToMainPageApplicantCommand extends AbstractCommand {
    @Override
    public Router execute(HttpServletRequest request) throws ServiceException {
        initCommand(request);
        HttpSession session = request.getSession();
        FacultyService facultyService = FacultyServiceImpl.getInstance();
        session.setAttribute(SessionAttribute.CURRENT_PAGE, APPLICANT_ADD_PERSONAL_INF_REDIRECT);
        Router router = new Router(APPLICANT_ADD_PERSONAL_INF);
        List<Faculty> faculties = facultyService.findAllFaculties(content);
        System.out.println(faculties);
        request.setAttribute(FACULTIES, faculties);
        content.addRequestAttribute(FACULTIES, facultyService);
        router.setType(Router.RouterType.REDIRECT);
        return router;
    }
}
