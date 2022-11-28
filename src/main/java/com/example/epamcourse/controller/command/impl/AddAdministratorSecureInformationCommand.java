package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.AccountService;
import com.example.epamcourse.model.service.AdministratorService;
import com.example.epamcourse.model.service.impl.AccountServiceImpl;
import com.example.epamcourse.model.service.impl.AdministratorServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;

/**
 * class AddAdministratorSecureInformationCommand
 *
 * @author M.Shubelko
 */
public class AddAdministratorSecureInformationCommand implements Command {

    /**
     * The logger.
     */
    private static final Logger logger = LogManager.getLogger();

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
        String name = request.getParameter(RequestParameter.NAME);
        String surname = request.getParameter(RequestParameter.SURNAME);
        String lastname = request.getParameter(RequestParameter.LASTNAME);
        Long accountId = (Long) session.getAttribute(SessionAttribute.ACCOUNT_ID);
        AdministratorService administratorService = AdministratorServiceImpl.getInstance();
        Router router = new Router(PagePath.ADMIN_ADD_PERSONAL_INF);
        try {
            if (administratorService.addPersonalInformation(name, surname, lastname, accountId)) {
                session.setAttribute(SessionAttribute.MESSAGE_RESULT, LocaleMessageKey.PROFILE_CREATED);
                router.setPage(PagePath.MAIN_PAGE_ADMINISTRATOR);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Adding administrator secure information failed.", e);
            throw new CommandException("Adding administrator secure information failed", e);
        }
        router.setType(Router.RouterType.REDIRECT);
        return router;
    }
}
