package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.Command;
import com.example.epamcourse.controller.command.PagePath;
import com.example.epamcourse.controller.command.Router;
import com.example.epamcourse.controller.command.SessionAttribute;
import com.example.epamcourse.model.exception.CommandException;

import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.ApplicantService;
import com.example.epamcourse.model.service.BillService;
import com.example.epamcourse.model.service.impl.ApplicantServiceImpl;
import com.example.epamcourse.model.service.impl.BillServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class PickUpDocumentsCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router(PagePath.MAIN_PAGE_APPLICANT_REDIRECT);
        ApplicantService applicantService = ApplicantServiceImpl.getInstance();
        BillService billService = BillServiceImpl.getInstance();
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.MAIN_PAGE_APPLICANT_REDIRECT);
        Long applicantId = (Long) session.getAttribute(SessionAttribute.APPLICANT_ID);
        try {
            billService.deleteBill(applicantId);
        } catch (ServiceException e) {
            e.printStackTrace(); // todo
        }

        return router;
    }

}
