package com.example.epamcourse.model.dao;

import com.example.epamcourse.model.entity.Subject;
import com.example.epamcourse.model.exception.DaoException;

import java.util.List;

public interface SubjectDao extends BaseDao<Subject> {

    List<Subject> findSubjectByApplicantId(Long applicantId) throws DaoException;
}
