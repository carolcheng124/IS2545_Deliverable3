/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Carol
 * ***** USER STORY 2 *****
 * As a user
 * I would like to search product name in the search bar
 * So that I could access to related product list
 * 
 */

public class SearchTest extends BaseTest{
    private StringBuffer verificationErrors = new StringBuffer();
    boolean isPresent = true;
    //========= Scenario 1 (TARGET FOUND)==============
    //Given an product "apple" that exists on this website
    //When I try to search "apple" in the search bar
    //Then I should able to get the product list whose titles contain keyword "apple" on it
    @Test
    public void SearchFoundTest() throws Exception {
        driver.get(baseUrl + "/");
        driver.findElement(By.name("s")).clear();
        driver.findElement(By.name("s")).sendKeys("Apple"+ Keys.RETURN);
        
        try {
//          By productList = By.cssSelector("product_grid_display group");
//          waitUntil(d -> d.findElement(productList).isDisplayed());
//            By link = By.xpath("//div[contains(@class, 'grid_product_info')]/a");
//            assertTrue(driver.findElement(link).getText().matches("Apple"));
//            Boolean isPresent = driver.findElements(By.cssSelector("")).size()>0;???
//            assertTrue(isPresent);
//          assertTrue(driver.findElement(productList).getText().matches("Apple"));//?????
//            driver.findElement(By.id("lst-ib")).sendKeys("Hello World!" + Keys.RETURN);
//        
//        WebElement rightHandSideHeader = driver.findElement(By.xpath("//div[contains(@class, 'kno-ecr-pt')]"));
            List<WebElement> items = driver.findElements(By.xpath("//h2[contains(@class, 'prodtitle')]/a"));//????
//            waitUntil(d -> d.findElement(By.xpath("//h2[contains(@class, 'prodtitle')]/a")).isDisplayed());
            for(WebElement item: items){
                if(!item.getText().matches("Apple")) isPresent = false;// ????
            }
            assertTrue(isPresent);
        } catch (Error e) {
          verificationErrors.append(e.toString());
        }
  }

    
    //========= Scenario 2 (TARGET NOT FOUND)==============
    //Given an product "brush" that doesn't exist on this website
    //When I try to search "brush" in the search bar
    //Then I should able to get an error message " Sorry, but nothing matched your search criteria. Please try again with some different keywords." on it
    @Test
    public void SearchNotFoundTest() throws Exception {
        driver.get(baseUrl + "/");
        driver.findElement(By.name("s")).clear();
        driver.findElement(By.name("s")).sendKeys("brush" + Keys.RETURN);
        
        try {
          By content = By.xpath("//div[@id = 'content']");
          waitUntil(d -> d.findElement(content).isDisplayed()); 
          assertEquals("Sorry, but nothing matched your search criteria. Please try again with some different keywords.", driver.findElement(content).getText());
        } catch (Error e) {
          verificationErrors.append(e.toString());
        }
  }

}