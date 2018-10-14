package com.bdev.hogwarts_api.connection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import static com.bdev.hogwarts_api.properties.PropertiesConfig.config;

@WebListener
public class ConnectionWebListener implements ServletContextListener {
    private SshConnection connection;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        if (config.getDatabaseRemoteConnectionEnabled()) {
            connection = new SshConnection();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if (config.getDatabaseRemoteConnectionEnabled()) {
            connection.close();
        }
    }
}