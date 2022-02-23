package com.example.epamcourse.model.dao.mapper.impl;

import com.example.epamcourse.model.dao.mapper.ResultSetHandler;
import com.example.epamcourse.model.entity.Applicant;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.epamcourse.model.dao.TableColumn.*;

/**
 * class ApplicantResultSetHandler
 *
 * @author M.Shubelko
 */
public class ApplicantResultSetHandler implements ResultSetHandler<Applicant> {

    /**
     * Result to object
     *
     * @param resultSet the result set
     * @return applicant the applicant
     * @throws SQLException the SQLException
     */
    @Override
    public Applicant resultToObject(ResultSet resultSet) throws SQLException {
        Applicant applicant = new Applicant.ApplicantBuilder()
                .setCertificateId(resultSet.getLong(CERTIFICATE_ID))
                .setApplicantId(resultSet.getLong(APPLICANT_ID))
                .setAccountId(resultSet.getLong(APPLICANT_ACCOUNT_ID))
                .setBeneficiary(resultSet.getBoolean(IS_BENEFICIARY))
                .setFirstname(resultSet.getString(APPLICANT_FIRST_NAME))
                .setLastname(resultSet.getString(APPLICANT_LAST_NAME))
                .setSurname(resultSet.getString(APPLICANT_SURNAME))
                .createApplicant();

        return applicant;
    }
}
