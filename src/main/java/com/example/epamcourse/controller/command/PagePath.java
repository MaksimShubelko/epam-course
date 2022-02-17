package com.example.epamcourse.controller.command;

public final class PagePath {
    public static final String LOGIN = "/pages/not_automated/login.jsp";
    public static final String LOGIN_REDIRECT = "/controller?command=go_to_login_page";
    public static final String MAIN_PAGE_APPLICANT = "/pages/applicant/main_page_applicant.jsp";
    public static final String MAIN_PAGE_ADMINISTRATOR = "/pages/admin/main_page_administrator.jsp";
    public static final String CREATION_ACCOUNT = "/pages/not_automated/registration.jsp";
    public static final String CREATION_ACCOUNT_REDIRECT = "/controller?command=go_to_registration_page";
    public static final String EXCEPTION_ERROR_REDIRECT = "/controller?command=to_exception_page";
    public static final String ERROR_404 = "/error/error_404.jsp";
    public static final String ADMIN_ADD_PERSONAL_INF = "/pages/admin/admin_add_personal_inf.jsp";
    public static final String APPLICANT_ADD_PERSONAL_INF_REDIRECT = "/controller?command=applicant_add_secure_information";
    public static final String APPLICANT_ADD_PERSONAL_INF = "/pages/applicant/applicant_add_personal_inf.jsp";
    public static final String ADD_REQUEST_PAGE = "/pages/applicant/add_request_page.jsp";
    public static final String ADD_REQUEST_PAGE_REDIRECT = "/controller?command=go_to_add_request_page";
    public static final String EDIT_APPLICANT_DATA = "/pages/applicant/applicant_edit_data.jsp";
    public static final String EDIT_APPLICANT_DATA_REDIRECT = "/controller?command=go_to_edit_applicant_data";
    public static final String SHOW_FACULTIES_PAGE = "/pages/admin/show_faculties_administrator.jsp";
    public static final int ACCESS_ERROR_PAGE_403 = 403;
    public static final String EXCEPTION_ERROR_PAGE = "/error/exception_error.jsp";
    public static final String EDIT_FACULTY_PAGE = "/pages/admin/edit_faculty_page.jsp";
    public static final String EDIT_FACULTY_REDIRECT = "/controller?command=go_to_edit_faculty&faculty_id=";
    public static final String ADD_FACULTY_PAGE = "/pages/admin/add_faculty.jsp";
    public static final String ADD_FACULTY_PAGE_REDIRECT = "/controller?command=go_to_add_faculty_page";
    public static final String SHOW_ACCOUNTS_PAGE = "/pages/admin/show_accounts.jsp";
    public static final String SHOW_ACCOUNTS_PAGE_REDIRECT = "/controller?command=go_to_show_accounts&page=";
    public static final String ADD_ADMIN_ACCOUNT_PAGE = "/pages/admin/adding_admin_account.jsp";
    public static final String ADD_ADMIN_ACCOUNT_PAGE_REDIRECT = "/controller?command=go_to_add_admin_account";
    public static final String CONFIRM_EMAIL_PAGE = "/pages/not_automated/confirm_email_page.jsp";
    public static final String CONFIRM_EMAIL_PAGE_REDIRECT = "/controller?command=go_to_confirm_email_page";
    public static final String SHOW_APPLICANTS_PAGE = "/pages/admin/show_applicants.jsp";
    public static final String SENDER_EMAIL_PAGE = "/pages/admin/sender.jsp";
    public static final String BLOCKED_ACCOUNT_PAGE = "/pages/common/blocked_account_page.jsp";
    public static final String EDIT_RECRUITMENT = "/pages/admin/edit_recruitment.jsp";
    public static final String EDIT_ADMINISTRATOR_DATA = "/pages/admin/administrator_edit_data.jsp";
    public static final String SHOW_FACULTIES_REDIRECT = "/controller?command=go_to_show_faculties";
    public static final String GO_TO_SHOW_APPLICANTS_REDIRECT = "/controller?command=go_to_show_applicants";
    public static final String GO_TO_SHOW_ACCOUNT_REDIRECT = "/controller?command=go_to_show_accounts";
    public static final String MAIN_PAGE_APPLICANT_REDIRECT = "/controller?command=go_to_main_page_applicant";
    public static final String UPLOAD_IMAGE = "/pages/common/upload_image.jsp";
    public static final String EDIT_ADMINISTRATOR_DATA_REDIRECT = "/controller?command=go_to_edit_administrator_data";
    public static final String PROFILE = "/pages/admin/profile.jsp";
    public static final String SENDER_EMAIL_PAGE_REDIRECT = "/controller?command=go_to_sender_page";

    private PagePath() {
    }
}
