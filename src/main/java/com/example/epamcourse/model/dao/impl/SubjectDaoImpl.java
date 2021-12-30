package com.example.epamcourse.model.dao.impl;

import com.example.epamcourse.model.dao.SubjectDao;
import com.example.epamcourse.model.dao.mapper.impl.SubjectResultSetHandler;
import com.example.epamcourse.model.entity.Subject;
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
            WHERE subject_id = ?
            """;

    private static final String DELETE_SUBJECT = """
              DELETE FROM subjects 
              WHERE subject_id = ?
            """;

    private static final String ADD_SUBJECT = """
            INSERT INTO feedbacks (subject_type, mark, applicant_id)
            VALUE (?, ?, ?)
            """;

    private static final String UPDATE_SUBJECT = """
            UPDATE subjects SET subject_type = ?, mark = ?, 
            WHERE subject_id = ?
            """;

    private SubjectDaoImpl() {
        this.jdbcTemplate = new JdbcTemplate<>(new SubjectResultSetHandler());
    }

    @Override
    public List<Subject> findAll() throws DaoException {
        List<Subject> subjects = jdbcTemplate.executeSelectQuery(FIND_ALL_SUBJECTS);

        return subjects;
    }

    @Override
    public Optional<Subject> findEntityById(Long id) throws DaoException {
        Optional<Subject> subject = jdbcTemplate.executeSelectQueryForObject(FIND_SUBJECT_BY_ID, id);

        return subject;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SUBJECT)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error when deleting subject with Id {}. {}", id, e.getMessage());
            throw new DaoException("Error when deleting subject with Id " + id, e);
        }

        return true;
    }

    @Override
    public Long add(Subject subject) throws DaoException {
        long certificateId = jdbcTemplate.executeInsertQuery(ADD_SUBJECT,
                subject.getSubjectType(),
                subject.getMark(),
                subject.getApplicantId());

        return certificateId;
    }

    @Override
    public boolean update(Subject subject, String hashPassword) throws DaoException {
        jdbcTemplate.executeInsertQuery(UPDATE_SUBJECT,
                subject.getSubjectType(),
                subject.getApplicantId(),
                subject.getMark(),
                subject.getSubjectId());

        return true;

    }
}
