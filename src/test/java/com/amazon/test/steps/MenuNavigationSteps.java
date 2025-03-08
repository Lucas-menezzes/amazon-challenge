package com.amazon.test.steps;

import com.amazon.base.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import com.amazon.pages.AmazonHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Dimension;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;


public class MenuNavigationSteps {
    private WebDriver driver;
    private AmazonHomePage amazonHomePage;

    public MenuNavigationSteps() {
        this.driver = DriverManager.getDriver();
        this.amazonHomePage = new AmazonHomePage(driver);
    }
    private long startTime;

    @Given("que o usuario acessa o site da Amazon no modo {string}")
    public void usuario_acessa_site_no_modo(String device) {
        amazonHomePage.open();
        switch (device.toLowerCase()) {
            case "desktop":
                DriverManager.getDriver().manage().window().setSize(new Dimension(1366, 768));
                break;
            case "tablet":
                DriverManager.getDriver().manage().window().setSize(new Dimension(768, 1024));
                break;
            case "mobile":
                DriverManager.getDriver().manage().window().setSize(new Dimension(340, 812));
                break;
            default:
                throw new IllegalArgumentException("Dispositivo não reconhecido: " + device);
        }
        assertTrue(amazonHomePage.isPageLoaded(), "A página inicial da Amazon não carregou corretamente!");
        System.out.println("Página carregada corretamente no modo: " + device);
    }

    @Then("o menu de navegacao deve estar visivel ou recolhido para {string}")
    public void menu_visivel_ou_recolhido(String device) {
        boolean isMenuVisible = amazonHomePage.isMenuVisible();
        System.out.println("Verificação do menu no modo " + device + ": " + isMenuVisible);
        assertTrue(isMenuVisible, "O menu não está visível nem recolhido corretamente!");
    }   

    @When("icone do menu hambuguer deve estar visivel")
    public void icone_menu_hamburguer_visivel() {
        assertTrue(amazonHomePage.isHamburgerMenuVisible(), "O ícone do menu hamburguer não está visível!");
    }

    @When("usuario clica no icone do menu")
    public void usuario_clica_no_menu() {
        amazonHomePage.clickHamburgerMenu();
    }

    @Then("menu de navegacao deve ser expandido")
    public void menu_expandido() {
        assertTrue(amazonHomePage.isMenuExpanded(), "O menu não expandiu corretamente!");
    }

    @When("o usuario clica fora do menu")
    public void usuario_clica_fechar() {
        amazonHomePage.click_close();
    }

    @Then("o menu de navegacao deve ser recolhido")
    public void menu_deve_ser_recolhido() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        boolean menuFechou = wait.until(ExpectedConditions.invisibilityOfElementLocated(amazonHomePage.getMenuContent()));
        assertTrue(menuFechou, "O menu não foi recolhido corretamente!");
    }

    @When("a pagina e carregada completamente")
    public void pagina_carregada_completamente() {
        startTime = System.currentTimeMillis();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(webDriver -> 
            ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
            );
        System.out.println("Página carregada completamente.");
    }

    @Then("o tempo de carregamento deve ser inferior a {int} segundos")
    public void tempo_carregamento_deve_ser_inferior_a(int maxTempoSegundos) {
        long endTime = System.currentTimeMillis();
        long tempoCarregamento = (endTime - startTime) / 1000; 
        System.out.println("Tempo de carregamento: " + tempoCarregamento + " segundos");

        assertTrue(tempoCarregamento <= maxTempoSegundos,
            "A página demorou mais que " + maxTempoSegundos + " segundos para carregar!");
    }
}
