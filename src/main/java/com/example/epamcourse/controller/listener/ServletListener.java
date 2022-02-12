package com.example.epamcourse.controller.listener;

import com.example.epamcourse.model.pool.ConnectionPool;
import jakarta.servlet.annotation.WebListener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

@WebListener
public class ServletListener implements ServletContextListener {

    public ServletListener() {

    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().destroyPool();
    }
}