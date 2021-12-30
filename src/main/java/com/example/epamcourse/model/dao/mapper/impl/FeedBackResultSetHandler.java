package com.example.epamcourse.model.dao.mapper.impl;

import com.example.epamcourse.model.dao.mapper.ResultSetHandler;
import com.example.epamcourse.model.entity.FeedBack;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.epamcourse.model.dao.Table.*;

public class FeedBackResultSetHandler implements ResultSetHandler<FeedBack> {
    @Override
    public FeedBack resultToObject(ResultSet resultSet) throws SQLException {
        FeedBack feedBack = new FeedBack(resultSet.getLong(FEEDBACK_ID),
                resultSet.getLong(FEEDBACK_ID_ADMINISTRATOR_ID),
                resultSet.getLong(FEEDBACK_ID_APPLICANT_ID),
                resultSet.getString(MESSAGE));

        return feedBack;
    }


}
