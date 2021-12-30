package com.example.epamcourse.model.entity;

import java.time.LocalDateTime;

public class AccountToken extends BaseEntity {
    private Long tokenId;
    private Long accountId;
    private String token;
    private LocalDateTime creationDate;

    public AccountToken(Long accountId, String token, LocalDateTime creationDate) {
        this.accountId = accountId;
        this.token = token;
        this.creationDate = creationDate;
    }

    public long getTokenId() {
        return tokenId;
    }

    public void setTokenId(long tokenId) {
        this.tokenId = tokenId;
    }

    public long getUserId() {
        return accountId;
    }

    public void setUserId(long accountId) {
        this.accountId = accountId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
