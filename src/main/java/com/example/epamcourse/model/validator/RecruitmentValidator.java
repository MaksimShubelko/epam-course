package com.example.epamcourse.model.validator;

import java.time.LocalDateTime;

public interface RecruitmentValidator {

    boolean isFinishRecruitmentValid(LocalDateTime finishRecruitment);

}
