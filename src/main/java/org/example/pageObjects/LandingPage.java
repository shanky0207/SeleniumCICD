package org.example.pageObjects;

import org.example.Reusable_AbstractComponent.Abstract_ReusableComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends Abstract_ReusableComponent {

    WebDriver driver;

    public LandingPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="userEmail")
    WebElement emailid;

    @FindBy(css="#userPassword")
    WebElement password;

    @FindBy(css=".login-btn")
    WebElement loginbutton;

    @FindBy(css="[class*='flyInOut']")
    WebElement loginErrormsg;

    public ProductCataloguePage loginToApplication(String username, String pwd){
        emailid.sendKeys(username);
        password.sendKeys(pwd);
        loginbutton.click();
        ProductCataloguePage pc= new ProductCataloguePage(driver);
        return pc;
    }

    public void goTo(){
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/client");
    }

    public String verifyLoginErrormsg(){
        waitForElement(loginErrormsg);
        String errormsg=loginErrormsg.getText();
        return errormsg;
    }

}
