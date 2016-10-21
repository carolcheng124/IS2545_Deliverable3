/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store;


import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author Carol
 * ***** USER STORY 3 *****
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
    //Given I'm at a product page of "magic mouse"
    //When I try to add it into my shopping cart
    //Then I should be able to see this item in my shopping cart 
    //and item amount in my cart is increased to one 
   @Test
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
    //Given I just added a product in my shopping cart
    //When I try to remove it from my shopping cart
    //Then I should be able to see my shopping cart doesn't contain this product any longer.
    @Test
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
    
  
    //========= Scenario 3 (CALCULATE PRICE IN CART) ==============
    //Given I just added two products in my shopping cart
    //When I try to calculate the total price
    //Then I should able to see the total price shown in the shopping cart is the same with the sum of all product price
    @Test
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
    
    
    
    //========= Scenario 4 (CHECK OUT/HAPPY PATH) ==============
    //Given I have already filled in all required fields at the checkout page
    //When I try to make purchase
    //Then I should be able to enter a different page instead of returning back to the check-out page.
    @Test
    public void testCheckoutWithFilledForm() throws Exception {
        addItemInCart(MAGIC_MOUSE);
        driver.get(baseUrl + CHECKOUT);
        driver.findElement(By.cssSelector("#checkout_page_container .step2")).click(); //"continue" button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[value='Purchase']"))); //"purchase" button
        
        //fill in all the required fields
        //email
        driver.findElement(By.id("wpsc_checkout_form_9")).clear();
        driver.findElement(By.id("wpsc_checkout_form_9")).sendKeys("test@test.com");
        //firstname
        driver.findElement(By.id("wpsc_checkout_form_2")).clear();
        driver.findElement(By.id("wpsc_checkout_form_2")).sendKeys("test");
        //lastname
        driver.findElement(By.id("wpsc_checkout_form_3")).clear();
        driver.findElement(By.id("wpsc_checkout_form_3")).sendKeys("test");
        //address
        driver.findElement(By.id("wpsc_checkout_form_4")).clear();
        driver.findElement(By.id("wpsc_checkout_form_4")).sendKeys("test");
        //city
        driver.findElement(By.id("wpsc_checkout_form_5")).clear();
        driver.findElement(By.id("wpsc_checkout_form_5")).sendKeys("test");
        //country
        new Select(driver.findElement(By.id("wpsc_checkout_form_7"))).selectByVisibleText("USA");                
        //zipcode
        driver.findElement(By.id("wpsc_checkout_form_8")).clear();
        driver.findElement(By.id("wpsc_checkout_form_8")).sendKeys("12345");        
        //phone
        driver.findElement(By.id("wpsc_checkout_form_18")).clear();
        driver.findElement(By.id("wpsc_checkout_form_18")).sendKeys("1234567890");
        
        driver.findElement(By.cssSelector("[value='Purchase']")).click();
        
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//b[text()='Fatal error']")));
        //after fill in all required fields and click "purchase", it should redirect to a different page
        try {
          String pageText = driver.findElement(By.cssSelector("body")).getText();
          System.out.println(pageText);
          assertFalse(pageText.contains("Checkout")); //it should not go back to the check-out page
          
        } catch (Error e) {
          verificationErrors.append(e.toString());
        }
    }
    
    //========= Scenario 5 (CHECK OUT/BAD PATH) ==============
    //Given I have not filled any required fields at check-out page
    //When I try to make purchase
    //Then I should be able to return back to the check-out page instead of entering another page.
    @Test
    public void testCheckoutWithoutFilledForm() throws Exception {
        addItemInCart(MAGIC_MOUSE);
        driver.get(baseUrl + CHECKOUT);
        driver.findElement(By.cssSelector("#checkout_page_container .step2")).click(); //"continue" button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[value='Purchase']"))); 
        //click "purchase" button
        driver.findElement(By.cssSelector("[value='Purchase']")).click();
        
        //if I didn't fill in any form and click "purchase", it should return me back to check-out page
        try {
          wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(@class, 'entry-title')]")));
          String entryTitle = driver.findElement(By.xpath("//h1[contains(@class, 'entry-title')]")).getText();
          
          //it should jump back to the check-out page
          assertTrue(entryTitle.equals("Checkout")); 
          
        } catch (Error e) {
          verificationErrors.append(e.toString());
        }
    }
}
