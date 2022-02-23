package com.example.epamcourse.model.service.impl;

import com.example.epamcourse.model.dao.RecruitmentDao;
import com.example.epamcourse.model.dao.impl.RecruitmentDaoImpl;
import com.example.epamcourse.model.dao.impl.TransactionManager;
import com.example.epamcourse.model.entity.Recruitment;
import com.example.epamcourse.model.exception.DaoException;
import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.exception.TransactionException;
import com.example.epamcourse.model.service.RecruitmentService;
import com.example.epamcourse.model.validator.RecruitmentValidator;
import com.example.epamcourse.model.validator.impl.RecruitmentValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * class RecruitmentServiceImpl
 *
 * @author M.Shubelko
 */
public class RecruitmentServiceImpl implements RecruitmentService {

    /**
     * The logger
     */
    private static final Logger logger = LogManager.getLogger();

    /**
     * The instance
     */
    private static final RecruitmentService instance = new RecruitmentServiceImpl();

    /**
     * The transaction manager
     */
    private final TransactionManager transactionManager = TransactionManager.getInstance();

    /**
     * The recruitment dao
     */
    private final RecruitmentDao  recruitmentDao = RecruitmentDaoImpl.getInstance();

    /**
     * The getting of instance
     *
     * @return instance the instance
     */
    public static RecruitmentService getInstance() {
        return instance;
    }

    /**
     * The checking of recruitment's activity
     *
     * @return true if recruitment is active
     * @throws ServiceException the service exception
     */
    @Override
    public boolean isRecruitmentActive() throws ServiceException {
        Recruitment recruitment;
        boolean isRecruitmentActive = false;
        RecruitmentValidator recruitmentValidator = RecruitmentValidatorImpl.getInstance();
        try {
            transactionManager.initTransaction();
            Optional<Recruitment> recruitmentOptional;
            recruitmentOptional = recruitmentDao.findRecruitment();
            if (recruitmentOptional.isPresent()) {
                recruitment = recruitmentOptional.get();
                isRecruitmentActive = recruitmentValidator
                        .isFinishRecruitmentValid(recruitment.getFinishRecruitment())
                        && recruitment.getRecruitmentStatus();
            }
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when checking recruitment.", e);
            throw new ServiceException("Error when checking recruitment", e);
        } finally {
            transactionManager.endTransaction();
        }

        return isRecruitmentActive;
    }

    /**
     * The finding of recruitment
     *
     * @return recruitment the recruitment
     * @throws ServiceException the service exception
     */
    @Override
    public Recruitment findRecruitment() throws ServiceException {
        Recruitment recruitment = null;
        try {
            transactionManager.initTransaction();
            Optional<Recruitment> recruitmentOptional;
            recruitmentOptional = recruitmentDao.findRecruitment();
            if (recruitmentOptional.isPresent()) {
                recruitment = recruitmentOptional.get();
            }
            transactionManager.commit();
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when finding recruitment.", e);
            throw new ServiceException("Error when finding recruitment", e);
        } finally {
            transactionManager.endTransaction();
        }

        return recruitment;
    }

    /**
     * The updating of recruitment
     *
     * @param status the status
     * @param finishRecruitment the finish recruitment
     * @throws ServiceException the service exception
     */
    @Override
    public void updateRecruitment(boolean status, LocalDateTime finishRecruitment) throws ServiceException {
        boolean isRecruitmentUpdated = false;
        try {
            transactionManager.initTransaction();
            Optional<Recruitment> recruitmentOptional = recruitmentDao.findRecruitment();
            if (recruitmentOptional.isPresent()) {
                Recruitment recruitment = recruitmentOptional.get();
                recruitment.setRecruitmentStatus(status);
                recruitment.setFinishRecruitment(finishRecruitment);
                recruitmentDao.update(recruitment);
                transactionManager.commit();
                isRecruitmentUpdated = true;
            }
        } catch (DaoException | TransactionException e) {
            transactionManager.rollback();
            logger.log(Level.ERROR, "Error when updating recruitment", e);
            throw new ServiceException("Error when updating recruitment", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    /**
     * The checking of recruitment's validating
     *
     * @param finishRecruitment the finish recruitment
     * @return true if finish recruitment is valid
     */
    @Override
    public boolean isFinishRecruitmentValid(LocalDateTime finishRecruitment) {
        RecruitmentValidator recruitmentValidator = RecruitmentValidatorImpl.getInstance();

        return recruitmentValidator.isFinishRecruitmentValid(finishRecruitment);
    }
}
