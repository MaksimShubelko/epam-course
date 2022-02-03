package com.example.epamcourse.model.dao;

import com.example.epamcourse.model.entity.Recruitment;
import com.example.epamcourse.model.exception.DaoException;

import java.util.Optional;

public interface RecruitmentDao extends BaseDao<Recruitment> {

    Optional<Recruitment> findRecruitment() throws DaoException;
}
