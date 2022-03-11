package com.example.epamcourse.model.dao;

import com.example.epamcourse.model.entity.Faculty;
import com.example.epamcourse.model.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * interface FacultyDao extends BaseDao<Faculty>
 *
 * @author M.Shubelko
 */
public interface FacultyDao extends BaseDao<Faculty> {

    /**
     * Find faculties to page
     *
     * @param facultiesGet the faculties get
     * @param facultiesSkip the faculties skip
     * @return List<Faculty> the faculties
     * @throws DaoException the DaoException
     */
    List<Faculty> findFacultiesPage(int facultiesSkip, int facultiesGet) throws DaoException;


    /**
     * The getting of count of faculties
     *
     * @return the count of faculties
     * @throws DaoException the DaoException
     */
    int getCountOfFaculties() throws DaoException;

    /**
     * The getting of count of faculties
     *
     * @param name the faculty name
     * @param facultyId the facultyId
     * @return true if faculty with name ia exist
     * @throws DaoException the DaoException
     */
    int isFacultyNameExist(String name, long facultyId) throws DaoException;

    /**
     * The getting of count of faculties
     *
     * @param name the faculty name
     * @return true if faculty with name ia exist
     * @throws DaoException the DaoException
     */
    int isFacultyNameExist(String name) throws DaoException;
}
