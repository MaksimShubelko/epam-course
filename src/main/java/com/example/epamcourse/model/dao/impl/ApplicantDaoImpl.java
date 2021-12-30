package com.example.epamcourse.model.dao.impl;

import com.example.epamcourse.model.dao.ApplicantDao;
import com.example.epamcourse.model.dao.mapper.impl.ApplicantResultSetHandler;
import com.example.epamcourse.model.entity.Applicant;
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

public class ApplicantDaoImpl implements ApplicantDao {
    private static final Logger logger = LogManager.getLogger();
    private static ApplicantDao instance = new ApplicantDaoImpl();

    private JdbcTemplate<Applicant> jdbcTemplate;

    private static final String FIND_APPLICANTS_BY_SURNAME = """
            SELECT applicant_id, firstname, lastname, surname, account_id, beneficiary
            WHERE surname = ?
            """;

    private static final String FIND_APPLICANTS_IN_ORDER_BY_MARK_IN_FACULTY = """
            SELECT applicants.applicant_id, surname, firstname, lastname, account_id, beneficiary FROM applicants 
            INNER JOIN bills b on applicants.applicant_id = b.applicant_id
            INNER JOIN faculties f on f.faculty_id = b.faculty_id AND f.faculty_name = ? ORDER BY b.total_mark DESC
            """;

    private static final String FIND_APPLICANT_BY_ACCOUNT_LOGIN = """
            SELECT applicants.applicant_id, surname, firstname, lastname, applicants.account_id, beneficiary FROM applicants
            INNER JOIN accounts ac on applicants.account_id = ac.account_id AND ac.login = ?       
            """;

    private static final String FIND_APPLICANT_BY_ACCOUNT_ID = """
            SELECT applicants.applicant_id, surname, firstname, lastname, applicants.account_id, beneficiary FROM applicants
            WHERE account_id = ?       
            """;

    private static final String FIND_APPLICANTS_BENEFICIARY_IN_FACULTY = """
            SELECT applicants.applicant_id, surname, firstname, lastname, account_id, beneficiary FROM applicants 
            INNER JOIN bills b on applicants.applicant_id = b.applicant_id
            INNER JOIN faculties f on f.faculty_id = b.faculty_id AND f.faculty_name = ? AND applicants.beneficiary = true;
            """;

    private static final String FIND_ALL_APPLICANTS = """
            SELECT applicant_id, firstname, lastname, surname, account_id, beneficiary
            FROM applicants
            """;

    private static final String FIND_APPLICANT_BY_ID = """
            SELECT applicant_id, firstname, lastname, surname, account_id, beneficiary
            WHERE applicant_id = ?
            """;

    private static final String DELETE_APPLICANT = """
              DELETE FROM applicants 
              WHERE applicant_id = ?
            """;

    private static final String ADD_APPLICANT = """
            INSERT INTO administrators (firstname, lastname, surname, account_id, beneficiary, total_mark)
            VALUE (?, ?, ?, ?, ?)
            """;

    private static final String UPDATE_APPLICANT = """
            UPDATE account SET firstname = ?, lastname = ?, surname = ?, beneficiary = ?
            WHERE applicant_id = ?
            """;

    private ApplicantDaoImpl() {
        this.jdbcTemplate = new JdbcTemplate<>(new ApplicantResultSetHandler());
    }


    @Override
    public List<Applicant> findApplicantsBySurname(String surname) throws DaoException {
        List<Applicant>  applicants = jdbcTemplate.executeSelectQuery(FIND_ALL_APPLICANTS);

        return applicants;
    }

    @Override
    public List<Applicant> findApplicantsInOrderByMarkInFaculty(String facultyName) throws DaoException {
        List<Applicant>  applicants = jdbcTemplate.executeSelectQuery(FIND_APPLICANTS_IN_ORDER_BY_MARK_IN_FACULTY);

        return applicants;
    }

    @Override
    public Optional<Applicant> getApplicantByLogin(String login) throws DaoException {
        Optional<Applicant> applicant = jdbcTemplate.executeSelectQueryForObject(FIND_APPLICANT_BY_ACCOUNT_LOGIN, login);

        return applicant;
    }

    @Override
    public Optional<Applicant> getApplicantByAccountId(Long id) throws DaoException {
        Optional<Applicant> applicant = jdbcTemplate.executeSelectQueryForObject(FIND_APPLICANT_BY_ACCOUNT_LOGIN, id);

        return applicant;
    }

    @Override
    public List<Applicant> findApplicantsIsBeneficiary(String facultyName) throws DaoException {
        List<Applicant>  applicants = jdbcTemplate.executeSelectQuery(FIND_APPLICANTS_BENEFICIARY_IN_FACULTY, facultyName);

        return applicants;
    }

    @Override
    public List<Applicant> findAll() throws DaoException {
        List<Applicant>  applicants = jdbcTemplate.executeSelectQuery(FIND_ALL_APPLICANTS);

        return applicants;
    }

    @Override
    public Optional<Applicant> findEntityById(Long id) throws DaoException {
        Optional<Applicant> applicant = jdbcTemplate.executeSelectQueryForObject(FIND_APPLICANT_BY_ID, id);

        return applicant;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_APPLICANT)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error when deleting applicant with Id {}. {}", id, e.getMessage());
            throw new DaoException("Error when applicant with Id " + id, e);
        }

        return true;
    }

    @Override
    public Long add(Applicant applicant) throws DaoException {
        long applicantId = jdbcTemplate.executeInsertQuery(ADD_APPLICANT,
                applicant.getFirstname(),
                applicant.getLastname(),
                applicant.getLastname(),
                applicant.getBeneficiary(),
                applicant.getAccountId());

        return applicantId;
    }

    @Override
    public boolean update(Applicant applicant, String hashPassword) throws DaoException {
        jdbcTemplate.executeInsertQuery(UPDATE_APPLICANT,
                applicant.getFirstname(),
                applicant.getLastname(),
                applicant.getLastname(),
                applicant.getBeneficiary(),
                applicant.getAccountId(),
                applicant.getAccountId());

        return true;
    }
}
