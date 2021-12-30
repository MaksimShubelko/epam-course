package com.example.epamcourse.model.dao.mapper.impl;

import com.example.epamcourse.model.dao.mapper.ResultSetHandler;
import com.example.epamcourse.model.entity.Certificate;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.epamcourse.model.dao.Table.CERTIFICATE_ID;
import static com.example.epamcourse.model.dao.Table.CERTIFICATE_TOTAL_MARK;

public class CertificateResultSetHandler implements ResultSetHandler<Certificate> {
    @Override
    public Certificate resultToObject(ResultSet resultSet) throws SQLException {
        Certificate certificate = new Certificate(resultSet.getLong(CERTIFICATE_ID),
                resultSet.getInt(CERTIFICATE_TOTAL_MARK));

        return certificate;
    }
}
