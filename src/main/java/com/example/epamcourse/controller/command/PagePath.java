package com.example.epamcourse.controller.command;

public final class PagePath {
    public static final String INDEX = "index.jsp";
    public static final String LOGIN = "pages/login.jsp";                               // todo delete
    public static final String EXCEPTION_ERROR_REDIRECT = "/controller?command=to_exception_page";
    public static final String ERROR_404 = "pages/errors/error_404.jsp";

    public static final int ACCESS_ERROR_PAGE_403 = 403;

    private PagePath() {
    }
}
