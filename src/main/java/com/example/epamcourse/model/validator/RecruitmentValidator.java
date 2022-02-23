package com.example.epamcourse.model.validator;

import java.time.LocalDateTime;

/**
 * interface FacultyValidator
 *
 * @author M.Shubelko
 */
public interface RecruitmentValidator {

    /**
     * The validation of finishing of recruitment date
     *
     * @param finishRecruitment the finish recruitment
     * @return true if finish recruitment date is valid
     */
    boolean isFinishRecruitmentValid(LocalDateTime finishRecruitment);

}
