package com.example.epamcourse.model.service.impl;

import com.example.epamcourse.model.dao.BillDao;
import com.example.epamcourse.model.dao.impl.BillDaoImpl;
import com.example.epamcourse.model.dao.impl.TransactionManager;
import com.example.epamcourse.model.entity.Bill;
import com.example.epamcourse.model.exception.DaoException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.exception.TransactionException;
import com.example.epamcourse.model.service.BillService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

/**
 * class AccountServiceImpl
 *
 * @author M.Shubelko
 */
public class BillServiceImpl implements BillService {

    /**
     * The logger
     */
    private static final Logger logger = LogManager.getLogger();

    /**
     * The instance
     */
    private static final BillService instance = new BillServiceImpl();

    /**
     * The transaction manager
     */
    private final TransactionManager transactionManager = TransactionManager.getInstance();

    /**
     * The bill dao
     */
    private final BillDao billDao = BillDaoImpl.getInstance();

    /**
     * The private constructor
     */
    private BillServiceImpl() {
    }

    /**
     * The getting of instance
     *
     * @return instance the instance
     */
    public static BillService getInstance() {
        return instance;
    }

    /**
     * The adding of bill
     *
     * @param applicantId the applicant id
     * @return true if bill is added
     * @throws ServiceException the service exception
     */
    @Override
    public boolean addBill(Long applicantId) throws ServiceException {
        boolean isBillAdded = false;
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

    /**
     * The updating of bill
     *
     * @param applicantId the applicant id
     * @param facultyId the faculty id
     * @return true if bill is updated
     * @throws ServiceException the service exception
     */
    @Override
    public boolean updateBill(Long applicantId, Long facultyId) throws ServiceException {
        boolean isBillUpdated = false;
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

    /**
     * The deleting of bill
     *
     * @param applicantId the applicant id
     * @return true if bill is deleted
     * @throws ServiceException the service exception
     */
    @Override
    public boolean deleteBill(Long applicantId) throws ServiceException {
        boolean isBillDeleted;
        try {
            transactionManager.initTransaction();
            isBillDeleted = billDao.deleteBillByApplicantId(applicantId);
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when updating bill for applicant", e);
            throw new ServiceException("Error when updating bill for applicant", e);
        } finally {
            transactionManager.endTransaction();
        }
        return isBillDeleted;
    }

    /**
     * The checking of bill's archiving
     *
     * @param applicantId the applicant id
     * @return true if bill is updated
     * @throws ServiceException the service exception
     */
    @Override
    public boolean isBillArchive(Long applicantId) throws ServiceException {
        boolean isBillArchive;
        try {
            transactionManager.initTransaction();
            isBillArchive = billDao.isBillArchive(applicantId);
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when checked archived bill", e);
            throw new ServiceException("Error when checked archived bill", e);
        } finally {
            transactionManager.endTransaction();
        }
        return isBillArchive;
    }

    /**
     * The getting the count of bills in faculty
     *
     * @param isArchive the archiving
     * @param facultyId the faculty id
     * @return countOfBillsInFaculty the count of bills in faculty
     * @throws ServiceException the service exception
     */
    @Override
    public long getCountOfBillsInFaculty(Long facultyId, Boolean isArchive) throws ServiceException {
        long countOfBillsInFaculty;
        try {
            transactionManager.initTransaction();
            countOfBillsInFaculty = billDao.findAllBillsByFacultyId(facultyId, isArchive).size();
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

    /**
     * The restarting of recruitment
     *
     * @throws ServiceException the service exception
     */
    @Override
    public void restartRecruitment() throws ServiceException {
        try {
            transactionManager.initTransaction();
            billDao.changeStatusToArchive();
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when restarting recruitment", e);
            throw new ServiceException("Error when restarting recruitment", e);
        } finally {
            transactionManager.endTransaction();
        }
    }
}
