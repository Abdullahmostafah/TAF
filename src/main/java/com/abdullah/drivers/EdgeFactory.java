package com.abdullah.drivers;

import com.abdullah.utils.logs.LogsManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;

public class EdgeFactory extends AbstractDriver {


    private EdgeOptions getOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");
        if (executionType.equalsIgnoreCase("LocalHeadless") ||
                executionType.equalsIgnoreCase("Remote")) {
            options.addArguments("--headless");
        }
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.setAcceptInsecureCerts(true);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        return options;
    }

    @Override
    public WebDriver createDriver() {
        if (executionType.equalsIgnoreCase("LocalHeadless") ||
                executionType.equalsIgnoreCase("Local")) {
            return new EdgeDriver(getOptions());
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
