package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * class LogoutCommand
 *
 * @author M.Shubelko
 */
public class LogoutCommand implements Command {

    /**
     * Execute
     *
     * @param request the request
     * @return the router
     * @throws CommandException the command exception
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router(PagePath.LOGIN_REDIRECT);
        router.setType(Router.RouterType.REDIRECT);
        request.getSession().invalidate();
        return router;
    }
}
