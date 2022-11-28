package com.example.epamcourse.model.service.impl;

import com.example.epamcourse.model.dao.SubjectDao;
import com.example.epamcourse.model.dao.impl.SubjectDaoImpl;
import com.example.epamcourse.model.dao.impl.TransactionManager;
import com.example.epamcourse.model.entity.Subject;
import com.example.epamcourse.model.exception.DaoException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.exception.TransactionException;
import com.example.epamcourse.model.service.SubjectService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * class SubjectServiceImpl
 *
 * @author M.Shubelko
 */
public class SubjectServiceImpl implements SubjectService {

    /**
     * The logger
     */
    private static final Logger logger = LogManager.getLogger();

    /**
     * The instance
     */
    private static final SubjectService instance = new SubjectServiceImpl();

    /**
     * The subject dao
     */
    private final SubjectDao subjectDao = SubjectDaoImpl.getInstance();

    /**
     * The transaction manager
     */
    private final TransactionManager transactionManager = TransactionManager.getInstance();

    /**
     * The private constructor
     */
    private SubjectServiceImpl() {
    }

    /**
     * The getting of instance
     *
     * @return instance the instance
     */
    public static SubjectService getInstance() {
        return instance;
    }

    /**
     * The adding of subject
     *
     * @param applicantId the applicant id
     * @throws ServiceException the service exception
     */
    @Override
    public void addSubject(Long applicantId) throws ServiceException {
        try {
            transactionManager.initTransaction();
            if (subjectDao.findSubjectByApplicantId(applicantId).isEmpty()) {
                for (Subject.SubjectType subjectType : Subject.SubjectType.values()) {
                    Subject subject = new Subject();
                    subject.setApplicantId(applicantId);
                    subject.setSubjectType(subjectType);
                    subjectDao.add(subject);
                }
            }
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when adding subjects for applicant", e);
            throw new ServiceException("Error when adding subjects for applicant", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    /**
     * The updating of subject's marks
     *
     * @param applicantId the applicant id
     * @param subjectMarks the subject marks
     * @throws ServiceException the service exception
     */
    @Override
    public void updateSubject(Long applicantId, Integer... subjectMarks) throws ServiceException {
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
        } catch (TransactionException | DaoException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when updating subject for applicant", e);
            throw new ServiceException("Error when updating subject for applicant", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    /**
     * The deleting of subjects
     *
     * @param applicantId the applicant id
     * @throws ServiceException the service exception
     */
    @Override
    public void deleteSubjects(Long applicantId) throws ServiceException {
        boolean isSubjectDeleted;
        try {
            transactionManager.initTransaction();
            isSubjectDeleted = subjectDao.delete(applicantId);
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when deleting subjects for applicant}", e);
            throw new ServiceException("Error when deleting subjects for applicant", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    /**
     * The finding of subjects
     *
     * @param applicantId the applicant id
     * @return subjects the subjects
     * @throws ServiceException the service exception
     */
    @Override
    public List<Subject> findSubject(Long applicantId) throws ServiceException {
        List<Subject> subjects;
        try {
            transactionManager.initTransaction();
            subjects = subjectDao.findSubjectByApplicantId(applicantId);
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when finding subjects for applicant}", e);
            throw new ServiceException("Error when finding subjects for applicant", e);
        } finally {
            transactionManager.endTransaction();
        }
        return subjects;
    }

}
