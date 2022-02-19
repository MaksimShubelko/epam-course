package com.example.epamcourse.model.service.impl;

import com.example.epamcourse.model.dao.AdministratorDao;
import com.example.epamcourse.model.dao.impl.AdministratorDaoImpl;
import com.example.epamcourse.model.dao.impl.TransactionManager;
import com.example.epamcourse.model.entity.Administrator;
import com.example.epamcourse.model.exception.DaoException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.exception.TransactionException;
import com.example.epamcourse.model.service.AdministratorService;
import com.example.epamcourse.model.validator.SecureInformationValidator;
import com.example.epamcourse.model.validator.impl.SecureInformationValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class AdministratorServiceImpl implements AdministratorService {
    private static final Logger logger = LogManager.getLogger();
    private static final AdministratorServiceImpl instance = new AdministratorServiceImpl();
    private final TransactionManager transactionManager = TransactionManager.getInstance();
    private final AdministratorDao administratorDao = AdministratorDaoImpl.getInstance();

    private AdministratorServiceImpl() {
    }

    public static AdministratorServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean addPersonalInformation(String name, String surname, String lastname, Long accountId) throws ServiceException {
        boolean isAdministratorSecureInformationAdded = false;
        SecureInformationValidator validator = SecureInformationValidatorImpl.getInstance();
        try {
            if (isAdminSecureInformationValid(name, surname, lastname)) {
                Administrator administrator = new Administrator();
                administrator.setFirstname(name);
                administrator.setSurname(surname);
                administrator.setLastname(lastname);
                administrator.setAccountId(accountId);
                transactionManager.initTransaction();
                administratorDao.add(administrator);
                transactionManager.commit();
                isAdministratorSecureInformationAdded = true;
            }
        } catch(DaoException | TransactionException e){
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when adding secure information for administrator", e);
            throw new ServiceException("Error when adding secure information for administrator", e);
        } finally{
            transactionManager.endTransaction();
        }
        return isAdministratorSecureInformationAdded;
    }

    @Override
    public Optional<Administrator> getAdministratorByAccountId(Long accountId) throws ServiceException {
        Optional<Administrator> administratorOptional = Optional.empty();
        try {
            transactionManager.initTransaction();
            administratorOptional = administratorDao.findAdministratorByAccountId(accountId);
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when getting applicant id{}", e);
            throw new ServiceException("Error when getting applicant id", e);
        } finally {
            transactionManager.endTransaction();
        }
        return administratorOptional;
    }

    @Override
    public Optional<Administrator> getAdministratorById(Long administratorId) throws ServiceException {
        Optional<Administrator> administratorOptional = Optional.empty();
        try {
            transactionManager.initTransaction();
            administratorOptional = administratorDao.findEntityById(administratorId);
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when getting applicant id{}", e);
            throw new ServiceException("Error when getting applicant id", e);
        } finally {
            transactionManager.endTransaction();
        }
        return administratorOptional;
    }

    @Override
    public Long getAdministratorIdByAccountId(Long accountId) throws ServiceException {
        Optional<Administrator> administratorOptional;
        Long administratorId = 0L;
        try {
            transactionManager.initTransaction();
            administratorOptional = administratorDao.findAdministratorByAccountId(accountId);
            if (administratorOptional.isPresent()) {
                administratorId = administratorOptional.get().getAdministratorId();
            }
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when getting applicant id", e);
            throw new ServiceException("Error when getting applicant id", e);
        } finally {
            transactionManager.endTransaction();
        }
        return administratorId;
    }

    @Override
    public boolean editAdministratorPersonalInformation(String name, String surname, String lastname, Long administratorId) throws ServiceException {
        boolean isAdministratorUpdated = false;
        Administrator administrator;
        try {
            transactionManager.initTransaction();
            Optional<Administrator> administratorOptional = administratorDao.findEntityById(administratorId);
            if (administratorOptional.isPresent()) {
                if (isAdminSecureInformationValid(name, surname, lastname)) {
                    administrator = administratorOptional.get();
                    administrator.setFirstname(name);
                    administrator.setLastname(lastname);
                    administrator.setSurname(surname);
                    administratorDao.update(administrator);
                    isAdministratorUpdated = true;
                }
            }
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when updating administrator data", e);
            throw new ServiceException("Error when updating administrator data", e);
        } finally {
            transactionManager.endTransaction();
        }

        return isAdministratorUpdated;
    }

    public boolean isAdminSecureInformationValid(String name, String surname, String lastname) {
        SecureInformationValidator validator = SecureInformationValidatorImpl.getInstance();
        return validator.isNameValid(name)
                && validator.isLastnameValid(lastname)
                && validator.isSurnameValid(surname);
    }
}
