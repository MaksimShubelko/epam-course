package com.example.epamcourse.model.service;

import com.example.epamcourse.model.entity.Administrator;
import com.example.epamcourse.model.exception.ServiceException;

import java.util.Optional;

/**
 * interface AdministratorService
 *
 * @author M.Shubelko
 */
public interface AdministratorService {

    /**
     * The adding of personal information
     *
     * @param name the name
     * @param surname the surname
     * @param lastname the lastname
     * @param accountId the account id
     * @return true if personal information is added
     * @throws ServiceException the service exception
     */
    boolean addPersonalInformation(String name, String surname, String lastname, Long accountId) throws ServiceException;

    /**
     * The finding of administrator by account id
     *
     * @param accountId the account id
     * @return the administratorOptional
     * @throws ServiceException the service exception
     */
    Optional<Administrator> findAdministratorByAccountId(Long accountId) throws ServiceException;

    /**
     * The getting of administrator id by account id
     *
     * @param accountId the account id
     * @return the administrator id
     * @throws ServiceException the service exception
     */
    Long getAdministratorIdByAccountId(Long accountId) throws ServiceException;

    /**
     * The edition of administrator personal information
     *
     * @param name the name
     * @param surname the surname
     * @param lastname the lastname
     * @param administratorId the administrator id
     * @return true if administrator personal information is edited
     * @throws ServiceException the service exception
     */
    boolean editAdministratorPersonalInformation(String name, String surname, String lastname, Long administratorId) throws ServiceException;
}
