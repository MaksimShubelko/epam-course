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
import com.example.epamcourse.model.validator.impl.CertificateValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class CertificateServiceImpl implements CertificateService {
    private static final Logger logger = LogManager.getLogger();
    private static final CertificateService instance = new CertificateServiceImpl();
    private final CertificateDao certificateDao = CertificateDaoImpl.getInstance();
    private final ApplicantDao applicantDao = ApplicantDaoImpl.getInstance();
    private final TransactionManager transactionManager = TransactionManager.getInstance();

    public static CertificateService getInstance() {
        return instance;
    }

    private CertificateServiceImpl() {
    }

    @Override
    public void addCertificate(Long applicantId) throws ServiceException {
        boolean isCertificateAdded = false;
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
                    isCertificateAdded = true;
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

    @Override
    public void updateCertificate(Long applicantId, double certificateMark) throws ServiceException {
        boolean isCertificateUpdated = false;
        CertificateValidatorImpl validator = CertificateValidatorImpl.getInstance();
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
                isCertificateUpdated = true;
            }
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when updating certificate for applicant", e);
            throw new ServiceException("Error when updating certificate for applicant", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    @Override
    public void deleteCertificate(Long applicantId) throws ServiceException {
        boolean isCertificateDeleted = false;
        Optional<Certificate> certificate = Optional.empty();
        try {
            transactionManager.initTransaction();
            isCertificateDeleted = certificateDao.delete(applicantId);
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when finding certificate for applicant", e);
            throw new ServiceException("Error when finding certificate for applicant", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    @Override
    public Optional<Certificate> findCertificate(Long applicantId) throws ServiceException {
        Optional<Certificate> certificate = Optional.empty();
        try {
            transactionManager.initTransaction();
            Optional<Applicant> applicant = applicantDao.findEntityById(applicantId);
            Long certificateId = 0L;
            if (applicant.isPresent()) {
                certificateId = applicant.get().getCertificateId();
                certificate = certificateDao.findEntityById(certificateId);
            }
            transactionManager.commit();

        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when finding certificate for applicant", e);
            throw new ServiceException("Error when finding certificate for applicant", e);
        } finally {
            transactionManager.endTransaction();
        }
        return certificate;
    }

}
