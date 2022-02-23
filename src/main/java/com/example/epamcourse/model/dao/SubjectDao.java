package com.example.epamcourse.model.dao;

import com.example.epamcourse.model.entity.Subject;
import com.example.epamcourse.model.exception.DaoException;

import java.util.List;

/**
 * interface SubjectDao extends BaseDao<Subject>
 *
 * @author M.Shubelko
 */
public interface SubjectDao extends BaseDao<Subject> {

    /**
     * Find faculties to page
     *
     * @param applicantId the applicant id
     * @return List<Faculty> the faculties
     * @throws DaoException the DaoException
     */
    List<Subject> findSubjectByApplicantId(Long applicantId) throws DaoException;
}
