package com.bdev.hogwarts_api.connection

import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener
import javax.servlet.annotation.WebListener

import com.bdev.hogwarts_api.properties.PropertiesConfig.config

@WebListener
open class ConnectionWebListener : ServletContextListener {
    private lateinit var connection: SshConnection

    override fun contextInitialized(servletContextEvent: ServletContextEvent) {
        if (config.databaseRemoteConnectionEnabled) {
            try {
                connection = SshConnection()
            } catch (e: Exception) {
                /* Do nothing */
            }
        }
    }

    override fun contextDestroyed(servletContextEvent: ServletContextEvent) {
        if (config.databaseRemoteConnectionEnabled) {
            connection.close()
        }
    }
}