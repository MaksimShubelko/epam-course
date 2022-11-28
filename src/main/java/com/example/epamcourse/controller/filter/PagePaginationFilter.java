package com.example.epamcourse.controller.filter;

import com.example.epamcourse.controller.command.RequestAttribute;
import com.example.epamcourse.controller.command.RequestParameter;
import com.example.epamcourse.controller.command.SessionAttribute;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "PageRedirectSecurityFilter", urlPatterns = "/*")
public class PagePaginationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        int page = 1;
        if (request.getParameter(RequestParameter.PAGE) != null) {
            page = Integer.parseInt(request.getParameter(RequestParameter.PAGE));
        }
        session.setAttribute(SessionAttribute.PAGE, page);
        chain.doFilter(servletRequest, servletResponse);
    }
}
