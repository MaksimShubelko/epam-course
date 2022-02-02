package com.example.epamcourse.controller.command.impl.go;

import com.example.epamcourse.controller.command.Command;
import com.example.epamcourse.controller.command.PagePath;
import com.example.epamcourse.controller.command.Router;
import com.example.epamcourse.controller.command.SessionAttribute;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GoToAddFacultyPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router(PagePath.ADD_FACULTY_PAGE);
        router.setType(Router.RouterType.REDIRECT);
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.ADD_FACULTY_PAGE_REDIRECT);
        return router;
    }
}