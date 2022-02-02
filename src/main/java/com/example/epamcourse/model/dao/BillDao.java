package com.example.epamcourse.model.dao;

import com.example.epamcourse.model.entity.Bill;
import com.example.epamcourse.model.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface BillDao extends BaseDao<Bill> {

    Optional<Bill> findBillByApplicantId(Long applicantId) throws DaoException;

    List<Bill> findAllBillsByFacultyId(Long facultyId) throws DaoException;

    long getCountOfBillsInFaculty(Long facultyId) throws DaoException;

}
