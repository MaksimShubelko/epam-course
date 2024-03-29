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

/**
 * class GoToAddingAdminAccountPageCommand
 *
 * @author M.Shubelko
 */
public class GoToAddingAdminAccountPageCommand implements Command {

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
        Router router = new Router(ADD_ADMIN_ACCOUNT_PAGE);
        session.setAttribute(SessionAttribute.CURRENT_PAGE, ADD_ADMIN_ACCOUNT_PAGE_REDIRECT);
        return router;
    }
}
