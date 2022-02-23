package com.example.epamcourse.model.service.impl;

import com.example.epamcourse.model.service.ApplicantFindingService;
import com.example.epamcourse.model.service.ApplicantFindingType;

/**
 * class ApplicantFindingServiceImpl
 *
 * @author M.Shubelko
 */
public class ApplicantFindingServiceImpl implements ApplicantFindingService {

    /**
     * The instance
     */
    private static final ApplicantFindingService instance = new ApplicantFindingServiceImpl();

    /**
     * The private constructor
     */
    private ApplicantFindingServiceImpl() {
    }

    /**
     * The getting of instance
     *
     * @return instance the instance
     */
    public static ApplicantFindingService getInstance() {
        return instance;
    }

    /**
     * The getting of count of applicants to skip
     *
     * @param recruitmentStatus the recruitment status
     * @param recruitmentPlanCanvas the recruitment plan canvas
     * @param recruitmentPlanFree the recruitment plan free
     * @param countApplicants the count of applicants
     * @return applicantsSkipDepOnRecruitStatus the applicants
     * skip in dependency on recruit status
     */
    @Override
    public int getCountOfApplicantsToSkip(String recruitmentStatus, int recruitmentPlanCanvas, int recruitmentPlanFree, int countApplicants) {
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

    /**
     * The getting of count of applicants to take
     *
     * @param recruitmentStatus the recruitment status
     * @param recruitmentPlanCanvas the recruitment plan canvas
     * @param recruitmentPlanFree the recruitment plan free
     * @param countApplicants the count of applicants
     * @return applicantsSkipDepOnRecruitStatus the applicants
     * skip in dependency on recruit status
     */
    @Override
    public int getCountOfApplicantsToTake(String recruitmentStatus, int recruitmentPlanCanvas, int recruitmentPlanFree, int countApplicants) {
        countApplicants = findTotalCountOfApplicants(recruitmentStatus, recruitmentPlanCanvas, recruitmentPlanFree, countApplicants);
        int applicantsTakeDepOnRecruitStatus = 0;
        switch (ApplicantFindingType.valueOf(recruitmentStatus.toUpperCase())) {
            case ALL:
                applicantsTakeDepOnRecruitStatus = countApplicants;
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

    /**
     * The getting the count of applicants
     *
     * @param recruitmentStatus the recruitment status
     * @param recruitmentPlanCanvas the recruitment plan canvas
     * @param recruitmentPlanFree the recruitment plan free
     * @param countApplicants the count of applicants
     * @return applicantsSkipDepOnRecruitStatus the applicants
     * skip in dependency on recruit status
     */
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
