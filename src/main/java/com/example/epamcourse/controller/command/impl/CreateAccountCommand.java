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
 * class CreateAccountCommand
 *
 * @author M.Shubelko
 */
public class CreateAccountCommand implements Command {

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
        AccountService accountService = AccountServiceImpl.getInstance();
        HttpSession session = request.getSession();
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String passwordCheck = request.getParameter(RequestParameter.PASSWORD_CHECK);
        String email = request.getParameter(RequestParameter.EMAIL);
        String ip = request.getRemoteAddr();
        Router router = new Router(PagePath.CREATION_ACCOUNT);
        router.setType(Router.RouterType.REDIRECT);
        try {
            if (accountService.isAccountLoginExist(login)) {
                session.setAttribute(SessionAttribute.MESSAGE, LocaleMessageKey.LOGIN_PRESENT_ERROR);
            } else {
                if (accountService.isIpPresent(ip)) {
                    session.setAttribute(SessionAttribute.MESSAGE, LocaleMessageKey.IP_PRESENT_ERROR);
                } else {
                    if (accountService.validateRegistrationData(login, password, passwordCheck, email)) {
                        router.setPage(PagePath.CONFIRM_EMAIL_PAGE);
                        MailingService mailingService = MailingServiceImpl.getInstance();
                        EmailCodeGenerator emailCodeGenerator = EmailCodeGeneratorImpl.getInstance();
                        int code = emailCodeGenerator.generateCode();
                        mailingService.sendMessage(EmailMessages.MESSAGE_BODY_CONFIRM_EMAIL + code, EmailMessages.MESSAGE_HEAD, email);
                        session.setAttribute(RequestAttribute.EMAIL_CODE_EXPECTED, code);
                        session.setAttribute(RequestAttribute.LOGIN, login);
                        session.setAttribute(RequestAttribute.EMAIL, email);
                        session.setAttribute(RequestAttribute.PASSWORD, password);
                        session.setAttribute(RequestAttribute.PASSWORD_CHECK, passwordCheck);
                        session.setAttribute(RequestAttribute.IP, ip);
                    }
                }
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Register account failed.", e);
            throw new CommandException("Register account failed.", e);
        }
        session.setAttribute(SessionAttribute.CURRENT_PAGE, router.getPage());
        return router;
    }

}
