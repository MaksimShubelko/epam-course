package com.example.epamcourse.model.validator.impl;

import com.example.epamcourse.model.dao.BillDao;
import com.example.epamcourse.model.dao.impl.BillDaoImpl;
import com.example.epamcourse.model.validator.RecruitmentValidator;

import java.time.LocalDateTime;

public class RecruitmentValidatorImpl implements RecruitmentValidator {
    private static RecruitmentValidator instance = new RecruitmentValidatorImpl();

    private RecruitmentValidatorImpl() {
    }

    public static RecruitmentValidator getInstance() {
        return instance;
    }

    @Override
    public boolean isFinishRecruitmentValid(LocalDateTime finishRecruitment) {
        return LocalDateTime.now().isBefore(finishRecruitment);
    }

}
