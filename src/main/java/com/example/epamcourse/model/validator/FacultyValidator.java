package com.example.epamcourse.model.validator;

/**
 * interface FacultyValidator
 *
 * @author M.Shubelko
 */
public interface FacultyValidator {

     /**
      * The validation of faculty name
      *
      * @param facultyName the faculty name
      * @return true if faculty name is valid
      */
     boolean isFacultyNameValid(String facultyName);

     /**
      * The validation of recruitment plan
      *
      * @param recruitmentPlan the recruitment plan
      * @return true if recruitment plan is valid
      */
     boolean isRecruitmentPlanValid(int recruitmentPlan);
}
