package com.example.epamcourse.model.dao;

import com.example.epamcourse.model.entity.Bill;
import com.example.epamcourse.model.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * interface BillDao extends BaseDao<Bill>
 *
 * @author M.Shubelko
 */
public interface BillDao extends BaseDao<Bill> {

    /**
     * Find an administrator by accountId
     *
     * @param applicantId the id
     * @return Optional<Administrator>
     * @throws DaoException the DaoException
     */
    boolean deleteBillByApplicantId(Long applicantId) throws DaoException;

    /**
     * Checking the bill's archiving
     *
     * @param applicantId the applicantId
     * @return true if bill is archive
     * @throws DaoException the DaoException
     */
    boolean isBillArchive(Long applicantId) throws DaoException;

    /**
     * Find bill by applicantId
     *
     * @param applicantId the applicantId
     * @return Optional<Bill>
     * @throws DaoException the DaoException
     */
    Optional<Bill> findBillByApplicantId(Long applicantId) throws DaoException;

    /**
     * Find bills by facultyId
     *
     * @param facultyId the faculty id
     * @param isArchive the archiving
     * @return List<Bill>
     * @throws DaoException the DaoException
     */
    List<Bill> findAllBillsByFacultyId(Long facultyId, Boolean isArchive) throws DaoException;

    boolean changeStatusToArchive() throws DaoException;
}
