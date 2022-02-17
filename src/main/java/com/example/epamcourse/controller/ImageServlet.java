package com.example.epamcourse.controller;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.exception.CommandException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

import static com.example.epamcourse.controller.command.SessionAttribute.EXCEPTION;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024,
        maxRequestSize = 1024 * 1024)
@WebServlet(urlPatterns = "/upload_image_controller")
public class ImageServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Part content = request.getPart(RequestParameter.IMAGE);
        InputStream imageInputStream = content.getInputStream();
        String imageName = content.getSubmittedFileName();
        request.setAttribute(RequestAttribute.IMAGE_INPUT_STREAM, imageInputStream);
        request.setAttribute(RequestAttribute.IMAGE_NAME, imageName);
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