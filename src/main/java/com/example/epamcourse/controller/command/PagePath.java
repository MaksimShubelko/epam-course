package com.example.epamcourse.controller.command;

public final class PagePath {
    public static final String INDEX = "/index.jsp";
    public static final String LOGIN = "/pages/login.jsp";
    public static final String LOGIN_REDIRECT = "/controller?command=go_to_login_page";
    public static final String MAIN_PAGE_APPLICANT = "/pages/main_page_applicant.jsp";
    public static final String MAIN_PAGE_ADMINISTRATOR = "/pages/main_page_applicant.jsp";
    public static final String CREATION_ACCOUNT = "/pages/registration.jsp";
    public static final String CREATION_ACCOUNT_REDIRECT = "/controller?command=go_to_registration_page";
    public static final String EXCEPTION_ERROR_REDIRECT = "/controller?command=to_exception_page";
    public static final String ERROR_404 = "pages/errors/error_404.jsp";
    public static final String ADMIN_ADD_PERSONAL_INF = "/pages/admin_add_personal_inf.jsp";
    public static final String APPLICANT_ADD_PERSONAL_INF_REDIRECT = "/controller?command=applicant_add_secure_information";
    public static final String APPLICANT_ADD_PERSONAL_INF = "/pages/applicant_add_personal_inf.jsp";

    public static final int ACCESS_ERROR_PAGE_403 = 403;
    public static final String EXCEPTION_ERROR_PAGE = "/error/exception_error.jsp";

    private PagePath() {
    }
}
