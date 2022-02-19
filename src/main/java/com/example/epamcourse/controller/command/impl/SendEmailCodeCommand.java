package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.AccountService;
import com.example.epamcourse.model.service.MailingService;
import com.example.epamcourse.model.service.impl.AccountServiceImpl;
import com.example.epamcourse.model.service.impl.MailingServiceImpl;
import com.example.epamcourse.util.EmailCodeGenerator;
import com.example.epamcourse.util.EmailMessages;
import com.example.epamcourse.util.impl.EmailCodeGeneratorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * class SendEmailCodeCommand
 *
 * @author M.Shubelko
 */
public class SendEmailCodeCommand implements Command {

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
        String login = (String) session.getAttribute(RequestParameter.LOGIN);
        String password = (String) session.getAttribute(RequestParameter.PASSWORD);
        String passwordCheck = (String) session.getAttribute(RequestParameter.PASSWORD_CHECK);
        String email = (String) session.getAttribute(RequestParameter.EMAIL);
        String ip = (String) session.getAttribute(RequestParameter.IP);
        Router router = new Router(PagePath.CONFIRM_EMAIL_PAGE);
        router.setType(Router.RouterType.REDIRECT);
        int codeExpected = Integer.parseInt(request.getParameter(RequestParameter.EMAIL_CODE_EXPECTED));
        String codeActualString = request.getParameter(RequestParameter.EMAIL_CODE_ACTUAL);
        int codeActual = Integer.parseInt(codeActualString.isBlank() ? "" : codeActualString);
        if (codeActual == codeExpected) {
            try {
                if (accountService.addAccount(login, password, passwordCheck, email, ip)) {
                    router.setPage(PagePath.LOGIN);
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Adding account failed. FinishRegistrationCommand failed", e);
                throw new CommandException("Adding account failed. FinishRegistrationCommand failed", e);
            }
        }
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.CONFIRM_EMAIL_PAGE_REDIRECT);
        return router;
    }
}
