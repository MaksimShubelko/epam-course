package com.example.epamcourse.model.service.impl;

import com.example.epamcourse.model.dao.FacultyDao;
import com.example.epamcourse.model.dao.impl.FacultyDaoImpl;
import com.example.epamcourse.model.dao.impl.TransactionManager;
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

import java.util.List;
import java.util.Optional;


/**
 * class FacultyServiceImpl
 *
 * @author M.Shubelko
 */
public class FacultyServiceImpl implements FacultyService {

    /**
     * The logger
     */
    private static final Logger logger = LogManager.getLogger();

    /**
     * The instance
     */
    private static final FacultyService instance = new FacultyServiceImpl();

    /**
     * The transaction manager
     */
    private final TransactionManager transactionManager = TransactionManager.getInstance();

    /**
     * The faculty dao
     */
    private final FacultyDao facultyDao = FacultyDaoImpl.getInstance();

    /**
     * The getting of instance
     *
     * @return instance the instance
     */
    public static FacultyService getInstance() {
        return instance;
    }

    /**
     * The private constructor
     */
    private FacultyServiceImpl() {
    }

    /**
     * The finding of all faculties
     *
     * @return faculties the faculties
     * @throws ServiceException the service exception
     */
    @Override
    public List<Faculty> findAllFaculties() throws ServiceException {
        List<Faculty> faculties;
        try {
            transactionManager.initTransaction();
            faculties = facultyDao.findAll();
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when finding all faculties.", e);
            throw new ServiceException("Error when finding faculties", e);
        } finally {
            transactionManager.endTransaction();
        }

        return faculties;
    }

    /**
     * The finding of faculty by id
     *
     * @param facultyId the faculty id
     * @return facultyOptional the faculty optional
     * @throws ServiceException the service exception
     */
    @Override
    public Optional<Faculty> findFacultyById(Long facultyId) throws ServiceException {
        Optional<Faculty> facultyOptional;
        try {
            transactionManager.initTransaction();
            facultyOptional = facultyDao.findEntityById(facultyId);
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when finding all faculties.", e);
            throw new ServiceException("Error when finding faculties", e);
        } finally {
            transactionManager.endTransaction();
        }

        return facultyOptional;
    }

    /**
     * The edition of faculty
     *
     * @param facultyId the faculty id
     * @param facultyName the faculty name
     * @param recruitmentPlanFree the recruitment plan free
     * @param recruitmentPlanCanvas the recruitment plan canvas
     * @return true if faculty is edited
     * @throws ServiceException the service exception
     */
    @Override
    public boolean editFaculty(Long facultyId, String facultyName, int recruitmentPlanFree, int recruitmentPlanCanvas) throws ServiceException {
        boolean isFacultyEdited = false;
        Optional<Faculty> facultyOptional;
        FacultyValidator facultyValidator = FacultyValidatorImpl.getInstance();
        Faculty faculty;
        try {
            transactionManager.initTransaction();
            facultyOptional = facultyDao.findEntityById(facultyId);
            if (facultyOptional.isPresent()) {
                if (facultyValidator.isFacultyNameValid(facultyName)
                        && facultyValidator.isRecruitmentPlanValid(recruitmentPlanFree)
                && facultyValidator.isRecruitmentPlanValid(recruitmentPlanCanvas)){
                    faculty = facultyOptional.get();
                    faculty.setFacultyName(facultyName);
                    faculty.setRecruitmentPlanCanvas(recruitmentPlanCanvas);
                    faculty.setRecruitmentPlanFree(recruitmentPlanFree);
                    facultyDao.update(faculty);
                    isFacultyEdited = true;
                }
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

    /**
     * The deleting of faculty
     *
     * @param facultyId the faculty id
     * @throws ServiceException the service exception
     */
    @Override
    public void deleteFaculty(Long facultyId) throws ServiceException {
        try {
            transactionManager.initTransaction();
            facultyDao.delete(facultyId);
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when finding all faculties.", e);
            throw new ServiceException("Error when finding faculties", e);
        } finally {
            transactionManager.endTransaction();
        }

    }

    /**
     * The adding of faculty
     *
     * @param facultyName the faculty name
     * @param recruitmentPlanFree the recruitment plan free
     * @param recruitmentPlanCanvas the recruitment plan canvas
     * @return true if faculty is added
     * @throws ServiceException the service exception
     */
    @Override
    public boolean addFaculty(String facultyName, int recruitmentPlanFree, int recruitmentPlanCanvas) throws ServiceException {
        boolean isFacultyAdded = false;
        FacultyValidator facultyValidatorImpl = FacultyValidatorImpl.getInstance();
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

    /**
     * The finding of faculties
     *
     * @param currentPageNumber the current page number
     * @return faculties the faculties
     * @throws ServiceException the service exception
     */
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

    /**
     * The getting of count of faculties
     *
     * @return countOfFaculties the count of faculties
     * @throws ServiceException the service exception
     */
    @Override
    public int getCountOfFaculties() throws ServiceException {
        int countOfFaculties;
        try {
            transactionManager.initTransaction();
            countOfFaculties = facultyDao.getCountOfFaculties();
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when getting of count of faculties faculties", e);
            throw new ServiceException("Error when getting of count of faculties faculties", e);
        } finally {
            transactionManager.endTransaction();
        }
        return countOfFaculties;
    }

    /**
     * The checking of existing of faculty with name
     *
     * @param name the faculty name
     * @param facultyId the faculty id
     * @return true if faculty with name is exist
     * @throws ServiceException the service exception
     */
    @Override
    public boolean isFacultyNameExist(String name, long facultyId) throws ServiceException {
        int countOfFaculties;
        try {
            transactionManager.initTransaction();
            countOfFaculties = facultyDao.isFacultyNameExist(name, facultyId);
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when getting of count of faculties", e);
            throw new ServiceException("Error when getting of count of faculties", e);
        } finally {
            transactionManager.endTransaction();
        }
        return countOfFaculties != 0;
    }

    /**
     * The checking of existing of faculty with name
     *
     * @param name the faculty name
     * @return true if faculty with name is exist
     * @throws ServiceException the service exception
     */
    @Override
    public boolean isFacultyNameExist(String name) throws ServiceException {
        int countOfFaculties;
        try {
            transactionManager.initTransaction();
            countOfFaculties = facultyDao.isFacultyNameExist(name);
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when getting of count of faculties", e);
            throw new ServiceException("Error when getting of count of faculties", e);
        } finally {
            transactionManager.endTransaction();
        }
        return countOfFaculties != 0;
    }
}
