package com.example.epamcourse.model.service.impl;

import com.example.epamcourse.model.dao.ApplicantDao;
import com.example.epamcourse.model.dao.CertificateDao;
import com.example.epamcourse.model.dao.impl.ApplicantDaoImpl;
import com.example.epamcourse.model.dao.impl.CertificateDaoImpl;
import com.example.epamcourse.model.dao.impl.TransactionManager;
import com.example.epamcourse.model.entity.Applicant;
import com.example.epamcourse.model.entity.Certificate;
import com.example.epamcourse.model.exception.DaoException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.exception.TransactionException;
import com.example.epamcourse.model.service.CertificateService;
import com.example.epamcourse.model.validator.CertificateValidator;
import com.example.epamcourse.model.validator.impl.CertificateValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

/**
 * class CertificateServiceImpl
 *
 * @author M.Shubelko
 */
public class CertificateServiceImpl implements CertificateService {

    /**
     * The logger
     */
    private static final Logger logger = LogManager.getLogger();

    /**
     * The instance
     */
    private static final CertificateService instance = new CertificateServiceImpl();

    /**
     * The certificate dao
     */
    private final CertificateDao certificateDao = CertificateDaoImpl.getInstance();

    /**
     * The applicant dao
     */
    private final ApplicantDao applicantDao = ApplicantDaoImpl.getInstance();

    /**
     * The transaction manager
     */
    private final TransactionManager transactionManager = TransactionManager.getInstance();

    /**
     * The getting of instance
     *
     * @return instance the instance
     */
    public static CertificateService getInstance() {
        return instance;
    }

    /**
     * The private constructor
     */
    private CertificateServiceImpl() {
    }

    /**
     * The adding of certificate
     *
     * @param applicantId the applicant id
     * @throws ServiceException the service exception
     */
    @Override
    public void addCertificate(Long applicantId) throws ServiceException {
        Applicant applicant;
        try {
            transactionManager.initTransaction();
            Optional<Applicant> applicantOptional = applicantDao.findEntityById(applicantId);
            if (applicantOptional.isPresent()) {
                applicant = applicantOptional.get();
                if (applicant.getCertificateId() == 0) {
                    Certificate certificate = new Certificate();
                    Long certificateId = certificateDao.add(certificate);
                    applicant.setCertificateId(certificateId);
                    applicantDao.update(applicant);
                    transactionManager.commit();
                }
            }
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when adding certificate for applicant", e);
            throw new ServiceException("Error when adding certificate for applicant", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    /**
     * The updating of certificate
     *
     * @param applicantId the applicant id
     * @param certificateMark the certificate mark
     * @throws ServiceException the service exception
     */
    @Override
    public void updateCertificate(Long applicantId, double certificateMark) throws ServiceException {
        CertificateValidator validator = CertificateValidatorImpl.getInstance();
        try {
            if (validator.isCertificateMarkValid(certificateMark)) {
                transactionManager.initTransaction();
                Optional<Applicant> applicant = applicantDao.findEntityById(applicantId);
                Long certificateId = 0L;
                if (applicant.isPresent()) {
                    certificateId = applicant.get().getCertificateId();
                }
                Certificate certificate = new Certificate(certificateId, certificateMark);
                certificateDao.update(certificate);
                transactionManager.commit();
            }
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when updating certificate for applicant", e);
            throw new ServiceException("Error when updating certificate for applicant", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    /**
     * The deleting of certificate
     *
     * @param applicantId the applicant id
     * @throws ServiceException the service exception
     */
    @Override
    public void deleteCertificate(Long applicantId) throws ServiceException {
        try {
            transactionManager.initTransaction();
            certificateDao.delete(applicantId);
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when finding certificate for applicant", e);
            throw new ServiceException("Error when finding certificate for applicant", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    /**
     * The finding of certificate
     *
     * @param applicantId the applicant id
     * @return certificateOptional the certificateOptional
     * @throws ServiceException the service exception
     */
    @Override
    public Optional<Certificate> findCertificate(Long applicantId) throws ServiceException {
        Optional<Certificate> certificateOptional = Optional.empty();
        try {
            transactionManager.initTransaction();
            Optional<Applicant> applicant = applicantDao.findEntityById(applicantId);
            Long certificateId;
            if (applicant.isPresent()) {
                certificateId = applicant.get().getCertificateId();
                certificateOptional = certificateDao.findEntityById(certificateId);
            }
            transactionManager.commit();

        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when finding certificate for applicant", e);
            throw new ServiceException("Error when finding certificate for applicant", e);
        } finally {
            transactionManager.endTransaction();
        }
        return certificateOptional;
    }

}
