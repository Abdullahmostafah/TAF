package com.abdullah.report;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.lang.System.getProperty;

public class AllureConstants {
    //Paths > final - static
    public static final Path USER_DIR = Paths.get(getProperty("user.dir"), File.separator);  // 3 usages
    public static final Path USER_HOME = Paths.get(getProperty("user.home"), File.separator);  // 1 usage
    public static final Path RESULTS_FOLDER = Paths.get(String.valueOf(USER_DIR), "test-output", "allure-results", File.separator);  // 1 usage
    public static final Path RESULTS_HISTORY_FOLDER = Paths.get(RESULTS_FOLDER.toString(), "history", File.separator);  // no usages
    public static final Path REPORT_PATH = Paths.get(String.valueOf(USER_DIR), "test-output", "reports", File.separator);  // no usages
    public static final Path FULL_REPORT_PATH = Paths.get(String.valueOf(USER_DIR), "test-output", "full-report", File.separator);  // 1 usage
    public static final Path HISTORY_FOLDER = Paths.get(FULL_REPORT_PATH.toString(), "history", File.separator);  // no usages
    //Strings > final - static
    public static final String INDEX_HTML = "index.html";  // no usages
    public static final String REPORT_PREFIX = "AllureReport_";  // no usages
    public static final String REPORT_EXTENSION = ".html";  // no usages
    public static final String ALLURE_ZIP_BASE_URL = "https://repo.maven.apache.org/maven2/io/gameta/allure/allure-commandline/";  // no usages

    public static final Path EXTRACTION_DIR = Paths.get(String.valueOf(USER_HOME), ".m2/repository/allure", File.separator);  // no usages
}
