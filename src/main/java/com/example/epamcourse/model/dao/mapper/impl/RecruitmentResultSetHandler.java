package com.example.epamcourse.model.dao.mapper.impl;

import com.example.epamcourse.model.dao.mapper.ResultSetHandler;
import com.example.epamcourse.model.entity.BaseEntity;
import com.example.epamcourse.model.entity.Recruitment;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.epamcourse.model.dao.TableColumn.*;

public class RecruitmentResultSetHandler implements ResultSetHandler<Recruitment> {
    @Override
    public Recruitment resultToObject(ResultSet resultSet) throws SQLException {
        Recruitment recruitment = new Recruitment(
                resultSet.getLong(RECRUITMENT_ID),
                resultSet.getBoolean(RECRUITMENT_STATUS),
                resultSet.getDate(RECRUITMENT_FINISHING_DATE)
                        .toLocalDate()
                        .atStartOfDay()
        );

        return recruitment;
    }
}
