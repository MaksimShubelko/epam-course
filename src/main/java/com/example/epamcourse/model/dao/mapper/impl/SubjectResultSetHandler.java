package com.example.epamcourse.model.dao.mapper.impl;

import com.example.epamcourse.model.dao.mapper.ResultSetHandler;
import com.example.epamcourse.model.entity.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.epamcourse.model.dao.TableColumn.*;

public class SubjectResultSetHandler implements ResultSetHandler<Subject> {

    @Override
    public Subject resultToObject(ResultSet resultSet) throws SQLException {
        Subject subject = new Subject(resultSet.getLong(SUBJECT_ID),
                resultSet.getLong(SUBJECT_APPLICANT_ID),
                Subject.Type.valueOf(resultSet.getString(SUBJECT_TYPE).toUpperCase()),
                resultSet.getInt(MARK));

        return subject;
    }
}
