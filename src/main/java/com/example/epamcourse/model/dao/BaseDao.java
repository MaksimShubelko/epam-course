package com.example.epamcourse.model.dao;

import com.example.epamcourse.model.entity.BaseEntity;
import com.example.epamcourse.model.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * interface BaseDao <T extends BaseEntity>
 *
 * @author M.Shubelko
 */
public interface BaseDao <T extends BaseEntity> {

    /**
     * Find all entities
     *
     * @return List<T extends BaseEntity>
     * @throws DaoException the DaoException
     */
    List<T> findAll() throws DaoException;

    /**
     * Find entity by id
     *
     * @param id the id
     * @return List<T extends BaseEntity>
     * @throws DaoException the DaoException
     */
    Optional<T> findEntityById(Long id) throws DaoException;

    /**
     * Find delete entity by id
     *
     * @param id the id
     * @return List<T extends BaseEntity>
     * @throws DaoException the DaoException
     */
    boolean delete(Long id) throws DaoException;

    /**
     * Add entity
     *
     * @return List<T extends BaseEntity>
     * @throws DaoException the DaoException
     */
    Long add(T t) throws DaoException;

    /**
     * Update entity
     *
     * @param t extends BaseEntity
     * @return boolean
     * @throws DaoException the DaoException
     */
    boolean update(T t) throws DaoException;

}
