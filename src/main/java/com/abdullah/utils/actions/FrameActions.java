package com.abdullah.utils.actions;

import com.abdullah.utils.WaitManager;
import com.abdullah.utils.logs.LogsManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FrameActions {
    private final WebDriver driver;
    private final WaitManager waitManager;

    public FrameActions(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
        LogsManager.info("Initialized FrameActions for driver: " + driver);
    }

    // Switch to a frame by index
    public void switchToFrame(int index) {
        LogsManager.info("Attempting to switch to frame by index: " + index);
        waitManager.fluentWait().until(d -> {
            try {
                d.switchTo().frame(index);
                LogsManager.info("Successfully switched to frame by index: " + index);
                return true;
            } catch (Exception e) {
                LogsManager.error("Failed to switch to frame by index " + index + ": " + e.getMessage());
                return false;
            }
        });
    }

    // Switch to a frame by name or ID
    public void switchToFrame(String nameOrId) {
        LogsManager.info("Attempting to switch to frame by name/ID: " + nameOrId);
        waitManager.fluentWait().until(d -> {
            try {
                d.switchTo().frame(nameOrId);
                LogsManager.info("Successfully switched to frame by name/ID: " + nameOrId);
                return true;
            } catch (Exception e) {
                LogsManager.error("Failed to switch to frame by name/ID " + nameOrId + ": " + e.getMessage());
                return false;
            }
        });
    }

    // Switch to a frame by WebElement
    public void switchToFrame(By frameLocator) {
        waitManager.fluentWait().until(d -> {
            try {
                d.switchTo().frame(d.findElement(frameLocator));
                LogsManager.info("Switched to frame located by: " + frameLocator);
                return true;
            } catch (Exception e) {
                LogsManager.error("Failed to switch to frame: " + frameLocator);
                return false;
            }
        });
    }

    // Switch back to the default content
    public void switchToDefaultContent() {
        LogsManager.info("Attempting to switch to default content");
        waitManager.fluentWait().until(d -> {
            try {
                d.switchTo().defaultContent();
                LogsManager.info("Successfully switched to default content");
                return true;
            } catch (Exception e) {
                LogsManager.error("Failed to switch to default content: " + e.getMessage());
                return false;
            }
        });
    }

    // Switch to parent frame
    public void switchToParentFrame() {
        LogsManager.info("Attempting to switch to parent frame");
        waitManager.fluentWait().until(d -> {
            try {
                d.switchTo().parentFrame();
                LogsManager.info("Successfully switched to parent frame");
                return true;
            } catch (Exception e) {
                LogsManager.error("Failed to switch to parent frame: " + e.getMessage());
                return false;
            }
        });
    }

    // Get current frame information (new method)
    public String getCurrentFrameInfo() {
        try {
            // This will throw exception if we're in default content
            String frameInfo = driver.switchTo().activeElement().toString();
            LogsManager.info("Current frame info: " + frameInfo);
            return frameInfo;
        } catch (Exception e) {
            LogsManager.info("Currently in default content");
            return "default-content";
        }
    }

}