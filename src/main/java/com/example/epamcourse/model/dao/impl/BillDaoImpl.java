package com.example.epamcourse.model.dao.impl;

import com.example.epamcourse.model.dao.BillDao;
import com.example.epamcourse.model.dao.mapper.impl.BillResultSetHandler;
import com.example.epamcourse.model.entity.Bill;
import com.example.epamcourse.model.exception.DaoException;
import com.example.epamcourse.model.exception.TransactionException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * class BillDaoImpl
 *
 * @author M.Shubelko
 */
public class BillDaoImpl implements BillDao {
    /**
     * The logger
     */
    private static final Logger logger = LogManager.getLogger();

    /**
     * The instance
     */
    private static BillDao instance = new BillDaoImpl();

    /**
     * The jdbcTemplate
     */
    private JdbcTemplate<Bill> jdbcTemplate;

    private static final String FIND_ALL_BILLS = """
            SELECT bill_id, applicant_id, faculty_id, archive 
            FROM bills
            """;

    private static final String FIND_ALL_BILLS_BY_FACULTY_ID = """
            SELECT bill_id, applicant_id, faculty_id, archive 
            FROM bills
            WHERE faculty_id = ? AND archive = ?
            """;

    private static final String FIND_BILL_BY_ID = """
            SELECT bill_id, applicant_id, faculty_id, archive 
            FROM bills 
            WHERE bill_id = ?
            """;

    private static final String FIND_BILL_BY_APPLICANT_ID = """
            SELECT bill_id, applicant_id, faculty_id, archive 
            FROM bills 
            WHERE applicant_id = ?
            """;

    private static final String DELETE_BILL = """
              DELETE FROM bills 
              WHERE bill_id = ?
            """;

    private static final String DELETE_BILL_BY_APPLICANT_ID = """
              DELETE FROM bills 
              WHERE applicant_id = ? AND archive = 0
            """;

    private static final String ADD_BILL = """
            INSERT INTO bills (applicant_id)
            VALUE (?)
            """;

    private static final String FIND_ARCHIVE_BILL_BY_APPLICANT_ID = """
            SELECT bill_id, applicant_id, faculty_id, archive
            FROM bills
            WHERE applicant_id = ? AND archive = 1
            """;

    private static final String UPDATE_BILL = """
            UPDATE bills SET faculty_id = ?
            WHERE bill_id = ? AND archive = 0
            """;

    private static final String DELETE_BILLS = """
            UPDATE bills set archive = 1
            WHERE archive = 0
            """;

    private static final String SELECT_COUNT_OF_BILLS_BY_FACULTY_ID = """
            SELECT COUNT(bill_id)
            FROM bills
            WHERE faculty_id = ? AND archive = ?
            """;

    /**
     * The private constructor
     */
    private BillDaoImpl() {
        this.jdbcTemplate = new JdbcTemplate<>(new BillResultSetHandler());
    }

    /**
     * Gent instance
     *
     * @return instance the instance
     */
    public static BillDao getInstance() {
        return instance;
    }

    /**
     * Find all bills
     *
     * @return bills the bills
     * @throws DaoException the DaoException
     */
    @Override
    public List<Bill> findAll() throws DaoException {
        List<Bill> bills;
        try {
            bills = jdbcTemplate.executeSelectQuery(FIND_ALL_BILLS);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding all bills", e);
            throw new DaoException("Error when finding all bills", e);
        }

        return bills;
    }

    /**
     * Find bill by id
     *
     * @param id the id
     * @return billOptional the bill optional
     * @throws DaoException the DaoException
     */
    @Override
    public Optional<Bill> findEntityById(Long id) throws DaoException {
        Optional<Bill> billOptional;
        try {
            billOptional = jdbcTemplate.executeSelectQueryForObject(FIND_BILL_BY_ID, id);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding bill by id {} {}", id, e);
            throw new DaoException("Error when finding bill by id " + id, e);
        }

        return billOptional;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        return false;
    }

    /**
     * Add bill
     *
     * @param bill the bill
     * @return billId the bill id
     * @throws DaoException the DaoException
     */
    @Override
    public Long add(Bill bill) throws DaoException {
        long billId;
        try {
            billId = jdbcTemplate.executeInsertQuery(ADD_BILL,
                    bill.getApplicantId());
            System.out.println("Bill id: " + billId);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when adding all bills", e);
            throw new DaoException("Error when adding all bills", e);
        }

        return billId;
    }

    /**
     * Update bill
     *
     * @param bill the bill
     * @return true the true
     * @throws DaoException the DaoException
     */
    @Override
    public boolean update(Bill bill) throws DaoException {
        try {
            jdbcTemplate.executeInsertQuery(UPDATE_BILL,
                    bill.getFacultyId(),
                    bill.getBillId());
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when updating all bills", e);
            throw new DaoException("Error when updating all bills", e);
        }

        return true;
    }

    /**
     * Delete bill by applicantId
     *
     * @param applicantId the applicant id
     * @return true the true
     * @throws DaoException the DaoException
     */
    @Override
    public boolean deleteBillByApplicantId(Long applicantId) throws DaoException {
        try {
            jdbcTemplate.executeUpdateDeleteFields(DELETE_BILL_BY_APPLICANT_ID,
                    applicantId);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when updating all bills", e);
            throw new DaoException("Error when updating all bills", e);
        }

        return true;
    }

    /**
     * Checking bill's archiving
     *
     * @param applicantId the applicant id
     * @return applicantOptional the applicantOptional
     * @throws DaoException the DaoException
     */
    @Override
    public boolean isBillArchive(Long applicantId) throws DaoException {
        Optional<Bill> bill;
        try {
            bill = jdbcTemplate.executeSelectQueryForObject(FIND_ARCHIVE_BILL_BY_APPLICANT_ID, applicantId);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when checking bill's archiving", e);
            throw new DaoException("Error when checking bill's archiving", e);
        }
        return bill.isPresent();
    }

    /**
     * Find bill by applicantId
     *
     * @param applicantId the applicant id
     * @return applicantId the applicant id
     * @throws DaoException the DaoException
     */
    @Override
    public Optional<Bill> findBillByApplicantId(Long applicantId) throws DaoException {
        Optional<Bill> billOptional;
        try {
            billOptional = jdbcTemplate.executeSelectQueryForObject(FIND_BILL_BY_APPLICANT_ID, applicantId);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding bill by applicant id", e);
            throw new DaoException("Error when finding bill by applicant id ", e);
        }

        return billOptional;
    }

    /**
     * Find bills by facultyId
     *
     * @param facultyId the faculty id
     * @param isArchive the archiving
     * @return bills the bills
     * @throws DaoException the DaoException
     */
    @Override
    public List<Bill> findAllBillsByFacultyId(Long facultyId, Boolean isArchive) throws DaoException {
        List<Bill> bills;
        try {
            bills = jdbcTemplate.executeSelectQuery(FIND_ALL_BILLS_BY_FACULTY_ID, facultyId, isArchive);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding bill by faculty id {} {}", facultyId, e);
            throw new DaoException("Error when finding bill by faculty id " + facultyId, e);
        }
        return bills;
    }

    /**
     * Change all bill's status to archive
     *
     * @throws DaoException the DaoException
     */
    @Override
    public void changeStatusToArchive() throws DaoException {
        try {
            jdbcTemplate.executeUpdateDeleteFields(DELETE_BILLS);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when updating all bills", e);
            throw new DaoException("Error when updating all bills", e);
        }

    }

    @Override
    public int getCountOfBillsInFaculty(Long facultyId, boolean isArchive) throws DaoException {
        int count;
        try {
            count = jdbcTemplate.executeSelectCountQuery(SELECT_COUNT_OF_BILLS_BY_FACULTY_ID, facultyId, isArchive);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding faculties", e);
            throw new DaoException("Error when finding all faculties", e);
        }
        return count;
    }
}
