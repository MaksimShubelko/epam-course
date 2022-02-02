package com.example.epamcourse.controller;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.epamcourse.controller.command.SessionAttribute.EXCEPTION;

@WebServlet(urlPatterns = {"/controller"})
public class ControllerServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String stringCommand = request.getParameter(RequestParameter.COMMAND);
        Command command = CommandProvider.defineCommand(stringCommand).orElseThrow(IllegalArgumentException::new);
        try {
            Router router = command.execute(request);
            switch (router.getType()) {
                case FORWARD -> request.getRequestDispatcher(router.getPage()).forward(request, response);
                case REDIRECT -> response.sendRedirect(request.getContextPath() + router.getPage());
                default -> {
                    logger.log(Level.ERROR, "Router type {} is incorrect", router.getType());
                    response.sendRedirect(request.getContextPath() + PagePath.ERROR_404);
                }
            }
        } catch (IOException | CommandException | ServletException e) {
            logger.log(Level.ERROR, "Error when executing command {} ", stringCommand);
            request.getSession().setAttribute(EXCEPTION, e);
            response.sendRedirect(request.getContextPath() + PagePath.EXCEPTION_ERROR_REDIRECT);
        }
    }
}
