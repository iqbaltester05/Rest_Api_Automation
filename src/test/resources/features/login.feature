Feature: Login

Scenario: login with valid credential

Given user set base uri
And user added header for ContentType
And the added body as "john.doe@example.com" and "password123"
When user send post request with endpoint "/api/login"
Then validate the status code as 200
Then validate schema from json file ""
Then user store the accesstoken

Scenario: get current user detail
Given user set base uri
And user added header for ContentType
And user added header for Authorization
When user send get request with endpoint "/api/me"
Then validate the status code as 200
