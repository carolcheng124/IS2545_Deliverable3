/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store;


import java.util.List;
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
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 *
 * @author Carol
 * ***** USER STORY 1 *****
 * As a user
 * I would like to manipulate products in my shopping cart
 * So that I could have satisfied products and finally checkout
 * 
 */
public class ShoppingTest extends BaseTest{
    private StringBuffer verificationErrors = new StringBuffer();
    final static String MAGIC_MOUSE = "/products-page/product-category/accessories/magic-mouse/";
    final static String IPHONE5 = "/products-page/product-category/n/";
    final static String CHECKOUT = "/products-page/checkout/";
    
    //========= Scenario 1 (ADD PRODUCT INTO CART) ==============
    //Given I'm at a product page
    //When I try to add it into my shopping cart
    //Then I should be able to receive a notification as "just added ** to your cart" and item amount in my cart is increased by one 
   //@Test
    public void testAddItem() throws Exception{
        String productName = addItemInCart(MAGIC_MOUSE);
        
        try {
            //check whether count of cart icon is >0
            //check shopping cart
            verifyCount(1);
            assertTrue(checkShoppingCart(productName));
          
        } catch (Error e) {
          verificationErrors.append(e.toString());
        }
    }
    
    //add item into cart method
    private String addItemInCart(String url) throws Exception {
        
        driver.get(baseUrl + url);
        
        //get the name of product that
        String productName = driver.findElement(By.xpath("//h1[contains(@class, 'prodtitle')]")).getText();
        
        //click "Add to chart" button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Buy"))); 
        driver.findElement(By.name("Buy")).click();
        
        //nofication pop-out 
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("fancy_notification_content")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".continue_shopping")));
        driver.findElement(By.xpath("//*[text() = 'Continue Shopping']")).click();
        
        return productName;
    }
    
    //Check whether shoppingcart contains specific item or not
    private boolean checkShoppingCart(String productName) {
        driver.get(baseUrl + CHECKOUT);
        //open Shopping chart and check the amount
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".entry-content")));
        //check whether shopping chart contain the target product title
         WebElement productList = driver.findElement(By.cssSelector(".entry-content"));
         
         boolean isPresent = productList.getText().contains(productName);
         return isPresent;
    }
    
    //verify the amount of products in the shopping cart
    private void verifyCount(int expected) {
        //click shopping chart icon
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("header_cart")));
        int count = Integer.parseInt(driver.findElement(By.cssSelector("#header_cart .count")).getText());
        assertEquals(expected, count);
    }
    
    //========= Scenario 2 (REMOVE PRODUCT INTO CART) ==============
    //Given a product "iPhone 5" has been already in my shopping cart
    //When I try to remove it from my shopping cart
    //Then I should able to see the items in my shopping cart decreased by one
    //@Test
    public void testRemoveItem() throws Exception{
       String productName = addItemInCart(MAGIC_MOUSE);
       removeItemInCart();
       try {
            //shopping cart should not include this item any more
            assertFalse(checkShoppingCart(productName)); 
          
        } catch (Error e) {
          verificationErrors.append(e.toString());
        }
       
        
    }
    
    private void removeItemInCart() throws Exception {
        //add one item into the shopping cart
        driver.get(baseUrl + CHECKOUT);
        //if nothing in the shopping cart
        if(driver.findElement(By.cssSelector(".entry-content")).getText().contains("Oops, there is nothing in your cart.")){
           System.out.println("the shopping cart is empty");
        }else{
            //open Shopping cart and remove the only existed item
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".entry-content")));
            driver.findElement(By.xpath("//input[@value = 'Remove']")).click();
        }
    }
    
  
    //========= Scenario 3 (CALCULATING PRICE IN CART) ==============
    //Given a product already in my shopping cart
    //When I try to remove it from my shopping cart
    //Then I should able to see the items in my shopping cart decreased by one
    //@Test
    public void testTotalPrice() throws Exception {
       addItemInCart(MAGIC_MOUSE);
       addItemInCart(IPHONE5);
       
       driver.get(baseUrl + CHECKOUT);
     
        try {
          
          List<WebElement> items = driver.findElements(By.cssSelector(".entry-content .product_row"));
          double total = 0;
          for (WebElement item : items) {
              String sPrice = item.findElement(By.cssSelector(".pricedisplay")).getText();
              double price = Double.parseDouble(sPrice.substring(1));
              System.out.println("price: " + price);
              int quantity = Integer.parseInt(item.findElement(By.cssSelector("[name='quantity']")).getAttribute("value"));
              total += price * quantity;
              System.out.println("total: " + total);
          }
          
          String sTotal = driver.findElement(By.cssSelector("#checkout_page_container .yourtotal .pricedisplay")).getText();
          assertEquals(total, Double.parseDouble(sTotal.substring(1)), 0);
          
          
        } catch (Error e) {
          verificationErrors.append(e.toString());
        }
    }
    
    
    
    //========= Scenario 4 (CHECK OUT) ==============
    //Given I'm ready to make payment
    //When I try to make payment after review shopping cart
    //Then I should able to see a different payment page, however, for this website, it will return to the previous page
    @Test
    public void testCheckout() throws Exception {
        addItemInCart(MAGIC_MOUSE);
        driver.get(baseUrl + CHECKOUT);
        driver.findElement(By.cssSelector("#checkout_page_container .step2")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[value='Purchase']")));

        driver.findElement(By.cssSelector("[value='Purchase']")).click();
        
        //check-out page should be different with payment page
        try {
          String currentUrl = driver.getCurrentUrl();
          System.out.println(currentUrl);
          System.out.println(currentUrl.equals(baseUrl + CHECKOUT));
          assertFalse(currentUrl.equals(baseUrl + CHECKOUT)); //it jumps back to the check-out page
        } catch (Error e) {
          verificationErrors.append(e.toString());
        }
    }
}
