package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.RequestParameter;
import com.example.epamcourse.controller.command.Router;
import com.example.epamcourse.controller.command.SessionAttribute;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LocaleCommand extends AbstractCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
        logger.log(Level.DEBUG, "Current page is " + currentPage);
        Router router = new Router(currentPage);
        router.setType(Router.RouterType.REDIRECT);
        String currentLocale = (String) session.getAttribute(SessionAttribute.SESSION_LOCALE);
        String newLocale = request.getParameter(RequestParameter.LOCALE);

        if (!newLocale.equals(currentLocale)) {
            session.setAttribute(SessionAttribute.SESSION_LOCALE, newLocale);
            logger.log(Level.DEBUG, "Language changes to {} ", newLocale);
        }

        return router;
    }
}
