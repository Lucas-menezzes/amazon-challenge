package com.amazon.test.steps;

import com.amazon.base.DriverManager;
import com.amazon.pages.AmazonHomePage;
import com.amazon.pages.ProductPage;
import com.amazon.pages.CartPage;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.WebDriver;

public class CartSteps {
    private WebDriver driver;
    private AmazonHomePage amazonHomePage;
    private ProductPage productPage;
    private CartPage cartPage;
    private CommonSteps commonSteps;

    public CartSteps() {
        this.driver = DriverManager.getDriver();
        this.amazonHomePage = new AmazonHomePage(driver);
        this.productPage = new ProductPage(driver);
        this.cartPage = new CartPage(driver);
        this.commonSteps = new CommonSteps();
    }

    @Given("que o usuario esta na pagina inicial da Amazon")
    public void usuario_na_pagina_inicial() {
        commonSteps.usuario_na_pagina_inicial();
    }

    @When("o usuario pesquisa por {string}")
    public void usuario_pesquisa_por(String produto) {
        amazonHomePage.searchFor(produto);
    }
    @When("seleciona primeiro produto")
    public void clica_no_primeiro_resultado() {
        amazonHomePage.submitSearch();
    }

    @When("valor do produto correto com o selecionado")
    public void verifica_valor_produto(){
        productPage.captureFirstProductPrice();
    }

    @When("adiciona o produto ao carrinho")
    public void adiciona_ao_carrinho() {
        productPage.addToCart();
        productPage.cartGo();
    }

    @Then("o produto deve aparecer no carrinho corretamente")
    public void verifica_produto_no_carrinho() {
        String capturedPrice = productPage.getStoredPrice(); 
        assertTrue(cartPage.isProductInCart(capturedPrice), "O preço do produto no carrinho não corresponde ao selecionado!");
    }
}
