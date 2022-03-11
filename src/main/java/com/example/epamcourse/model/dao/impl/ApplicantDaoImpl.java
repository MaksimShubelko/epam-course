package com.example.epamcourse.model.dao.impl;

import com.example.epamcourse.model.dao.ApplicantDao;
import com.example.epamcourse.model.dao.mapper.impl.ApplicantResultSetHandler;
import com.example.epamcourse.model.entity.Applicant;
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

/**
 * class ApplicantDaoImpl
 *
 * @author M.Shubelko
 */
public class ApplicantDaoImpl implements ApplicantDao {

    /**
     * The logger
     */
    private static final Logger logger = LogManager.getLogger();

    /**
     * The instance
     */
    private static final ApplicantDao instance = new ApplicantDaoImpl();

    /**
     * The jdbcTemplate
     */
    private final JdbcTemplate<Applicant> jdbcTemplate;

    private static final String FIND_APPLICANTS_IN_ORDER_BY_MARK_IN_FACULTY = """
            SELECT applicants.applicant_id, surname, firstname, lastname, account_id, beneficiary, certificate_id,
            (SELECT SUM(mark) FROM subjects WHERE applicant_id = applicants.applicant_id) as subjects_mark,
            (SELECT middle_mark FROM certificates WHERE certificate_id = applicants.certificate_id) as certificate_mark
            FROM applicants
            INNER JOIN bills b on applicants.applicant_id = b.applicant_id AND b.archive = 0
            INNER JOIN faculties f on f.faculty_id = b.faculty_id AND f.faculty_id = ?
            ORDER BY beneficiary DESC, subjects_mark + certificate_mark * 10 DESC 
            LIMIT ?, ?
            """;

    private static final String FIND_APPLICANTS_BY_SURNAME_IN_ORDER_BY_MARK_IN_FACULTY = """
            SELECT applicants.applicant_id, surname, firstname, lastname, account_id, beneficiary, certificate_id,
            (SELECT SUM(mark) FROM subjects WHERE applicant_id = applicants.applicant_id)           as subjects_mark,
            (SELECT middle_mark FROM certificates WHERE certificate_id = applicants.certificate_id) as certificate_mark
            FROM ( SELECT applicants.applicant_id, surname, firstname, lastname, account_id, beneficiary, certificate_id,
                   ROW_NUMBER() OVER (ORDER BY beneficiary DESC,
                   (SELECT SUM(mark) FROM subjects WHERE applicant_id = applicants.applicant_id) +
                   (SELECT middle_mark FROM certificates WHERE certificate_id = applicants.certificate_id) *
                   10 DESC) as row_num
                   FROM applicants
                   INNER JOIN bills b on applicants.applicant_id = b.applicant_id
                   INNER JOIN faculties f on f.faculty_id = b.faculty_id AND f.faculty_id = ?
                   WHERE b.archive = ?) as applicants
                   WHERE applicants.row_num > ? AND applicants.row_num <= ?
            ORDER BY beneficiary DESC, subjects_mark + certificate_mark * 10 DESC
            LIMIT ?, ?
            """;

    private static final String FIND_APPLICANT_BY_ACCOUNT_LOGIN = """
            SELECT applicants.applicant_id, surname, firstname, lastname, applicants.account_id, beneficiary
            FROM applicants
            INNER JOIN accounts ac on applicants.account_id = ac.account_id AND ac.login = ?       
            """;

    private static final String FIND_APPLICANT_BY_ACCOUNT_ID = """
            SELECT applicant_id, surname, firstname, lastname, account_id, beneficiary, certificate_id
            FROM applicants
            WHERE account_id = ?       
            """;

    private static final String FIND_APPLICANTS_BENEFICIARY_IN_FACULTY = """
            SELECT applicants.applicant_id, surname, firstname, lastname, account_id, beneficiary 
            FROM applicants 
            INNER JOIN bills b on applicants.applicant_id = b.applicant_id
            INNER JOIN faculties f on f.faculty_id = b.faculty_id AND f.faculty_name = ? AND applicants.beneficiary = true
            """;

    private static final String FIND_ALL_APPLICANTS = """
            SELECT applicant_id, firstname, lastname, surname, account_id, beneficiary
            FROM applicants
            """;

    private static final String FIND_APPLICANT_BY_ID = """
            SELECT applicant_id, firstname, lastname, surname, account_id, beneficiary, certificate_id
            FROM applicants
            WHERE applicant_id = ?
            """;

    private static final String DELETE_APPLICANT = """
              DELETE FROM applicants 
              WHERE applicant_id = ?
            """;

    private static final String ADD_APPLICANT = """
            INSERT INTO applicants (firstname, lastname, surname, account_id, beneficiary)
            VALUE (?, ?, ?, ?, ?)
            """;

    private static final String UPDATE_APPLICANT = """
            UPDATE applicants SET firstname = ?, 
            lastname = ?, surname = ?, certificate_id = ?, beneficiary = ?
            WHERE applicant_id = ?
            """;

    /**
     * The private constructor
     */
    private ApplicantDaoImpl() {
        this.jdbcTemplate = new JdbcTemplate<>(new ApplicantResultSetHandler());
    }

    /**
     * Get instance
     */
    public static ApplicantDao getInstance() {
        return instance;
    }

    /**
     * Find applicant in faculty in order by mark
     *
     * @param facultyId the faculty id
     * @param rowSkip the row skip
     * @param rowNext the row next
     * @return applicant the applicants
     * @throws DaoException the DaoException
     */
    @Override
    public List<Applicant> findApplicantsInOrderByMarkInFaculty(Long facultyId, long rowSkip,
                                                                int rowNext) throws DaoException {
        List<Applicant> applicants;
        try {
            applicants = jdbcTemplate.executeSelectQuery(FIND_APPLICANTS_IN_ORDER_BY_MARK_IN_FACULTY, facultyId, rowSkip, rowNext);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding applicants in order by mark", e);
            throw new DaoException("Error when finding applicants in order by mark", e);
        }

        return applicants;
    }

    /**
     * Find applicant in faculty in order by mark and recruitment status
     *
     * @param facultyId the faculty id
     * @param applicantsSkip the applicants skip
     * @param  applicantsTake the applicants take
     * @param rowSkip the row skip
     * @param rowNext the row next
     * @return applicants the applicants
     * @throws DaoException the DaoException
     */
    @Override
    public List<Applicant> findApplicantsInOrderByMarkInFacultyAndRecruitmentStatus(Long facultyId,
                                                                                    int applicantsSkip, int applicantsTake,
                                                                                    long rowSkip, int rowNext, Boolean isArchive) throws DaoException {

        List<Applicant> applicants;
        try {
            applicants = jdbcTemplate.executeSelectQuery(FIND_APPLICANTS_BY_SURNAME_IN_ORDER_BY_MARK_IN_FACULTY, facultyId, isArchive,
                    applicantsSkip, applicantsTake, rowSkip, rowNext);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding applicants in faculty in order by mark", e);
            throw new DaoException("Error when finding applicants in faculty in order by mark", e);
        }

        return applicants;
    }

    /**
     * Find applicant by account id
     *
     * @param id the id
     * @return applicantOptional the applicant optional
     * @throws DaoException the DaoException
     */
    @Override
    public Optional<Applicant> findApplicantByAccountId(Long id) throws DaoException {
        Optional<Applicant> applicantOptional;
        try {
            applicantOptional = jdbcTemplate.executeSelectQueryForObject(FIND_APPLICANT_BY_ACCOUNT_ID, id);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when getting applicant by id {} {}", id, e);
            throw new DaoException("Error when getting applicant by account id " + id, e);
        }

        return applicantOptional;
    }

    /**
     * Find all applicants
     *
     * @return applicants the applicants
     * @throws DaoException the DaoException
     */
    @Override
    public List<Applicant> findAll() throws DaoException {
        List<Applicant> applicants;
        try {
            applicants = jdbcTemplate.executeSelectQuery(FIND_ALL_APPLICANTS);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding all applicants", e);
            throw new DaoException("Error when finding all applicants", e);

        }

        return applicants;
    }

    /**
     * Find applicant by id
     *
     * @param id the id
     * @return applicantOptional the applicant optional
     * @throws DaoException the DaoException
     */
    @Override
    public Optional<Applicant> findEntityById(Long id) throws DaoException {
        Optional<Applicant> applicantOptional;
        try {
            applicantOptional = jdbcTemplate.executeSelectQueryForObject(FIND_APPLICANT_BY_ID, id);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding applicant by id {} {}", id, e);
            throw new DaoException("Error when finding applicant by id " + id, e);
        }

        return applicantOptional;
    }

    /**
     * Delete applicant
     *
     * @param id the id
     * @throws DaoException the DaoException
     */
    @Override
    public boolean delete(Long id) throws DaoException {
        try {
            jdbcTemplate.executeUpdateDeleteFields(DELETE_APPLICANT, id);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when deleting applicant", e);
            throw new DaoException("Error when deleting applicant", e);
        }

        return true;
    }

    /**
     * Add applicant
     *
     * @param applicant the applicant
     * @return id the id
     * @throws DaoException the DaoException
     */
    @Override
    public Long add(Applicant applicant) throws DaoException {
        long applicantId = 0;
        try {
            applicantId = jdbcTemplate.executeInsertQuery(ADD_APPLICANT,
                    applicant.getFirstname(),
                    applicant.getLastname(),
                    applicant.getSurname(),
                    applicant.getAccountId(),
                    applicant.getBeneficiary());
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when adding applicant", e);
            throw new DaoException("Error when adding applicant", e);
        }

        return applicantId;
    }

    /**
     * Update applicant
     *
     * @param applicant the applicant
     * @return true the true
     * @throws DaoException the DaoException
     */
    @Override
    public boolean update(Applicant applicant) throws DaoException {
        try {
            jdbcTemplate.executeInsertQuery(UPDATE_APPLICANT,
                    applicant.getFirstname(),
                    applicant.getLastname(),
                    applicant.getSurname(),
                    applicant.getCertificateId(),
                    applicant.getBeneficiary(),
                    applicant.getApplicantId());
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when updating applicant", e);
            throw new DaoException("Error when updating applicant", e);
        }

        return true;
    }
}
