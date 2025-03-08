package com.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import java.time.Duration;



public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait; 

    private By addToCartButton = By.id("a-autoid-1-announce"); 
    private By firstProductPrice = By.xpath("(//span[@class='a-price']//span[@class='a-offscreen'])[1]/following-sibling::span[@aria-hidden='true']");//("(//span[@class='a-price']//span[@class='a-offscreen'])[1]");
    private String storedPrice;
    private By cartGoBtn = By.id("nav-cart-text-container");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void captureFirstProductPrice() {

        try {
            if (driver.findElements(firstProductPrice).size() > 0) {

                WebElement priceElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(firstProductPrice)
                );
                storedPrice = priceElement.getText().trim();

            } else {
                System.out.println("preço não encontrado na página. Verifique se está correto.");
            }
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        } catch (Exception e) {
            System.out.println("Erro ao capturar o preço do produto: " + e.getMessage());
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
    }

    public String getStoredPrice() {
        return storedPrice;
    }

    public void addToCart() {
        try{
            WebElement addToCartButtonElemen = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(addToCartButton)
                );
            if(addToCartButtonElemen != null){
                addToCartButtonElemen.click();
            } else {
                System.out.println("NÃO ACHEI Button!");
            }
        } catch (NoSuchElementException e) {
            System.out.println("O elemento não foi encontrado: " + e.getMessage());
        }
    }

    public void cartGo(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(cartGoBtn).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}
