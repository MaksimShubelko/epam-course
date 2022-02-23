package com.example.epamcourse.model.dao;

import com.example.epamcourse.model.entity.Administrator;
import com.example.epamcourse.model.entity.Applicant;
import com.example.epamcourse.model.exception.DaoException;

import java.sql.SQLException;
import java.util.Optional;

/**
 * interface AdministratorDao extends BaseDao<Administrator>
 *
 * @author M.Shubelko
 */
public interface AdministratorDao extends BaseDao<Administrator> {

    /**
     * Find an administrator by accountId
     *
     * @param id the id
     * @return Optional<Administrator>
     * @throws DaoException the DaoException
     */
    Optional<Administrator> findAdministratorByAccountId(Long id) throws DaoException;

}
