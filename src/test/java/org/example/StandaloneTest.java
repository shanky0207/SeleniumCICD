package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pageObjects.LandingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

public class StandaloneTest {

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options= new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);

        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String productname="ADIDAS ORIGINAL";
        driver.get("https://rahulshettyacademy.com/client");
        LandingPage lpg= new LandingPage(driver);
        driver.findElement(By.id("userEmail")).sendKeys("seleniumtesting@practice.com");
        driver.findElement(By.cssSelector("#userPassword")).sendKeys("Selenium@1234");
        driver.findElement(By.cssSelector(".login-btn")).click();

        List<WebElement> products=driver.findElements(By.xpath("//*[@class='card-body']"));

        WebElement prod= products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals("ADIDAS ORIGINAL")).findFirst().orElse(null);
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();



        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        List<WebElement> cartProducts=driver.findElements(By.xpath("//*[@class='cartSection']/h3"));
        boolean isaddedProductPresent=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productname));
        Assert.assertTrue(isaddedProductPresent);
        driver.findElement(By.xpath("//*[text()='Checkout']")).click();

        driver.findElement(By.xpath("//*[@placeholder='Select Country']")).sendKeys("India");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results ")));

        List<WebElement> searchedDetails=driver.findElements(By.cssSelector(".ta-results button"));
        for (WebElement searchedDetail : searchedDetails) {
            if (searchedDetail.getText().equals("India")) {
                searchedDetail.click();
            }
        }

        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,800)");

        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit"))).click();
        Thread.sleep(5000);

        driver.findElement(By.cssSelector(".action__submit")).click();

        boolean flag=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=' Thankyou for the order. ']"))).getText().equals("THANKYOU FOR THE ORDER.");
        Assert.assertTrue(flag);



        //driver.close();
    }
}
