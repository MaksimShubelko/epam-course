package com.example.epamcourse.model.service;

import com.example.epamcourse.model.entity.Subject;
import com.example.epamcourse.model.exception.ServiceException;

import java.util.List;

public interface SubjectService {
    boolean addSubject(Long applicantId) throws ServiceException;

    boolean updateSubject(Long applicantId, Integer... subjectMarks) throws ServiceException;

    List<Subject> findSubject(Long applicantId) throws ServiceException;

}
