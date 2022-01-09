package com.example.epamcourse.model.service.impl;

import com.example.epamcourse.controller.command.RequestParameter;
import com.example.epamcourse.controller.command.SessionAttribute;
import com.example.epamcourse.controller.command.SessionRequestContent;
import com.example.epamcourse.model.dao.ApplicantDao;
import com.example.epamcourse.model.dao.impl.ApplicantDaoImpl;
import com.example.epamcourse.model.dao.impl.TransactionManager;
import com.example.epamcourse.model.entity.Applicant;
import com.example.epamcourse.model.exception.DaoException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.exception.TransactionException;
import com.example.epamcourse.model.service.ApplicantService;
import com.example.epamcourse.model.validator.SecureInformationValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApplicantServiceImpl implements ApplicantService {
    private static final Logger logger = LogManager.getLogger();
    private static final ApplicantServiceImpl instance = new ApplicantServiceImpl();
    private final SecureInformationValidator validator = SecureInformationValidator.getInstance();
    private final TransactionManager transactionManager = TransactionManager.getInstance();
    private final ApplicantDao applicantDao = ApplicantDaoImpl.getInstance();

    private ApplicantServiceImpl() {
    }

    public static ApplicantServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean addPersonalInformation(SessionRequestContent content) throws ServiceException {
        boolean isApplicantSecureInformationAdded = false;
        String name = content.getRequestParameter(RequestParameter.NAME);
        String surname = content.getRequestParameter(RequestParameter.SURNAME);
        String lastname = content.getRequestParameter(RequestParameter.LASTNAME);
        Long account_id = (Long) content.getSessionAttribute(SessionAttribute.ACCOUNT_ID);
        System.out.println(name + " " + surname + " " + lastname + " " + account_id);
        try {
            if (validator.isNameValid(name) && validator.isLastnameValid(lastname) && validator.isSurnameValid(surname)) {
                Applicant applicant = new Applicant(name, surname, lastname, account_id);
                transactionManager.initTransaction();
                System.out.println("Пытаюсь добавить абитуриента");
                applicantDao.add(applicant);
                transactionManager.commit();
                isApplicantSecureInformationAdded = true;
            }
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when adding secure information for applicant {}", e.getMessage());
            throw new ServiceException("Error when adding secure information for applicant", e);
        } finally {
            transactionManager.endTransaction();
        }
        return isApplicantSecureInformationAdded;
    }
}
