package org.example.TestScenarios;


import org.example.TestComponents.BaseTest;
import org.example.pageObjects.*;

import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class StandaloneCopyTest extends BaseTest {



        @Test(dataProvider = "getData")
        public void eCommerecetest(HashMap<String,String> data) throws InterruptedException, IOException {

        String country="India";
        String text="THANKYOU FOR THE ORDER.";

        ProductCataloguePage pc=landingPage.loginToApplication(data.get("emailid"),data.get("password"));

        List<WebElement>productList=pc.getProductList();
        CartPage cp=pc.addTocart(data.get("productName"));
        cp.addTocartButton();
        boolean flag=cp.checkDesiredProductisAdded(data.get("productName"));

        Assert.assertTrue(flag);

        PlaceOrderPage po=cp.checkOut();
        po.selectCountry(country);
        OrderHistoryPage oh=po.PlaceOrder();
        boolean checktext=oh.checkOrderPlaced(text);

        Assert.assertTrue(checktext);

        //driver.close();
    }



    @DataProvider
    public Object[][] getData() throws IOException {

//      HashMap<String,String> map1 = new HashMap<String,String>();
//      map1.put("emailid","seleniumtesting@practice.com");
//      map1.put("password","Selenium@1234");
//      map1.put("productName","IPHONE 13 PRO");
//
//      HashMap<String,String> map2 = new HashMap<String,String>();
//      map2.put("emailid","testdata@testing.co");
//      map2.put("password","Testdata@1234");
//      map2.put("productName","ADIDAS ORIGINAL");

        List<HashMap<String,String>> data=getJsonDataToHashMap();
            return new Object[][] {{data.get(0)},{data.get(1)}};
    }


}
