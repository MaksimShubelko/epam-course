package com.example.epamcourse.model.service;

import com.example.epamcourse.controller.command.SessionRequestContent;
import com.example.epamcourse.model.exception.ServiceException;

public interface AdministratorService {
    boolean addPersonalInformation(SessionRequestContent content) throws ServiceException;
}
