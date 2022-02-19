package com.example.epamcourse.controller.command;

import com.example.epamcourse.model.exception.CommandException;

import jakarta.servlet.http.HttpServletRequest;

/**
 * interface Command
 *
 * @author M.Shubelko
 */
public interface Command {

    /**
     * Execute.
     *
     * @param request the request
     * @return Router
     * @throws CommandException the command exception
     */
    Router execute(HttpServletRequest request) throws CommandException;
}
