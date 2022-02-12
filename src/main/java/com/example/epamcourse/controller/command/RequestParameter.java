package com.example.epamcourse.controller.command;

public final class RequestParameter {
    public static final String COMMAND = "command";
    //account
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String ROLE = "role";
    public static final String PASSWORD_CHECK = "password_check";

    //applicant/administrator secure information
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String LASTNAME = "lastname";

    //applicant
    public static final String PRIVILEGES = "privileges";

    //certificate
    public static final String APPLICANT_ID = "applicant_id";
    public static final String CERTIFICATE_MARK = "certificate_mark";

    //subject
    public static final String SUBJECT_MATH_MARK = "MATH";
    public static final String SUBJECT_PHYSIC_MARK = "PHYSIC";
    public static final String SUBJECT_ENGLISH_MARK = "ENGLISH";
    public static final String SUBJECT_TYPE = "subject_types";

    public static final String FACULTY_ID = "faculty_id";

    public static final String LOCALE = "locale";
    public static final String PAGE = "page";
    public static final String APPLICANTS = "applicants";
    public static final String FACULTY_NAME = "faculty_name";
    public static final String RECRUITMENT_PLAN_FREE = "recruitment_plan_free";
    public static final String RECRUITMENT_PLAN_CANVAS = "recruitment_plan_canvas";
    public static final String ACCOUNT_ID = "account_id";
    public static final String EMAIL_CODE_EXPECTED = "email_code_expected";
    public static final String EMAIL_CODE_ACTUAL = "email_code_actual";
    public static final String IP = "ip";
    public static final String RECRUITMENT_STATUS = "recruitment_status";
    public static final String ACCOUNTS = "accounts";
    public static final String CERTIFICATES = "certificates";
    public static final String TITLE = "title";
    public static final String MESSAGE = "message";
    public static final String FACULTY = "faculty";
    public static final String FINISH_RECRUITMENT = "finish_recruitment";
    public static final String IMAGE = "img";
    public static final String IMAGE_INPUT_STREAM = "image_input_stream";
    public static final String IMAGE_NAME = "image_name";


    private RequestParameter() {
    }
}
