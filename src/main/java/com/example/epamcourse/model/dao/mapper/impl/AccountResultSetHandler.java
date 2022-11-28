package com.example.epamcourse.model.dao.mapper.impl;

import com.example.epamcourse.model.dao.mapper.ResultSetHandler;
import com.example.epamcourse.model.entity.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.epamcourse.model.dao.TableColumn.*;

/**
 * class AccountResultSetHandler
 *
 * @author M.Shubelko
 */
public class AccountResultSetHandler implements ResultSetHandler<Account> {

    /**
     * Result to object
     *
     * @param resultSet the result set
     * @return account the account
     * @throws SQLException the SQLException
     */
    @Override
    public Account resultToObject(ResultSet resultSet) throws SQLException {
        Account account = new Account.AccountBuilder()
                .setAccountId(resultSet.getLong(ACCOUNT_ID))
                .setLogin(resultSet.getString(LOGIN))
                .setEmail(resultSet.getString(EMAIL))
                .setRole(Account.Role.valueOf(resultSet.getString(ROLE).toUpperCase()))
                .setStatus(Account.Status.valueOf(resultSet.getString(STATUS).toUpperCase()))
                .setIp(resultSet.getString(IP))
                .setImagePath(resultSet.getString(IMAGE_PATH))
                .createAccount();

        return account;
    }
}
