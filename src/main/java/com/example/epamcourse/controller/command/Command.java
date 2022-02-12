package com.example.epamcourse.controller.command;

import com.example.epamcourse.model.exception.CommandException;

import jakarta.servlet.http.HttpServletRequest;

public interface Command {
    Router execute(HttpServletRequest request) throws CommandException;
}
