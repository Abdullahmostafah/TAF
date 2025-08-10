package com.abdullah.utils;

import com.abdullah.dataReader.PropertyReader;

public class OSUtils {
    public static OS getCurrentOS() {
        String osName = PropertyReader.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            return OS.WINDOWS;
        } else if (osName.contains("mac")) {
            return OS.MAC;
        } else if (osName.contains("nix") || osName.contains("nux")) {
            return OS.LINUX;
        } else {
            return OS.OTHER;
        }
    }

    public enum OS {
        WINDOWS, MAC, LINUX, OTHER
    }
}
