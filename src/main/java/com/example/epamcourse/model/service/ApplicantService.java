package com.example.epamcourse.model.service;

import com.example.epamcourse.model.entity.Applicant;
import com.example.epamcourse.model.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface ApplicantService {
    boolean addPersonalInformation(String name, String surname, String lastname, Long accountId) throws ServiceException;

    Long getApplicantIdByAccountId(Long accountId) throws ServiceException;

    List<Applicant> findApplicantsInFaculty(Long facultyId, int currentPageNumber) throws ServiceException;

    List<Applicant> findApplicantsInFacultyBySurname(Long facultyId, int currentPageNumber, String recruitmentStatus) throws ServiceException;

    Optional<Applicant> getApplicantById(Long accountId) throws ServiceException;

    boolean updateApplicantPrivileges(Long applicantId, boolean privilege) throws ServiceException;

    boolean updateApplicantPersonalData(Long applicantId, String name, String surname, String lastname) throws ServiceException;

    boolean isCertificateExist(Long applicantId) throws ServiceException;

    boolean isSubjectsExist(Long applicantId) throws ServiceException;
}
