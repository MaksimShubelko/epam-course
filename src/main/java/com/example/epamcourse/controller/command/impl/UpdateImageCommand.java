package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.Command;
import com.example.epamcourse.controller.command.Router;
import com.example.epamcourse.model.exception.CommandException;

import jakarta.servlet.http.HttpServletRequest;

public class UpdateImageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return null;
    }
}
