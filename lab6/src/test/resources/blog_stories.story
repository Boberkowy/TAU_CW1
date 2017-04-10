
Scenario: user enter proper login and password should be logged in after click on "Login" button.

Given user is on tinpage
When user enter useru and useru as login and password
Then user should be logged in and WYLOGUJ visible


Scenario: user enter inproper login and password should see an big red error.

Given user is on tinpage
When user enter inproper creds as asdfasdf and asdfasdf as login and password
Then user shouldnt be logged in and Zaloguj visible


Scenario: user is logged in, visit new page, come back to blog again - still logged in

Given user is logged in
When user visit new page
Then user come back to blog
Then user is still logged in