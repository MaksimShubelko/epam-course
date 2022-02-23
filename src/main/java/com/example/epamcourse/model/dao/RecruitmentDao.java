package com.example.epamcourse.model.dao;

import com.example.epamcourse.model.entity.Recruitment;
import com.example.epamcourse.model.exception.DaoException;

import java.util.Optional;

/**
 * interface RecruitmentDao extends BaseDao<Recruitment>
 *
 * @author M.Shubelko
 */
public interface RecruitmentDao extends BaseDao<Recruitment> {

    /**
     * Find recruitment
     *
     * @return Optional<Recruitment> the recruitment
     * @throws DaoException the DaoException
     */
    Optional<Recruitment> findRecruitment() throws DaoException;
}
