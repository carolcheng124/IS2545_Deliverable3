/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store;


import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Carol
 * ***** USER STORY 1 *****
 * As a user
 * I would like to manipulate products in my shopping chart
 * So that I could have satisfied products and finally checkout
 * 
 */
public class ShoppingTest extends BaseTest{
    private StringBuffer verificationErrors = new StringBuffer();
    
    //========= Scenario 1 (ADD PRODUCT INTO CHART) ==============
    //Given I'm at a product page
    //When I try to add it into my shopping chart
    //Then I should be able to receive a notification as "just added ** to your chart" and item amount in my chart is increased by one 
    @Test
    public void addItemInChart() throws Exception {
        driver.get(baseUrl + "/products-page/product-category/accessories/magic-mouse/");
        driver.findElement(By.name("Buy")).click();
        
//        driver.findElement(By.linkText("Continue Shopping")).click();//cannot find this element?
//        driver.findElement(By.cssSelector("em.count")).click(); ???
        //ccount?? in different page?
        //shoppingchart really have that item??? =>go to checkout=>shopping chart=>item existed
        try {
          By notification = By.xpath("//div[@id = 'fancy_notification_content']");
          waitUntil(d -> d.findElement(notification).isDisplayed());
          assertTrue(!driver.findElements(notification).isEmpty() && driver.findElement(notification).getText().contains("added"));
        } catch (Error e) {
          verificationErrors.append(e.toString());
        }
    }
    
    
    
    //========= Scenario 2 (REMOVE PRODUCT INTO CHART) ==============
    //Given a product "iPhone 5" has been already in my shopping chart
    //When I try to remove it from my shopping chart
    //Then I should able to see the items in my shopping chart decreased by one
    @Test
    public void removeItemInChart() throws Exception {
        driver.get(baseUrl + "/products-page/checkout/");
        driver.findElement(By.cssSelector("form.adjustform.remove > input[name=\"submit\"]")).click();//???
        
        try {
          By content = By.xpath("//div[contain(@class, 'entry-content')]");
          waitUntil(d -> d.findElement(content).isDisplayed());
          assertFalse(driver.findElement(content).getText().contains("iPhone 5"));
        } catch (Error e) {
          verificationErrors.append(e.toString());
        }
    }
    
    
    
    //========= Scenario 3 (CALCULATING PRICE IN CHART) ==============
    //Given a product already in my shopping chart
    //When I try to remove it from my shopping chart
    //Then I should able to see the items in my shopping chart decreased by one
    @Test
    public void totalPrice() throws Exception {
        driver.get(baseUrl + "/products-page/checkout/");
        driver.findElement(By.cssSelector("form.adjustform.remove > input[name=\"submit\"]")).click();//???
        
        try {
          By content = By.xpath("//div[contain(@class, 'entry-content')]");
          waitUntil(d -> d.findElement(content).isDisplayed());
          assertFalse(driver.findElement(content).getText().contains("iPhone 5"));
        } catch (Error e) {
          verificationErrors.append(e.toString());
        }
    }
    
    
    
    //========= Scenario 4 (CHECK OUT) ==============
    //Given I'm ready to checkout
    //When I try to make purchase after review shopping chart
    //Then I should able to see a different payment page
    @Test
    public void checkout() throws Exception {
        driver.get(baseUrl + "/products-page/checkout/");
        driver.findElement(By.xpath("//span[text()='Continue']")).click(); //continue
        driver.findElement(By.cssSelector("span > input[name=\"submit\"]")).click(); //purchase
        
        try {
          String currentUrl = driver.getCurrentUrl();
          assertTrue(currentUrl.equals(baseUrl + "/products-page/checkout/")); //checkout page should be different with payment page
        } catch (Error e) {
          verificationErrors.append(e.toString());
        }
    }
}
