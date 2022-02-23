package com.example.epamcourse.model.service;

import com.example.epamcourse.model.entity.Recruitment;
import com.example.epamcourse.model.exception.ServiceException;

import java.time.LocalDateTime;

/**
 * interface MailingService
 *
 * @author M.Shubelko
 */
public interface RecruitmentService {

    /**
     * The checking of recruitment's activity
     *
     * @return trie if recruitment is active
     * @throws ServiceException the service exception
     */
    boolean isRecruitmentActive() throws ServiceException;

    /**
     * The finding of recruitment
     *
     * @return the recruitment
     * @throws ServiceException the service exception
     */
    Recruitment findRecruitment() throws ServiceException;

    /**
     * The updating of recruitment
     *
     * @param status the status
     * @param finishRecruitment the finish recruitment
     * @throws ServiceException the service exception
     */
    void updateRecruitment(boolean status, LocalDateTime finishRecruitment) throws ServiceException;

    /**
     * The checking of recruitment's finish date validity
     *
     * @param finishRecruitment the finish recruitment
     * @return true if recruitment finish date is valid
     */
    boolean isFinishRecruitmentValid(LocalDateTime finishRecruitment);
}
