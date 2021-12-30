package com.example.epamcourse.model.dao.impl;

import com.example.epamcourse.model.dao.BillDao;
import com.example.epamcourse.model.dao.mapper.impl.BillResultSetHandler;
import com.example.epamcourse.model.entity.Bill;
import com.example.epamcourse.model.exception.DaoException;
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
            SELECT bill_id, applicant_id, total_mark, faculty_id 
            FROM bills
            """;

    private static final String FIND_BILL_BY_ID = """
            SELECT bill_id, applicant_id, total_mark, faculty_id 
            FROM bills 
            WHERE bill_id = ?
            """;

    private static final String DELETE_BILL = """
              DELETE FROM bills 
              WHERE bill_id = ?
            """;

    private static final String ADD_BILL = """
            INSERT INTO bills (bill_id, applicant_id, total_mark, faculty_id )
            VALUE (?, ?, ?, ?)
            """;

    private static final String UPDATE_BILL = """
            UPDATE bills SET faculty_id = ?
            WHERE bill_id = ?
            """;

    private BillDaoImpl() {
        this.jdbcTemplate = new JdbcTemplate<>(new BillResultSetHandler());
    }

    @Override
    public List<Bill> findAll() throws DaoException {
        List<Bill>  bills = jdbcTemplate.executeSelectQuery(FIND_ALL_BILLS);

        return bills;
    }

    @Override
    public Optional<Bill> findEntityById(Long id) throws DaoException {
        Optional<Bill> bill = jdbcTemplate.executeSelectQueryForObject(FIND_BILL_BY_ID, id);

        return bill;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BILL)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error when deleting bill with Id {}. {}", id, e.getMessage());
            throw new DaoException("Error when deleting bill with Id " + id, e);
        }
        return false;
    }

    @Override
    public Long add(Bill bill) throws DaoException {
        long billId = jdbcTemplate.executeInsertQuery(ADD_BILL,
                bill.getApplicantId(),
                bill.getFacultyId(),
                bill.getTotalMark());

        return billId;
    }

    @Override
    public boolean update(Bill bill, String hashPassword) throws DaoException {
        jdbcTemplate.executeInsertQuery(UPDATE_BILL,
                bill.getApplicantId(),
                bill.getFacultyId(),
                bill.getTotalMark(),
                bill.getBillId());

        return true;
    }
}
