package com.abdullah.dataReader;

import com.abdullah.utils.logs.LogsManager;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Collection;
import java.util.Properties;

public class PropertyReader {
    public static Properties loadProperties() {
        try {
            Properties properties = new Properties();
            Collection<File> propertiesFiles;
            propertiesFiles = FileUtils.listFiles(new File("src/main/resources"), new String[]{"properties"}, true);
            propertiesFiles.forEach(file -> {
                try {
                    LogsManager.info("Loading properties file: " + file.getName());
                    properties.load(FileUtils.openInputStream(file));
                } catch (Exception e) {
                    LogsManager.error("Error loading properties file " + file.getName() + ": " + e.getMessage());
                }
                properties.putAll(System.getProperties());
                System.getProperties().putAll(properties);
            });
            LogsManager.info("Successfully loaded all properties files");
            return properties;

        } catch (Exception e) {
            LogsManager.error("Error loading properties: " + e.getMessage());
            return null;
        }
    }

    public static String getProperty(String key) {
        try {
            String value = System.getProperty(key);
            LogsManager.info("Retrieved property: " + key + "=" + value);
            return value;
        } catch (Exception e) {
            LogsManager.error("Error retrieving property " + key + ": " + e.getMessage());
            return "";
        }
    }
}