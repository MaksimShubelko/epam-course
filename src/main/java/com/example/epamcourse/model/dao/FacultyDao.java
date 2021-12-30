package com.example.epamcourse.model.dao;

import com.example.epamcourse.model.entity.Faculty;
import com.example.epamcourse.model.exception.DaoException;

import java.util.Optional;

public interface FacultyDao extends BaseDao<Faculty> {
    Optional<Faculty> findFacultyByName(String name) throws DaoException;

}
