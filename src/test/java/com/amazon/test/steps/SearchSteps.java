package com.amazon.test.steps;

import com.amazon.pages.AmazonHomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.Duration;
import com.amazon.base.DriverManager;


public class SearchSteps {
    private WebDriver driver;
    private AmazonHomePage amazonHomePage;
    private CommonSteps commonSteps;

    public SearchSteps() {
        this.driver = DriverManager.getDriver();
        this.amazonHomePage = new AmazonHomePage(driver);
        this.commonSteps = new CommonSteps();
    }
    
    public void usuario_na_pagina_inicial() {
        commonSteps.usuario_na_pagina_inicial();
    }

    @When("o usuario digita {string} na barra de pesquisa")
    public void o_usuario_digita_na_barra_de_pesquisa(String term) {
        amazonHomePage.searchFor(term);
        System.out.println("termo pesquisado: " + term);
    }

    @Then("o sistema deve exibir sugestoes relacionadas ao termo digitado")
    public void sistema_exibe_sugestoes() {
        assertTrue(amazonHomePage.isSuggestionsContainerVisible(), "Nenhuma sugestão");
        int numSugestoes = amazonHomePage.getNumberOfSuggestions();
        System.out.println("Numero de sugestões: " + numSugestoes);

        assertTrue(numSugestoes > 0, "Nenhuma sugestao encontrada!");
    }

    @Then("o sistema deve nao exibir sugestoes relacionadas ao termo digitado")
    public void sistema_nao_exibe_sugestoes() {
        int numSugestoes = amazonHomePage.getNumberOfSuggestions();
        System.out.println("Numero de sugestoes: " + numSugestoes);
        assertTrue(numSugestoes == 0, "Nao existe sugestoes!");
    }
}