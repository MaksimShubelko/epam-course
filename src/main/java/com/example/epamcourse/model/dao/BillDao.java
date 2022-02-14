package com.example.epamcourse.model.dao;

import com.example.epamcourse.model.entity.Bill;
import com.example.epamcourse.model.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface BillDao extends BaseDao<Bill> {

    boolean deleteBillByApplicantId(Long applicantId) throws DaoException;

    boolean isBillArchive(Long applicantId) throws DaoException;

    Optional<Bill> findBillByApplicantId(Long applicantId) throws DaoException;

    List<Bill> findAllBillsByFacultyId(Long facultyId, Boolean isArchive) throws DaoException;

    boolean changeStatusToArchive() throws DaoException;
}
