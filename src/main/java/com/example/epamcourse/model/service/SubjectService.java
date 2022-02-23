package com.example.epamcourse.model.service;

import com.example.epamcourse.model.entity.Subject;
import com.example.epamcourse.model.exception.ServiceException;

import java.util.List;

/**
 * interface SubjectService
 *
 * @author M.Shubelko
 */
public interface SubjectService {

    /**
     * The adding of subjects
     *
     * @param applicantId the applicant id
     * @throws ServiceException the service exception
     */
    void addSubject(Long applicantId) throws ServiceException;

    /**
     * The updating of subjects
     *
     * @param applicantId the applicant id
     * @param subjectMarks the subject marks
     * @throws ServiceException the service exception
     */
    void updateSubject(Long applicantId, Integer... subjectMarks) throws ServiceException;

    /**
     * The deleting of subjects
     *
     * @param applicantId the applicant id
     * @throws ServiceException the service exception
     */
    void deleteSubjects(Long applicantId) throws ServiceException;

    /**
     * The finding of subjects
     *
     * @param applicantId the applicant id
     * @return the subjects
     * @throws ServiceException the service exception
     */
    List<Subject> findSubject(Long applicantId) throws ServiceException;

}
