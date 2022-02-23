package com.example.epamcourse.model.service;

/**
 * interface ApplicantFindingService
 *
 * @author M.Shubelko
 */
public interface ApplicantFindingService {

    /**
     * The getting of count of applicants to skip
     *
     * @param recruitmentStatus the recruitment status
     * @param recruitmentPlanCanvas the recruitment plan free
     * @param recruitmentPlanFree the recruitment plan canvas
     * @param countApplicants the count of applicants in faculty
     * @return the count of applicants to skip
     */
    int getCountOfApplicantsToSkip(String recruitmentStatus, int recruitmentPlanCanvas, int recruitmentPlanFree, int countApplicants);

    /**
     * The getting of count of applicants to take
     *
     * @param recruitmentStatus the recruitment status
     * @param recruitmentPlanCanvas the recruitment plan free
     * @param recruitmentPlanFree the recruitment plan canvas
     * @param countApplicants the total count of applicants
     * @return the count of applicants to take
     */
    int getCountOfApplicantsToTake(String recruitmentStatus, int recruitmentPlanCanvas, int recruitmentPlanFree, int countApplicants);

    /**
     * The finding of total count of applicants
     *
     * @param recruitmentStatus the recruitment status
     * @param recruitmentPlanCanvas the recruitment plan free
     * @param recruitmentPlanFree the recruitment plan canvas
     * @param countApplicants the total count of applicants
     * @return the count of applicant in choose group
     */
    int findTotalCountOfApplicants(String recruitmentStatus, int recruitmentPlanCanvas, int recruitmentPlanFree, int countApplicants);
}