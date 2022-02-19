package com.example.epamcourse.controller.filter;

import com.example.epamcourse.controller.command.SessionAttribute;
import com.example.epamcourse.model.entity.Account;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static com.example.epamcourse.controller.command.PagePath.*;

/**
 * class PageRedirectSecurityFilter
 *
 * @author M.Shubelko
 */
@WebFilter(filterName = "PageRedirectSecurityFilter",
        urlPatterns = "/pages/*")
public class PageRedirectSecurityFilter implements Filter {

    /**
     * Pages for not automated users
     **/
    private Set<String> notAutomatedPages;

    /**
     * Pages for applicants
     **/
    private Set<String> applicantPages;

    /**
     * Pages for administrators
     **/
    private Set<String> administratorPages;

    /**
     * All pages
     **/
    private Set<String> allPages;

    /**
     * Do filter
     *
     * @param servletRequest  the servletRequest
     * @param servletResponse the servletResponse
     * @param chain           the chain
     * @throws IOException      the IOException
     * @throws ServletException the ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getServletPath();
        boolean isPageExist = allPages.stream().anyMatch(requestURI::contains);

        if (!isPageExist) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } else {
            HttpSession session = request.getSession();
            Account.Role role = session.getAttribute(SessionAttribute.ROLE) == null
                    ? Account.Role.NOT_AUTOMATED
                    : Account.Role.valueOf(session.getAttribute(SessionAttribute.ROLE).toString());
            boolean isAccept =
                    switch (role) {
                        case APPLICANT -> applicantPages.stream().anyMatch(requestURI::contains);
                        case ADMIN -> administratorPages.stream().anyMatch(requestURI::contains);
                        default -> notAutomatedPages.stream().anyMatch(requestURI::contains);
                    };
            if (!isAccept) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            } else {
                chain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    /**
     * Init filter
     *
     * @param filterConfig the filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {
        notAutomatedPages = Set.of(
                LOGIN,
                CREATION_ACCOUNT,
                CONFIRM_EMAIL_PAGE);

        applicantPages = Set.of(
                MAIN_PAGE_APPLICANT,
                APPLICANT_ADD_PERSONAL_INF,
                ADD_REQUEST_PAGE,
                EDIT_APPLICANT_DATA,
                BLOCKED_ACCOUNT_PAGE,
                EDIT_RECRUITMENT,
                UPLOAD_IMAGE,
                PROFILE);

        administratorPages = Set.of(
                EDIT_RECRUITMENT,
                ADMIN_ADD_PERSONAL_INF,
                MAIN_PAGE_ADMINISTRATOR,
                SHOW_FACULTIES_PAGE,
                EDIT_FACULTY_PAGE,
                ADD_FACULTY_PAGE,
                SHOW_ACCOUNTS_PAGE,
                ADD_ADMIN_ACCOUNT_PAGE,
                SHOW_APPLICANTS_PAGE,
                SENDER_EMAIL_PAGE,
                BLOCKED_ACCOUNT_PAGE,
                EDIT_ADMINISTRATOR_DATA,
                UPLOAD_IMAGE,
                PROFILE);

        allPages = new HashSet<>();
        allPages.addAll(notAutomatedPages);
        allPages.addAll(applicantPages);
        allPages.addAll(administratorPages);
    }


    /**
     * Destroy
     */
    @Override
    public void destroy() {
    }
}
