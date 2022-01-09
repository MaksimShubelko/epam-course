package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.AccountService;
import com.example.epamcourse.model.service.AdministratorService;
import com.example.epamcourse.model.service.impl.AccountServiceImpl;
import com.example.epamcourse.model.service.impl.AdministratorServiceImpl;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;

public class AddAdministratorSecureInformationCommand extends AbstractCommand {
    @Override
    public Router execute(HttpServletRequest request) throws ServiceException {
        initCommand(request);
        AdministratorService administratorService = AdministratorServiceImpl.getInstance();
        Router router = new Router(PagePath.ADMIN_ADD_PERSONAL_INF);
        router.setType(Router.RouterType.REDIRECT);
        try {
            if (administratorService.addPersonalInformation(content)) {
                router.setPage(PagePath.MAIN_PAGE_ADMINISTRATOR);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Adding administrator secure information failed. {}", e.getMessage());
            throw new ServiceException("Adding administrator secure information failed", e);
        }

        return router;
    }
}
