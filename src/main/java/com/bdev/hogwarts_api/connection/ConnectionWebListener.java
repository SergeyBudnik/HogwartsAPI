package com.bdev.hogwarts_api.connection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ConnectionWebListener implements ServletContextListener {
    private SshConnection connection;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        connection = new SshConnection();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        connection.close();
    }
}