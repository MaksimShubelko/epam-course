package com.example.epamcourse.controller.command;

import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    Router execute(HttpServletRequest request) throws CommandException;
}
