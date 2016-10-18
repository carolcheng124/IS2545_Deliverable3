/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store;

import com.google.common.base.Predicate;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import static org.junit.Assert.*;
/**
 *
 * @author AsphaltPanthers
 */
public class BaseTest {
    public WebDriver driver;
    private WebDriverWait wait;
    public String baseUrl;
    
    @Before
    public void setup() {
        System.setProperty("webdriver.gecko.driver", "libs/geckodriver");
        driver = new FirefoxDriver();
        baseUrl = "http://store.demoqa.com/";
//        selenium = new WebDriverBackedSelenium(driver, baseUrl);
        wait = new WebDriverWait(driver, 30);
        
    }
    
    @After
    public void teardown() {
        driver.quit();
    }
    
    public void waitUntil(Predicate<WebDriver> predicate) {
        wait.until(predicate);
    }
}
