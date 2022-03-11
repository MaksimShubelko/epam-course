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

/**
 * class GoToUploadImagePageCommand
 *
 * @author M.Shubelko
 */
public class GoToUploadImagePageCommand implements Command {

    /** The logger. */
    private static final Logger logger = LogManager.getLogger();

    /**
     * Execute
     *
     * @param request the request
     * @return the router
     * @throws CommandException the command exception
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Router router = new Router(PagePath.UPLOAD_IMAGE);
        router.setType(Router.RouterType.REDIRECT);
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
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.UPLOAD_IMAGE_REDIRECT);
        return router;
    }
}
