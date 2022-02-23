package com.example.epamcourse.model.dao.mapper.impl;

import com.example.epamcourse.model.dao.mapper.ResultSetHandler;
import com.example.epamcourse.model.entity.Certificate;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.epamcourse.model.dao.TableColumn.CERTIFICATE_ID;
import static com.example.epamcourse.model.dao.TableColumn.CERTIFICATE_TOTAL_MARK;

/**
 * class CertificateResultSetHandler
 *
 * @author M.Shubelko
 */
public class CertificateResultSetHandler implements ResultSetHandler<Certificate> {

    /**
     * Result to object
     *
     * @param resultSet the result set
     * @return certificate the certificate
     * @throws SQLException the SQLException
     */
    @Override
    public Certificate resultToObject(ResultSet resultSet) throws SQLException {
        Certificate certificate = new Certificate(resultSet.getLong(CERTIFICATE_ID),
                resultSet.getDouble(CERTIFICATE_TOTAL_MARK));

        return certificate;
    }
}
