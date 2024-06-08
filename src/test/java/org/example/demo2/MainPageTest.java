package org.example.demo2;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

public class MainPageTest {
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
    public void search() {
        // Accept cookies if the button exists
        WebElement acceptCookiesButton = driver.findElement(By.cssSelector("#bnp_btn_accept"));
        acceptCookiesButton.click();

        // Locate the search field and input search text
        String input = "Selenium";
        WebElement searchField = driver.findElement(By.cssSelector("#sb_form_q"));
        searchField.sendKeys(input);
        searchField.submit();

        // Verify that the search field contains the expected input value
        WebElement searchPageField = driver.findElement(By.cssSelector("#sb_form_q"));
        assertEquals(input, searchPageField.getAttribute("value"));
    }

    @Test
    public void search1() {
        // Accept cookies if the button exists
        WebElement acceptCookiesButton = driver.findElement(By.cssSelector("#bnp_btn_accept"));

        // Locate the search field and input search text
        String input = "Selenium";
        WebElement searchField = driver.findElement(By.cssSelector("#sb_form_q"));
        searchField.sendKeys(input);
        searchField.submit();

        // Find and continue to the first element fom the SERP
        List<WebElement> results = driver.findElements(By.cssSelector("#b_results > li.b_algo.b_vtl_deeplinks > h2 > a"));
        String expectedUrl = results.get(0).getAttribute("href");
        clickElement(results, 0);

        // Get the current URL and assert it matches the expected URL
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl, "The URL of the first search result does not match the current URL");
    }

    private void clickElement(List<WebElement> results, int num) {
        WebElement element = results.get(num);
        System.out.println("Clicking on element: " + element.getText());
        element.click();

    }
}
