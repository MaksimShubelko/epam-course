package com.example.epamcourse.model.service;

import com.example.epamcourse.model.entity.Recruitment;
import com.example.epamcourse.model.exception.ServiceException;

import java.time.LocalDateTime;

public interface RecruitmentService {

    Recruitment findRecruitment() throws ServiceException;

    boolean updateRecruitment(boolean status, LocalDateTime finishRecruitment) throws ServiceException;
}