package com.example.epamcourse.model.service.impl;

import com.example.epamcourse.model.dao.RecruitmentDao;
import com.example.epamcourse.model.dao.impl.RecruitmentDaoImpl;
import com.example.epamcourse.model.dao.impl.TransactionManager;
import com.example.epamcourse.model.entity.Applicant;
import com.example.epamcourse.model.entity.Bill;
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

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

public class RecruitmentServiceImpl implements RecruitmentService {
    private static final Logger logger = LogManager.getLogger();
    private static final RecruitmentService instance = new RecruitmentServiceImpl();
    private final TransactionManager transactionManager = TransactionManager.getInstance();
    private final RecruitmentDao  recruitmentDao = RecruitmentDaoImpl.getInstance();

    public static RecruitmentService getInstance() {
        return instance;
    }

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

    @Override
    public boolean updateRecruitment(boolean status, LocalDateTime finishRecruitment) throws ServiceException {
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
        return isRecruitmentUpdated;
    }

    @Override
    public boolean isFinishRecruitmentValid(LocalDateTime finishRecruitment) {
        RecruitmentValidator recruitmentValidator = RecruitmentValidatorImpl.getInstance();

        return recruitmentValidator.isFinishRecruitmentValid(finishRecruitment);
    }
}
