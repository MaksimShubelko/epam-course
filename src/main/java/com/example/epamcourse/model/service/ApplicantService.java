package com.example.epamcourse.model.service;

import com.example.epamcourse.model.entity.Applicant;
import com.example.epamcourse.model.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * interface ApplicantService
 *
 * @author M.Shubelko
 */
public interface ApplicantService {

    /**
     * The adding of personal information
     *
     * @param name the name
     * @param surname the surname
     * @param lastname the lastname
     * @param accountId the account id
     * @return true if personal information is added
     * @throws ServiceException the service exception
     */
    boolean addPersonalInformation(String name, String surname, String lastname, Long accountId) throws ServiceException;

    /**
     * The finding of applicant's id by account's id
     *
     * @param accountId the account id
     * @return the applicant id
     * @throws ServiceException the service exception
     */
    Long findApplicantIdByAccountId(Long accountId) throws ServiceException;

    /**
     * The finding of applicants in faculty
     *
     * @param facultyId the faculty id
     * @param currentPageNumber the current page number
     * @return the applicants
     * @throws ServiceException the service exception
     */
    List<Applicant> findApplicantsInFaculty(Long facultyId, int currentPageNumber) throws ServiceException;

    /**
     *
     * @param facultyId the faculty id
     * @param currentPageNumber the recruitment page number
     * @param recruitmentStatus the recruitment status
     * @return the applicants
     * @throws ServiceException the service exception
     */
    List<Applicant> findApplicantsByFacultyIdAndRecruitmentStatus(Long facultyId, int currentPageNumber, String recruitmentStatus) throws ServiceException;

    /**
     * The finding pf applicant by id
     *
     * @param accountId the account id
     * @return tha applicant optional
     * @throws ServiceException the service exception
     */
    Optional<Applicant> findApplicantById(Long accountId) throws ServiceException; // todo

    /**
     * The finding pf applicant by account id
     *
     * @param accountId the account id
     * @return tha applicant optional
     * @throws ServiceException the service exception
     */
    Optional<Applicant> findApplicantByAccountId(Long accountId) throws ServiceException;

    /**
     * The updating of applicant's privileges
     *
     * @param applicantId the applicant id
     * @param privilege the privilege
     * @throws ServiceException the service exception
     */
    void updateApplicantPrivileges(Long applicantId, boolean privilege) throws ServiceException;

    /**
     * The edition of applicant personal information
     *
     * @param applicantId the applicant id
     * @param name the name
     * @param surname the surname
     * @param lastname the lastname
     * @return true if applicant's personal information is updated
     * @throws ServiceException the service exception
     */
    boolean editApplicantPersonalInformation(Long applicantId, String name, String surname, String lastname) throws ServiceException;

}
