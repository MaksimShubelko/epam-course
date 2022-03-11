package com.example.epamcourse.controller.command;

/**
 * class PagePath
 *
 * @author M.Shubelko
 */
public final class PagePath {

    /** The constant LOGIN **/
    public static final String LOGIN = "/pages/not_automated/login.jsp";

    /** The constant LOGIN_REDIRECT **/
    public static final String LOGIN_REDIRECT = "/controller?command=go_to_login_page";

    /** The constant MAIN_PAGE_APPLICANT **/
    public static final String MAIN_PAGE_APPLICANT = "/pages/applicant/main_page_applicant.jsp";

    /** The constant MAIN_PAGE_ADMINISTRATOR **/
    public static final String MAIN_PAGE_ADMINISTRATOR = "/pages/admin/main_page_administrator.jsp";

    /** The constant CREATION_ACCOUNT **/
    public static final String CREATION_ACCOUNT = "/pages/not_automated/registration.jsp";

    /** The constant CREATION_ACCOUNT_REDIRECT **/
    public static final String CREATION_ACCOUNT_REDIRECT = "/controller?command=go_to_registration_page";

    /** The constant EXCEPTION_ERROR_REDIRECT **/
    public static final String EXCEPTION_ERROR_REDIRECT = "/controller?command=to_exception_page";

    /** The constant ERROR_404 **/
    public static final String ERROR_404 = "/error/error_404.jsp";

    /** The constant ADMIN_ADD_PERSONAL_INF **/
    public static final String ADMIN_ADD_PERSONAL_INF = "/pages/admin/admin_add_personal_inf.jsp";

    /** The constant APPLICANT_ADD_PERSONAL_INF_REDIRECT **/
    public static final String APPLICANT_ADD_PERSONAL_INF_REDIRECT = "/controller?command=applicant_add_secure_information";

    /** The constant APPLICANT_ADD_PERSONAL_INF **/
    public static final String APPLICANT_ADD_PERSONAL_INF = "/pages/applicant/applicant_add_personal_inf.jsp";

    /** The constant ADD_REQUEST_PAGE **/
    public static final String ADD_REQUEST_PAGE = "/pages/applicant/add_request_page.jsp";

    /** The constant ADD_REQUEST_PAGE_REDIRECT **/
    public static final String ADD_REQUEST_PAGE_REDIRECT = "/controller?command=go_to_add_request_page";

    /** The constant EDIT_APPLICANT_DATA **/
    public static final String EDIT_APPLICANT_DATA = "/pages/applicant/applicant_edit_data.jsp";

    /** The constant EDIT_APPLICANT_DATA_REDIRECT **/
    public static final String EDIT_APPLICANT_DATA_REDIRECT = "/controller?command=go_to_edit_applicant_data";

    /** The constant SHOW_FACULTIES_PAGE **/
    public static final String SHOW_FACULTIES_PAGE = "/pages/admin/show_faculties_administrator.jsp";

    /** The constant EXCEPTION_ERROR_PAGE **/
    public static final String EXCEPTION_ERROR_PAGE = "/error/exception_error.jsp";

    /** The constant EDIT_FACULTY_PAGE **/
    public static final String EDIT_FACULTY_PAGE = "/pages/admin/edit_faculty_page.jsp";

    /** The constant EDIT_FACULTY_REDIRECT **/
    public static final String EDIT_FACULTY_REDIRECT = "/controller?command=go_to_edit_faculty&faculty_id=";

    /** The constant ADD_FACULTY_PAGE **/
    public static final String ADD_FACULTY_PAGE = "/pages/admin/add_faculty.jsp";

    /** The constant ADD_FACULTY_PAGE_REDIRECT **/
    public static final String ADD_FACULTY_PAGE_REDIRECT = "/controller?command=go_to_add_faculty_page";

    /** The constant SHOW_ACCOUNTS_PAGE **/
    public static final String SHOW_ACCOUNTS_PAGE = "/pages/admin/show_accounts.jsp";

    /** The constant SHOW_ACCOUNTS_PAGE_REDIRECT **/
    public static final String SHOW_ACCOUNTS_PAGE_REDIRECT = "/controller?command=go_to_show_accounts&page=";

    /** The constant ADD_ADMIN_ACCOUNT_PAGE **/
    public static final String ADD_ADMIN_ACCOUNT_PAGE = "/pages/admin/adding_admin_account.jsp";

    /** The constant ADD_ADMIN_ACCOUNT_PAGE_REDIRECT **/
    public static final String ADD_ADMIN_ACCOUNT_PAGE_REDIRECT = "/controller?command=go_to_add_admin_account";

    /** The constant CONFIRM_EMAIL_PAGE **/
    public static final String CONFIRM_EMAIL_PAGE = "/pages/not_automated/confirm_email_page.jsp";

    /** The constant CONFIRM_EMAIL_PAGE_REDIRECT **/
    public static final String CONFIRM_EMAIL_PAGE_REDIRECT = "/controller?command=go_to_confirm_email_page";

    /** The constant SHOW_APPLICANTS_PAGE **/
    public static final String SHOW_APPLICANTS_PAGE = "/pages/admin/show_applicants.jsp";

    /** The constant SENDER_EMAIL_PAGE **/
    public static final String SENDER_EMAIL_PAGE = "/pages/admin/sender.jsp";

    /** The constant BLOCKED_ACCOUNT_PAGE **/
    public static final String BLOCKED_ACCOUNT_PAGE = "/pages/common/blocked_account_page.jsp";

    /** The constant EDIT_RECRUITMENT **/
    public static final String EDIT_RECRUITMENT = "/pages/admin/edit_recruitment.jsp";

    /** The constant EDIT_ADMINISTRATOR_DATA **/
    public static final String EDIT_ADMINISTRATOR_DATA = "/pages/admin/administrator_edit_data.jsp";

    /** The constant SHOW_FACULTIES_REDIRECT **/
    public static final String SHOW_FACULTIES_REDIRECT = "/controller?command=go_to_show_faculties&page=";

    /** The constant GO_TO_SHOW_APPLICANTS_REDIRECT **/
    public static final String GO_TO_SHOW_APPLICANTS_REDIRECT = "/controller?command=go_to_show_applicants";

    /** The constant GO_TO_SHOW_ACCOUNT_REDIRECT **/
    public static final String GO_TO_SHOW_ACCOUNT_REDIRECT = "/controller?command=go_to_show_accounts";

    /** The constant MAIN_PAGE_APPLICANT_REDIRECT **/
    public static final String MAIN_PAGE_APPLICANT_REDIRECT = "/controller?command=go_to_main_page_applicant";

    /** The constant UPLOAD_IMAGE **/
    public static final String UPLOAD_IMAGE = "/pages/common/upload_image.jsp";

    /** The constant EDIT_ADMINISTRATOR_DATA_REDIRECT **/
    public static final String EDIT_ADMINISTRATOR_DATA_REDIRECT = "/controller?command=go_to_edit_administrator_data";

    /** The constant PROFILE **/
    public static final String PROFILE = "/pages/admin/profile.jsp";

    /** The constant SENDER_EMAIL_PAGE_REDIRECT **/
    public static final String SENDER_EMAIL_PAGE_REDIRECT = "/controller?command=go_to_sender_page";

    /** The constant UPLOAD_IMAGE_REDIRECT **/
    public static final String UPLOAD_IMAGE_REDIRECT = "/controller?command=go_to_upload_image_page";

    /** The constant MAIN_PAGE_ADMINISTRATOR_REDIRECT **/
    public static final String MAIN_PAGE_ADMINISTRATOR_REDIRECT = "/controller?command=go_to_administrator_main_page";
    public static final String SHOW_FACULTIES_PAGE_REDIRECT = "/controller?command=go_to_show_faculties";

    /**
     * private constructor
     */
    private PagePath() {
    }
}
