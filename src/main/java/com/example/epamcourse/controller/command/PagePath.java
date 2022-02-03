package com.example.epamcourse.controller.command;

public final class PagePath {
    public static final String INDEX = "/index.jsp";
    public static final String LOGIN = "/pages/login.jsp";
    public static final String LOGIN_REDIRECT = "/controller?command=go_to_login_page";
    public static final String MAIN_PAGE_APPLICANT = "/pages/main_page_applicant.jsp";
    public static final String MAIN_PAGE_ADMINISTRATOR = "/pages/main_page_administrator.jsp";
    public static final String CREATION_ACCOUNT = "/pages/registration.jsp";
    public static final String CREATION_ACCOUNT_REDIRECT = "/controller?command=go_to_registration_page";
    public static final String EXCEPTION_ERROR_REDIRECT = "/controller?command=to_exception_page";
    public static final String ERROR_404 = "/pages/errors/error_404.jsp";
    public static final String ADMIN_ADD_PERSONAL_INF = "/pages/admin_add_personal_inf.jsp";
    public static final String APPLICANT_ADD_PERSONAL_INF_REDIRECT = "/controller?command=applicant_add_secure_information";
    public static final String APPLICANT_ADD_PERSONAL_INF = "/pages/applicant_add_personal_inf.jsp";
    public static final String ADD_REQUEST_PAGE = "/pages/add_request_page.jsp";
    public static final String ADD_REQUEST_PAGE_REDIRECT = "/controller?command=go_to_add_request_page";
    public static final String EDIT_APPLICANT_DATA = "/pages/applicant_edit_data.jsp";
    public static final String EDIT_APPLICANT_DATA_REDIRECT = "/controller?command=go_to_edit_applicant_data";
    public static final String SHOW_FACULTIES_PAGE = "/pages/show_faculties_administrator.jsp";
    public static final int ACCESS_ERROR_PAGE_403 = 403;
    public static final String EXCEPTION_ERROR_PAGE = "/error/exception_error.jsp";
    public static final String EDIT_FACULTY_PAGE = "/pages/edit_faculty_page.jsp";
    public static final String EDIT_FACULTY_REDIRECT = "/controller?command=go_to_edit_faculty&faculty_id=";
    public static final String ADD_FACULTY_PAGE = "/pages/add_faculty.jsp";
    public static final String ADD_FACULTY_PAGE_REDIRECT = "/controller?command=go_to_add_faculty_page";
    public static final String SHOW_ACCOUNTS_PAGE = "/pages/show_accounts.jsp";
    public static final String SHOW_ACCOUNTS_PAGE_REDIRECT = "/controller?command=show_accounts&page=";
    public static final String ADD_ADMIN_ACCOUNT_PAGE = "/pages/adding_admin_account.jsp";
    public static final String ADD_ADMIN_ACCOUNT_PAGE_REDIRECT = "/controller?command=go_to_add_admin_account";
    public static final String CONFIRM_EMAIL_PAGE = "/pages/confirm_email_page.jsp";
    public static final String CONFIRM_EMAIL_PAGE_REDIRECT = "/controller?command=go_to_confirm_email_page";
    public static final String SHOW_APPLICANTS_PAGE = "/pages/show_applicants.jsp";
    public static final String SENDER_EMAIL_PAGE = "/pages/show_applicants_and_sender.jsp";
    public static final String BLOCKED_ACCOUNT_PAGE = "/pages/blocked_account_page.jsp";
    public static final String EDIT_RECRUITMENT = "/pages/edit_recruitment.jsp";
    public static final String EDIT_ADMINISTRATOR_DATA = "/pages/administrator_edit_data.jsp";

    private PagePath() {
    }
}
