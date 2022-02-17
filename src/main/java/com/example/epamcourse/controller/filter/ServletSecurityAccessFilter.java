package com.example.epamcourse.controller.filter;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.entity.Account;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;

@WebFilter(filterName = "ServletSecurityFilter",
        urlPatterns = {"/controller", "/upload_image_controller"},
        dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST},
        initParams = { @WebInitParam(name = "index_path", value = "index.jsp") })
public class ServletSecurityAccessFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException, IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        Account.Role role = (Account.Role) session.getAttribute(SessionAttribute.ROLE);
        role = (role != null) ? role : Account.Role.NOT_AUTOMATED;
        String command = request.getParameter(RequestParameter.COMMAND);
        CommandType commandType;
        if (isCommandValid(command)) {
            commandType = CommandType.valueOf(command.toUpperCase());
            if (commandType.hasAccountRole(role)) {
                chain.doFilter(request, response);
                request.setAttribute(RequestAttribute.MESSAGE, LocaleMessageKey.ILLEGAL_ROLE);
            } else {
                httpResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            httpResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            request.setAttribute(RequestAttribute.MESSAGE, LocaleMessageKey.ILLEGAL_ADDRESS);
        }



    }

    private boolean isCommandValid(String command) {
        return command != null && Arrays.stream(CommandType.values())
                .anyMatch((commandType) -> commandType.name().equalsIgnoreCase(command));
    }
}
