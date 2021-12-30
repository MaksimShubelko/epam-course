package com.example.epamcourse.model.dao.mapper.impl;

import com.example.epamcourse.model.dao.mapper.ResultSetHandler;
import com.example.epamcourse.model.entity.AccountToken;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static com.example.epamcourse.model.dao.Table.*;

public class TokenResultSetHandler implements ResultSetHandler<AccountToken> {
    @Override
    public AccountToken resultToObject(ResultSet resultSet) throws SQLException {
        long accountId = resultSet.getLong(TOKEN_ACCOUNT_ID);
        String token = resultSet.getString(TOKEN);
        Timestamp creationDate = resultSet.getTimestamp(TOKEN_ID);
        return new AccountToken(accountId, token, creationDate.toLocalDateTime());
    }
}