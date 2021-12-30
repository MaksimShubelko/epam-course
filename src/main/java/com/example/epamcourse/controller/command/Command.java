package com.example.epamcourse.controller.command;

import com.example.epamcourse.model.exception.ControllerException;
import com.example.epamcourse.model.exception.DaoException;
import com.example.epamcourse.model.exception.ServiceException;


import javax.servlet.http.HttpServletRequest;

public interface Command {
    Router execute(HttpServletRequest request) throws ControllerException, DaoException, ServiceException;
}
