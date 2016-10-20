# IS2545_Deliverable3
Requirement: For the summary, add a description of issues you faced when writing these tests and problems you would expect going forward based on your experiences. If any tests you wrote fail, they should be included here. Also note if there are any special steps to get the tests running.

The issue that I have is when facing up with a complecated e-commerce website, I'm not sure how to pick up 9 test cases among those. I not sure how to design several test cases.

**** USER STORY 1 *****

As a user, I would like to login, So that I could access to my account information.

========= Scenario 1 (Valid login) ===============

Given correct username and correct password, When I try to log in with those credentials, Then I should able to get access to profile information such as purchase history.

========= Scenario 2 (INVALID LOGIN) ==============

Given a correct username And an incorrect password, When I try to log in with those credentials, Then I should receive an error message with “Invalid login credentials” on it.

========= Scenario 3 (INVALID LOGIN) ==============

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
