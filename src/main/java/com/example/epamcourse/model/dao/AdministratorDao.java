package com.example.epamcourse.model.dao;

import com.example.epamcourse.model.entity.Administrator;
import com.example.epamcourse.model.entity.Applicant;
import com.example.epamcourse.model.exception.DaoException;

import java.util.Optional;

public interface AdministratorDao extends BaseDao<Administrator> {
    Optional<Administrator> findAdministratorByAccountId(Long id) throws DaoException;

}
