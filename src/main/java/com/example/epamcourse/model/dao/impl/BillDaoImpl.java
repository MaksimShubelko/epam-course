package com.example.epamcourse.model.dao.impl;

import com.example.epamcourse.model.dao.BillDao;
import com.example.epamcourse.model.dao.mapper.impl.BillResultSetHandler;
import com.example.epamcourse.model.entity.Bill;
import com.example.epamcourse.model.exception.DaoException;
import com.example.epamcourse.model.exception.TransactionException;
import com.example.epamcourse.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BillDaoImpl implements BillDao {
    private static final Logger logger = LogManager.getLogger();
    private static BillDao instance = new BillDaoImpl();

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
            INSERT INTO bills (bill_id, applicant_id, faculty_id)
            VALUE (?, ?, ?)
            """;

    private static final String FIND_ARCHIVE_BILL_BY_APPLICANT_ID = """
            SELECT bill_id, applicant_id, faculty_id, archive\040
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

    private BillDaoImpl() {
        this.jdbcTemplate = new JdbcTemplate<>(new BillResultSetHandler());
    }

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

    @Override
    public Optional<Bill> findEntityById(Long id) throws DaoException {
        Optional<Bill> bill;
        try {
            bill = jdbcTemplate.executeSelectQueryForObject(FIND_BILL_BY_ID, id);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding bill by id {} {}", id, e);
            throw new DaoException("Error when finding bill by id " + id, e);
        }

        return bill;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        return false;
    }

    @Override
    public Long add(Bill bill) throws DaoException {
        long billId;
        try {
            billId = jdbcTemplate.executeInsertQuery(ADD_BILL,
                    bill.getBillId(),
                    bill.getApplicantId(),
                    bill.getFacultyId());
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when adding all bills", e);
            throw new DaoException("Error when adding all bills", e);
        }

        return billId;
    }

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

    public static BillDao getInstance() {
        return instance;
    }

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

    @Override
    public boolean isBillArchive(Long applicantId) throws DaoException {
        Optional<Bill> bill;
        try {
            bill = jdbcTemplate.executeSelectQueryForObject(FIND_ARCHIVE_BILL_BY_APPLICANT_ID, applicantId);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding bill by applicant id {} {}", applicantId, e);
            throw new DaoException("Error when finding bill by applicant id " + applicantId, e);
        }
        return bill.isPresent();
    }

    @Override
    public Optional<Bill> findBillByApplicantId(Long applicantId) throws DaoException {
        Optional<Bill> bill;
        try {
            bill = jdbcTemplate.executeSelectQueryForObject(FIND_BILL_BY_APPLICANT_ID, applicantId);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding bill by applicant id {} {}", applicantId, e);
            throw new DaoException("Error when finding bill by applicant id " + applicantId, e);
        }

        return bill;
    }

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

    @Override
    public boolean changeStatusToArchive() throws DaoException {
        try {
            jdbcTemplate.executeUpdateDeleteFields(DELETE_BILLS);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when updating all bills", e);
            throw new DaoException("Error when updating all bills", e);
        }

        return true;
    }
}
