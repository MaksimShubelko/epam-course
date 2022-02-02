package com.example.epamcourse.model.service.impl;

import com.example.epamcourse.model.service.ApplicantFindingService;
import com.example.epamcourse.model.service.ApplicantFindingType;

import java.util.Map;

public class ApplicantFindingServiceImpl implements ApplicantFindingService {

    @Override
    public Map<String, Integer> getCountOfApplicantsToSkip(String recruitmentStatus) {
        return null;
    }

    @Override
    public Map<String, Integer> getCountOfApplicantsToTake(String recruitmentStatus) {
        return null;
    }

    @Override
    public int findTotalCountOfApplicants(String recruitmentStatus, int recruitmentPlanCanvas, int recruitmentPlanFree) {
        int countApplicants;
        //ApplicantFindingType.ALL.toString();
        switch (recruitmentStatus) {
            case "all":
                break;
            case "free":
                countApplicants = Math.min(recruitmentPlanFree, countApplicants);
                break;
            case "canvas":
                countApplicants = recruitmentPlanFree + recruitmentPlanCanvas > countApplicants
                        ? countApplicants - recruitmentPlanFree : recruitmentPlanCanvas;

                break;
            case "not_received":
                countApplicants = recruitmentPlanFree + recruitmentPlanCanvas > countApplicants ?
                        0 : countApplicants - recruitmentPlanFree - recruitmentPlanCanvas;
                break;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
