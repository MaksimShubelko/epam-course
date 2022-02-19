package com.example.epamcourse.controller;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.exception.CommandException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static com.example.epamcourse.controller.command.SessionAttribute.EXCEPTION;


/**
 * class ControllerServlet
 *
 * @author M.Shubelko
 * Overrides doPost and doGet methods by calling
 * the own method processRequest(request, response)
 */
@WebServlet(urlPatterns = "/controller", name = "controller")
public class ControllerServlet extends HttpServlet {

    /**
     * The logger
     */
    private static final Logger logger = LogManager.getLogger();

    /**
     * Do get.
     *
     * @param request the request
     * @param response the response
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    /**
     * Do post.
     *
     * @param request the request
     * @param response the response
     * @throws IOException Signals that an I/O exception has occurred.
     */
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
