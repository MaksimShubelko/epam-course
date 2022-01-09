package com.example.epamcourse.controller.command.impl.go;

import com.example.epamcourse.controller.command.PagePath;
import com.example.epamcourse.controller.command.Router;
import com.example.epamcourse.controller.command.SessionAttribute;
import com.example.epamcourse.controller.command.impl.AbstractCommand;
import com.example.epamcourse.model.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.example.epamcourse.controller.command.PagePath.LOGIN;
import static com.example.epamcourse.controller.command.PagePath.LOGIN_REDIRECT;

public class GoToLoginPageCommand extends AbstractCommand {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.CURRENT_PAGE, LOGIN_REDIRECT);
        Router router = new Router(LOGIN);
        router.setType(Router.RouterType.REDIRECT);
        return router;
    }
}
