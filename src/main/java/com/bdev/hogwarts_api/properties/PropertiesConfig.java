package com.bdev.hogwarts_api.properties;

import com.bdev.smart.config.SmartConfig;
import com.bdev.smart.config.SmartConfigProperties;
import com.bdev.smart.config.data.SmartConfigValue;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class PropertiesConfig {
    public static SmartConfig config;

    static {
        Config instanceConfig = getInstanceConfig();

        String env = instanceConfig.getString("env");

        config = SmartConfigProperties.getConfig(env);

        override(config.getDatabaseUrlConfig(), instanceConfig);
        override(config.getDatabaseUsernameConfig(), instanceConfig);
        override(config.getDatabasePasswordConfig(), instanceConfig);
        override(config.getSecurityServiceRootUrlConfig(), instanceConfig);
    }

    private static void override(SmartConfigValue value, Config instanceConfig) {
        Object suitableValue = findSuitableInstanceConfigProperty(
                instanceConfig,
                value.getName()
        );

        value.override(suitableValue);
    }

    private static Object findSuitableInstanceConfigProperty(
            Config instanceConfig,
            String key
    ) {
        List<Object> values = instanceConfig.entrySet()
                .stream()
                .filter(e -> key.equals(normalizeInstanceConfigPropertyKey(e.getKey())))
                .map(e -> instanceConfig.getAnyRef(e.getKey()))
                .collect(Collectors.toList());

        if (values.size() == 0) {
            throw new RuntimeException("Unable to find property with key: '" + key + "'");
        } else if (values.size() > 1) {
            throw new RuntimeException("Multiple suitable properties with key: '" + key + "'");
        } else {
            Object value = values.get(0);

            if (value instanceof Integer) {
                return value;
            } else {
                return value;
            }
        }
    }

    private static String normalizeInstanceConfigPropertyKey(String key) {
        StringBuilder sb = new StringBuilder();

        boolean shouldMakeUpper = false;

        for (char ch : key.toCharArray()) {
            if (ch == '.') {
                shouldMakeUpper = true;
            } else {
                sb.append(shouldMakeUpper ? Character.toUpperCase(ch) : ch);
                shouldMakeUpper = false;
            }
        }

        return sb.toString();
    }

    private static Config getInstanceConfig() {
        String configHome = System.getProperty("CONFIG_HOME");

        if (configHome == null) {
            configHome = System.getenv("CONFIG_HOME");
        }

        String configFilePath =
                configHome +
                File.separator + "municipali-omega" +
                File.separator + "application.conf";

        return ConfigFactory.parseFile(new File(configFilePath));
    }
}
