package com.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;
import java.time.Duration;



public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait; 

    private By addToCartButton = By.id("a-autoid-1-announce"); 
    private By firstProductPrice = By.xpath("(//span[@class='a-price']//span[@class='a-offscreen'])[1]");
    private String storedPrice;
    private By cartGoBtn = By.id("nav-cart-text-container");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void captureFirstProductPrice() {
        try {
            WebElement priceElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstProductPrice)
            );
            storedPrice = priceElement.getText().trim(); 
            System.out.println("Preço capturado: " + storedPrice);
        } catch (Exception e) {
            System.out.println("Erro ao capturar o preço do produto: " + e.getMessage());
        }
    }
    public String getStoredPrice() {
        return storedPrice;
    }

    public void addToCart() {
        try{
            WebElement addToCartButtonElemen = driver.findElement(addToCartButton);
            if(addToCartButtonElemen != null){
                System.out.println("✅ ACHEI btn!");
                addToCartButtonElemen.click();
            } else {
                System.out.println("❌ NÃO ACHEI LOCAL!");
            }
        } catch (NoSuchElementException e) {
            System.out.println("⚠️ O elemento não foi encontrado: " + e.getMessage());
        }
    }

    public void cartGo(){
        driver.findElement(cartGoBtn).click();
    }
}
