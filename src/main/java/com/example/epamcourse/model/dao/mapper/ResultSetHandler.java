package com.example.epamcourse.model.dao.mapper;

import com.example.epamcourse.model.entity.BaseEntity;
import com.example.epamcourse.model.exception.CommandException;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * interface ResultSetHandler<T extends BaseEntity>
 *
 * @author M.Shubelko
 */
public interface ResultSetHandler<T extends BaseEntity> {

    /**
     * Result to object
     *
     * @param resultSet the result set
     * @return T extends BaseEntity
     * @throws SQLException the SQLException
     */
    T resultToObject(ResultSet resultSet) throws SQLException;
}