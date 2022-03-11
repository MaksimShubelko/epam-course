package com.example.epamcourse.model.service;

import com.example.epamcourse.model.entity.Faculty;
import com.example.epamcourse.model.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * interface FacultyService
 *
 * @author M.Shubelko
 */
public interface FacultyService {

    /**
     * The finding of all faculties
     *
     * @return the faculties
     * @throws ServiceException the service exception
     */
    List<Faculty> findAllFaculties() throws ServiceException;

    /**
     * The finding of faculty by id
     *
     * @param facultyId the faculty id
     * @return the faculty optional
     * @throws ServiceException the service exception
     */
    Optional<Faculty> findFacultyById(Long facultyId) throws ServiceException;

    /**
     * The edition of faculty
     *
     * @param facultyId the faculty id
     * @param facultyName the faculty name
     * @param recruitmentPlanFree the recruitment plan free
     * @param recruitmentPlanCanvas the recruitment plan canvas
     * @return true if faculty is edited
     * @throws ServiceException the service exception
     */
    boolean editFaculty(Long facultyId, String facultyName, int recruitmentPlanFree, int recruitmentPlanCanvas) throws ServiceException;

    /**
     * The deleting of faculty
     *
     * @param facultyId the faculty id
     * @throws ServiceException the service exception
     */
    void deleteFaculty(Long facultyId) throws ServiceException;

    /**
     * The adding of faculty
     *
     * @param facultyName the faculty name
     * @param recruitmentPlanFree the recruitment plan free
     * @param recruitmentPlanCanvas the recruitment plan canvas
     * @return true if faculty is added
     * @throws ServiceException the service exception
     */
    boolean addFaculty(String facultyName, int recruitmentPlanFree, int recruitmentPlanCanvas) throws ServiceException;

    /**
     * The finding of faculties for page
     *
     * @param currentPageNumber the current page number
     * @return the faculties
     * @throws ServiceException the service exception
     */
    List<Faculty> findFaculties(int currentPageNumber) throws ServiceException;

    /**
     * The getting of count of faculties
     *
     * @return the count of faculties
     * @throws ServiceException the service exception
     */
    int getCountOfFaculties() throws ServiceException;

    /**
     * The getting of count of faculties
     *
     * @param name the faculty name
     * @param facultyId the faculty id
     * @return true if faculty with name is exist
     * @throws ServiceException the service exception
     */
    boolean isFacultyNameExist(String name, long facultyId) throws ServiceException;

    /**
     * The getting of count of faculties
     *
     * @param name the faculty name
     * @return true if faculty with name is exist
     * @throws ServiceException the service exception
     */
    boolean isFacultyNameExist(String name) throws ServiceException;
}
