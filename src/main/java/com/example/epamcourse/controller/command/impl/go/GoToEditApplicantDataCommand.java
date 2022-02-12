package com.example.epamcourse.controller.command.impl.go;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.entity.Account;
import com.example.epamcourse.model.entity.Applicant;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.AccountService;
import com.example.epamcourse.model.service.ApplicantService;
import com.example.epamcourse.model.service.impl.AccountServiceImpl;
import com.example.epamcourse.model.service.impl.ApplicantServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;

public class GoToEditApplicantDataCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Router router = new Router(PagePath.EDIT_APPLICANT_DATA);
        router.setType(Router.RouterType.REDIRECT);
        try {
            ApplicantService applicantService = ApplicantServiceImpl.getInstance();
            AccountService accountService = AccountServiceImpl.getInstance();
            Long accountId = (Long) request.getSession().getAttribute(SessionAttribute.ACCOUNT_ID);
            Long applicantId = (Long) request.getSession().getAttribute(SessionAttribute.APPLICANT_ID);
            Optional<Applicant> applicantOptional = applicantService.getApplicantById(applicantId);
            Optional<Account> accountOptional = accountService.findAccountById(accountId);
            Applicant applicant = applicantOptional.orElseThrow(IllegalArgumentException::new);
            Account account = accountOptional.orElseThrow(IllegalArgumentException::new);
            String image = accountService.loadImage(account.getLogin());
            System.out.println(image + " image");
            session.setAttribute(SessionAttribute.ACCOUNT, account);
            session.setAttribute(SessionAttribute.IMAGE, image);
            session.setAttribute(SessionAttribute.APPLICANT, applicant);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Go to edition applicant's data failed.", e);
            throw new CommandException("Go to edition applicant's data failed.", e);
        }
        session.setAttribute(SessionAttribute.CURRENT_PAGE, router.getPage());
        return router;
    }
}
