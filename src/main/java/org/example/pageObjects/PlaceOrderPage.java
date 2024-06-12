package org.example.pageObjects;

import org.example.Reusable_AbstractComponent.Abstract_ReusableComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PlaceOrderPage extends Abstract_ReusableComponent {

    WebDriver driver;

    public PlaceOrderPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//*[@placeholder='Select Country']")
    WebElement SelectCountryAutosuggestiveDropdown;

    @FindBy(css=".ta-results button")
    List<WebElement> searchResults;

    @FindBy(css=".action__submit")
    WebElement PlaceOrderButton;

    By dropdownResults=By.cssSelector(".ta-results ");
    By placeorderbtn=By.cssSelector(".ta-results ");


    public void selectCountry(String country){
        SelectCountryAutosuggestiveDropdown.sendKeys(country);
        waitForElementToAppear(dropdownResults);
        List<WebElement> searchedDetails=searchResults;
        for (WebElement searchedDetail : searchedDetails) {
            if (searchedDetail.getText().equals(country)) {
                searchedDetail.click();
            }
        }
    }

    public OrderHistoryPage PlaceOrder() throws InterruptedException {
        scrollDownPage();
        //waitForElementToAppear(placeorderbtn);
        //PlaceOrderButton.click();
        toClickOnHiddenElements(PlaceOrderButton);
        OrderHistoryPage oh= new OrderHistoryPage(driver);
        return oh;
    }

}
