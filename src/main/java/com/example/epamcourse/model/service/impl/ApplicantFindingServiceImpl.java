package com.example.epamcourse.model.service.impl;

import com.example.epamcourse.model.service.ApplicantFindingService;
import com.example.epamcourse.model.service.ApplicantFindingType;

import java.util.Map;

public class ApplicantFindingServiceImpl implements ApplicantFindingService {
    private static final ApplicantFindingServiceImpl instance = new ApplicantFindingServiceImpl();

    private ApplicantFindingServiceImpl() {
    }

    public static ApplicantFindingServiceImpl getInstance() {
        return instance;
    }

    @Override
    public int getCountOfApplicantsToSkip(String recruitmentStatus, int recruitmentPlanCanvas, int recruitmentPlanFree, int countApplicants) {
        countApplicants = findTotalCountOfApplicants(recruitmentStatus, recruitmentPlanCanvas, recruitmentPlanFree, countApplicants);
        int applicantsSkipDepOnRecruitStatus = 0;
        switch (ApplicantFindingType.valueOf(recruitmentStatus.toUpperCase())) {
            case ALL:
            case FREE:
                break;
            case CANVAS:
                applicantsSkipDepOnRecruitStatus = recruitmentPlanFree;
                break;
            case NOT_RECEIVED:
                applicantsSkipDepOnRecruitStatus = recruitmentPlanFree + recruitmentPlanCanvas;
                break;
            default:
                throw new UnsupportedOperationException();
        }

        return applicantsSkipDepOnRecruitStatus;
    }

    @Override
    public int getCountOfApplicantsToTake(String recruitmentStatus, int recruitmentPlanCanvas, int recruitmentPlanFree, int countApplicants) {
        countApplicants = findTotalCountOfApplicants(recruitmentStatus, recruitmentPlanCanvas, recruitmentPlanFree, countApplicants);
        int applicantsTakeDepOnRecruitStatus = 0;
        switch (ApplicantFindingType.valueOf(recruitmentStatus.toUpperCase())) {
            case ALL:
                applicantsTakeDepOnRecruitStatus = (int) countApplicants;
                break;
            case FREE:
                applicantsTakeDepOnRecruitStatus = recruitmentPlanFree;
                break;
            case CANVAS:
                applicantsTakeDepOnRecruitStatus = recruitmentPlanFree + recruitmentPlanCanvas;
                break;
            case NOT_RECEIVED:
                break;
            default:
                throw new UnsupportedOperationException();
        }

        return applicantsTakeDepOnRecruitStatus;
    }

    @Override
    public int findTotalCountOfApplicants(String recruitmentStatus, int recruitmentPlanCanvas, int recruitmentPlanFree, int countApplicants) {
        switch (ApplicantFindingType.valueOf(recruitmentStatus.toUpperCase())) {
            case ALL:
                break;
            case FREE:
                countApplicants = Math.min(recruitmentPlanFree, countApplicants);
                break;
            case CANVAS:
                countApplicants = recruitmentPlanFree + recruitmentPlanCanvas > countApplicants
                        ? countApplicants - recruitmentPlanFree : recruitmentPlanCanvas;

                break;
            case NOT_RECEIVED:
                countApplicants = recruitmentPlanFree + recruitmentPlanCanvas > countApplicants ?
                        0 : countApplicants - recruitmentPlanFree - recruitmentPlanCanvas;
                break;
            default:
                throw new UnsupportedOperationException();
        }

        return countApplicants;
    }

}
