/*package com.example.epamcourse.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter(dispatcherTypes = {DispatcherType.FORWARD}, urlPatterns = {"/*"})
public class RefererFilter implements Filter {
    private static final String REFERER = "referer";
    private static final String PATH_REGEX = "/controller.+";

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession(true);
        StringBuffer url = request.getRequestURL();
        String page = url.substring(url.lastIndexOf("/"));
        session.setAttribute("current_page", page);
    }

    private String substringPathWithRegex(String url) {
        Pattern pattern = Pattern.compile(PATH_REGEX);
        String path = null;
        if (url != null) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.find()) {
                path = matcher.group(0);
            } else {
                path = "index.jsp";
            }


        }
        return path;
    }

    public void init(FilterConfig config) throws ServletException {

    }
}*/
