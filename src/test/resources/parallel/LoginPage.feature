Feature: Login Page Feature

Scenario: Login Page Title
Given user is on login page
When user gets title of the page
Then page title should be "Login - My Store"

Scenario: Forgot Password Link
Given user is on login page
Then forgot your password link should be displayed

Scenario: Login with correct credentials
Given user is on login page
When user enters username "serkan.aksut@zorlu.com"
And user enters password "24041234"
And user click on login button
Then user gets title of the page
And page title should be "My account - My Store"