# IS2545_Deliverable3

ISSUE

The issue that I had at the begining was the confusion when facing up with a complicated e-commerce website, I was not sure how to organize nine test cases and how to design them. Then, I had problem implementing Selenium and Webdriver due to the lacking of basic knowledge and experience.

All my tests passed.

/******
    USER STORY 1
              ******/

As a user, I would like to login, So that I could access to my account information.

1. Scenario 1 (VALID LOGIN/HAPPY PATH)

Given correct username and correct password, When I try to log in with those credentials, Then I should able to login successfully with page showing my username.

2. Scenario 2 (INVALID LOGIN/BAD PATH) 

Given a correct username and an incorrect password, When I try to log in with those credentials, Then I should receive an error message with “Invalid login credentials” on it.

3. Scenario 3 (INVALID LOGIN/BAD PATH)

Given an incorrect username, When I try to log in with those credentials, Then I should receive an error message with “Invalid login credentials” on it.

***** USER STORY 2 *****

As a user, I would like to search product name in the search bar, So that I could access to related product list.

========= Scenario 1 (TARGET FOUND)==============

Given an product "apple" that exists on this website, When I try to search "apple" in the search bar, Then I should able to get the product list whose titles contain keyword "apple" on it

========= Scenario 2 (TARGET NOT FOUND)==============

Given an product "brush" that doesn't exist on this website, When I try to search "brush" in the search bar, Then I should able to get an error message " Sorry, but nothing matched your search criteria. Please try again with some different keywords." on it

***** USER STORY 3 *****

As a user, I would like to manipulate products in my shopping cart, So that I could have satisfied products and finally checkout.

========= Scenario 1 (ADD PRODUCT INTO CART) ==============

Given I'm at a product page of "magic mouse", When I try to add it into my shopping cart, Then I should be able to see product amount of my cart is increased to 1, and this item shown in my shopping cart

========= Scenario 2 (REMOVE PRODUCT INTO CART) ==============

Given a product "magic mouse" has been already in my shopping cart, When I try to remove it from my shopping cart, Then I should not able to find the items in my shopping cart

========= Scenario 3 (CALCULATING PRICE IN CART) ==============

Given adding two products in my shopping cart, When I try to calculate the total price, Then I should able to see the total price shown in the shopping cart is the same with the sum of all product price.

========= Scenario 4 (CHECK OUT) ==============

Given I'm ready to make payment, When I try to make payment after review shopping cart, Then I should able to see a different payment page where I could put in payment method, however, for this website, it will return to the previous page.
