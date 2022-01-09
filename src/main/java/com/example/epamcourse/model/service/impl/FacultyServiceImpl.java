package com.example.epamcourse.model.service.impl;

import com.example.epamcourse.controller.command.SessionRequestContent;
import com.example.epamcourse.model.dao.ApplicantDao;
import com.example.epamcourse.model.dao.FacultyDao;
import com.example.epamcourse.model.dao.impl.*;
import com.example.epamcourse.model.entity.Faculty;
import com.example.epamcourse.model.exception.DaoException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.exception.TransactionException;
import com.example.epamcourse.model.service.FacultyService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class FacultyServiceImpl implements FacultyService {
    private static final Logger logger = LogManager.getLogger();
    private static final FacultyService instance = new FacultyServiceImpl();
    private final TransactionManager transactionManager = TransactionManager.getInstance();
    private final ApplicantDao applicantDao = ApplicantDaoImpl.getInstance();
    private final FacultyDao facultyDao = FacultyDaoImpl.getInstance();

    @Override
    public List<Faculty> findAllFaculties(SessionRequestContent content) throws ServiceException {
        List<Faculty> faculties;
        try {
            transactionManager.initTransaction();
            faculties = facultyDao.findAll();
            transactionManager.commit();
        } catch (DaoException | TransactionException | SQLException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when finding all faculties. {}.", e.getMessage());
            throw new ServiceException("Error when finding faculties", e);
        } finally {
            transactionManager.endTransaction();
        }

        return faculties;
    }

    public static FacultyService getInstance() {
        return instance;
    }
}
