package com.example.epamcourse.model.dao.impl;

import com.example.epamcourse.model.dao.SubjectDao;
import com.example.epamcourse.model.dao.mapper.impl.SubjectResultSetHandler;
import com.example.epamcourse.model.entity.Subject;
import com.example.epamcourse.model.exception.DaoException;
import com.example.epamcourse.model.exception.TransactionException;
import com.example.epamcourse.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SubjectDaoImpl implements SubjectDao {
    private static final Logger logger = LogManager.getLogger();
    private static SubjectDao instance = new SubjectDaoImpl();

    private JdbcTemplate<Subject> jdbcTemplate;

    private static final String FIND_ALL_SUBJECTS = """
            SELECT subject_id, subject_type, mark, applicant_id
            FROM subjects
            """;

    private static final String FIND_SUBJECT_BY_ID = """
            SELECT subject_id, subject_type, mark, applicant_id
            FROM subjects
            WHERE subject_id = ?
            """;

    private static final String FIND_SUBJECT_BY_APPLICANT_ID = """
            SELECT subject_id, subject_type, mark, applicant_id
            FROM subjects
            WHERE applicant_id = ?
            """;

    private static final String DELETE_SUBJECTS_BY_APPLICANT_ID = """
              DELETE FROM subjects 
              WHERE applicant_id = ?
            """;

    private static final String ADD_SUBJECT = """
            INSERT INTO subjects (subject_type, mark, applicant_id)
            VALUE (?, ?, ?)
            """;

    private static final String UPDATE_SUBJECT = """
            UPDATE subjects SET mark = ? 
            WHERE subject_id = ?
            """;

    private SubjectDaoImpl() {
        this.jdbcTemplate = new JdbcTemplate<>(new SubjectResultSetHandler());
    }

    @Override
    public List<Subject> findAll() throws DaoException {
        List<Subject> subjects = null;
        try {
            subjects = jdbcTemplate.executeSelectQuery(FIND_ALL_SUBJECTS);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding all subjects");
            throw new DaoException("Error when finding all subjects", e);
        }

        return subjects;
    }

    @Override
    public Optional<Subject> findEntityById(Long id) throws DaoException {
        Optional<Subject> subject = null;
        try {
            subject = jdbcTemplate.executeSelectQueryForObject(FIND_SUBJECT_BY_ID, id);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding subject by id {}", id);
            throw new DaoException("Error when finding subject by id " + id, e);
        }

        return subject;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SUBJECTS_BY_APPLICANT_ID)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error when deleting subject with Id {}.", id);
            throw new DaoException("Error when deleting subject with Id " + id, e);
        }

        return true;
    }

    @Override
    public Long add(Subject subject) throws DaoException {
        long certificateId = 0;
        try {
            certificateId = jdbcTemplate.executeInsertQuery(ADD_SUBJECT,
                    subject.getSubjectType().toString(),
                    subject.getMark(),
                    subject.getApplicantId());
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when adding subject");
            throw new DaoException("Error when adding subject", e);
        }

        return certificateId;
    }

    @Override
    public boolean update(Subject subject) throws DaoException {
        try {
            jdbcTemplate.executeInsertQuery(UPDATE_SUBJECT,
                    subject.getMark(),
                    subject.getSubjectId());
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when updating subject");
            throw new DaoException("Error when updating subject ", e);
        }

        return true;
    }

    public static SubjectDao getInstance() {
        return instance;
    }

    @Override
    public List<Subject> findSubjectByApplicantId(Long applicantId) throws DaoException {
        List<Subject> subject = Collections.emptyList();
        try {
            subject = jdbcTemplate.executeSelectQuery(FIND_SUBJECT_BY_APPLICANT_ID, applicantId);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding subjects by applicantId {}", applicantId);
            throw new DaoException("Error when finding subjects by applicantId" + applicantId, e);
        }

        return subject;
    }
}
