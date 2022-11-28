package com.example.epamcourse.controller.command.impl.go;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.entity.*;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.*;
import com.example.epamcourse.model.service.impl.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * class GoToShowApplicantsPageCommand
 *
 * @author M.Shubelko
 */
public class GoToShowApplicantsPageCommand implements Command {

    /**
     * The logger.
     */
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
        final int recordsPerPage = (int) session.getAttribute(SessionAttribute.ROW_AMOUNT);
        long facultyId = 0;
        String recruitmentStatus;
        Router router = new Router(PagePath.SHOW_APPLICANTS_PAGE);
        router.setType(Router.RouterType.REDIRECT);
        AccountService accountService = AccountServiceImpl.getInstance();
        SubjectService subjectService = SubjectServiceImpl.getInstance();
        CertificateService certificateService = CertificateServiceImpl.getInstance();
        ApplicantService applicantService = ApplicantServiceImpl.getInstance();
        FacultyService facultyService = FacultyServiceImpl.getInstance();
        try {
            int page;
            List<Faculty> faculties = facultyService.findAllFaculties();
            session.setAttribute(RequestAttribute.FACULTIES, faculties);
            if (!Objects.equals(request.getParameter(RequestParameter.FACULTY_ID), null)) {
                facultyId = Long.parseLong(request.getParameter(RequestParameter.FACULTY_ID));
            }
            recruitmentStatus = request.getParameter(RequestParameter.RECRUITMENT_STATUS);
            page = (int) session.getAttribute(SessionAttribute.PAGE);
            List<Applicant> applicants = applicantService.findApplicantsByFacultyIdAndRecruitmentStatus(facultyId, page,
                    recruitmentStatus);
            long countOfApplicants = applicants.size();
            if (countOfApplicants != 0) {
                long noOfPages = (long) Math.ceil(countOfApplicants * 1.0 / recordsPerPage);
                List<List<Subject>> subjects = new ArrayList<>();
                List<Account> accounts = new ArrayList<>();
                List<Certificate> certificates = new ArrayList<>();
                for (Applicant applicant : applicants) {
                    subjects.add(subjectService.findSubject(applicant.getApplicantId()));
                    accounts.add(accountService.findAccountById(applicant.getAccountId()).orElseGet(null));
                    certificates.add(certificateService.findCertificate(applicant.getApplicantId()).orElseGet(null));
                };
                session.setAttribute(RequestParameter.ACCOUNTS, accounts);
                session.setAttribute(RequestParameter.CERTIFICATES, certificates);
                session.setAttribute(RequestAttribute.SUBJECTS, subjects);
                session.setAttribute(RequestAttribute.FACULTY_ID, facultyId);
                session.setAttribute(RequestAttribute.APPLICANTS, applicants);
                session.setAttribute(RequestAttribute.COUNT_PAGES, noOfPages);
            } else {
                if (facultyId != 0) {
                session.setAttribute(SessionAttribute.MESSAGE_RESULT, LocaleMessageKey.DATA_NOT_EXIST);
                }
            }
            session.setAttribute(RequestAttribute.PAGE, page);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Finding applicants failed", e);
            throw new CommandException("Finding applicants failed", e);
        }
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.GO_TO_SHOW_APPLICANTS_REDIRECT);
        return router;
    }
}
