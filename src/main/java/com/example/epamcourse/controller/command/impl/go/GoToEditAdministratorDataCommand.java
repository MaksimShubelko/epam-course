package com.example.epamcourse.controller.command.impl.go;

import com.example.epamcourse.controller.command.Command;
import com.example.epamcourse.controller.command.PagePath;
import com.example.epamcourse.controller.command.Router;
import com.example.epamcourse.controller.command.SessionAttribute;
import com.example.epamcourse.model.entity.Account;
import com.example.epamcourse.model.entity.Administrator;
import com.example.epamcourse.model.entity.Applicant;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.AccountService;
import com.example.epamcourse.model.service.AdministratorService;
import com.example.epamcourse.model.service.ApplicantService;
import com.example.epamcourse.model.service.impl.AccountServiceImpl;
import com.example.epamcourse.model.service.impl.AdministratorServiceImpl;
import com.example.epamcourse.model.service.impl.ApplicantServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class GoToEditAdministratorDataCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Router router = new Router(PagePath.EDIT_ADMINISTRATOR_DATA);
        router.setType(Router.RouterType.REDIRECT);
        try {
            AdministratorService administratorService = AdministratorServiceImpl.getInstance();
            Long administratorId = (Long) session.getAttribute(SessionAttribute.ADMINISTRATOR_ID);
            System.out.println(administratorId);
            Optional<Administrator> administratorOptional = administratorService.getAdministratorById(administratorId);
            Administrator administrator = administratorOptional.orElseThrow(IllegalArgumentException::new);
            session.setAttribute(SessionAttribute.ADMINISTRATOR, administrator);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Go to edition applicant's data failed.", e);
            throw new CommandException("Go to edition applicant's data failed.", e);
        }
        session.setAttribute(SessionAttribute.CURRENT_PAGE, router.getPage());
        return router;
    }
}