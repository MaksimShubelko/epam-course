package com.example.epamcourse.controller.listener;

import com.example.epamcourse.controller.command.SessionAttribute;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
    private static final String DEFAULT_LOCALE = "en_EN";
    private static final int ROW_AMOUNT = 5;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        session.setAttribute(SessionAttribute.SESSION_LOCALE, DEFAULT_LOCALE);
        session.setAttribute(SessionAttribute.ROW_AMOUNT, ROW_AMOUNT);


    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }

}