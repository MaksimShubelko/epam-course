package com.example.epamcourse.controller.command.impl.go;

import com.example.epamcourse.controller.command.Command;
import com.example.epamcourse.controller.command.RequestAttribute;
import com.example.epamcourse.controller.command.Router;
import com.example.epamcourse.controller.command.SessionAttribute;
import com.example.epamcourse.model.entity.Certificate;
import com.example.epamcourse.model.entity.Faculty;
import com.example.epamcourse.model.entity.Subject;
import com.example.epamcourse.model.exception.CommandException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.BillService;
import com.example.epamcourse.model.service.CertificateService;
import com.example.epamcourse.model.service.FacultyService;
import com.example.epamcourse.model.service.SubjectService;
import com.example.epamcourse.model.service.impl.BillServiceImpl;
import com.example.epamcourse.model.service.impl.CertificateServiceImpl;
import com.example.epamcourse.model.service.impl.FacultyServiceImpl;
import com.example.epamcourse.model.service.impl.SubjectServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

import static com.example.epamcourse.controller.command.PagePath.*;

public class GoToAddRequestPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        request.getSession().setAttribute(SessionAttribute.CURRENT_PAGE, ADD_REQUEST_PAGE);
        Long applicantId = (Long) request.getSession().getAttribute(SessionAttribute.APPLICANT_ID);
        SubjectService subjectService = SubjectServiceImpl.getInstance();
        CertificateService certificateService = CertificateServiceImpl.getInstance();
        FacultyService facultyService = FacultyServiceImpl.getInstance();
        BillService billService = BillServiceImpl.getInstance();
        try {
            subjectService.addSubject(applicantId);
            certificateService.addCertificate(applicantId);
            billService.addBill(applicantId);
            List<Subject> subjects = subjectService.findSubject(applicantId);
            Optional<Certificate> certificateOptional = certificateService.findCertificate(applicantId);
            List<Faculty> faculties = facultyService.findAllFaculties();
            HttpSession session = request.getSession();
            session.setAttribute(SessionAttribute.FACULTIES, faculties);
            session.setAttribute(SessionAttribute.SUBJECTS, subjects);
            session.setAttribute(SessionAttribute.CERTIFICATE, certificateOptional.get());
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Go to request page failed. {}", e);
            throw new CommandException("Go to request page failed", e);
        }
        Router router = new Router(ADD_REQUEST_PAGE);
        router.setType(Router.RouterType.REDIRECT);
        return router;
    }
}
