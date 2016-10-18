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
import org.openqa.selenium.WebElement;

/**
 *
 * @author Carol
 * ***** USER STORY 1 *****
 * As a user
 * I would like to login
 * So that I could access to my purchase history
 * 
 */
public class LoginTest extends BaseTest{
//    static WebElement usernameInputBox;
//    static WebElement psdInputBox;
//    static WebElement logInButton;
    private StringBuffer verificationErrors = new StringBuffer();
    
    
    //========= Scenario 1 (Valid login)==============
    //Given correct username and correct password
    //When I try to log in with those credentials
    //Then I should able to get access to profile information such as purchase history
    @Test
    public void validLogintest() throws Exception {
        driver.get(baseUrl + "/products-page/your-account/");
        driver.findElement(By.id("log")).clear();
        driver.findElement(By.id("log")).sendKeys("hanweicheng");
        driver.findElement(By.id("pwd")).clear();
        driver.findElement(By.id("pwd")).sendKeys("Pitt2016$");
        driver.findElement(By.id("rememberme")).click();
        driver.findElement(By.id("login")).click();
        //verifyTextPresent
        try {
//          assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
//          assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("Purchase History"));
            //By profile = By.xpath("//a[contains(@class, current)]");
            By username = By.xpath("//a[text()='hanweicheng']");
            
            waitUntil(d -> d.findElement(username).isDisplayed()); 
            assertTrue(driver.findElement(username).getText().contains("hanweicheng")); //the page shows username somewhere
            
          //assertEquals("Purchase History", driver.findElement(profile).getText());
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
        driver.findElement(By.id("user_login")).sendKeys("hanweicheng");
        driver.findElement(By.id("user_pass")).clear();
        driver.findElement(By.id("user_pass")).sendKeys("123");
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
        driver.findElement(By.id("user_login")).sendKeys("HAC79"); //wrong username
        driver.findElement(By.id("user_pass")).clear();
        driver.findElement(By.id("user_pass")).sendKeys("123");
        driver.findElement(By.id("wp-submit")).click();

        try {
          By response = By.xpath("//p[contains(@class, response)]");
          waitUntil(d -> d.findElement(response).isDisplayed()); 
          assertEquals("ERROR: Invalid login credentials.", driver.findElement(response).getText());
        } catch (Error e) {
                 verificationErrors.append(e.toString());
            }
    }
    
    //========= Scenario 4 (VALID REGISTRATION) ==============
    //Given a valid email
    //When I try to register with this email
    //Then I should receive an message with “register completed:” on it
    
   
    //========= Scenario 5 (INVALID REGISTRATION) ==============
    //Given an invalid email address
    //When I try to register with this address
    //Then I should receive an message with “register completed:” on it
    
    
}
