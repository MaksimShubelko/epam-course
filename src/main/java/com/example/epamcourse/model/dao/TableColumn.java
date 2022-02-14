package com.example.epamcourse.model.dao;

public final class TableColumn {
    //table accounts
    public static final String ACCOUNTS = "account";
    //columns
    public static final String ACCOUNT_ID = "account_id";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String ROLE = "role";
    public static final String STATUS = "status";
    public static final String IP = "ip";
    public static final String IMAGE_PATH = "image_path";

    //table administrators
    public static final String ADMINISTRATORS = "administrators";
    //columns
    public static final String ADMINISTRATOR_ID = "administrator_id";
    public static final String ADM_FIRST_NAME = "firstname";
    public static final String ADM_LAST_NAME = "lastname";
    public static final String ADM_SURNAME = "surname";
    public static final String ADMINISTRATOR_ACCOUNT_ID = "account_id";

    //table applicants
    public static final String APPLICANTS = "applicants";
    //columns
    public static final String APPLICANT_ID = "applicant_id";
    public static final String APPLICANT_ACCOUNT_ID = "account_id";
    public static final String IS_BENEFICIARY = "beneficiary";
    public static final String APPLICANT_FIRST_NAME = "firstname";
    public static final String APPLICANT_LAST_NAME = "lastname";
    public static final String APPLICANT_SURNAME = "surname";

    //table bills
    public static final String BILL = "bills";
    //columns
    public static final String BILL_ID = "bill_id";
    public static final String BILL_FACULTY_ID = "faculty_id";
    public static final String BILL_APPLICANT_ID = "applicant_id";
    public static final String BILL_ARCHIVE = "archive";

    //table certificates
    public static final String CERTIFICATES = "certificates";
    //columns
    public static final String CERTIFICATE_ID = "certificate_id";
    public static final String CERTIFICATE_TOTAL_MARK = "middle_mark";

    //table faculties
    public static final String FACULTY = "faculty";
    //columns
    public static final String FACULTY_ID = "faculty_id";
    public static final String FACULTY_NAME = "faculty_name";
    public static final String MARK_PASS = "mark_pass";
    public static final String RECRUITMENT_PLAN_FREE = "recruitment_plan_free";
    public static final String RECRUITMENT_PLAN_CANVAS = "recruitment_plan_canvas";

    //table subjects
    public static final String SUBJECTS = "subjects";
    //columns
    public static final String SUBJECT_ID = "subject_id";
    public static final String SUBJECT_APPLICANT_ID = "applicant_id";
    public static final String SUBJECT_TYPE = "subject_type";
    public static final String MARK = "mark";

    //table recruitment
    public static final String RECRUITMENT_ID = "recruitment_id";
    public static final String RECRUITMENT_STATUS = "status";
    public static final String RECRUITMENT_FINISHING_DATE = "finish_recruitment";

    private TableColumn() {
    }
}
