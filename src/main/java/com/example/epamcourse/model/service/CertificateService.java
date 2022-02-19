package com.example.epamcourse.model.service;

import com.example.epamcourse.model.entity.Certificate;
import com.example.epamcourse.model.exception.ServiceException;

import java.util.Optional;

public interface CertificateService {
    void addCertificate(Long applicantId) throws ServiceException;

    void updateCertificate(Long applicantId, double certificateMark) throws ServiceException;

    void deleteCertificate(Long applicantId) throws ServiceException;

    Optional<Certificate> findCertificate(Long applicantId) throws ServiceException;
}
