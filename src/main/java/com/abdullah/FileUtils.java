package com.abdullah;

import com.abdullah.dataReader.PropertyReader;
import com.abdullah.utils.logs.LogsManager;

import java.io.File;

public class FileUtils {
    private static final String USER_DIR = PropertyReader.getProperty("user.dir") + File.separator;

    private FileUtils() {
        // Private constructor to prevent instantiation
    }

    // Renaming
    public static void renameFile(String oldName, String newName) {
        try {
            File oldFile = new File(PropertyReader.getProperty(USER_DIR + oldName));
            File newFile = new File(PropertyReader.getProperty(USER_DIR + newName));
            if (oldFile.renameTo(newFile)) {
                LogsManager.info("File renamed from " + oldName + " to " + newName);
            } else {
                LogsManager.error("Failed to rename file from " + oldName + " to " + newName);
            }
        } catch (Exception e) {
            LogsManager.error("Error renaming file: " + oldName, e.getMessage());
        }
    }


    // Creating directories
    public static void createDirectory(String path) {
        try {
            File file = new File(PropertyReader.getProperty(USER_DIR + path));
            if (!file.exists()) {
                {
                    file.mkdirs();
                    LogsManager.info("Directory created: " + path);
                }
            }
        } catch (Exception e) {
            LogsManager.error("Error creating directory: " + path, e.getMessage());
        }
    }
        // Cleaning directories

        public static void cleanDirectory (File file){
            try {
                org.apache.commons.io.FileUtils.deleteDirectory(file);
            } catch (Exception e) {
                LogsManager.error("Error cleaning directory: " + file.getAbsolutePath(), e.getMessage());
            }
        }

    }
}
