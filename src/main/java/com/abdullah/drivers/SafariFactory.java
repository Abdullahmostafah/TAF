package com.abdullah.drivers;

import com.abdullah.utils.logs.LogsManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.URI;

public class SafariFactory extends AbstractDriver {

    public SafariOptions getOptions() {
        SafariOptions options = new SafariOptions();
        options.setAcceptInsecureCerts(true);
        options.setAutomaticInspection(false); // Disable automatic inspector
        options.setAutomaticProfiling(false);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        if (executionType.equalsIgnoreCase("LocalHeadless") ||
                executionType.equalsIgnoreCase("Remote")) {
            options.setCapability("safari:headless", true); // Safari's headless equivalent
        }
        return options;
    }

    @Override
    public WebDriver createDriver() {
        if (executionType.equalsIgnoreCase("LocalHeadless") ||
                executionType.equalsIgnoreCase("Local")) {
            return new SafariDriver(getOptions());
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
