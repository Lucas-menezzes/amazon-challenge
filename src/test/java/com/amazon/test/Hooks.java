package com.amazon.test.steps;

import com.amazon.base.DriverManager;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.AfterStep;
import com.amazon.base.ScreenshotUtil;
import io.cucumber.java.Scenario;

public class Hooks {

    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {

        driver = DriverManager.getDriver();
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        driver.navigate().refresh();
    }

    @AfterStep
    public void takeScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed()) {
            driver = DriverManager.getDriver();
            ScreenshotUtil.captureScreenshot(driver, scenario.getName());
        }
    }

    @AfterAll
    public static void tearDown() {
        DriverManager.quitDriver();
    }
}