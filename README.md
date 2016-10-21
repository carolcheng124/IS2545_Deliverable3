# IS2545_Deliverable3

###ISSUE

The issue that I had at the begining was the confusion when facing up with a complicated e-commerce website, I was not sure how to organize nine test cases and how to design them. Then, I had problem implementing Selenium and Webdriver due to the lacking of basic knowledge and experience.

All my test cases passed.


## *** USER STORY 1 ***
    

As a user, I would like to login, So that I could access to my account information.

#### 1. Scenario 1 (VALID LOGIN/HAPPY PATH)

Given correct username and correct password, When I try to log in with those credentials, Then I should be able to login successfully with page showing my username.

#### 2. Scenario 2 (INVALID LOGIN/BAD PATH) 

Given a correct username and an incorrect password, When I try to log in with those credentials, Then I should receive an error message with “Invalid login credentials” on it.

#### 3. Scenario 3 (INVALID LOGIN/BAD PATH)

Given an incorrect username, When I try to log in with those credentials, Then I should receive an error message with “Invalid login credentials” on it.


## *** USER STORY 2 ***

As a user, I would like to search a product name by typing keyword in the search bar, So that I could visit the list of all relative products.

#### 4. Scenario 1 (TARGET FOUND)

Given a keyword "Apple", When I type this keyword in the search bar, Then I should able to get the list of products whose name contain "Apple".



## *** USER STORY 3 ***

As a user, I would like to manipulate products in the shopping cart, calculate the price and checkout, So that I could buy satisfied products.

#### 5. Scenario 1 (ADD PRODUCT INTO CART)

Given I'm at a product page of "magic mouse" and my shopping cart is empty, When I try to add it into my shopping cart, Then I should be able to see product amount of my cart is increased to 1, and my shopping cart contains this product.

#### 6. Scenario 2 (REMOVE PRODUCT INTO CART)

Given I just added a product in my shopping cart, When I try to remove it from my shopping cart, Then I should be able to see my shopping cart doesn't contain this product any longer.

#### 7. Scenario 3 (CALCULATE PRICE IN CART)

Given I just added two products in my shopping cart, When I try to calculate the total price, Then I should able to see the total price shown in the shopping cart is the same with the sum of all product price.

#### 8. Scenario 4 (CHECK OUT/HAPPY PATH)

Given I have already filled in all required fields at check-out page, When I try to make purchase, Then I should be able to enter a different page instead of returning back to the check-out page.

#### 9. Scenario 5 (CHECK OUT/BAD PATH)
Given I have not filled any required fields at check-out page, When I try to make purchase, Then I should be able to return back to the check-out page instead of entering another page.

