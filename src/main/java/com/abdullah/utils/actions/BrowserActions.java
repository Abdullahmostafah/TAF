package com.abdullah.utils.actions;

import com.abdullah.utils.logs.LogsManager;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.List;

public class BrowserActions {
    private final WebDriver driver;

    public BrowserActions(WebDriver driver) {
        this.driver = driver;
        LogsManager.info("Initializing BrowserActions for driver: " + driver);
    }

    // Window Size Actions
    public void fullscreenWindow() {
        driver.manage().window().fullscreen();
        LogsManager.info("Set window to fullscreen mode");
    }

    public void maximizeWindow() {
        driver.manage().window().maximize();
        LogsManager.info("Maximized browser window");
    }

    public void setWindowSize(int width, int height) {
        driver.manage().window().setSize(new Dimension(width, height));
        LogsManager.info("Set window size to: " + width + "x" + height);
    }

    public Dimension getWindowSize() {
        Dimension size = driver.manage().window().getSize();
        LogsManager.info("Current window size: " + size.getWidth() + "x" + size.getHeight());
        return size;
    }

    public String getCurrentUrl() {
        String currentUrl = driver.getCurrentUrl();
        LogsManager.info("Current URL: " + currentUrl);
        return currentUrl;
    }

    public void navigateTo(String url) {
        LogsManager.info("Navigating to URL: " + url);
        driver.get(url);
        LogsManager.info("Successfully navigated to URL: " + url);
    }

    public void refreshPage() {
        LogsManager.info("Refreshing current page");
        driver.navigate().refresh();
        LogsManager.info("Page refreshed");
    }

    public void closeCurrentWindow() {
        LogsManager.info("Closing current window");
        driver.close();
        LogsManager.info("Window closed");
    }

    public void openNewWindow() {
        LogsManager.info("Opening new window");
        driver.switchTo().newWindow(WindowType.WINDOW);
        LogsManager.info("New window opened");
    }

    public void openNewTab() {
        LogsManager.info("Opening new tab");
        driver.switchTo().newWindow(WindowType.TAB);
        LogsManager.info("New tab opened");
    }

    // Tab Management
    public void switchToTab(int index) {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        if (index < 0 || index >= tabs.size()) {
            String errorMsg = "Invalid tab index: " + index + ". Total tabs: " + tabs.size();
            LogsManager.error(errorMsg);
            throw new IndexOutOfBoundsException(errorMsg);
        }
        LogsManager.info("Switching to tab index: " + index);
        driver.switchTo().window(tabs.get(index));
        LogsManager.info("Switched to tab index: " + index);
    }

    public int getOpenTabCount() {
        int count = driver.getWindowHandles().size();
        LogsManager.info("Current open tabs count: " + count);
        return count;
    }

    // Cookies & Storage Actions
    public void addCookie(Cookie cookie) {
        LogsManager.info("Adding cookie: " + cookie);
        driver.manage().addCookie(cookie);
        LogsManager.info("Cookie added");
    }

    public Cookie getCookie(String name) {
        Cookie cookie = driver.manage().getCookieNamed(name);
        LogsManager.info("Retrieved cookie '" + name + "': " + cookie);
        return cookie;
    }

    public void deleteAllCookies() {
        LogsManager.info("Deleting all cookies");
        driver.manage().deleteAllCookies();
        LogsManager.info("All cookies deleted");
    }

    public void clearLocalStorage() {
        LogsManager.info("Clearing local storage");
        ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
        LogsManager.info("Local storage cleared");
    }

    public void clearSessionStorage() {
        LogsManager.info("Clearing session storage");
        ((JavascriptExecutor) driver).executeScript("window.sessionStorage.clear();");
        LogsManager.info("Session storage cleared");
    }

    // Page State & JavaScript Actions
    public Object executeJS(String script, Object... args) {
        LogsManager.info("Executing JavaScript: " + script);
        Object result = ((JavascriptExecutor) driver).executeScript(script, args);
        LogsManager.info("JavaScript executed successfully");
        return result;
    }

    public boolean isPageLoaded() {
        boolean loaded = ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete");
        LogsManager.info("Page loaded status: " + loaded);
        return loaded;
    }

    public void scrollToElement(WebElement element) {
        LogsManager.info("Scrolling to element: " + element);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", element);
        LogsManager.info("Scrolled to element");
    }

    public void scrollToBottom() {
        LogsManager.info("Scrolling to bottom of page");
        ((JavascriptExecutor) driver).executeScript(
                "window.scrollTo(0, document.body.scrollHeight)");
        LogsManager.info("Scrolled to bottom");
    }

    // Browser Information
    public String getBrowserName() {
        String name = ((RemoteWebDriver) driver).getCapabilities().getBrowserName();
        LogsManager.info("Browser name: " + name);
        return name;
    }

    public String getBrowserVersion() {
        String version = ((RemoteWebDriver) driver).getCapabilities().getBrowserVersion();
        LogsManager.info("Browser version: " + version);
        return version;
    }
}