package com.example.epamcourse.model.dao.mapper.impl;

import com.example.epamcourse.model.dao.mapper.ResultSetHandler;
import com.example.epamcourse.model.entity.Bill;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.epamcourse.model.dao.TableColumn.*;

public class BillResultSetHandler implements ResultSetHandler<Bill> {
    @Override
    public Bill resultToObject(ResultSet resultSet) throws SQLException {
        Bill bill = new Bill(resultSet.getLong(BILL_ID),
                resultSet.getLong(BILL_FACULTY_ID),
                resultSet.getLong(BILL_APPLICANT_ID),
                resultSet.getBoolean(BILL_ARCHIVE));

        return bill;
    }
}
