package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.entity.Certificate;
import com.example.epamcourse.model.entity.Faculty;
import com.example.epamcourse.model.entity.Subject;
import com.example.epamcourse.model.exception.CommandException;

import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.*;
import com.example.epamcourse.model.service.impl.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

import static com.example.epamcourse.controller.command.PagePath.MAIN_PAGE_APPLICANT_REDIRECT;

/**
 * class PickUpDocumentsCommand
 *
 * @author M.Shubelko
 */
public class PickUpDocumentsCommand implements Command {

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
        Router router = new Router(PagePath.MAIN_PAGE_APPLICANT_REDIRECT);
        RecruitmentService recruitmentService = RecruitmentServiceImpl.getInstance();
        BillService billService = BillServiceImpl.getInstance();
        SubjectService subjectService = SubjectServiceImpl.getInstance();
        CertificateService certificateService = CertificateServiceImpl.getInstance();
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.MAIN_PAGE_APPLICANT_REDIRECT);
        Long applicantId = (Long) session.getAttribute(SessionAttribute.APPLICANT_ID);
        try {
            if (recruitmentService.isRecruitmentActive()) {
                if (!billService.isBillArchive(applicantId)) {
                    billService.deleteBill(applicantId);
                    subjectService.deleteSubjects(applicantId);
                    certificateService.deleteCertificate(applicantId);
                    session.setAttribute(SessionAttribute.MESSAGE_RESULT, LocaleMessageKey.DOCUMENTS_TAKEN_AWAY);
                } else {
                    router.setPage(MAIN_PAGE_APPLICANT_REDIRECT);
                    session.setAttribute(SessionAttribute.MESSAGE, LocaleMessageKey.ADD_REQUEST_ERROR);
                }
            } else {
                router.setPage(MAIN_PAGE_APPLICANT_REDIRECT);
                session.setAttribute(RequestAttribute.MESSAGE, LocaleMessageKey.RECRUITMENT_NOT_STARTED);
            }

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Pick up documents failed", e);
            throw new CommandException("Pick up documents failed", e);
        }

        return router;
    }

}
