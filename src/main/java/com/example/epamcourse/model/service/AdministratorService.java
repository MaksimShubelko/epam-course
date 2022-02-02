package com.example.epamcourse.model.service;

import com.example.epamcourse.model.exception.ServiceException;

public interface AdministratorService {
    boolean addPersonalInformation(String name, String surname, String lastname, Long accountId) throws ServiceException;
}
