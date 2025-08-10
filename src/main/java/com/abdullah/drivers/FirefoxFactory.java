package com.abdullah.drivers;

import com.abdullah.utils.logs.LogsManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;

public class FirefoxFactory extends AbstractDriver {


    private FirefoxOptions getOptions() {
        FirefoxOptions options = new FirefoxOptions();
        if (executionType.equalsIgnoreCase("LocalHeadless") ||
                executionType.equalsIgnoreCase("Remote")) {
            options.addArguments("--headless");
        }
        options.addArguments("--start-maximized");
        options.addArguments("--disable-gpu");
        options.addPreference("dom.webnotifications.enabled", false);
        options.addPreference("dom.disable_beforeunload", true);
        options.addPreference("browser.popups.showPopupBlocker", true);
        options.addPreference("extensions.update.enabled", false);
        options.setAcceptInsecureCerts(true);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        return options;
    }

    @Override
    public WebDriver createDriver() {
        if (executionType.equalsIgnoreCase("LocalHeadless") ||
                executionType.equalsIgnoreCase("Local")) {
            return new FirefoxDriver(getOptions());
        } else if (executionType.equalsIgnoreCase("Remote")) {

            try {
                return new RemoteWebDriver(
                        new URI("https://" + remoteHost + ":" + remotePort + "/wd/hub").toURL(), getOptions()
                );
            } catch (Exception e) {
                LogsManager.error("Failed to create RemoteWebDriver: " + e.getMessage());
                throw new RuntimeException("Failed to create remoteWebDriver", e);
            }
        } else {
            LogsManager.error("Unsupported execution type: " + executionType);
            throw new IllegalArgumentException("Unsupported execution type: " + executionType);
        }
    }
}