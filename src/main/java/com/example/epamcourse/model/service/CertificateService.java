package com.example.epamcourse.model.service;

import com.example.epamcourse.model.entity.Certificate;
import com.example.epamcourse.model.exception.ServiceException;

import java.util.Optional;

/**
 * interface CertificateService
 *
 * @author M.Shubelko
 */
public interface CertificateService {

    /**
     * The adding of certificate
     *
     * @param applicantId the applicant id
     * @throws ServiceException the service exception
     */
    void addCertificate(Long applicantId) throws ServiceException;

    /**
     * The updating of certificate
     *
     * @param applicantId the applicant id
     * @param certificateMark the certificate mark
     * @throws ServiceException the service exception
     */
    void updateCertificate(Long applicantId, double certificateMark) throws ServiceException;

    /**
     * The deleting of certificate
     *
     * @param applicantId the applicant id
     * @throws ServiceException the service exception
     */
    void deleteCertificate(Long applicantId) throws ServiceException;

    /**
     * The finding of certificate
     *
     * @param applicantId the applicant id
     * @return the certificate optional
     * @throws ServiceException the service exception
     */
    Optional<Certificate> findCertificate(Long applicantId) throws ServiceException;
}
