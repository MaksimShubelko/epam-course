package com.example.epamcourse.model.service;

import com.example.epamcourse.model.entity.Administrator;
import com.example.epamcourse.model.exception.ServiceException;

import java.util.Optional;

public interface AdministratorService {
    boolean addPersonalInformation(String name, String surname, String lastname, Long accountId) throws ServiceException;

    Optional<Administrator> getAdministratorByAccountId(Long accountId) throws ServiceException;

    Optional<Administrator> getAdministratorById(Long administratorId) throws ServiceException;

    Long getAdministratorIdByAccountId(Long accountId) throws ServiceException;

    boolean editAdministratorPersonalInformation(String name, String surname, String lastname, Long administratorId) throws ServiceException;
}
