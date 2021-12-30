package com.example.epamcourse.model.dao;

import com.example.epamcourse.model.entity.Applicant;
import com.example.epamcourse.model.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface ApplicantDao extends BaseDao<Applicant> {
    List<Applicant> findApplicantsBySurname(String surname) throws DaoException;

    List<Applicant> findApplicantsInOrderByMarkInFaculty(String facultyName) throws DaoException;

    Optional<Applicant> getApplicantByLogin(String login) throws DaoException;

    Optional<Applicant> getApplicantByAccountId(Long id) throws DaoException;

    List<Applicant> findApplicantsIsBeneficiary(String facultyName) throws DaoException;

}
