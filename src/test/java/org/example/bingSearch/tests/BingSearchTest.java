package org.example.bingSearch.tests;

import org.example.bingSearch.pages.MainPage;
import org.example.bingSearch.pages.ResultsPage;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

public class BingSearchTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.bing.com/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void searchFieldTests() {

        //Accept cookies if necessary
        MainPage mp = new MainPage(driver);
        mp.acceptCookies();

        // Locate the search field and input search text
        String input = "Selenium";
        mp.sendText(input);

        // Verify that the search field contains the expected input value
        ResultsPage rp = new ResultsPage(driver);
        assertEquals(input, rp.getTextFromSearchField(), "Text is not equal");
    }

    @Test
    public void searchResultsTest() {
        // Accept cookies if the button exists
        MainPage mp = new MainPage(driver);
        mp.acceptCookies();

        // Locate the search field and input search text
        String input = "Selenium";
        mp.sendText(input);

        // Find and continue to the first element fom the SERP
        ResultsPage rp = new ResultsPage(driver);
        rp.clickElement(0);

        // Get the current URL and assert it matches the expected URL
        assertEquals("https://www.selenium.dev/", driver.getCurrentUrl(), "The URL of the first search result does not match the current URL");
    }
}
