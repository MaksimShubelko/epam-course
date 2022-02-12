package com.example.epamcourse.model.service.impl;

import com.example.epamcourse.model.dao.ApplicantDao;
import com.example.epamcourse.model.dao.FacultyDao;
import com.example.epamcourse.model.dao.impl.*;
import com.example.epamcourse.model.entity.Faculty;
import com.example.epamcourse.model.exception.DaoException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.exception.TransactionException;
import com.example.epamcourse.model.service.FacultyService;
import com.example.epamcourse.model.validator.FacultyValidator;
import com.example.epamcourse.model.validator.impl.FacultyValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class FacultyServiceImpl implements FacultyService {
    private static final Logger logger = LogManager.getLogger();
    private static final FacultyService instance = new FacultyServiceImpl();
    private final TransactionManager transactionManager = TransactionManager.getInstance();
    private final ApplicantDao applicantDao = ApplicantDaoImpl.getInstance();
    private final FacultyDao facultyDao = FacultyDaoImpl.getInstance();

    @Override
    public List<Faculty> findAllFaculties() throws ServiceException {
        List<Faculty> faculties;
        try {
            transactionManager.initTransaction();
            faculties = facultyDao.findAll();
            transactionManager.commit();
        } catch (DaoException | TransactionException | SQLException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when finding all faculties.", e);
            throw new ServiceException("Error when finding faculties", e);
        } finally {
            transactionManager.endTransaction();
        }

        return faculties;
    }

    @Override
    public Optional<Faculty> findFacultyById(Long facultyId) throws ServiceException {
        Optional<Faculty> faculty;
        try {
            transactionManager.initTransaction();
            faculty = facultyDao.findEntityById(facultyId);
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when finding all faculties.", e);
            throw new ServiceException("Error when finding faculties", e);
        } finally {
            transactionManager.endTransaction();
        }

        return faculty;
    }

    @Override
    public boolean editFaculty(Long facultyId, String facultyName, int recruitmentPlanFree, int recruitmentPlanCanvas) throws ServiceException {
        boolean isFacultyEdited = false;
        Optional<Faculty> facultyOptional;
        Faculty faculty;
        try {
            transactionManager.initTransaction();
            facultyOptional = facultyDao.findEntityById(facultyId);
            if (facultyOptional.isPresent()) {
                faculty = facultyOptional.get();
                faculty.setFacultyName(facultyName);
                faculty.setRecruitmentPlanCanvas(recruitmentPlanCanvas);
                faculty.setRecruitmentPlanFree(recruitmentPlanFree);
                facultyDao.update(faculty);
                isFacultyEdited = true;
            }
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when edit faculty.", e);
            throw new ServiceException("Error when edit faculty", e);
        } finally {
            transactionManager.endTransaction();
        }

        return isFacultyEdited;
    }

    @Override
    public boolean deleteFaculty(Long facultyId) throws ServiceException {
        boolean isFacultyDeleted;
        Optional<Faculty> facultyOptional;
        Faculty faculty;
        try {
            transactionManager.initTransaction();
            isFacultyDeleted = facultyDao.delete(facultyId);
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when finding all faculties.", e);
            throw new ServiceException("Error when finding faculties", e);
        } finally {
            transactionManager.endTransaction();
        }

        return isFacultyDeleted;
    }

    @Override
    public boolean addFaculty(String facultyName, int recruitmentPlanFree, int recruitmentPlanCanvas) throws ServiceException {
        boolean isFacultyAdded = false;
        FacultyValidator facultyValidatorImpl = FacultyValidatorImpl.getInstance();
        Optional<Faculty> facultyOptional;
        Faculty faculty;
        try {
            transactionManager.initTransaction();
            if (facultyValidatorImpl.isFacultyNameValid(facultyName)
                    && facultyValidatorImpl.isRecruitmentPlanValid(recruitmentPlanFree)
            && facultyValidatorImpl.isRecruitmentPlanValid(recruitmentPlanCanvas)) {
                faculty = new Faculty();
                faculty.setFacultyName(facultyName);
                faculty.setRecruitmentPlanFree(recruitmentPlanFree);
                faculty.setRecruitmentPlanCanvas(recruitmentPlanCanvas);
                facultyDao.add(faculty);
                isFacultyAdded = true;
            }
            transactionManager.commit();
        } catch (TransactionException | DaoException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when edit faculty.", e);
            throw new ServiceException("Error when edit faculty", e);
        } finally {
            transactionManager.endTransaction();
        }

        return isFacultyAdded;
    }

    @Override
    public List<Faculty> findFaculties(int currentPageNumber) throws ServiceException {
        List<Faculty> faculties;
        try {
            int recordsPerPage = 5;
            transactionManager.initTransaction();
            int facultiesSkip = (currentPageNumber - 1) * recordsPerPage;
            faculties = facultyDao.findFacultiesPage(facultiesSkip, recordsPerPage);
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when finding faculties", e);
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
