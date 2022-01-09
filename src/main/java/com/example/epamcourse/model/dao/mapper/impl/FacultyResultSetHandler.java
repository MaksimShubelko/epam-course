package com.example.epamcourse.model.dao.mapper.impl;

import com.example.epamcourse.model.dao.mapper.ResultSetHandler;
import com.example.epamcourse.model.entity.Faculty;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.epamcourse.model.dao.Table.*;

public class FacultyResultSetHandler implements ResultSetHandler<Faculty> {
    @Override
    public Faculty resultToObject(ResultSet resultSet) throws SQLException {
        Faculty faculty = new Faculty.FacultyBuilder()
                .setFacultyId(resultSet.getLong(FACULTY_ID))
                .setMarkPass(resultSet.getInt(MARK_PASS))
                .setFacultyName(resultSet.getString(FACULTY_NAME))
                .setRecruitmentPlanFree(resultSet.getInt(RECRUITMENT_PLAN_FREE))
                .setRecruitmentPlanCanvas(resultSet.getInt(RECRUITMENT_PLAN_CANVAS))
                .createFaculty();

        return faculty;
    }
}
