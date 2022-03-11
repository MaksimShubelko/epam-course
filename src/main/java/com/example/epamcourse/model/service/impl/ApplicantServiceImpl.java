package com.example.epamcourse.model.service.impl;

import com.example.epamcourse.model.dao.*;
import com.example.epamcourse.model.dao.impl.*;
import com.example.epamcourse.model.entity.Applicant;
import com.example.epamcourse.model.entity.Faculty;
import com.example.epamcourse.model.exception.DaoException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.exception.TransactionException;
import com.example.epamcourse.model.service.ApplicantFindingService;
import com.example.epamcourse.model.service.ApplicantService;
import com.example.epamcourse.model.validator.SecureInformationValidator;
import com.example.epamcourse.model.validator.impl.SecureInformationValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.example.epamcourse.model.service.ApplicantFindingType.ALL;
import static com.example.epamcourse.model.service.ApplicantFindingType.ARCHIVE;

/**
 * class ApplicantServiceImpl
 *
 * @author M.Shubelko
 */
public class ApplicantServiceImpl implements ApplicantService {

    /**
     * The logger
     */
    private static final Logger logger = LogManager.getLogger();

    /**
     * The instance
     */
    private static final ApplicantServiceImpl instance = new ApplicantServiceImpl();

    /**
     * The transaction manager
     */
    private final TransactionManager transactionManager = TransactionManager.getInstance();

    /**
     * The applicant dao
     */
    private final ApplicantDao applicantDao = ApplicantDaoImpl.getInstance();

    /**
     * The bill dao
     */
    private final BillDao billDao = BillDaoImpl.getInstance();

    /**
     * The faculty dao
     */
    private final FacultyDao facultyDao = FacultyDaoImpl.getInstance();

    /**
     * The private constructor
     */
    private ApplicantServiceImpl() {
    }

    /**
     * The getting of instance
     *
     * @return instance the instance
     */
    public static ApplicantServiceImpl getInstance() {
        return instance;
    }

    /**
     * The adding of applicant's personal information
     *
     * @param name the name
     * @param surname the surname
     * @param lastname the lastname
     * @param accountId the account id
     * @return true if applicant's personal information is added
     * @throws ServiceException the service exception
     */
    @Override
    public boolean addPersonalInformation(String name, String surname, String lastname, Long accountId) throws ServiceException {
        boolean isApplicantSecureInformationAdded = false;
        try {
            if (isApplicantSecureInformationValid(name, surname, lastname)) {
                Applicant applicant = new Applicant();
                applicant.setFirstname(name);
                applicant.setSurname(surname);
                applicant.setLastname(lastname);
                applicant.setAccountId(accountId);
                transactionManager.initTransaction();
                applicantDao.add(applicant);
                transactionManager.commit();
                isApplicantSecureInformationAdded = true;
            }
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when adding secure information for applicant", e);
            throw new ServiceException("Error when adding secure information for applicant", e);
        } finally {
            transactionManager.endTransaction();
        }

        return isApplicantSecureInformationAdded;
    }

    /**
     * The finding of applicant by account id
     *
     * @param accountId the account id
     * @return applicantOptional the applicant optional
     * @throws ServiceException the service exception
     */
    @Override
    public Long findApplicantIdByAccountId(Long accountId) throws ServiceException {
        Optional<Applicant> applicantOptional;
        Long applicantId = 0L;
        try {
            transactionManager.initTransaction();
            applicantOptional = applicantDao.findApplicantByAccountId(accountId);
            if (applicantOptional.isPresent()) {
                applicantId = applicantOptional.get().getApplicantId();
            }
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when getting applicant id", e);
            throw new ServiceException("Error when getting applicant id", e);
        } finally {
            transactionManager.endTransaction();
        }
        return applicantId;
    }

    /**
     * The finding of applicants in faculty
     *
     * @param facultyId the faculty id
     * @param currentPageNumber the current page number
     * @return applicants the applicants
     * @throws ServiceException the service exception
     */
    @Override
    public List<Applicant> findApplicantsInFaculty(Long facultyId, int currentPageNumber) throws ServiceException {
        List<Applicant> applicants;
        try {
            int recordsPerPage = 5;
            transactionManager.initTransaction();
            applicants = applicantDao
                    .findApplicantsInOrderByMarkInFaculty(facultyId, (long) (currentPageNumber - 1) * recordsPerPage,
                            recordsPerPage);
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when getting applicant id", e);
            throw new ServiceException("Error when getting applicant id", e);
        } finally {
            transactionManager.endTransaction();
        }
        return applicants;
    }

    /**
     * The finding of applicant if faculty by faculty id and recruitment status
     *
     * @param facultyId the faculty id
     * @param currentPageNumber the current page number
     * @param recruitmentStatus the recruitment status
     * @return applicants the applicants
     * @throws ServiceException the service exception
     */
    @Override
    public List<Applicant> findApplicantsByFacultyIdAndRecruitmentStatus(Long facultyId,
                                                                         int currentPageNumber,
                                                                         String recruitmentStatus) throws ServiceException {
        ApplicantFindingService applicantFindingService = ApplicantFindingServiceImpl.getInstance();
        List<Applicant> applicants = Collections.emptyList();
        boolean isArchive = false;
        try {
            recruitmentStatus = recruitmentStatus != null ? recruitmentStatus : ALL.name();
            if (ARCHIVE.name().equals(recruitmentStatus.toUpperCase())) {
                isArchive = true;
                recruitmentStatus = ALL.name();
            }
            transactionManager.initTransaction();
            int recordsPerPage = 5;
            Optional<Faculty> facultyOptional = facultyDao.findEntityById(facultyId);
            if (facultyOptional.isPresent()) {
                Faculty faculty = facultyOptional.get();
                int recruitmentPlanFree = faculty.getRecruitmentPlanFree();
                int recruitmentPlanCanvas = faculty.getRecruitmentPlanCanvas();
                int countApplicants = billDao.getCountOfBillsInFaculty(facultyId, isArchive);
                int applicantsSkipDepOnRecruitStatus = applicantFindingService.getCountOfApplicantsToSkip(recruitmentStatus,
                        recruitmentPlanFree, recruitmentPlanCanvas, countApplicants);
                int applicantsTakeDepOnRecruitStatus = applicantFindingService.getCountOfApplicantsToTake(recruitmentStatus,
                        recruitmentPlanFree, recruitmentPlanCanvas, countApplicants);
                applicants = applicantDao.findApplicantsInOrderByMarkInFacultyAndRecruitmentStatus(facultyId,
                        applicantsSkipDepOnRecruitStatus, applicantsTakeDepOnRecruitStatus,
                        (long) (currentPageNumber - 1) * recordsPerPage,
                        recordsPerPage, isArchive);
            }
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when getting applicant id", e);
            throw new ServiceException("Error when getting applicant id", e);
        } finally {
            transactionManager.endTransaction();
        }
        return applicants;
    }

    /**
     * The finding of applicant by id
     *
     * @param applicantId the applicant id
     * @return administratorOptional the applicant optional
     * @throws ServiceException the service exception
     */
    @Override
    public Optional<Applicant> findApplicantById(Long applicantId) throws ServiceException {
        Optional<Applicant> applicantOptional;
        try {
            transactionManager.initTransaction();
            applicantOptional = applicantDao.findEntityById(applicantId);
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when getting applicant id", e);
            throw new ServiceException("Error when getting applicant id", e);
        } finally {
            transactionManager.endTransaction();
        }
        return applicantOptional;
    }

    /**
     * The finding of applicant by account id
     *
     * @param accountId the account id
     * @return applicantOptional the applicant optional
     * @throws ServiceException the service exception
     */
    @Override
    public Optional<Applicant> findApplicantByAccountId(Long accountId) throws ServiceException {
        Optional<Applicant> applicantOptional;
        try {
            transactionManager.initTransaction();
            applicantOptional = applicantDao.findApplicantByAccountId(accountId);
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when getting applicant", e);
            throw new ServiceException("Error when getting applicant", e);
        } finally {
            transactionManager.endTransaction();
        }
        return applicantOptional;
    }

    /**
     * The updating of applicant's personal information
     *
     * @param applicantId the applicant id
     * @param privilege the privilege
     * @throws ServiceException the service exception
     */
    @Override
    public void updateApplicantPrivileges(Long applicantId, boolean privilege) throws ServiceException {
        boolean isApplicantUpdated = false;
        Applicant applicant;
        try {
            transactionManager.initTransaction();
            Optional<Applicant> applicantOptional = applicantDao.findEntityById(applicantId);
            if (applicantOptional.isPresent()) {
                applicant = applicantOptional.get();
                applicant.setBeneficiary(privilege);
                applicantDao.update(applicant);
                isApplicantUpdated = true;
            }
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when updating applicant", e);
            throw new ServiceException("Error when updating applicant", e);
        } finally {
            transactionManager.endTransaction();
        }

    }

    /**
     * The edition of applicant's personal information
     *
     * @param name the name
     * @param surname the surname
     * @param lastname the lastname
     * @return true if applicant's personal information is edited
     */
    @Override
    public boolean editApplicantPersonalInformation(Long applicantId, String name, String surname, String lastname) throws ServiceException {
        boolean isApplicantUpdated = false;
        Applicant applicant;
        try {
            transactionManager.initTransaction();
            Optional<Applicant> applicantOptional = applicantDao.findEntityById(applicantId);
            if (applicantOptional.isPresent()) {
                System.out.println("Applicant is present");
                if (isApplicantSecureInformationValid(name, surname, lastname)) {
                    applicant = applicantOptional.get();
                    applicant.setFirstname(name);
                    applicant.setLastname(lastname);
                    applicant.setSurname(surname);
                    applicantDao.update(applicant);
                    isApplicantUpdated = true;
                }
            }
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when updating applicant data", e);
            throw new ServiceException("Error when updating applicant data", e);
        } finally {
            transactionManager.endTransaction();
        }

        return isApplicantUpdated;
    }

    /**
     * The validation of applicant's personal information
     *
     * @param name the name
     * @param surname the surname
     * @param lastname the lastname
     * @return true if applicant's personal information is validated
     */
    public boolean isApplicantSecureInformationValid(String name, String surname, String lastname) {
        SecureInformationValidator validator = SecureInformationValidatorImpl.getInstance();
        System.out.println(validator.isNameValid(name));
        System.out.println(validator.isLastnameValid(lastname));
        System.out.println(validator.isSurnameValid(surname));
        return validator.isNameValid(name)
                && validator.isLastnameValid(lastname)
                && validator.isSurnameValid(surname);
    }


}
