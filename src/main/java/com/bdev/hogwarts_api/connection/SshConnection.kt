package com.bdev.hogwarts_api.connection

import com.bdev.hogwarts_api.properties.PropertiesConfig.config
import com.jcraft.jsch.JSch
import com.jcraft.jsch.Session
import java.util.*

class SshConnection {
    private val session: Session

    init {
        val jsch = JSch()

        val host = config.databaseRemoteConnectionHost
        val login = config.databaseRemoteConnectionUsername
        val password = config.databaseRemoteConnectionPassword
        val port = config.databaseRemoteConnectionPort.toInt()
        val databaseLocalPort = config.databaseRemoteConnectionLocalPort.toInt()
        val databaseRemotePort = config.databaseRemoteConnectionRemotePort.toInt()

        session = jsch.getSession(login, host, port)

        session.setPassword(password)
        session.setConfig(getConfig())
        session.setPortForwardingL(databaseLocalPort, host, databaseRemotePort)

        session.connect()
    }

    fun close() {
        session.disconnect()
    }

    private fun getConfig(): Properties {
        val config = Properties()

        config["StrictHostKeyChecking"] = "no"

        return config
    }
}