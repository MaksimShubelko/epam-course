package com.example.epamcourse.controller.command.impl.go;

import com.example.epamcourse.controller.command.Command;
import com.example.epamcourse.controller.command.RequestParameter;
import com.example.epamcourse.controller.command.Router;
import com.example.epamcourse.controller.command.SessionAttribute;
import com.example.epamcourse.model.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.example.epamcourse.controller.command.PagePath.*;

public class GoToAdministratorMainPage implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Router router = new Router(MAIN_PAGE_ADMINISTRATOR);
        session.setAttribute(SessionAttribute.CURRENT_PAGE, MAIN_PAGE_ADMINISTRATOR_REDIRECT);
        return router;
    }
}
