package com.example.epamcourse.model.service;

import com.example.epamcourse.model.entity.Faculty;
import com.example.epamcourse.model.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface FacultyService {
    List<Faculty> findAllFaculties() throws ServiceException;

    Optional<Faculty> findFacultyById(Long facultyId) throws ServiceException;

    boolean editFaculty(Long facultyId, String facultyName, int recruitmentPlanFree, int recruitmentPlanCanvas) throws ServiceException;

    boolean deleteFaculty(Long facultyId) throws ServiceException;

    boolean addFaculty(String facultyName, int recruitmentPlanFree, int recruitmentPlanCanvas) throws ServiceException;

    List<Faculty> findFaculties(int currentPageNumber) throws ServiceException;
}
