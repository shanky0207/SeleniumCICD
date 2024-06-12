package org.example.TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.example.pageObjects.LandingPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage;

    public WebDriver initializeDriver() throws IOException {

        Properties prop= new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\shashank.chavan\\IdeaProjects\\SeleniumFramework\\src\\main\\java\\org\\example\\resources\\GlobalData.properties");
        prop.load(fis);

        String browserName=prop.getProperty("browser");

        if(browserName.equals("chrome")){
            WebDriverManager.chromedriver().setup();

            ChromeOptions options= new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        }


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    @BeforeMethod
    public LandingPage launchApplication() throws IOException {
        driver=initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;

    }

    public List<HashMap<String, String>> getJsonDataToHashMap() throws IOException {
        //to read json file and convert into string
        String jsonContent=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//org//example//Data//PurchaseOrder.json"));

        //convert String to Hashmap using Json DataBind
        ObjectMapper objectMapper = new ObjectMapper();

        List<HashMap<String,String>>data=objectMapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
        });

        return data;
    }

    public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
        TakesScreenshot takesScreenshot=(TakesScreenshot)driver;
        File src=takesScreenshot.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
        FileUtils.copyFile(src,file);
        return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
    }

}
