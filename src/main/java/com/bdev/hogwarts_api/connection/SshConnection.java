package com.bdev.hogwarts_api.connection;

import com.bdev.hogwarts_api.properties.PropertiesConfig;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import lombok.SneakyThrows;

import java.util.Properties;

import static com.bdev.hogwarts_api.properties.PropertiesConfig.config;

public class SshConnection {
    private Session session;

    @SneakyThrows
    public SshConnection() {
        JSch jsch = new JSch();

        String host = config.getDatabaseRemoteConnectionHost();
        String login = config.getDatabaseRemoteConnectionUsername();
        String password = config.getDatabaseRemoteConnectionPassword();
        int port = (int) config.getDatabaseRemoteConnectionPort();
        int databaseLocalPort = (int) config.getDatabaseRemoteConnectionLocalPort();
        int databaseRemotePort = (int) config.getDatabaseRemoteConnectionRemotePort();

        session = jsch.getSession(login, host, port); {
            Properties config = new Properties(); {
                config.put("StrictHostKeyChecking", "no");
            }

            session.setPassword(password);
            session.setConfig(config);
            session.connect();
        }

        session.setPortForwardingL(databaseLocalPort, host, databaseRemotePort);
    }

    public void close() {
        session.disconnect();
    }
}