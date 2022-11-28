package com.example.epamcourse.controller.command.impl.go;

import com.example.epamcourse.controller.command.Command;
import com.example.epamcourse.controller.command.PagePath;
import com.example.epamcourse.controller.command.Router;
import com.example.epamcourse.controller.command.SessionAttribute;
import com.example.epamcourse.model.entity.Account;
import com.example.epamcourse.model.entity.Administrator;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.AccountService;
import com.example.epamcourse.model.service.AdministratorService;
import com.example.epamcourse.model.service.impl.AccountServiceImpl;
import com.example.epamcourse.model.service.impl.AdministratorServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;

/**
 * class GoToEditAdministratorDataPageCommand
 *
 * @author M.Shubelko
 */
public class GoToEditAdministratorDataPageCommand implements Command {

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
        Router router = new Router(PagePath.EDIT_ADMINISTRATOR_DATA);
        AccountService accountService = AccountServiceImpl.getInstance();
        try {
            Long accountId = (Long) session.getAttribute(SessionAttribute.ACCOUNT_ID);
            Optional<Account> accountOptional = accountService.findAccountById(accountId);
            Account account = accountOptional.orElseThrow(IllegalArgumentException::new);
            AdministratorService administratorService = AdministratorServiceImpl.getInstance();
            Optional<Administrator> administratorOptional = administratorService.findAdministratorByAccountId(accountId);
            Administrator administrator = administratorOptional.orElseThrow(IllegalArgumentException::new);
            String image = accountService.loadImage(account.getLogin());
            session.setAttribute(SessionAttribute.ACCOUNT, account);
            session.setAttribute(SessionAttribute.IMAGE, image);
            session.setAttribute(SessionAttribute.ADMINISTRATOR, administrator);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Go to edition applicant's data failed.", e);
            throw new CommandException("Go to edition applicant's data failed.", e);
        }
        session.setAttribute(SessionAttribute.CURRENT_PAGE, router.getPage());
        return router;
    }
}
