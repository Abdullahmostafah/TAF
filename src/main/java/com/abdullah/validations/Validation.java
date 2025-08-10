package com.abdullah.validations;

import com.abdullah.utils.logs.LogsManager;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

//Soft Assertion
public class Validation extends BaseAssertion {
    private static SoftAssert softAssert = new SoftAssert();
    private static boolean used = false; // Flag to track if softAssert has been used

    protected Validation(WebDriver driver) {
        super(driver); // Assuming null for WebDriver, replace with actual driver instance if needed
    }

    public static void assertAll() {
        if (!used) return; // If softAssert has not been used, do nothing
        try {
            softAssert.assertAll();
        } catch (AssertionError e) {
            // Handle the exception if needed
            LogsManager.error("Soft assertion failed: " + e.getMessage());
            throw e; // Re-throw the exception to fail the test
        } finally {
            softAssert = new SoftAssert(); // Reset for next test
        }
    }


    @Override
    protected void assertTrue(boolean condition, String message) {

        used = true; // Mark that softAssert has been used
        softAssert.assertTrue(condition, message);

    }

    @Override
    protected void assertFalse(boolean condition, String message) {
        used = true; // Mark that softAssert has been used
        softAssert.assertFalse(condition, message);

    }

    @Override
    protected void assertEquals(String actual, String expected, String message) {
        used = true; // Mark that softAssert has been used
        softAssert.assertEquals(actual, expected, message);

    }


}
