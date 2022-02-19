package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.Command;
import com.example.epamcourse.controller.command.RequestParameter;
import com.example.epamcourse.controller.command.Router;
import com.example.epamcourse.controller.command.SessionAttribute;
import com.example.epamcourse.model.exception.CommandException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * class LocaleCommand
 *
 * @author M.Shubelko
 */
public class  LocaleCommand implements Command {

    /** The logger. */
    private static final Logger logger = LogManager.getLogger();

    /**
     * Execute
     *
     * @param request the request
     * @return the router
     * @throws CommandException the command exception
     */
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
        logger.log(Level.DEBUG, "Current page is " + currentPage);
        Router router = new Router(currentPage);
        String currentLocale = (String) session.getAttribute(SessionAttribute.SESSION_LOCALE);
        String newLocale = request.getParameter(RequestParameter.LOCALE);
        if (!newLocale.equals(currentLocale)) {
            logger.log(Level.DEBUG, "Language changes to {} ", newLocale);
            session.setAttribute(SessionAttribute.SESSION_LOCALE, newLocale);
        }
        return router;
    }
}
