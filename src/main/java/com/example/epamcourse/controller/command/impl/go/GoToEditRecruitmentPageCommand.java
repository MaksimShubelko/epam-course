package com.example.epamcourse.controller.command.impl.go;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.entity.Recruitment;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.RecruitmentService;
import com.example.epamcourse.model.service.impl.RecruitmentServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Date;

public class GoToEditRecruitmentPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Router router = new Router(PagePath.EDIT_RECRUITMENT);
        router.setType(Router.RouterType.REDIRECT);
        try {
            RecruitmentService recruitmentService = RecruitmentServiceImpl.getInstance();
            Recruitment recruitment = recruitmentService.findRecruitment();
            session.setAttribute(SessionAttribute.RECRUITMENT, recruitment);
            System.out.println(recruitment.getFinishRecruitment());
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Go to edition recruitment failed.", e);
            throw new CommandException("Go to edition recruitment failed.", e);
        }
        session.setAttribute(SessionAttribute.CURRENT_PAGE, router.getPage());
        return router;
    }
}
