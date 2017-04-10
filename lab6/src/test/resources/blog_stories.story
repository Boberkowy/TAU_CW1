
Scenario: user enter proper login and password should be logged in after click on "Login" button.

Given user is on tinpage
When user enter useru and useru as login and password
Then user should be logged in and WYLOGUJ visible


Scenario: user enter inproper login and password should see an big red error.

Given user is on tinpage
When user enter user and user as login and password
Then user should see error which has id error
