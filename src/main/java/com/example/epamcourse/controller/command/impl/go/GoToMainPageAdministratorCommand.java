package com.example.epamcourse.controller.command.impl.go;

import com.example.epamcourse.controller.command.Router;
import com.example.epamcourse.controller.command.impl.AbstractCommand;
import com.example.epamcourse.model.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class GoToMainPageAdministratorCommand extends AbstractCommand {
    @Override
    public Router execute(HttpServletRequest request) throws ServiceException {
        return null;
    }
}
