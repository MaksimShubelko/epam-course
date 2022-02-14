package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.BillService;
import com.example.epamcourse.model.service.RecruitmentService;
import com.example.epamcourse.model.service.impl.BillServiceImpl;
import com.example.epamcourse.model.service.impl.RecruitmentServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;

public class UpdateRecruitmentCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        RecruitmentService recruitmentService = RecruitmentServiceImpl.getInstance();
        Router router = new Router(PagePath.MAIN_PAGE_ADMINISTRATOR);
        LocalDateTime dateNow = LocalDateTime.now();
        try {
            boolean recruitmentStatus = Boolean.parseBoolean(request.getParameter(RequestParameter.RECRUITMENT_STATUS));
            boolean isRestartRecruitment = Boolean.parseBoolean(request.getParameter(RequestParameter.RESTART_RECRUITMENT));
            BillService billService = BillServiceImpl.getInstance();
            if (isRestartRecruitment) {
                billService.restartRecruitment();
            }
            LocalDateTime finishRecruitment = LocalDateTime.parse(request.getParameter(RequestParameter.FINISH_RECRUITMENT));
            if (dateNow.isAfter(finishRecruitment)) {
                router.setPage(PagePath.EDIT_RECRUITMENT);
                request.setAttribute(RequestAttribute.MESSAGE, LocaleMessageKey.INVALID_DATE);
            }
            recruitmentService.updateRecruitment(recruitmentStatus, finishRecruitment);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Updating recruitment failed", e);
            throw new CommandException("Updating recruitment failed", e);
        }
        session.setAttribute(SessionAttribute.CURRENT_PAGE, router.getPage());
        return router;
    }
}
