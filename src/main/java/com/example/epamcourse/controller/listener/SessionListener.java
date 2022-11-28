package com.example.epamcourse.controller.listener;

import com.example.epamcourse.controller.command.SessionAttribute;
import jakarta.servlet.annotation.WebListener;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
    /** The constant DEFAULT_LOCALE **/
    private static final String DEFAULT_LOCALE = "ru_RUS";

    /** The constant ROW_AMOUNT **/
    private static final int ROW_AMOUNT = 5;

    /**
     * Session created
     *
     * @param sessionEvent
     */
    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();
        session.setAttribute(SessionAttribute.SESSION_LOCALE, DEFAULT_LOCALE);
        session.setAttribute(SessionAttribute.ROW_AMOUNT, ROW_AMOUNT);

    }

}