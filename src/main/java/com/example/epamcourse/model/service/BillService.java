package com.example.epamcourse.model.service;

import com.example.epamcourse.model.exception.ServiceException;

public interface BillService {
    boolean addBill(Long applicantId) throws ServiceException;

    boolean updateBill(Long applicantId, Long facultyId) throws ServiceException;

    boolean deleteBill(Long applicantId) throws ServiceException;

    boolean isBillArchive(Long applicantId) throws ServiceException;

    long getCountOfBillsInFaculty(Long facultyId, Boolean isArchive) throws ServiceException;

    void restartRecruitment() throws ServiceException;
}
