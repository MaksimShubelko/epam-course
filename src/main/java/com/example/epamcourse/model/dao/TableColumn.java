package com.example.epamcourse.model.dao;

/**
 * class TableColumn
 *
 * @author M.Shubelko
 */
public final class TableColumn {
    //table accounts
    //columns

    /** The constant ACCOUNT_ID **/
    public static final String ACCOUNT_ID = "account_id";

    /** The constant LOGIN **/
    public static final String LOGIN = "login";

    /**
     * The constant PASSWORD
     */
    public static final String PASSWORD = "password";

    /** The constant EMAIL **/
    public static final String EMAIL = "email";

    /** The constant ROLE **/
    public static final String ROLE = "role";

    /** The constant STATUS **/
    public static final String STATUS = "status";

    /** The constant IP **/
    public static final String IP = "ip";

    /** The constant IMAGE_PATH **/
    public static final String IMAGE_PATH = "image_path";

    //table administrators
    //columns

    /** The constant ADMINISTRATOR_ID **/
    public static final String ADMINISTRATOR_ID = "administrator_id";

    /** The constant ADM_FIRST_NAME **/
    public static final String ADM_FIRST_NAME = "firstname";

    /** The constant ADM_LAST_NAME **/
    public static final String ADM_LAST_NAME = "lastname";

    /** The constant ADM_SURNAME **/
    public static final String ADM_SURNAME = "surname";

    /** The constant ADMINISTRATOR_ACCOUNT_ID **/
    public static final String ADMINISTRATOR_ACCOUNT_ID = "account_id";

    //table applicants
    //columns

    /** The constant APPLICANT_ID **/
    public static final String APPLICANT_ID = "applicant_id";

    /** The constant APPLICANT_ACCOUNT_ID **/
    public static final String APPLICANT_ACCOUNT_ID = "account_id";

    /** The constant IS_BENEFICIARY **/
    public static final String IS_BENEFICIARY = "beneficiary";

    /** The constant APPLICANT_FIRST_NAME **/
    public static final String APPLICANT_FIRST_NAME = "firstname";

    /** The constant APPLICANT_LAST_NAME **/
    public static final String APPLICANT_LAST_NAME = "lastname";

    /** The constant APPLICANT_SURNAME **/
    public static final String APPLICANT_SURNAME = "surname";

    //table bills
    //columns

    /** The constant BILL_ID **/
    public static final String BILL_ID = "bill_id";

    /** The constant BILL_FACULTY_ID **/
    public static final String BILL_FACULTY_ID = "faculty_id";

    /** The constant BILL_APPLICANT_ID **/
    public static final String BILL_APPLICANT_ID = "applicant_id";

    /** The constant BILL_ARCHIVE **/
    public static final String BILL_ARCHIVE = "archive";

    //table certificates
    //columns

    /** The constant CERTIFICATE_ID **/
    public static final String CERTIFICATE_ID = "certificate_id";

    /** The constant CERTIFICATE_TOTAL_MARK **/
    public static final String CERTIFICATE_TOTAL_MARK = "middle_mark";

    //table faculties
    //columns

    /** The constant FACULTY_ID **/
    public static final String FACULTY_ID = "faculty_id";

    /** The constant FACULTY_NAME **/
    public static final String FACULTY_NAME = "faculty_name";

    /** The constant RECRUITMENT_PLAN_FREE **/
    public static final String RECRUITMENT_PLAN_FREE = "recruitment_plan_free";

    /** The constant RECRUITMENT_PLAN_CANVAS **/
    public static final String RECRUITMENT_PLAN_CANVAS = "recruitment_plan_canvas";

    //table subjects
    //columns

    /** The constant SUBJECT_ID **/
    public static final String SUBJECT_ID = "subject_id";

    /** The constant SUBJECT_APPLICANT_ID **/
    public static final String SUBJECT_APPLICANT_ID = "applicant_id";

    /** The constant SUBJECT_TYPE **/
    public static final String SUBJECT_TYPE = "subject_type";

    /** The constant MARK **/
    public static final String MARK = "mark";

    //table recruitment
    //columns

    /** The constant RECRUITMENT_ID **/
    public static final String RECRUITMENT_ID = "recruitment_id";

    /** The constant RECRUITMENT_STATUS **/
    public static final String RECRUITMENT_STATUS = "status";

    /** The constant RECRUITMENT_FINISHING_DATE **/
    public static final String RECRUITMENT_FINISHING_DATE = "finish_recruitment";

    /**
     * The private constructor
     */
    private TableColumn() {
    }
}
