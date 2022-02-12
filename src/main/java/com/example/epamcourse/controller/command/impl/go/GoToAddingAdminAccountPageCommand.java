package com.example.epamcourse.controller.command.impl.go;

import com.example.epamcourse.controller.command.Command;
import com.example.epamcourse.controller.command.Router;
import com.example.epamcourse.controller.command.SessionAttribute;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.example.epamcourse.controller.command.PagePath.ADD_ADMIN_ACCOUNT_PAGE;
import static com.example.epamcourse.controller.command.PagePath.ADD_ADMIN_ACCOUNT_PAGE_REDIRECT;

public class GoToAddingAdminAccountPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.CURRENT_PAGE, ADD_ADMIN_ACCOUNT_PAGE_REDIRECT);
        Router router = new Router(ADD_ADMIN_ACCOUNT_PAGE);
        router.setType(Router.RouterType.REDIRECT);
        return router;
    }
}
