package com.example.epamcourse.model.dao.mapper.impl;

import com.example.epamcourse.model.dao.mapper.ResultSetHandler;
import com.example.epamcourse.model.entity.Administrator;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.epamcourse.model.dao.TableColumn.*;

/**
 * class AdministratorResultSetHandler
 *
 * @author M.Shubelko
 */
public class AdministratorResultSetHandler implements ResultSetHandler<Administrator> {

    /**
     * Result to object
     *
     * @param resultSet the result set
     * @return administrator the administrator
     * @throws SQLException the SQLException
     */
    @Override
    public Administrator resultToObject(ResultSet resultSet) throws SQLException {
        Administrator administrator = new Administrator.AdministratorBuilder()
                .setAdministratorId(resultSet.getLong(ADMINISTRATOR_ID))
                .setFirstname(resultSet.getString(ADM_FIRST_NAME))
                .setLastname(resultSet.getString(ADM_LAST_NAME))
                .setSurname(resultSet.getString(ADM_SURNAME))
                .setAccountId(resultSet.getLong(ADMINISTRATOR_ACCOUNT_ID))
                .createAdministrator();

        return administrator;
    }
}
