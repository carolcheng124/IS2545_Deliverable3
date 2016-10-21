/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 *
 * @author Carol
 * ***** USER STORY 1 *****
 * As a user
 * I would like to login
 * So that I could access to my account information
 * 
 */
public class LoginTest extends BaseTest{

    private StringBuffer verificationErrors = new StringBuffer();
    static final String VALIDUSERNAME = "hanweicheng";
    static final String VALIDPASSWORD = "Pitt2016$";
    static final String INVALIDPASSWORD = "123";
    static final String INVALIDUSERNAME = "hac79";
    
    
    //========= Scenario 1 (VALID lOGIN/HAPPY PATH)==============
    //Given correct username and correct password
    //When I try to log in with those credentials
    //Then I should be able to login successfully with page showing my username
    @Test
    public void validLogintest() throws Exception {
        driver.get(baseUrl + "/products-page/your-account/");
        driver.findElement(By.id("log")).clear();
        driver.findElement(By.id("log")).sendKeys(VALIDUSERNAME);
        driver.findElement(By.id("pwd")).clear();
        driver.findElement(By.id("pwd")).sendKeys(VALIDPASSWORD);
        driver.findElement(By.id("rememberme")).click();
        driver.findElement(By.id("login")).click();
        //verifyTextPresent
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@class,'ab-item')]")));
            By user = By.xpath("//a[contains(@class,'ab-item')]");
            assertTrue(driver.findElement(user).getText().contains(VALIDUSERNAME)); //the page shows username somewhere
            
        } catch (Error e) {
             verificationErrors.append(e.toString());
        }
    }
    
    
    //========= Scenario 2 (INVALID LOGIN) ==============
    //Given a correct username And an incorrect password
    //When I try to log in with those credentials
    //Then I should receive an error message with “Invalid login credentials” on it 
    @Test
    public void invalidLoginWrongPsdTest() throws Exception {
        driver.get(baseUrl + "/tools-qa/?loggedout=true");
        driver.findElement(By.id("user_login")).clear();
        driver.findElement(By.id("user_login")).sendKeys(VALIDUSERNAME);
        driver.findElement(By.id("user_pass")).clear();
        driver.findElement(By.id("user_pass")).sendKeys(INVALIDPASSWORD);
        driver.findElement(By.id("wp-submit")).click();
      
        try {
            By response = By.xpath("//p[contains(@class, response)]");
            waitUntil(d -> d.findElement(response).isDisplayed()); 
            assertEquals("ERROR: Invalid login credentials.", driver.findElement(response).getText());
            } catch (Error e) {
                 verificationErrors.append(e.toString());
            }
    }
    
    
    //========= Scenario 3 (INVALID LOGIN) ==============
    //Given an incorrect username
    //When I try to log in with those credentials
    //Then I should receive an error message with “Invalid login credentials” on it
    @Test
    public void invalidLoginWrongUserNameTest() throws Exception {
        driver.get(baseUrl + "/tools-qa/");
        driver.findElement(By.id("user_login")).clear();
        driver.findElement(By.id("user_login")).sendKeys(INVALIDUSERNAME);
        driver.findElement(By.id("user_pass")).clear();
        driver.findElement(By.id("user_pass")).sendKeys(INVALIDPASSWORD);
        driver.findElement(By.id("wp-submit")).click();

        try {
          By response = By.xpath("//p[contains(@class, response)]");
          waitUntil(d -> d.findElement(response).isDisplayed()); 
          assertEquals("ERROR: Invalid login credentials.", driver.findElement(response).getText());
        } catch (Error e) {
                 verificationErrors.append(e.toString());
            }
    }
    
    
}
