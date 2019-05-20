package com.etollpay.srpc.service.util;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SysConfig {
    private static Logger log = LoggerFactory.getLogger(SysConfig.class);

    private static PropertiesConfiguration properties;

    private static byte[] lock = new byte[0];

    public static void init() throws ConfigurationException {
        synchronized (lock) {
            if (properties == null) {
                String configTag =  System.getProperty("configuration.tag");
                String configFile =
                        configTag == null || configTag.trim().equals("") ?
                                "config.properties" : "config_" + configTag.trim() + ".properties";
                properties = new PropertiesConfiguration(configFile);
                properties.setReloadingStrategy(new FileChangedReloadingStrategy());
            }
        }
    }

    public static PropertiesConfiguration getProperties() throws ConfigurationException {
        init();
        return properties;
    }


    public static String getString(String key, String defaultValue) {
        try {
            return getProperties().getString(key, defaultValue);
        } catch (ConfigurationException e) {
            log.error(e.getLocalizedMessage(), e);
            return defaultValue;
        }
    }

    public static String getString(String key) {
        return getString(key, null);
    }

    public static int getInt(String key, int defaultValue) {
        try {
            return getProperties().getInt(key, defaultValue);
        } catch (ConfigurationException e) {
            log.error(e.getLocalizedMessage(), e);
            return defaultValue;
        }
    }

    public static int getInt(String key) {
        return getInt(key, 0);
    }

    public static long getLong(String key, long defaultValue) {
        try {
            return getProperties().getLong(key, defaultValue);
        } catch (ConfigurationException e) {
            log.error(e.getLocalizedMessage(), e);
            return defaultValue;
        }
    }

    public static long getLong(String key) {
        return getLong(key, 0);
    }

    public static double getDouble(String key, double defaultValue) {
        try {
            return getProperties().getDouble(key, defaultValue);
        } catch (ConfigurationException e) {
            log.error(e.getLocalizedMessage(), e);
            return defaultValue;
        }
    }

    public static double getDouble(String key) {
        return getDouble(key, 0);
    }

    public static boolean getBool(String key, boolean defaultValue) {
        try {
            return getProperties().getBoolean(key, defaultValue);
        } catch (ConfigurationException e) {
            log.error(e.getLocalizedMessage(), e);
            return defaultValue;
        }
    }

    public static boolean getBool(String key) {
        return getBool(key, false);
    }

    public static String[] getStringArray(String key) {
        try {
            return getProperties().getStringArray(key);
        } catch (ConfigurationException e) {
            log.error(e.getLocalizedMessage(), e);
            return new String[0];
        }
    }
}
