package com.example.epamcourse.controller.listener;

import com.example.epamcourse.model.pool.ConnectionPool;
import jakarta.servlet.annotation.WebListener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;


/**
 * class ServletListener
 *
 * @author M.Shubelko
 */
@WebListener
public class ServletListener implements ServletContextListener {


    /**
     * Context destroyed
     *
     * @param servletContextEvent the servletContextEvent
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ConnectionPool.getInstance().destroyPool();
    }
}