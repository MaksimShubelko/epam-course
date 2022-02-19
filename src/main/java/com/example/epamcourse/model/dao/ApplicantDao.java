package com.example.epamcourse.model.dao;

import com.example.epamcourse.model.entity.Applicant;
import com.example.epamcourse.model.exception.DaoException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ApplicantDao extends BaseDao<Applicant> {

    List<Applicant> findApplicantsInOrderByMarkInFaculty(Long facultyId, long rowSkip, int rowNext)
            throws DaoException;

    List<Applicant> findApplicantsInOrderByMarkInFacultyAndRecruitmentStatus(Long facultyId, int applicantsSkip, int applicantsTake, long rowSkip, int rowNext, Boolean isArchive) throws DaoException;

    Optional<Applicant> findApplicantByLogin(String login) throws DaoException;

    Optional<Applicant> findApplicantByAccountId(Long id) throws DaoException;

}
