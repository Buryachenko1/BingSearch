package org.example.bingSearch.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//https://www.bing.com/

public class MainPage {

    @FindBy (css = "#sb_form_q")
    private WebElement searchField;

    @FindBy (css = "#bnp_btn_accept")
    private WebElement acceptCookiesButton;

    public void acceptCookies (){
        acceptCookiesButton.click();
    }

    public void sendText (String text){
        searchField.sendKeys(text);
        searchField.submit();
        System.out.println ("Text entered: " + text);
    }

    public MainPage (WebDriver driver){
        PageFactory.initElements(driver, this);
    }
}
