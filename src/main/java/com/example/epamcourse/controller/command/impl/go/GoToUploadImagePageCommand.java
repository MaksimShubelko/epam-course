package com.example.epamcourse.controller.command.impl.go;

import com.example.epamcourse.controller.command.Command;
import com.example.epamcourse.controller.command.PagePath;
import com.example.epamcourse.controller.command.Router;
import com.example.epamcourse.controller.command.SessionAttribute;
import com.example.epamcourse.model.entity.Account;
import com.example.epamcourse.model.entity.Applicant;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.AccountService;
import com.example.epamcourse.model.service.ApplicantService;
import com.example.epamcourse.model.service.impl.AccountServiceImpl;
import com.example.epamcourse.model.service.impl.ApplicantServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class GoToUploadImagePageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Router router = new Router(PagePath.UPLOAD_IMAGE);
        try {
            AccountService accountService = AccountServiceImpl.getInstance();
            Long accountId = (Long) session.getAttribute(SessionAttribute.ACCOUNT_ID);
            Optional<Account> accountOptional;
            accountOptional = accountService.findAccountById(accountId);
            Account account = accountOptional.orElseThrow(IllegalArgumentException::new);
            String image = accountService.loadImage(account.getLogin());
            session.setAttribute(SessionAttribute.IMAGE, image);
            session.setAttribute(SessionAttribute.LOGIN, account.getLogin());
        } catch (ServiceException e) {
            logger.error("Error when go to the upload image page" + e);
            throw new CommandException("Error when go to the upload image page", e);
        }
        return router;
    }
}
