package com.example.epamcourse.model.dao.impl;

import com.example.epamcourse.model.dao.FacultyDao;
import com.example.epamcourse.model.dao.mapper.impl.FacultyResultSetHandler;
import com.example.epamcourse.model.entity.Faculty;
import com.example.epamcourse.model.exception.DaoException;
import com.example.epamcourse.model.exception.TransactionException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * class FacultyDaoImpl
 *
 * @author M.Shubelko
 */
public class FacultyDaoImpl implements FacultyDao {

    /**
     * The logger
     */
    private static final Logger logger = LogManager.getLogger();

    /**
     * The instance
     */
    private static final FacultyDao instance = new FacultyDaoImpl();

    /**
     * The jdbcTemplate
     */
    private final JdbcTemplate<Faculty> jdbcTemplate;

    private static final String FIND_ALL_FACULTIES = """
            SELECT faculty_id, faculty_name, 
            recruitment_plan_free, recruitment_plan_canvas
            FROM faculties
            """;

    private static final String FIND_FACULTY_BY_ID = """
            SELECT faculty_id, faculty_name, 
            recruitment_plan_free, recruitment_plan_canvas
            FROM faculties
            WHERE faculty_id = ?
            """;

    private static final String FIND_FACULTIES_LIMIT = """
            SELECT faculty_id, faculty_name, 
            recruitment_plan_free, recruitment_plan_canvas
            FROM faculties
            LIMIT ?, ?
            """;

    private static final String SELECT_COUNT_OF_FACULTIES = """
            SELECT COUNT(faculty_id) 
            FROM faculties
            """;

    private static final String SELECT_COUNT_OF_FACULTIES_WITH_NAME_AND_ID = """
            SELECT COUNT(faculty_id)
            FROM faculties
            WHERE faculty_name = ? AND NOT faculty_id = ?
            """;

    private static final String SELECT_COUNT_OF_FACULTIES_WITH_NAME = """
            SELECT COUNT(faculty_id)
            FROM faculties
            WHERE faculty_name = ?
            """;

    private static final String DELETE_FACULTY = """
              DELETE FROM faculties 
              WHERE faculty_id = ?
            """;

    private static final String ADD_FACULTY = """
            INSERT INTO faculties (faculty_name, 
            recruitment_plan_free, recruitment_plan_canvas)
            VALUE (?, ?, ?)
            """;

    private static final String UPDATE_FACULTY = """
            UPDATE faculties SET faculty_name = ?, 
            recruitment_plan_free = ?, recruitment_plan_canvas = ?
            WHERE faculty_id = ?
            """;

    /**
     * The private constructor
     */
    private FacultyDaoImpl() {
        this.jdbcTemplate = new JdbcTemplate<>(new FacultyResultSetHandler());
    }

    /**
     * Get instance
     *
     * @return instance
     */
    public static FacultyDao getInstance() {
        return instance;
    }


    /**
     * Find all faculties
     *
     * @return faculties the faculties
     * @throws DaoException the DaoException
     */
    @Override
    public List<Faculty> findAll() throws DaoException {
        List<Faculty> faculties = null;
        try {
            faculties = jdbcTemplate.executeSelectQuery(FIND_ALL_FACULTIES);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding all faculties", e);
            throw new DaoException("Error when finding all faculties", e);
        }

        return faculties;
    }

    /**
     * Find faculties to page
     *
     * @param facultiesSkip the faculties skip
     * @param facultiesGet the faculties get
     * @return faculties the faculties
     * @throws DaoException the DaoException
     */
    @Override
    public List<Faculty> findFacultiesPage(int facultiesSkip, int facultiesGet) throws DaoException {
        List<Faculty> faculties;
        try {
            faculties = jdbcTemplate.executeSelectQuery(FIND_FACULTIES_LIMIT, facultiesSkip, facultiesGet);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding faculties", e);
            throw new DaoException("Error when finding all faculties", e);
        }
        return faculties;
    }

    /**
     *
     * @return
     * @throws DaoException the DaoException
     */
    @Override
    public int getCountOfFaculties() throws DaoException {
        int count;
        try {
            count = jdbcTemplate.executeSelectCountQuery(SELECT_COUNT_OF_FACULTIES);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding faculties", e);
            throw new DaoException("Error when finding all faculties", e);
        }
        return count;
    }

    /**
     * The checking of existing of faculties with name
     *
     * @param name the faculty name
     * @return countOfFaculties the count of faculties with name
     * @throws DaoException the DaoException
     */
    @Override
    public int isFacultyNameExist(String name, long facultyId) throws DaoException {
        int countOfFaculties;
        try {
            countOfFaculties = jdbcTemplate.executeSelectCountQuery(SELECT_COUNT_OF_FACULTIES_WITH_NAME_AND_ID, name, facultyId);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding count of faculties with name", e);
            throw new DaoException("Error when finding count of faculties with name", e);
        }
        return countOfFaculties;
    }

    /**
     * The checking of existing of faculties with name
     *
     * @param name the faculty name
     * @return countOfFaculties the count of faculties with name
     * @throws DaoException the DaoException
     */
    @Override
    public int isFacultyNameExist(String name) throws DaoException {
        int countOfFaculties;
        try {
            countOfFaculties = jdbcTemplate.executeSelectCountQuery(SELECT_COUNT_OF_FACULTIES_WITH_NAME, name);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding count of faculties with name", e);
            throw new DaoException("Error when finding count of faculties with name", e);
        }
        return countOfFaculties;
    }

    /**
     * Find faculty by id
     *
     * @param id the id
     * @return facultyOptional the faculty optional
     * @throws DaoException the DaoException
     */
    @Override
    public Optional<Faculty> findEntityById(Long id) throws DaoException {
        Optional<Faculty> facultyOptional;
        try {
            facultyOptional = jdbcTemplate.executeSelectQueryForObject(FIND_FACULTY_BY_ID, id);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding faculty by id {} {}", id, e);
            throw new DaoException("Error when finding faculty by id " + id, e);
        }

        return facultyOptional;
    }

    /**
     * Delete faculty
     *
     * @param id the id
     * @return facultyId the faculty id
     * @throws DaoException the DaoException
     */
    @Override
    public boolean delete(Long id) throws DaoException {
        try {
            jdbcTemplate.executeUpdateDeleteFields(DELETE_FACULTY, id);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when deleting faculty", e);
            throw new DaoException("Error when deleting faculty", e);
        }

        return true;
    }

    /**
     * Add faculty
     *
     * @param faculty the faculty
     * @return facultyId the faculty id
     * @throws DaoException the DaoException
     */
    @Override
    public Long add(Faculty faculty) throws DaoException {
        long facultyId = 0;
        try {
            facultyId = jdbcTemplate.executeInsertQuery(ADD_FACULTY,
                    faculty.getFacultyName(),
                    faculty.getRecruitmentPlanFree(),
                    faculty.getRecruitmentPlanCanvas());
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when adding faculty", e);
            throw new DaoException("Error when adding faculty", e);
        }

        return facultyId;
    }

    /**
     * Update faculty
     *
     * @param faculty the faculty
     * @return true the true
     * @throws DaoException the DaoException
     */
    @Override
    public boolean update(Faculty faculty) throws DaoException {
        try {
            jdbcTemplate.executeInsertQuery(UPDATE_FACULTY,
                    faculty.getFacultyName(),
                    faculty.getRecruitmentPlanFree(),
                    faculty.getRecruitmentPlanCanvas(),
                    faculty.getFacultyId());
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when updating faculty", e);
            throw new DaoException("Error when updating faculty", e);
        }

        return true;
    }
}
