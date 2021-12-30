package com.example.epamcourse.model.dao.impl;

import com.example.epamcourse.model.dao.FacultyDao;
import com.example.epamcourse.model.entity.Faculty;
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

public class FacultyDaoImpl implements FacultyDao {
    private static final Logger logger = LogManager.getLogger();
    private static FacultyDao instance = new FacultyDaoImpl();

    private JdbcTemplates<Faculty> jdbcTemplate;

    private static final String FIND_ALL_FACULTIES = """
            SELECT faculty_id, mark_pass, faculty_name, 
            recruitment_plan_free, recruitment_plan_canvas
            FROM certificates
            """;

    private static final String FIND_FACULTY_BY_ID = """
            SELECT faculty_id, mark_pass, faculty_name, 
            recruitment_plan_free, recruitment_plan_canvas
            WHERE certificate_id = ?
            """;

    private static final String FIND_FACULTY_BY_NAME = """
            SELECT faculty_id, mark_pass, faculty_name, 
            recruitment_plan_free, recruitment_plan_canvas
            WHERE faculty_id = ?
            """;

    private static final String DELETE_FACULTY = """
              DELETE FROM faculties 
              WHERE faculty_id = ?
            """;

    private static final String ADD_FACULTY = """
            INSERT INTO faculties (mark_pass, faculty_name, 
            recruitment_plan_free, recruitment_plan_canvas)
            VALUE (?, ?, ?, ?)
            """;

    private static final String UPDATE_FACULTY = """
            UPDATE faculties SET mark_pass = ?, faculty_name = ?, 
            recruitment_plan_free = ?, recruitment_plan_canvas = ?
            WHERE faculty_id = ?
            """;

    @Override
    public List<Faculty> findAll() throws DaoException {
        List<Faculty> faculties = jdbcTemplate.executeSelectQuery(FIND_ALL_FACULTIES);

        return faculties;
    }

    @Override
    public Optional<Faculty> findFacultyByName(String name) throws DaoException {
        Optional<Faculty> faculty = jdbcTemplate.executeSelectQueryForObject(FIND_FACULTY_BY_NAME, name);

        return faculty;
    }

    @Override
    public Optional<Faculty> findEntityById(Long id) throws DaoException {
        Optional<Faculty> faculty = jdbcTemplate.executeSelectQueryForObject(FIND_FACULTY_BY_ID, id);

        return faculty;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_FACULTY)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error when deleting faculty with Id {}. {}", id, e.getMessage());
            throw new DaoException("Error when faculty with Id " + id, e);
        }

        return true;
    }

    @Override
    public Long add(Faculty faculty) throws DaoException {
        long facultyId = jdbcTemplate.executeInsertQuery(ADD_FACULTY,
                faculty.getMarkPass(),
                faculty.getFacultyName(),
                faculty.getRecruitmentPlanFree(),
                faculty.getRecruitmentPlanCanvas());

        return facultyId;
    }

    @Override
    public boolean update(Faculty faculty, String hashPassword) throws DaoException {
        jdbcTemplate.executeInsertQuery(UPDATE_FACULTY,
                faculty.getMarkPass(),
                faculty.getFacultyName(),
                faculty.getRecruitmentPlanFree(),
                faculty.getRecruitmentPlanCanvas());

        return true;
    }
}
