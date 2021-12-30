package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


public abstract class AbstractCommand implements Command {
    protected static final Logger logger = LogManager.getLogger();
    protected SessionRequestContent content = new SessionRequestContent();
    protected Router router;

    protected void initCommand(HttpServletRequest request) {
        content.extractValues(request);
        router = new Router(PagePath.INDEX);
        router.setType(Router.RouterType.REDIRECT);
    }


}