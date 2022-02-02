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
            if (validator.isNameValid(name) && validator.isLastnameValid(lastname) && validator.isSurnameValid(surname)) {
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
}
