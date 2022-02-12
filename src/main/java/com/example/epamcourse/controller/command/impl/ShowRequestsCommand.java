package com.example.epamcourse.controller.command.impl;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.entity.Applicant;
import com.example.epamcourse.model.entity.Faculty;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.ApplicantService;
import com.example.epamcourse.model.service.BillService;
import com.example.epamcourse.model.service.FacultyService;
import com.example.epamcourse.model.service.impl.ApplicantServiceImpl;
import com.example.epamcourse.model.service.impl.BillServiceImpl;
import com.example.epamcourse.model.service.impl.FacultyServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

import static com.example.epamcourse.controller.command.PagePath.MAIN_PAGE_APPLICANT;
import static com.example.epamcourse.controller.command.PagePath.MAIN_PAGE_APPLICANT_REDIRECT;

public class ShowRequestsCommand implements Command {
    private static Long facultyId = 0L;
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        final int recordsPerPage = 5;
        int page = 1;
        HttpSession session = request.getSession();
        Router router = new Router(MAIN_PAGE_APPLICANT_REDIRECT);
        ApplicantService applicantService = ApplicantServiceImpl.getInstance();
        FacultyService facultyService = FacultyServiceImpl.getInstance();
        try {
            BillService billService = BillServiceImpl.getInstance();
            String facultyIdRequest = request.getParameter(RequestParameter.FACULTY_ID);
            if (facultyIdRequest != null) {
                facultyId = Long.valueOf(facultyIdRequest);
            }
            long countOfApplicants = billService.getCountOfBillsInFaculty(facultyId);
            long noOfPages = (long) Math.ceil(countOfApplicants * 1.0 / recordsPerPage);
            if (request.getParameter(RequestParameter.PAGE) != null) {
                page = Integer.parseInt(request.getParameter(RequestParameter.PAGE));
            }
            List<Applicant> applicants = applicantService.findApplicantsInFaculty(facultyId, page);
            Optional<Faculty> facultyOptional = facultyService.findFacultyById(facultyId);
            if (facultyOptional.isPresent()) {
                Faculty faculty = facultyOptional.get();
                request.setAttribute(RequestAttribute.FACULTY, faculty);
            }
            request.setAttribute(RequestAttribute.APPLICANTS, applicants);
            request.setAttribute(RequestAttribute.PAGE, page);
            request.setAttribute(RequestAttribute.COUNT_PAGES, noOfPages);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Finding applicants failed", e);
            throw new CommandException("Finding applicants failed", e);
        }
        session.setAttribute(SessionAttribute.CURRENT_PAGE, MAIN_PAGE_APPLICANT);
        return router;
    }
}
