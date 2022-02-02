package com.example.epamcourse.controller.command.impl.go;

import com.example.epamcourse.controller.command.Command;
import com.example.epamcourse.controller.command.PagePath;
import com.example.epamcourse.controller.command.Router;
import com.example.epamcourse.controller.command.SessionAttribute;
import com.example.epamcourse.model.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GoToConfirmEmailPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Router router = new Router(PagePath.CONFIRM_EMAIL_PAGE);
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.CONFIRM_EMAIL_PAGE_REDIRECT);
        return router;
    }
}
