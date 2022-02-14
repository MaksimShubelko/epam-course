package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.exception.CommandException;

import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.ApplicantService;
import com.example.epamcourse.model.service.BillService;
import com.example.epamcourse.model.service.CertificateService;
import com.example.epamcourse.model.service.SubjectService;
import com.example.epamcourse.model.service.impl.ApplicantServiceImpl;
import com.example.epamcourse.model.service.impl.BillServiceImpl;
import com.example.epamcourse.model.service.impl.CertificateServiceImpl;
import com.example.epamcourse.model.service.impl.SubjectServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PickUpDocumentsCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router(PagePath.MAIN_PAGE_APPLICANT_REDIRECT);
        ApplicantService applicantService = ApplicantServiceImpl.getInstance();
        BillService billService = BillServiceImpl.getInstance();
        SubjectService subjectService = SubjectServiceImpl.getInstance();
        CertificateService certificateService = CertificateServiceImpl.getInstance();
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.MAIN_PAGE_APPLICANT_REDIRECT);
        Long applicantId = (Long) session.getAttribute(SessionAttribute.APPLICANT_ID);
        try {
            if (!billService.isBillArchive(applicantId)) {
                billService.deleteBill(applicantId);
                subjectService.deleteSubjects(applicantId);
                certificateService.deleteCertificate(applicantId);
                request.setAttribute(RequestAttribute.MESSAGE, LocaleMessageKey.PICK_UP_DOCUMENTS_SUCCESS);
            } else {
                request.setAttribute(RequestAttribute.MESSAGE, LocaleMessageKey.PICK_UP_DOCUMENTS_ERROR);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Pick up documents failed", e);
            throw new CommandException("Pick up documents failed", e);
        }

        return router;
    }

}
