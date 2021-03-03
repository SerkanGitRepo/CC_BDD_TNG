Feature: Account Page Feature

Background:
Given user has already logged in to application
|username|password|
|serkan.aksut@zorlu.com|24041234|

@accounts
Scenario: Accounts Page Title
Given user is on Account page
When user gets title of the page
Then page title should be "My account - My Store"

@accounts
Scenario: Accounts section count
Given user is on Account page
Then user gets account section
|ORDER HISTORY AND DETAILS|
|MY CREDIT SLIPS|
|MY ADDRESSES|
|MY PERSONAL INFORMATION|
|MY WISHLISTS|
|Home|
And accounts section count should be 6