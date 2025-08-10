package com.abdullah.utils.actions;

import com.abdullah.utils.WaitManager;
import com.abdullah.utils.logs.LogsManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;

public class ElementActions {
    private final WebDriver driver;
    private final WaitManager waitManager;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
        LogsManager.info("Initialized ElementActions for driver: " + driver);
    }

    // Click on an element
    public void clickOn(By locator) {
        LogsManager.info("Attempting to click on element: " + locator);
        waitManager.fluentWait().until(d -> {
            try {
                WebElement element = d.findElement(locator);
                scrollToElementJS(locator);
                element.click();
                LogsManager.info("Successfully clicked on element: " + locator);
                return true;
            } catch (Exception e) {
                LogsManager.error("Failed to click on element " + locator + ": " + e.getMessage());
                return false;
            }
        });
    }

    // Send text to an element
    public void sendText(By locator, String text) {
        LogsManager.info("Attempting to send text to element: " + locator + " with text: " + text);
        waitManager.fluentWait().until(d -> {
            try {
                WebElement element = d.findElement(locator);
                scrollToElementJS(locator);
                element.clear();
                element.sendKeys(text);
                LogsManager.info("Successfully sent text to element: " + locator);
                return true;
            } catch (Exception e) {
                LogsManager.error("Failed to send text to element " + locator + ": " + e.getMessage());
                return false;
            }
        });
    }

    // Get text from an element
    public String getText(By locator) {
        LogsManager.info("Attempting to get text from element: " + locator);
        String text = waitManager.fluentWait().until(d -> {
            try {
                WebElement element = d.findElement(locator);
                scrollToElementJS(locator);
                String elementText = element.getText();
                return !elementText.isEmpty() ? elementText : null;
            } catch (Exception e) {
                LogsManager.error("Failed to get text from element " + locator + ": " + e.getMessage());
                return null;
            }
        });
        LogsManager.info("Retrieved text from element " + locator + ": " + text);
        return text;
    }

    // Find an element and return it
    public WebElement findElement(By locator) {
        LogsManager.info("Attempting to find element: " + locator);
        WebElement element = driver.findElement(locator);
        LogsManager.info("Successfully found element: " + locator);
        return element;
    }

    // Scroll to an element using JavaScript
    public void scrollToElementJS(By locator) {
        LogsManager.info("Attempting to scroll to element: " + locator);
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior:'auto',block:'center',inline:'center'});",
                element
        );
        LogsManager.info("Successfully scrolled to element: " + locator);
    }

    // Upload a file to an input element
    public void uploadFile(By locator, String filePath) {
        String fileAbsolutePath = System.getProperty("user.dir") + File.separator + filePath;
        LogsManager.info("Attempting to upload file to element: " + locator + " with path: " + fileAbsolutePath);
        waitManager.fluentWait().until(d -> {
            try {
                WebElement element = d.findElement(locator);
                scrollToElementJS(locator);
                element.sendKeys(fileAbsolutePath);
                LogsManager.info("Successfully uploaded file to element: " + locator);
                return true;
            } catch (Exception e) {
                LogsManager.error("Failed to upload file to element " + locator + ": " + e.getMessage());
                return false;
            }
        });
    }

    // Additional useful element actions
    public boolean isElementDisplayed(By locator) {
        LogsManager.info("Checking if element is displayed: " + locator);
        boolean isDisplayed = waitManager.fluentWait().until(d -> {
            try {
                return d.findElement(locator).isDisplayed();
            } catch (Exception e) {
                return false;
            }
        });
        LogsManager.info("Element " + locator + " display status: " + isDisplayed);
        return isDisplayed;
    }

    public boolean isElementEnabled(By locator) {
        LogsManager.info("Checking if element is enabled: " + locator);
        boolean isEnabled = waitManager.fluentWait().until(d -> {
            try {
                return d.findElement(locator).isEnabled();
            } catch (Exception e) {
                return false;
            }
        });
        LogsManager.info("Element " + locator + " enabled status: " + isEnabled);
        return isEnabled;
    }

    public boolean isElementSelected(By locator) {
        LogsManager.info("Checking if element is selected: " + locator);
        boolean isSelected = waitManager.fluentWait().until(d -> {
            try {
                return d.findElement(locator).isSelected();
            } catch (Exception e) {
                return false;
            }
        });
        LogsManager.info("Element " + locator + " selected status: " + isSelected);
        return isSelected;
    }


    public void clearText(By locator) {
        LogsManager.info("Attempting to clear text from element: " + locator);
        waitManager.fluentWait().until(d -> {
            try {
                d.findElement(locator).clear();
                LogsManager.info("Successfully cleared text from element: " + locator);
                return true;
            } catch (Exception e) {
                LogsManager.error("Failed to clear text from element: " + e.getMessage());
                return false;
            }
        });
    }


}