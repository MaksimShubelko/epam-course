package com.example.epamcourse.model.service;

import java.util.Map;

public interface ApplicantFindingService {

    Map<String, Integer> getCountOfApplicantsToSkip(String recruitmentStatus);

    Map<String, Integer> getCountOfApplicantsToTake(String recruitmentStatus);

    int findTotalCountOfApplicants(String recruitmentStatus, int recruitmentPlanCanvas, int recruitmentPlanFree);
