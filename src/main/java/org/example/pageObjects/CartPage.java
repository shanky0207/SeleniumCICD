package org.example.pageObjects;

import org.example.Reusable_AbstractComponent.Abstract_ReusableComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends Abstract_ReusableComponent {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="button[routerlink='/dashboard/cart']")
    WebElement cartButton;

    @FindBy(xpath="//*[@class='cartSection']/h3")
    List<WebElement> cartproducts;

    @FindBy(xpath = "//*[text()='Checkout']")
    WebElement checkoutButton;

    By toaster=By.cssSelector("#toast-container");
    By pageToLoad=By.cssSelector(".ng-animating");

    public void addTocartButton(){
        waitForElementToAppear(toaster);
        waitForInvisibilityOfElement(pageToLoad);
        cartButton.click();
    }

    public boolean checkDesiredProductisAdded(String productname){
        return cartproducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productname));
    }

    public PlaceOrderPage checkOut(){
        checkoutButton.click();
        PlaceOrderPage po=new PlaceOrderPage(driver);
        return po;
    }
}
