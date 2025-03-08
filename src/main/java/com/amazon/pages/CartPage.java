package com.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;
import java.time.Duration;


public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By cartItem = By.id("activeCartViewForm");
    private By cartItemPrice = By.xpath("//span[@id='sc-subtotal-amount-buybox']/span");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isProductInCart(String expectedPrice) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(cartItem));

            WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cartItemPrice));
            String cartPrice = normalizePrice(priceElement.getText().trim());

            String formattedCartPrice = normalizePrice(cartPrice);
            String formattedExpectedPrice = normalizePrice(expectedPrice);

            System.out.println("Preço capturado no carrinho: " + formattedCartPrice);
            System.out.println("Preço esperado: " + formattedExpectedPrice);

            if (formattedCartPrice.equals(formattedExpectedPrice)) {
                System.out.println("O preço do produto no carrinho está correto!");
                return true;
            } else {
                System.out.println("O preço do produto no carrinho NÃO corresponde ao esperado!");
                return false;
            }

        } catch (NoSuchElementException e) {
            System.out.println("O preço do produto no carrinho não foi encontrado!");
        } catch (Exception e) {
            System.out.println("Erro inesperado ao validar o preço no carrinho: " + e.getMessage());
        }
        return false;
    }

    private String normalizePrice(String price) {
        if (price == null || price.isEmpty()) {
            return "";
        }
        price = price.replaceAll("[^0-9,]", "").trim();
        price = price.replace(",", ".");
        return price;
    }
}


