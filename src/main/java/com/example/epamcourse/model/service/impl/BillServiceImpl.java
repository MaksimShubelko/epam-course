package com.example.epamcourse.model.service.impl;

import com.example.epamcourse.model.dao.AdministratorDao;
import com.example.epamcourse.model.dao.ApplicantDao;
import com.example.epamcourse.model.dao.BillDao;
import com.example.epamcourse.model.dao.impl.*;
import com.example.epamcourse.model.entity.Applicant;
import com.example.epamcourse.model.entity.Bill;
import com.example.epamcourse.model.exception.DaoException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.exception.TransactionException;
import com.example.epamcourse.model.service.BillService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class BillServiceImpl implements BillService {
    private static final Logger logger = LogManager.getLogger();
    private static final BillServiceImpl instance = new BillServiceImpl();
    private final TransactionManager transactionManager = TransactionManager.getInstance();
    private final BillDao billDao = BillDaoImpl.getInstance();
    private final ApplicantDao applicantDao = ApplicantDaoImpl.getInstance();
    private final AdministratorDao administratorDao = AdministratorDaoImpl.getInstance();


    public static BillServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean addBill(Long applicantId) throws ServiceException {
        boolean isBillAdded = false;
        Applicant applicant;
        try {
            transactionManager.initTransaction();
            Optional<Bill> billOptional = billDao.findBillByApplicantId(applicantId);
            if (billOptional.isEmpty()) {
                Bill bill = new Bill(applicantId);
                    billDao.add(bill);
                    transactionManager.commit();
                    isBillAdded = true;
            }
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when adding bill for applicant", e);
            throw new ServiceException("Error when adding bill for applicant", e);
        } finally {
            transactionManager.endTransaction();
        }
        return isBillAdded;
    }

    @Override
    public boolean updateBill(Long applicantId, Long facultyId) throws ServiceException {
        boolean isBillUpdated = false;
        Applicant applicant;
        try {
            transactionManager.initTransaction();
            Optional<Bill> billOptional = billDao.findBillByApplicantId(applicantId);
            if (billOptional.isPresent()) {
                Bill bill = billOptional.get();
                bill.setFacultyId(facultyId);
                billDao.update(bill);
                transactionManager.commit();
                isBillUpdated = true;
            }
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when updating bill for applicant", e);
            throw new ServiceException("Error when updating bill for applicant", e);
        } finally {
            transactionManager.endTransaction();
        }
        return isBillUpdated;
    }

    @Override
    public boolean deleteBill(Long applicantId) throws ServiceException {
        return false; //todo
    }


    @Override
    public long getCountOfBillsInFaculty(Long facultyId) throws ServiceException {
        long countOfBillsInFaculty;
        try {
            transactionManager.initTransaction();
            countOfBillsInFaculty = billDao.findAllBillsByFacultyId(facultyId).size();
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when finding count of bills in faculty", e);
            throw new ServiceException("Error when finding count of bills in faculty", e);
        } finally {
            transactionManager.endTransaction();
        }
        return countOfBillsInFaculty;
    }
}
