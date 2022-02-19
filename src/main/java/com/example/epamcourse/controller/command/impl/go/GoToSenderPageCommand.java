package com.example.epamcourse.controller.command.impl.go;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.example.epamcourse.controller.command.PagePath.*;

/**
 * class GoToSenderPageCommand
 *
 * @author M.Shubelko
 */
public class GoToSenderPageCommand implements Command {

    /**
     * Execute
     *
     * @param request the request
     * @return the router
     * @throws CommandException the command exception
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.CURRENT_PAGE, SENDER_EMAIL_PAGE_REDIRECT);
        Router router = new Router(SENDER_EMAIL_PAGE);
        String email = request.getParameter(RequestParameter.EMAIL);
        if (email != null) {
            session.setAttribute(SessionAttribute.EMAIL, email);
        }
        return router;
    }
}
