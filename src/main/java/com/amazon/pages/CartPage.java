package com.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By cartItem = By.cssSelector(".sc-list-item-content");
    private By cartItemPrice = By.cssSelector(".sc-price");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isProductInCart(String expectedPrice) {
        try {
            // Aguarda até que o item esteja visível no carrinho
            wait.until(ExpectedConditions.visibilityOfElementLocated(cartItem));

            // Captura o preço do produto dentro do carrinho
            WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cartItemPrice));
            String cartPrice = priceElement.getText().trim(); 

            System.out.println("Preço capturado no carrinho: " + cartPrice);
            System.out.println("Preço esperado: " + expectedPrice);

            // Retorna verdadeiro se os preços forem iguais
            return cartPrice.equals(expectedPrice);
        } catch (Exception e) {
            System.out.println("Erro ao validar o produto no carrinho: " + e.getMessage());
            return false;
        }
    }
}
