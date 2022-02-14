package com.example.epamcourse.model.service.impl;

import com.example.epamcourse.model.dao.ApplicantDao;
import com.example.epamcourse.model.dao.SubjectDao;
import com.example.epamcourse.model.dao.impl.ApplicantDaoImpl;
import com.example.epamcourse.model.dao.impl.SubjectDaoImpl;
import com.example.epamcourse.model.dao.impl.TransactionManager;
import com.example.epamcourse.model.entity.Subject;
import com.example.epamcourse.model.exception.DaoException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.exception.TransactionException;
import com.example.epamcourse.model.service.ApplicantService;
import com.example.epamcourse.model.service.SubjectService;
import com.example.epamcourse.model.validator.SubjectValidator;
import com.example.epamcourse.model.validator.impl.SubjectValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

public class SubjectServiceImpl implements SubjectService {
    private static final Logger logger = LogManager.getLogger();
    private static final SubjectService instance = new SubjectServiceImpl();
    private final SubjectDao subjectDao = SubjectDaoImpl.getInstance();
    private final ApplicantDao applicantDao = ApplicantDaoImpl.getInstance();
    private final TransactionManager transactionManager = TransactionManager.getInstance();

    private SubjectServiceImpl() {
    }

    @Override
    public boolean addSubject(Long applicantId) throws ServiceException {
        boolean isSubjectAdded = false;
        SubjectValidator validator = SubjectValidatorImpl.getInstance();

        try {
            transactionManager.initTransaction();
            ApplicantService applicantService = ApplicantServiceImpl.getInstance();
            if (subjectDao.findSubjectByApplicantId(applicantId).isEmpty()) {
                for (Subject.Type type : Subject.Type.values()) {
                    Subject subject = new Subject();
                    subject.setApplicantId(applicantId);
                    subject.setSubjectType(type);
                    subjectDao.add(subject);
                }
            }
            transactionManager.commit();
            isSubjectAdded = true;
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when adding subjects for applicant", e);
            throw new ServiceException("Error when adding subjects for applicant", e);
        } finally {
            transactionManager.endTransaction();
        }
        return isSubjectAdded;
    }

    @Override
    public boolean updateSubject(Long applicantId, Integer... subjectMarks) throws ServiceException {
        boolean isSubjectUpdated = false;
        try {
            transactionManager.initTransaction();
            List<Subject> subjects = subjectDao.findSubjectByApplicantId(applicantId);
            for (int i = 0; i < subjects.size(); i++) {
                subjects.get(i)
                        .setMark(subjectMarks[i]);
            }
            for (Subject subject : subjects) {
                subjectDao.update(subject);
            }
            transactionManager.commit();
            isSubjectUpdated = true;
        } catch (TransactionException | DaoException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when updating subject for applicant", e);
            throw new ServiceException("Error when updating subject for applicant", e);
        } finally {
            transactionManager.endTransaction();
        }
        return isSubjectUpdated;
    }

    @Override
    public boolean deleteSubjects(Long applicantId) throws ServiceException {
        boolean isSubjectDeleted = false;
        List<Subject> subjects;
        try {
            transactionManager.initTransaction();
            isSubjectDeleted = subjectDao.delete(applicantId); // todo
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when finding certificate for applicant}", e);
            throw new ServiceException("Error when finding certificate for applicant", e);
        } finally {
            transactionManager.endTransaction();
        }
        return isSubjectDeleted;
    }

    @Override
    public List<Subject> findSubject(Long applicantId) throws ServiceException {
        List<Subject> subjects = Collections.emptyList();
        try {
            transactionManager.initTransaction();
            subjects = subjectDao.findSubjectByApplicantId(applicantId);
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when finding certificate for applicant}", e);
            throw new ServiceException("Error when finding certificate for applicant", e);
        } finally {
            transactionManager.endTransaction();
        }
        return subjects;
    }

    public static SubjectService getInstance() {
        return instance;
    }
}
