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

/**
 * class ServletSecurityAccessFilter
 *
 * @author M.Shubelko
 */
@WebFilter(filterName = "ServletSecurityFilter",
        urlPatterns = {"/controller", "/upload_image_controller"},
        dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST},
        initParams = { @WebInitParam(name = "index_path", value = "index.jsp") })
public class ServletSecurityAccessFilter implements Filter {

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
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException, IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpRequest.getSession();
        Account.Role role = (Account.Role) session.getAttribute(SessionAttribute.ROLE);
        role = (role != null) ? role : Account.Role.NOT_AUTOMATED;
        String command = servletRequest.getParameter(RequestParameter.COMMAND);
        CommandType commandType;
        if (isCommandValid(command)) {
            commandType = CommandType.valueOf(command.toUpperCase());
            if (commandType.hasAccountRole(role)) {
                chain.doFilter(servletRequest, servletResponse);
                servletRequest.setAttribute(RequestAttribute.MESSAGE, LocaleMessageKey.ILLEGAL_ROLE);
            } else {
                httpResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            httpResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            servletRequest.setAttribute(RequestAttribute.MESSAGE, LocaleMessageKey.ILLEGAL_ADDRESS);
        }



    }

    /**
     * Check the validness of command
     *
     * @param command the command
     * @return validness of command
     */
    private boolean isCommandValid(String command) {
        return command != null && Arrays.stream(CommandType.values())
                .anyMatch((commandType) -> commandType.name().equalsIgnoreCase(command));
    }
}
