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

/**
 * class AdministratorServiceImpl
 *
 * @author M.Shubelko
 */
public class AdministratorServiceImpl implements AdministratorService {

    /**
     * The logger
     */
    private static final Logger logger = LogManager.getLogger();

    /**
     * The instance
     */
    private static final AdministratorServiceImpl instance = new AdministratorServiceImpl();

    /**
     * The transaction manager
     */
    private final TransactionManager transactionManager = TransactionManager.getInstance();

    /**
     * The administrator dao
     */
    private final AdministratorDao administratorDao = AdministratorDaoImpl.getInstance();

    /**
     * The private constructor
     */
    private AdministratorServiceImpl() {
    }

    /**
     * The getting of instance
     *
     * @return instance the instance
     */
    public static AdministratorServiceImpl getInstance() {
        return instance;
    }

    /**
     * The adding of administrator's personal information
     *
     * @param name the name
     * @param surname the surname
     * @param lastname the lastname
     * @param accountId the account id
     * @return true if administrator's personal information is added
     * @throws ServiceException the service exception
     */
    @Override
    public boolean addPersonalInformation(String name, String surname, String lastname, Long accountId) throws ServiceException {
        boolean isAdministratorSecureInformationAdded = false;
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
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when adding secure information for administrator", e);
            throw new ServiceException("Error when adding secure information for administrator", e);
        } finally {
            transactionManager.endTransaction();
        }
        return isAdministratorSecureInformationAdded;
    }

    /**
     * The finding of administrator by account id
     *
     * @param accountId the account id
     * @return administratorOptional the administrator optional
     * @throws ServiceException the service exception
     */
    @Override
    public Optional<Administrator> findAdministratorByAccountId(Long accountId) throws ServiceException {
        Optional<Administrator> administratorOptional;
        try {
            transactionManager.initTransaction();
            administratorOptional = administratorDao.findAdministratorByAccountId(accountId);
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when getting applicant id", e);
            throw new ServiceException("Error when getting applicant id", e);
        } finally {
            transactionManager.endTransaction();
        }
        return administratorOptional;
    }

    /**
     * The getting of administrator id by account id
     *
     * @param accountId the account id
     * @return administratorOptional the administrator optional
     * @throws ServiceException the service exception
     */
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

    /**
     * The edition of administrator's personal information
     *
     * @param name the name
     * @param surname the surname
     * @param lastname the lastname
     * @return true if administrator's personal information is edited
     * @throws ServiceException the service exception
     */
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

    /**
     * The validation of administrator's personal information
     *
     * @param name the name
     * @param surname the surname
     * @param lastname the lastname
     * @return true if administrator's personal information is validated
     */
    public boolean isAdminSecureInformationValid(String name, String surname, String lastname) {
        SecureInformationValidator validator = SecureInformationValidatorImpl.getInstance();
        return validator.isNameValid(name)
                && validator.isLastnameValid(lastname)
                && validator.isSurnameValid(surname);
    }
}
