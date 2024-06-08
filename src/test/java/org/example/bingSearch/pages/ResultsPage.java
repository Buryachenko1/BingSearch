package org.example.bingSearch.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ResultsPage {

    @FindBy(css = "#sb_form_q")
    private WebElement searchField;

    @FindBy (css = "h2 > a[href]")
    private List <WebElement> results;


    public void clickElement(int num) {
        results.get(num).click();
        System.out.println("Click on the result number " + num);
    }

    public String getTextFromSearchField(){
       String val = searchField.getAttribute("value");
        System.out.println("The text in the search field: " + val);
        return val;
    }



    public ResultsPage (WebDriver driver){
        PageFactory.initElements(driver, this);
    }
}
