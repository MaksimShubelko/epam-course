package com.example.epamcourse.controller.command.impl.go;

import com.example.epamcourse.controller.command.Router;
import com.example.epamcourse.controller.command.SessionAttribute;
import com.example.epamcourse.controller.command.impl.AbstractCommand;
import com.example.epamcourse.model.exception.ControllerException;
import com.example.epamcourse.model.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.example.epamcourse.controller.command.PagePath.*;

public class GoToRegistrationPageCommand extends AbstractCommand {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.CURRENT_PAGE, CREATION_ACCOUNT_REDIRECT);
        Router router = new Router(CREATION_ACCOUNT);
        router.setType(Router.RouterType.REDIRECT);
        return router;
    }
}
