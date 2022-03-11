package com.example.epamcourse.model.dao;

import com.example.epamcourse.model.entity.Applicant;
import com.example.epamcourse.model.exception.DaoException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * interface ApplicantDao extends BaseDao<Applicant>
 *
 * @author M.Shubelko
 */
public interface ApplicantDao extends BaseDao<Applicant> {

    /**
     * Find applicants by facultyId in order by mark to page
     *
     * @param facultyId the faculty id
     * @param rowNext the row next
     * @param rowSkip the row skip
     * @return List<Applicants> the applicants
     * @throws DaoException the DaoException
     */
    List<Applicant> findApplicantsInOrderByMarkInFaculty(Long facultyId, long rowSkip, int rowNext)
            throws DaoException;

    /**
     * Find an applicant by accountId
     *
     * @param facultyId the faculty id
     * @param applicantsSkip the applicants skip
     * @param applicantsTake the applicants take
     * @param rowNext the row next
     * @param rowSkip the row skip
     * @param isArchive the archiving
     * @return List<Applicant> the applicants
     * @throws DaoException the DaoException
     */
    List<Applicant> findApplicantsInOrderByMarkInFacultyAndRecruitmentStatus(Long facultyId,
                                                                             int applicantsSkip,
                                                                             int applicantsTake,
                                                                             long rowSkip,
                                                                             int rowNext, Boolean isArchive)
            throws DaoException;

    /**
     * Find an applicant by accountId
     *
     * @param id the id
     * @return Optional<Applicant>
     * @throws DaoException the DaoException
     */
    Optional<Applicant> findApplicantByAccountId(Long id) throws DaoException;

}
