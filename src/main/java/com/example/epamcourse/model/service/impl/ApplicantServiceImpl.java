package com.example.epamcourse.model.service.impl;

import com.example.epamcourse.controller.command.SessionAttribute;
import com.example.epamcourse.model.dao.*;
import com.example.epamcourse.model.dao.impl.*;
import com.example.epamcourse.model.entity.Applicant;
import com.example.epamcourse.model.entity.Faculty;
import com.example.epamcourse.model.entity.Subject;
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
import java.util.Locale;
import java.util.Optional;

import static com.example.epamcourse.model.service.ApplicantFindingType.ALL;
import static com.example.epamcourse.model.service.ApplicantFindingType.ARCHIVE;

public class ApplicantServiceImpl implements ApplicantService {
    private static final Logger logger = LogManager.getLogger();
    private static final ApplicantServiceImpl instance = new ApplicantServiceImpl();
    private final TransactionManager transactionManager = TransactionManager.getInstance();
    private final ApplicantDao applicantDao = ApplicantDaoImpl.getInstance();
    private final SubjectDao subjectDao = SubjectDaoImpl.getInstance();
    private final BillDao billDao = BillDaoImpl.getInstance();
    private final CertificateDao certificateDao = CertificateDaoImpl.getInstance();
    private final FacultyDao facultyDao = FacultyDaoImpl.getInstance();

    private ApplicantServiceImpl() {
    }

    public static ApplicantServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean addPersonalInformation(String name, String surname, String lastname, Long accountId) throws ServiceException {
        boolean isApplicantSecureInformationAdded = false;
        SecureInformationValidator validator = SecureInformationValidatorImpl.getInstance();
        try {
            if (validator.isNameValid(name) && validator.isLastnameValid(lastname) && validator.isSurnameValid(surname)) {
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

    @Override
    public Long getApplicantIdByAccountId(Long accountId) throws ServiceException {
        Optional<Applicant> applicant;
        Long applicantId = 0L;
        try {
            transactionManager.initTransaction();
            applicant = applicantDao.getApplicantByAccountId(accountId);
            if (applicant.isPresent()) {
                applicantId = applicant.get().getApplicantId();
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

    @Override
    public List<Applicant> findApplicantsInFaculty(Long facultyId, int currentPageNumber) throws ServiceException {
        List<Applicant> applicants;
        Long applicantId = 0L;
        try {
            int recordsPerPage = 5;
            transactionManager.initTransaction();
            Optional<Faculty> faculty = facultyDao.findEntityById(facultyId);
            applicants = applicantDao
                    .findApplicantsInOrderByMarkInFaculty(facultyId, (currentPageNumber - 1) * recordsPerPage,
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

    @Override
    public List<Applicant> findApplicantsInFacultyBySurname(Long facultyId,
                                                            int currentPageNumber,
                                                            String recruitmentStatus) throws ServiceException {
        ApplicantFindingService applicantFindingService = ApplicantFindingServiceImpl.getInstance();
        List<Applicant> applicants = Collections.emptyList();
        Long applicantId = 0L;
        boolean isArchive = false;
        try {
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
                int countApplicants = billDao.findAllBillsByFacultyId(facultyId, isArchive).size();
                int applicantsSkipDepOnRecruitStatus = applicantFindingService.getCountOfApplicantsToSkip(recruitmentStatus,
                        recruitmentPlanFree, recruitmentPlanCanvas, countApplicants);
                int applicantsTakeDepOnRecruitStatus = applicantFindingService.getCountOfApplicantsToTake(recruitmentStatus,
                        recruitmentPlanFree, recruitmentPlanCanvas, countApplicants);
                applicants = applicantDao.findApplicantsInOrderByMarkInFacultyAndSurname(facultyId,
                        applicantsSkipDepOnRecruitStatus, applicantsTakeDepOnRecruitStatus,
                        (currentPageNumber - 1) * recordsPerPage,
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

    @Override
    public Optional<Applicant> getApplicantById(Long applicantId) throws ServiceException {
        Optional<Applicant> applicant = Optional.empty();
        try {
            transactionManager.initTransaction();
            applicant = applicantDao.findEntityById(applicantId);

            if (applicant.isPresent()) {
                applicantId = applicant.get().getApplicantId();
            }
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when getting applicant id{}", e);
            throw new ServiceException("Error when getting applicant id", e);
        } finally {
            transactionManager.endTransaction();
        }
        return applicant;
    }

    @Override
    public Optional<Applicant> getApplicantByAccountId(Long accountId) throws ServiceException {
        Optional<Applicant> applicantOptional = Optional.empty();
        try {
            transactionManager.initTransaction();
            applicantOptional = applicantDao.getApplicantByAccountId(accountId);
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

    @Override
    public boolean updateApplicantPrivileges(Long applicantId, boolean privilege) throws ServiceException {
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

        return isApplicantUpdated;
    }

    @Override
    public boolean updateApplicantPersonalData(Long applicantId, String name, String surname, String lastname) throws ServiceException {
        boolean isApplicantUpdated = false;
        Applicant applicant;
        try {
            transactionManager.initTransaction();
            Optional<Applicant> applicantOptional = applicantDao.findEntityById(applicantId);
            if (applicantOptional.isPresent()) {
                applicant = applicantOptional.get();
                applicant.setFirstname(name);
                applicant.setLastname(lastname);
                applicant.setSurname(surname);
                applicantDao.update(applicant);
                isApplicantUpdated = true;
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

    @Override
    public boolean isSubjectsExist(Long applicantId) throws ServiceException {
        List<Subject> subjects = Collections.emptyList();
        try {
            transactionManager.initTransaction();
            subjects = subjectDao.findSubjectByApplicantId(applicantId);
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when checking existing of subjects", e);
            throw new ServiceException("Error when checking existing of subjects", e);
        } finally {
            transactionManager.endTransaction();
        }
        return subjects.size() != 0;
    }

    @Override
    public boolean isCertificateExist(Long applicantId) throws ServiceException {
        Optional<Applicant> applicantOptional;
        boolean isCertificatePresent = false;
        try {
            transactionManager.initTransaction();
            applicantOptional = applicantDao.findEntityById(applicantId);
            if (applicantOptional.isPresent()) {
                isCertificatePresent = applicantOptional.get().getCertificateId() != null;
            }
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when checking existing of certificate", e);
            throw new ServiceException("Error when checking existing of certificate", e);
        } finally {
            transactionManager.endTransaction();
        }
        return isCertificatePresent;
    }


}
