package com.example.epamcourse.model.dao.mapper.impl;

import com.example.epamcourse.model.dao.mapper.ResultSetHandler;
import com.example.epamcourse.model.entity.Account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import static com.example.epamcourse.model.dao.Table.*;

public class AccountResultSetHandler implements ResultSetHandler<Account> {

    @Override
    public Account resultToObject(ResultSet resultSet) throws SQLException {
        Account account = new Account.AccountBuilder()
                .setAccountId(resultSet.getLong(ACCOUNT_ID))
                .setLogin(resultSet.getString(LOGIN))
                .setPassword(resultSet.getString(PASSWORD))
                .setEmail(resultSet.getString(EMAIL))
                .setRole(Account.Role.valueOf(resultSet.getString(ROLE).toUpperCase()))
                .setStatus(Account.Status.valueOf(resultSet.getString(STATUS).toUpperCase()))
                .setIp(resultSet.getString(IP))
                .createAccount();

        return account;
    }
}
