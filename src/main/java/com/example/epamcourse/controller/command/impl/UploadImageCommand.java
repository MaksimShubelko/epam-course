package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.entity.Account;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.AccountService;
import com.example.epamcourse.model.service.impl.AccountServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;

/**
 * class UploadImageCommand
 *
 * @author M.Shubelko
 */
public class UploadImageCommand implements Command {

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
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        AccountService accountService = AccountServiceImpl.getInstance();
        String pagePath = (String) session.getAttribute(RequestAttribute.PAGE);
        Router router;
        try {
            String login = (String) session.getAttribute(SessionAttribute.LOGIN);
            Account.Role role = accountService.getAccountRoleByLogin(login);
            switch (role) {
                case APPLICANT -> router = new Router(PagePath.EDIT_APPLICANT_DATA_REDIRECT);
                case ADMIN -> router = new Router(PagePath.EDIT_ADMINISTRATOR_DATA_REDIRECT);
                default -> throw new IllegalArgumentException();
            }
            router.setType(Router.RouterType.REDIRECT);
            InputStream inputStream = (InputStream) request.getAttribute(RequestAttribute.IMAGE_INPUT_STREAM);
            String fileName = (String) request.getAttribute(RequestAttribute.IMAGE_NAME);
            accountService.uploadImage(inputStream, fileName, login);
        } catch (ServiceException e) {
            logger.error("Try to execute UploadImageCommand was failed" + e);
            throw new CommandException("Try to execute UploadImageCommand was failed", e);
        }

        return router;

    }

}
