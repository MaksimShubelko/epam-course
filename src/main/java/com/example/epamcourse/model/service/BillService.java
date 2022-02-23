package com.example.epamcourse.model.service;

import com.example.epamcourse.model.exception.ServiceException;

/**
 * interface BillService
 *
 * @author M.Shubelko
 */
public interface BillService {

    /**
     * The adding of bill
     *
     * @param applicantId the applicant id
     * @return true if bill is added
     * @throws ServiceException the service exception
     */
    boolean addBill(Long applicantId) throws ServiceException;

    /**
     * The updating of bill
     *
     * @param applicantId the applicant id
     * @param facultyId the faculty id
     * @return true if bill is updated
     * @throws ServiceException the service exception
     */
    boolean updateBill(Long applicantId, Long facultyId) throws ServiceException;

    /**
     * The deleting of bill
     *
     * @param applicantId the applicant id
     * @return true if bill is deleted
     * @throws ServiceException the service exception
     */
    boolean deleteBill(Long applicantId) throws ServiceException;

    /**
     * The checking of bill's archiving
     *
     * @param applicantId the applicant id
     * @return trie if bill is archive
     * @throws ServiceException the service exception
     */
    boolean isBillArchive(Long applicantId) throws ServiceException;

    /**
     * The getting of count of bills in faculty
     *
     * @param facultyId the faculty id
     * @param isArchive the archiving
     * @return the count of bills
     * @throws ServiceException the service exception
     */
    long getCountOfBillsInFaculty(Long facultyId, Boolean isArchive) throws ServiceException;

    /**
     * The restarting of recruitment
     *
     * @throws ServiceException the service exception
     */
    void restartRecruitment() throws ServiceException;
}
