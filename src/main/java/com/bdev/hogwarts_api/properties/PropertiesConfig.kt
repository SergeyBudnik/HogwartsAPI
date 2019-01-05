package com.bdev.hogwarts_api.properties

import com.bdev.smart.config.SmartConfig
import com.bdev.smart.config.SmartConfigProperties
import com.bdev.smart.config.data.SmartConfigValue
import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import java.io.File
import kotlin.streams.toList

object PropertiesConfig {
    var config: SmartConfig

    private val instanceConfig: Config
        get() {
            var configHome: String? = System.getProperty("CONFIG_HOME")

            if (configHome == null) {
                configHome = System.getenv("CONFIG_HOME")
            }

            val configFilePath = configHome +
                    File.separator + "municipali-omega" +
                    File.separator + "application.conf"

            return ConfigFactory.parseFile(File(configFilePath))
        }

    init {
        val instanceConfig = instanceConfig

        val env = instanceConfig.getString("env")

        config = SmartConfigProperties.getConfig(env)

        override(config.databaseUrlConfig, instanceConfig)
        override(config.databaseUsernameConfig, instanceConfig)
        override(config.databasePasswordConfig, instanceConfig)
        override(config.securityServiceRootUrlConfig, instanceConfig)

        override(config.vkGroupIdConfig, instanceConfig)
        override(config.vkGroupSecretKeyConfig, instanceConfig)
    }

    private fun <T> override(value: SmartConfigValue<T>, instanceConfig: Config) {
        val suitableValue = findSuitableInstanceConfigProperty(
                instanceConfig,
                value.name
        ) as T

        value.override(suitableValue)
    }

    private fun findSuitableInstanceConfigProperty(
            instanceConfig: Config,
            key: String
    ): Any {
        val values = instanceConfig
                .entrySet()
                .stream()
                .filter { e -> key == normalizeInstanceConfigPropertyKey(e.key) }
                .map { e -> instanceConfig.getAnyRef(e.key) }
                .toList()

        return when {
            values.isEmpty() -> throw RuntimeException("Unable to find property with key: '$key'")
            values.size > 1 -> throw RuntimeException("Multiple suitable properties with key: '$key'")
            else -> {
                val value = values[0]

                value as? Int ?: value
            }
        }
    }

    private fun normalizeInstanceConfigPropertyKey(key: String): String {
        val sb = StringBuilder()

        var shouldMakeUpper = false

        for (ch in key.toCharArray()) {
            if (ch == '.') {
                shouldMakeUpper = true
            } else {
                sb.append(if (shouldMakeUpper) Character.toUpperCase(ch) else ch)
                shouldMakeUpper = false
            }
        }

        return sb.toString()
    }
}
