package com.example.epamcourse.model.dao.impl;

import com.example.epamcourse.model.dao.RecruitmentDao;
import com.example.epamcourse.model.dao.mapper.impl.RecruitmentResultSetHandler;
import com.example.epamcourse.model.entity.Recruitment;
import com.example.epamcourse.model.exception.DaoException;
import com.example.epamcourse.model.exception.TransactionException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * class RecruitmentDaoImpl
 *
 * @author M.Shubelko
 */
public class RecruitmentDaoImpl implements RecruitmentDao {

    /**
     * The logger
     */
    private static final Logger logger = LogManager.getLogger();

    /** The constant RECRUITMENT_ID **/
    private static final Long RECRUITMENT_ID = 1L;

    /**
     * The instance
     */
    private static RecruitmentDao instance = new RecruitmentDaoImpl();

    /**
     * The jdbcTemplate
     */
    private JdbcTemplate<Recruitment> jdbcTemplate;

    private static final String UPDATE_RECRUITMENT = """
            UPDATE recruitments SET status = ?, 
            finish_recruitment = ?
            WHERE recruitment_id = ?
            """;

    private static final String FIND_RECRUITMENT = """
            SELECT recruitment_id, status, finish_recruitment
            FROM recruitments
            WHERE recruitment_id = ?
            """;

    /**
     * The private constructor
     */
    private RecruitmentDaoImpl() {
        this.jdbcTemplate = new JdbcTemplate<>(new RecruitmentResultSetHandler());
    }

    /**
     * GetInstance
     *
     * @return instance
     */
    public static RecruitmentDao getInstance() {
        return instance;
    }

    @Override
    public List<Recruitment> findAll() throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Recruitment> findEntityById(Long id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Long add(Recruitment recruitment) throws DaoException {
        throw new UnsupportedOperationException();
    }

    /**
     * Update recruitment
     *
     * @return true the true
     * @throws DaoException the DaoException
     */
    @Override
    public boolean update(Recruitment recruitment) throws DaoException {
        try {
            jdbcTemplate.executeInsertQuery(UPDATE_RECRUITMENT,
                    recruitment.getRecruitmentStatus(),
                    recruitment.getFinishRecruitment(),
                    recruitment.getRecruitmentId());
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when updating recruitment", e);
            throw new DaoException("Error when updating recruitment ", e);
        }

        return true;
    }

    /**
     * Find recruitment
     *
     * @return recruitmentOptional the recruitment optional
     * @throws DaoException the DaoException
     */
    @Override
    public Optional<Recruitment> findRecruitment() throws DaoException {
        Optional<Recruitment> recruitment;
        try {
            recruitment = jdbcTemplate.executeSelectQueryForObject(FIND_RECRUITMENT, RECRUITMENT_ID);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding faculty by", e);
            throw new DaoException("Error when finding faculty by id", e);
        }

        return recruitment;
    }
}
