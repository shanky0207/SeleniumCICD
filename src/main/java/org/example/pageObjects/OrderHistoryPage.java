package org.example.pageObjects;

import org.example.Reusable_AbstractComponent.Abstract_ReusableComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderHistoryPage extends Abstract_ReusableComponent {
    WebDriver driver;
    public OrderHistoryPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//*[text()=' Thankyou for the order. ']")
    WebElement checkOrderPlaceText;

    By checkOrderPlacedText=By.xpath("//*[text()=' Thankyou for the order. ']");

    public boolean checkOrderPlaced(String textToCompare){
        waitForElementToAppear(checkOrderPlacedText);
        return checkOrderPlaceText.getText().equals(textToCompare);
    }
}
