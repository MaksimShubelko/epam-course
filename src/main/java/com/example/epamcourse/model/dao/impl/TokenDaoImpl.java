package com.example.epamcourse.model.dao.impl;

import com.example.epamcourse.model.dao.mapper.impl.TokenResultSetHandler;
import com.example.epamcourse.model.entity.AccountToken;
import com.example.epamcourse.model.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

public class TokenDaoImpl {
    private static final Logger logger = LogManager.getLogger();
    private static TokenDaoImpl instance = new TokenDaoImpl();

    private static final String ADD_ACCOUNT_TOKEN_SQL = """
            INSERT INTO tokens (account_id, token, creation_date)
            VALUES (?, ?, ?)
            """;
    private static final String FIND_ACCOUNT_TOKEN_SQL = """
            SELECT account_id, token, create_date
            FROM tokens
            WHERE token_id = ?
            """;

    private JdbcTemplates<AccountToken> jdbcTemplate;

    private TokenDaoImpl() {
        jdbcTemplate = new JdbcTemplates<AccountToken>(new TokenResultSetHandler());
    }

    public static TokenDaoImpl getInstance() {
        return instance;
    }

    public Optional<AccountToken> findTokenById(long tokenId) throws DaoException {
        Optional<AccountToken> accountToken = jdbcTemplate.executeSelectQueryForObject(FIND_ACCOUNT_TOKEN_SQL, tokenId);

        return accountToken;
    }

    public long addRegistrationToken(long accountId, String token) throws DaoException {
        long tokenId = jdbcTemplate.executeInsertQuery(ADD_ACCOUNT_TOKEN_SQL, accountId,
                token, Timestamp.valueOf(LocalDateTime.now()));

        return tokenId;
    }
}
