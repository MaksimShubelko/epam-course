package com.example.epamcourse.controller.command.impl.go;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.entity.Faculty;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.ApplicantService;
import com.example.epamcourse.model.service.FacultyService;
import com.example.epamcourse.model.service.impl.ApplicantServiceImpl;
import com.example.epamcourse.model.service.impl.FacultyServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.example.epamcourse.controller.command.PagePath.*;
import static com.example.epamcourse.controller.command.RequestAttribute.FACULTIES;

public class GoToSenderPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        request.getSession().setAttribute(SessionAttribute.CURRENT_PAGE, APPLICANT_ADD_PERSONAL_INF_REDIRECT);
        Router router = new Router(SENDER_EMAIL_PAGE);
        router.setType(Router.RouterType.FORWARD);
        String email = request.getParameter(RequestParameter.EMAIL);
        request.setAttribute(RequestAttribute.EMAIL, email);
        return router;
    }
}
