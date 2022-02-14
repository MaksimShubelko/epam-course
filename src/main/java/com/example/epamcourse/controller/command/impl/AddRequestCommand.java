package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.*;
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

import static com.example.epamcourse.controller.command.RequestAttribute.FACULTIES;

public class AddRequestCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router(PagePath.MAIN_PAGE_APPLICANT_REDIRECT);
        HttpSession session = request.getSession();
        FacultyService facultyService = FacultyServiceImpl.getInstance();
        BillService billService = BillServiceImpl.getInstance();
        SubjectService subjectService = SubjectServiceImpl.getInstance();
        CertificateService certificateService = CertificateServiceImpl.getInstance();
        ApplicantService applicantService = ApplicantServiceImpl.getInstance();
        try {
            List<Faculty> faculties = facultyService.findAllFaculties();
            request.setAttribute(FACULTIES, faculties);
            double certificateMark = Double.parseDouble(request.getParameter(RequestParameter.CERTIFICATE_MARK));
            Long applicantId = (Long) request.getSession().getAttribute(SessionAttribute.APPLICANT_ID);
            certificateService.updateCertificate(applicantId, certificateMark);
            Integer mathMark = Integer.parseInt(request.getParameter(RequestParameter.SUBJECT_MATH_MARK));
            Integer physicMark = Integer.parseInt(request.getParameter(RequestParameter.SUBJECT_PHYSIC_MARK));
            Integer englishMark = Integer.parseInt(request.getParameter(RequestParameter.SUBJECT_ENGLISH_MARK));
            boolean privilege = Boolean.parseBoolean(request.getParameter(RequestParameter.PRIVILEGES));
            Long facultyId = Long.parseLong(request.getParameter(RequestParameter.FACULTY_ID));
            subjectService.updateSubject(applicantId, mathMark, physicMark, englishMark);
            applicantService.updateApplicantPrivileges(applicantId, privilege);
            billService.updateBill(applicantId, facultyId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Adding request failed.", e);
            throw new CommandException("Adding request failed", e);
        }
        session.setAttribute(SessionAttribute.CURRENT_PAGE, router.getPage());
        return router;
    }
}
