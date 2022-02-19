package com.example.epamcourse.model.service;

public interface ApplicantFindingService {

    int getCountOfApplicantsToSkip(String recruitmentStatus, int recruitmentPlanCanvas, int recruitmentPlanFree, int countApplicants);

    int getCountOfApplicantsToTake(String recruitmentStatus, int recruitmentPlanCanvas, int recruitmentPlanFree, int countApplicants);

    int findTotalCountOfApplicants(String recruitmentStatus, int recruitmentPlanCanvas, int recruitmentPlanFree, int countApplicants);
}