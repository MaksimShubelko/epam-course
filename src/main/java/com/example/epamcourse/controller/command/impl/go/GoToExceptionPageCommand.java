package com.example.epamcourse.controller.command.impl.go;

import com.example.epamcourse.controller.command.Command;
import com.example.epamcourse.controller.command.Router;
import com.example.epamcourse.controller.command.SessionAttribute;

import com.example.epamcourse.model.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.example.epamcourse.controller.command.PagePath.*;

/**
 * class GoToExceptionPageCommand
 *
 * @author M.Shubelko
 */
public class GoToExceptionPageCommand implements Command {

    /**
     * Execute
     *
     * @param request the request
     * @return the router
     */
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.CURRENT_PAGE, EXCEPTION_ERROR_REDIRECT);
        Router router = new Router(EXCEPTION_ERROR_PAGE);
        return router;
    }
}
