/*
package com.example.epamcourse.controller.filter;

import com.example.epamcourse.controller.command.*;
import com.example.epamcourse.model.entity.Account;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;

@WebFilter(filterName = "ServletSecurityAccessFilter",
        urlPatterns = {"/controller"},
        initParams = {@WebInitParam(name = "index", value = "index.jsp")})
class ServletSecurityAccessFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        Account account = (Account) session.getAttribute(SessionAttribute.ACCOUNT);
        Account.Role role = (account != null) ? account.getRole() : Account.Role.NOT_AUTOMATED;
        String command = request.getParameter(RequestParameter.COMMAND);
        CommandType commandType;

        if (isCommandValid(command)) {
            commandType = CommandType.valueOf(command.toUpperCase());
            if (!commandType.hasAccountRole(role)) {
                commandType = CommandType.GO_TO_LOGIN_PAGE;
                request.setAttribute(RequestAttribute.MESSAGE, LocaleMessageKey.ILLEGAL_ROLE);
            }
        } else {
            commandType = CommandType.GO_TO_LOGIN_PAGE;
            request.setAttribute(RequestAttribute.MESSAGE, LocaleMessageKey.ILLEGAL_ADDRESS);
        }

        request.setAttribute(RequestAttribute.COMMAND, commandType.getCommand());

        chain.doFilter(request, response);
    }

    private boolean isCommandValid(String command) {
        return command != null && Arrays.stream(CommandType.values())
                .anyMatch((commandType) -> commandType.name().equalsIgnoreCase(command));
    }
}

*/
