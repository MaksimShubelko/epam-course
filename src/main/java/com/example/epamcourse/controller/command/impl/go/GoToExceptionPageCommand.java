package com.example.epamcourse.controller.command.impl.go;

import com.example.epamcourse.controller.command.Command;
import com.example.epamcourse.controller.command.Router;
import com.example.epamcourse.controller.command.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.example.epamcourse.controller.command.PagePath.*;

public class GoToExceptionPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.CURRENT_PAGE, EXCEPTION_ERROR_REDIRECT);
        Router router = new Router(EXCEPTION_ERROR_PAGE);
        router.setType(Router.RouterType.REDIRECT);
        return router;
    }
}
