package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.PagePath;
import com.example.epamcourse.controller.command.Router;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.ApplicantService;
import com.example.epamcourse.model.service.impl.ApplicantServiceImpl;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;

public class AddApplicantSecureInformationCommand extends AbstractCommand {

    @Override
    public Router execute(HttpServletRequest request) throws ServiceException {
        initCommand(request);
        ApplicantService applicantService = ApplicantServiceImpl.getInstance();
        Router router = new Router(PagePath.APPLICANT_ADD_PERSONAL_INF);
        router.setType(Router.RouterType.REDIRECT);
        try {
            if (applicantService.addPersonalInformation(content)) {
                router.setPage(PagePath.MAIN_PAGE_APPLICANT);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Adding administrator secure information failed. {}", e.getMessage());
            throw new ServiceException("Adding administrator secure information failed", e);
        }

        return router;
    }
}
