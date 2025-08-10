package com.abdullah.utils.actions;

import com.abdullah.utils.WaitManager;
import com.abdullah.utils.logs.LogsManager;
import org.openqa.selenium.WebDriver;

public class AlertActions {
    private final WebDriver driver;
    private final WaitManager waitManager;

    public AlertActions(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
        LogsManager.info("Initialized AlertActions for driver: " + driver);
    }

    // Check if alert is present
    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            LogsManager.info("Alert is present");
            return true;
        } catch (Exception e) {
            LogsManager.info("No alert present");
            return false;
        }
    }

    // Accept an alert
    public void acceptAlert() {
        LogsManager.info("Attempting to accept alert");
        waitManager.fluentWait().until(d -> {
            try {
                d.switchTo().alert().accept();
                LogsManager.info("Alert accepted successfully");
                return true;
            } catch (Exception e) {
                LogsManager.error("Failed to accept alert: " + e.getMessage());
                return false;
            }
        });
    }

    // Dismiss an alert
    public void dismissAlert() {
        LogsManager.info("Attempting to dismiss alert");
        waitManager.fluentWait().until(d -> {
            try {
                d.switchTo().alert().dismiss();
                LogsManager.info("Alert dismissed successfully");
                return true;
            } catch (Exception e) {
                LogsManager.error("Failed to dismiss alert: " + e.getMessage());
                return false;
            }
        });
    }

    // Get alert text
    public String getAlertText() {
        LogsManager.info("Attempting to get alert text");
        String text = waitManager.fluentWait().until(d -> {
            try {
                String alertText = d.switchTo().alert().getText();
                return !alertText.isEmpty() ? alertText : null;
            } catch (Exception e) {
                LogsManager.error("Failed to get alert text: " + e.getMessage());
                return null;
            }
        });
        LogsManager.info("Alert text: " + text);
        return text;
    }

    // Send text to an alert prompt
    public void sendTextToAlert(String text) {
        LogsManager.info("Attempting to send text to alert: " + text);
        waitManager.fluentWait().until(d -> {
            try {
                d.switchTo().alert().sendKeys(text);
                LogsManager.info("Text sent to alert successfully");
                return true;
            } catch (Exception e) {
                LogsManager.error("Failed to send text to alert: " + e.getMessage());
                return false;
            }
        });
    }

    // Accept alert if present (safe method)
    public void acceptAlertIfPresent() {
        if (isAlertPresent()) {
            acceptAlert();
        } else {
            LogsManager.info("No alert present to accept");
        }
    }

    // Dismiss alert if present (safe method)
    public void dismissAlertIfPresent() {
        if (isAlertPresent()) {
            dismissAlert();
        } else {
            LogsManager.info("No alert present to dismiss");
        }
    }
}