package com.example.epamcourse.model.dao.mapper.impl;

import com.example.epamcourse.model.dao.mapper.ResultSetHandler;
import com.example.epamcourse.model.entity.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.epamcourse.model.dao.TableColumn.*;

/**
 * class SubjectResultSetHandler
 *
 * @author M.Shubelko
 */
public class SubjectResultSetHandler implements ResultSetHandler<Subject> {

    /**
     * Result to object
     *
     * @param resultSet the result set
     * @return subject the subject
     * @throws SQLException the SQLException
     */
    @Override
    public Subject resultToObject(ResultSet resultSet) throws SQLException {
        Subject subject = new Subject(resultSet.getLong(SUBJECT_ID),
                resultSet.getLong(SUBJECT_APPLICANT_ID),
                Subject.SubjectType.valueOf(resultSet.getString(SUBJECT_TYPE).toUpperCase()),
                resultSet.getInt(MARK));

        return subject;
    }
}
