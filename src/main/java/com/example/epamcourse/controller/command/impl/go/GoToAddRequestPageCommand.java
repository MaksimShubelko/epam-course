package com.example.epamcourse.controller.command.impl.go;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.entity.Certificate;
import com.example.epamcourse.model.entity.Faculty;
import com.example.epamcourse.model.entity.Subject;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.*;
import com.example.epamcourse.model.service.impl.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

import static com.example.epamcourse.controller.command.PagePath.*;

/**
 * class GoToAddRequestPageCommand
 *
 * @author M.Shubelko
 */
public class GoToAddRequestPageCommand implements Command {
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
        session.setAttribute(SessionAttribute.CURRENT_PAGE, ADD_REQUEST_PAGE_REDIRECT);
        Long applicantId = (Long) session.getAttribute(SessionAttribute.APPLICANT_ID);
        SubjectService subjectService = SubjectServiceImpl.getInstance();
        RecruitmentService recruitmentService = RecruitmentServiceImpl.getInstance();
        CertificateService certificateService = CertificateServiceImpl.getInstance();
        FacultyService facultyService = FacultyServiceImpl.getInstance();
        BillService billService = BillServiceImpl.getInstance();
        Router router = new Router(ADD_REQUEST_PAGE);
        try {
            billService.addBill(applicantId);
            if (recruitmentService.isRecruitmentActive()) {
                if (!billService.isBillArchive(applicantId)) {
                    subjectService.addSubject(applicantId);
                    certificateService.addCertificate(applicantId);
                    List<Subject> subjects = subjectService.findSubject(applicantId);
                    Optional<Certificate> certificateOptional = certificateService.findCertificate(applicantId);
                    List<Faculty> faculties = facultyService.findAllFaculties();
                    session.setAttribute(SessionAttribute.FACULTIES, faculties);
                    session.setAttribute(SessionAttribute.SUBJECTS, subjects);
                    session.setAttribute(SessionAttribute.CERTIFICATE, certificateOptional.get());
                } else {
                    router.setPage(MAIN_PAGE_APPLICANT_REDIRECT);
                    session.setAttribute(RequestAttribute.MESSAGE, LocaleMessageKey.ADD_REQUEST_ERROR);
                }
            } else {
                router.setPage(MAIN_PAGE_APPLICANT_REDIRECT);
                session.setAttribute(RequestAttribute.MESSAGE, LocaleMessageKey.RECRUITMENT_NOT_STARTED);
            }

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Go to request page failed. {}", e);
            throw new CommandException("Go to request page failed", e);
        }
        router.setType(Router.RouterType.REDIRECT);
        return router;
    }
}
