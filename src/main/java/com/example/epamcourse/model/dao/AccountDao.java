package com.example.epamcourse.model.dao;

import com.example.epamcourse.model.entity.Account;
import com.example.epamcourse.model.exception.DaoException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AccountDao extends BaseDao<Account> {
    Optional<Account> findAccountByLogin(String login) throws DaoException;

    Optional<Account> findAccountByLoginAndPassword(String login, String password) throws DaoException;

    List<Map<String, Object>> getAccountRoleById(Long accountId) throws DaoException;

    boolean update(Account account, String password) throws DaoException;

    boolean updateRole(Account account, int role) throws DaoException;

/*
    Long getAccountIdByLogin(String login) throws DaoException;
*/

    Long add(Account account, String password) throws DaoException;
}
