 package com.amazon.test.steps;

import com.amazon.base.DriverManager;
import com.amazon.pages.AmazonHomePage;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommonSteps {
    private WebDriver driver;
    private AmazonHomePage amazonHomePage;

    public CommonSteps() {
        this.driver = DriverManager.getDriver();
        this.amazonHomePage = new AmazonHomePage(driver);
    }

    public void usuario_na_pagina_inicial() {
        amazonHomePage.open();
        assertTrue(amazonHomePage.isPageLoaded(), "A página inicial da Amazon não carregou corretamente!");
    }
}