Feature: Login

Scenario: login with valid credential

Given user set base uri "http://localhost:3000"
And user added header as "Content-Type" and "application/json"
And the added body as "myrtle.streich9@yahoo.com" and "password123"
When user send post request with endpoint "/api/login"
Then validate the status code as 200
Then user store the accesstoken

Scenario: get current user detail
Given user set base uri "http://localhost:3000"
And user added header as "Content-Type" and "application/json"
And user added header for Authorization
When user send get request with endpoint "/api/me"
Then validate the status code as 200
