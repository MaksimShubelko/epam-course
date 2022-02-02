package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.MailingService;
import com.example.epamcourse.model.service.impl.MailingServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class SendMessageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router(PagePath.SENDER_EMAIL_PAGE);
        String email = request.getParameter(RequestParameter.EMAIL);
        String title = request.getParameter(RequestParameter.TITLE);
        String message = request.getParameter(RequestParameter.MESSAGE);
        MailingService mailingService = MailingServiceImpl.getInstance();
        try {
            mailingService.sendMessage(message, title, email);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Sending message failed", e);
            throw new CommandException("Sending message failed", e);
        }
        router.setType(Router.RouterType.FORWARD);
        request.setAttribute(RequestAttribute.EMAIL, email);
        return router;

    }
}