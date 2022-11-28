package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.MailingService;
import com.example.epamcourse.model.service.impl.MailingServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;

/**
 * class GoToUploadImagePageCommand
 *
 * @author M.Shubelko
 */
public class SendMessageCommand implements Command {

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
        Router router = new Router(PagePath.SENDER_EMAIL_PAGE);
        String email = request.getParameter(RequestParameter.EMAIL);
        String title = request.getParameter(RequestParameter.TITLE);
        String message = request.getParameter(RequestParameter.MESSAGE);
        MailingService mailingService = MailingServiceImpl.getInstance();
        try {
            if (!(title.isBlank() || message.isBlank())) {
                session.setAttribute(SessionAttribute.MESSAGE_RESULT, LocaleMessageKey.MESSAGE_SEND);
                mailingService.sendMessage(message, title, email);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Sending message failed", e);
            throw new CommandException("Sending message failed", e);
        }
        router.setType(Router.RouterType.FORWARD);
        request.setAttribute(RequestAttribute.EMAIL, email);
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.SENDER_EMAIL_PAGE_REDIRECT);
        return router;

    }
}
