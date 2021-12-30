package com.example.epamcourse.model.dao.mapper;

import com.example.epamcourse.model.entity.BaseEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetHandler<T extends BaseEntity> {

    T resultToObject(ResultSet resultSet) throws SQLException;
}