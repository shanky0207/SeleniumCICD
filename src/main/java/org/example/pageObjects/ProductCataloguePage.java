package org.example.pageObjects;

import org.example.Reusable_AbstractComponent.Abstract_ReusableComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCataloguePage extends Abstract_ReusableComponent {

    WebDriver driver;

    public ProductCataloguePage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//*[@class='card-body']")
    List<WebElement> products;

   By productlist= By.xpath("//*[@class='card-body']");
   By addToCart=By.cssSelector(".card-body button:last-of-type");


    public List<WebElement> getProductList(){
        waitForElementToAppear(productlist);
        return products;
    }

    public WebElement getProductByName(String productName){
       WebElement prod= getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
       return prod;
    }

    public CartPage addTocart(String productName){
        WebElement prod=getProductByName(productName);
        prod.findElement(addToCart).click();
        CartPage cp= new CartPage(driver);
        return cp;

    }

}
