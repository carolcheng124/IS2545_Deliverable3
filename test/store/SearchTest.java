/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store;

import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 *
 * @author Carol
 * ***** USER STORY 2 *****
 *As a user
 *I would like to search a product name by typing keyword in the search bar
 *So that I could visit the list of all relative products.
 * 
 */

public class SearchTest extends BaseTest{
    private StringBuffer verificationErrors = new StringBuffer();
    final static String KEYWORD = "Apple";
            
    //========= Scenario 1 (TARGET FOUND)==============
    //Given a keyword "Apple"
    //When I type this keyword in the search bar
    //Then I should able to get the list of products whose name contain "Apple".
    @Test
    public void searchFoundTest() throws Exception {
        
        driver.get(baseUrl + "/");
        driver.findElement(By.name("s")).clear();
        driver.findElement(By.name("s")).sendKeys(KEYWORD + Keys.RETURN);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(@class, 'prodtitle')]")));
        try {
            boolean isPresent = true;
            List<WebElement> titles = driver.findElements(By.xpath("//h2[contains(@class, 'prodtitle')]/a"));
            //whether each title of product list contains 
            for(WebElement title: titles){
                if(!title.getText().contains(KEYWORD)) isPresent = false;// ????
            }
            assertTrue(isPresent);
        } catch (Error e) {
          verificationErrors.append(e.toString());
        }
  }

}