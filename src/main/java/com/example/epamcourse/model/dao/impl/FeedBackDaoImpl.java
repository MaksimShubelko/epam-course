package com.example.epamcourse.model.dao.impl;

import com.example.epamcourse.model.dao.FeedBackDao;
import com.example.epamcourse.model.dao.mapper.impl.FeedBackResultSetHandler;
import com.example.epamcourse.model.entity.FeedBack;
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

public class FeedBackDaoImpl implements FeedBackDao {
    private static final Logger logger = LogManager.getLogger();
    private static FeedBackDao instance = new FeedBackDaoImpl();

    private JdbcTemplates<FeedBack> jdbcTemplate;

    private static final String FIND_ALL_FEEDBACKS = """
            SELECT feedback_id, administrator_id, applicant_id, message
            FROM feedbacks
            """;

    private static final String FIND_FEEDBACK_BY_ID = """
            SELECT feedback_id, administrator_id, applicant_id, message
            WHERE feedback_id = ?
            """;

    private static final String DELETE_FEEDBACK = """
              DELETE FROM feedbacks 
              WHERE feedback_id = ?
            """;

    private static final String ADD_FEEDBACK = """
            INSERT INTO feedbacks (administrator_id, applicant_id, message)
            VALUE (?, ?, ?)
            """;

    private static final String UPDATE_FEEDBACK = """
            UPDATE feedbacks SET message = ?
            WHERE feedback_id = ?
            """;

    private FeedBackDaoImpl() {
        this.jdbcTemplate = new JdbcTemplates<>(new FeedBackResultSetHandler());
    }

    @Override
    public List<FeedBack> findAll() throws DaoException {
        List<FeedBack>  feedBacks = jdbcTemplate.executeSelectQuery(FIND_ALL_FEEDBACKS);

        return feedBacks;
    }

    @Override
    public Optional<FeedBack> findEntityById(Long id) throws DaoException {
        Optional<FeedBack> feedBack = jdbcTemplate.executeSelectQueryForObject(FIND_FEEDBACK_BY_ID, id);

        return feedBack;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_FEEDBACK)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error when deleting feedback with Id {}. {}", id, e.getMessage());
            throw new DaoException("Error when deleting feedback with Id " + id, e);
        }

        return true;
    }

    @Override
    public Long add(FeedBack feedBack) throws DaoException {
        long certificateId = jdbcTemplate.executeInsertQuery(ADD_FEEDBACK,
                feedBack.getAdministratorId(),
                feedBack.getApplicantId(),
                feedBack.getMessage());

        return certificateId;
    }

    @Override
    public boolean update(FeedBack feedBack, String hashPassword) throws DaoException {
        jdbcTemplate.executeInsertQuery(UPDATE_FEEDBACK,
                feedBack.getAdministratorId(),
                feedBack.getApplicantId(),
                feedBack.getMessage());

        return true;

    }
}
