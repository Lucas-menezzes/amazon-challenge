package com.amazon.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;
import java.util.List;
import com.amazon.base.DriverManager;


public class AmazonHomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    private By searchBox = By.id("twotabsearchtextbox");
    private By suggestionsContainer = By.className("left-pane-results-container");
    private By suggestionsList = By.className("s-suggestion");
    private By navigation = By.id("nav-main");
    private By menuHamburger = By.id("nav-hamburger-menu");
    private By menuContent = By.id("hmenu-content");
    private By closeIcon = By.id("hmenu-close-icon");
    private By firstProduct = By.id("sac-suggestion-row-1-cell-1");

    public By getMenuContent() {
        return menuContent;
    }
    public AmazonHomePage(WebDriver driver) {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofMillis(500));
    }

    public void open() {
        driver.get("https://www.amazon.com.br");
    }
    public boolean isPageLoaded() {
        return driver.findElement(searchBox).isDisplayed();
    }

    public void searchFor(String term) {
        WebElement searchField = driver.findElement(searchBox);
        searchField.clear();
        searchField.sendKeys(term);
    }

    public void submitSearch() {    
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    
        int tentativas = 0;
        while (tentativas < 3) {
            try {
                WebElement firstProductElement = wait.until(ExpectedConditions.elementToBeClickable(firstProduct));

                firstProductElement.click();
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("Elemento ficou 'stale', tentando novamente... Tentativa " + (tentativas + 1));
            } catch (NoSuchElementException e) {
                System.out.println("O elemento não foi encontrado: " + e.getMessage());
                break;
            }
            tentativas++;
        }
    }

    public boolean isSuggestionsContainerVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(suggestionsContainer)).isDisplayed();
    }

    public List<WebElement> getSuggestions() {
        return driver.findElements(suggestionsList);
    }

    public int getNumberOfSuggestions() {
        return getSuggestions().size();
    }

    public boolean isMenuVisible() {
        WebElement menu = driver.findElement(navigation);
        return menu.isDisplayed();
    }

    public boolean isHamburgerMenuVisible() {
        try {
            WebElement menuButton = wait.until(ExpectedConditions.visibilityOfElementLocated(menuHamburger));
            return menuButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickHamburgerMenu() {
        try {
            WebElement menuButton = wait.until(ExpectedConditions.visibilityOfElementLocated(menuHamburger));
            if (menuButton.isDisplayed() && menuButton.isEnabled()) {
                menuButton.click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(menuContent));

                System.out.println("Menu hamburguer clicado com sucesso!");
            } else {
                System.out.println("O menu hamburguer não está habilitado para clique.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao clicar no menu: " + e.getMessage());
        }
    }

    public boolean isMenuExpanded() {
        try {
            return driver.findElement(menuContent).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void click_close() {
        try {
            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(closeIcon));
            closeButton.click();
            System.out.println("Botão de fechar menu clicado com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao clicar no botão de fechar o menu: " + e.getMessage());
        }
    }

}