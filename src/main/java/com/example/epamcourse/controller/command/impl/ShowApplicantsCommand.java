package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.entity.*;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.*;
import com.example.epamcourse.model.service.impl.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.epamcourse.controller.command.PagePath.*;

public class ShowApplicantsCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private Long facultyId = 0L;
    private String recruitmentStatus;


    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        final int recordsPerPage = 5;
        int page = 1;
        request.getSession().setAttribute(SessionAttribute.CURRENT_PAGE, MAIN_PAGE_ADMINISTRATOR);
        Router router = new Router(PagePath.SHOW_APPLICANTS_PAGE);
        BillService billService = BillServiceImpl.getInstance();
        AccountService accountService = AccountServiceImpl.getInstance();
        SubjectService subjectService = SubjectServiceImpl.getInstance();
        CertificateService certificateService = CertificateServiceImpl.getInstance();
        ApplicantService applicantService = ApplicantServiceImpl.getInstance();
        FacultyService facultyService = FacultyServiceImpl.getInstance();
        ApplicantFindingService applicantFindingService = ApplicantFindingServiceImpl.getInstance();
        try {
            List<Faculty> faculties = facultyService.findAllFaculties();
            request.setAttribute(RequestAttribute.FACULTIES, faculties);
            if (!Objects.equals(request.getParameter(RequestParameter.FACULTY_ID), null)) {
                facultyId = Long.parseLong(request.getParameter(RequestParameter.FACULTY_ID));
            }
            if (!Objects.equals(request.getParameter(RequestParameter.RECRUITMENT_STATUS), null)) {
                recruitmentStatus = request.getParameter(RequestParameter.RECRUITMENT_STATUS);
            }
            if (request.getParameter(RequestParameter.PAGE) != null) {
                page = Integer.parseInt(request.getParameter(RequestParameter.PAGE));
            }
            int countApplicants = (int) billService.getCountOfBillsInFaculty(facultyId);
            List<Applicant> applicants = applicantService.findApplicantsInFacultyBySurname(facultyId, page, recruitmentStatus);
            long countOfApplicants = applicants.size();
            long noOfPages = (long) Math.ceil(countOfApplicants * 1.0 / recordsPerPage);
            List<List<Subject>> subjects = new ArrayList<>();
            List<Account> accounts = new ArrayList<>();
            List<Certificate> certificates = new ArrayList<>();
            for (Applicant applicant : applicants) {
                subjects.add(subjectService.findSubject(applicant.getApplicantId()));
                accounts.add(accountService.findAccountById(applicant.getAccountId()).orElseThrow(UnsupportedOperationException::new));
                certificates.add(certificateService.findCertificate(applicant.getApplicantId()).orElseThrow(UnsupportedOperationException::new));
            };
            request.setAttribute(RequestParameter.ACCOUNTS, accounts);
            request.setAttribute(RequestParameter.CERTIFICATES, certificates);
            request.setAttribute(RequestAttribute.SUBJECTS, subjects);
            request.setAttribute(RequestAttribute.FACULTY_ID, facultyId);
            request.setAttribute(RequestAttribute.APPLICANTS, applicants);
            request.setAttribute(RequestAttribute.CURRENT_PAGE, page);
            request.setAttribute(RequestAttribute.COUNT_PAGES, noOfPages);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Finding applicants failed", e);
            throw new CommandException("Finding applicants failed", e);
        }
        return router;
    }
}
