package com.example.epamcourse.controller.command.impl.go;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

import static com.example.epamcourse.controller.command.PagePath.APPLICANT_ADD_PERSONAL_INF_REDIRECT;
import static com.example.epamcourse.controller.command.PagePath.SENDER_EMAIL_PAGE;

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
