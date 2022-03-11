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
        final int recordsPerPage = 5;
        int page = 1;
        HttpSession session = request.getSession();
        long facultyId = 0L;
        String recruitmentStatus = ApplicantFindingType.ALL.name();
        Router router = new Router(PagePath.SHOW_APPLICANTS_PAGE);
        router.setType(Router.RouterType.REDIRECT);
        AccountService accountService = AccountServiceImpl.getInstance();
        SubjectService subjectService = SubjectServiceImpl.getInstance();
        CertificateService certificateService = CertificateServiceImpl.getInstance();
        ApplicantService applicantService = ApplicantServiceImpl.getInstance();
        FacultyService facultyService = FacultyServiceImpl.getInstance();
        try {
            List<Faculty> faculties = facultyService.findAllFaculties();
            session.setAttribute(RequestAttribute.FACULTIES, faculties);
            if (!Objects.equals(request.getParameter(RequestParameter.FACULTY_ID), null)) {
                facultyId = Long.parseLong(request.getParameter(RequestParameter.FACULTY_ID));
            }
            System.out.println(facultyId);
            recruitmentStatus = request.getParameter(RequestParameter.RECRUITMENT_STATUS);
            page = request.getParameter(RequestParameter.PAGE) != null
                    ? Integer.parseInt(request.getParameter(RequestParameter.PAGE)) : page;
            List<Applicant> applicants = applicantService.findApplicantsByFacultyIdAndRecruitmentStatus(facultyId, page,
                    recruitmentStatus);
            long countOfApplicants = applicants.size();
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
            session.setAttribute(RequestAttribute.PAGE, page);
            session.setAttribute(RequestAttribute.COUNT_PAGES, noOfPages);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Finding applicants failed", e);
            throw new CommandException("Finding applicants failed", e);
        }
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.GO_TO_SHOW_APPLICANTS_REDIRECT);
        return router;
    }
}
