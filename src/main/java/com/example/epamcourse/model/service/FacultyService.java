package com.example.epamcourse.model.service;

import com.example.epamcourse.controller.command.SessionRequestContent;
import com.example.epamcourse.model.entity.Faculty;
import com.example.epamcourse.model.exception.ServiceException;

import java.util.List;

public interface FacultyService {
    List<Faculty> findAllFaculties(SessionRequestContent content) throws ServiceException;
}
