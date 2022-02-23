package com.example.epamcourse.model.validator.impl;

import com.example.epamcourse.model.validator.RecruitmentValidator;

import java.time.LocalDateTime;

/**
 * class RecruitmentValidatorImpl
 *
 * @author M.Shubelko
 */
public class RecruitmentValidatorImpl implements RecruitmentValidator {

    /**
     * The instance
     */
    private static RecruitmentValidator instance = new RecruitmentValidatorImpl();

    /**
     * The private constructor
     */
    private RecruitmentValidatorImpl() {
    }

    /**
     * The getting of instance
     *
     * @return instance the instance
     */
    public static RecruitmentValidator getInstance() {
        return instance;
    }

    /**
     * The validation of finishing of recruitment date
     *
     * @param finishRecruitment the finish recruitment
     * @return true if finish recruitment date is valid
     */
    @Override
    public boolean isFinishRecruitmentValid(LocalDateTime finishRecruitment) {
        return LocalDateTime.now().isBefore(finishRecruitment);
    }

}
