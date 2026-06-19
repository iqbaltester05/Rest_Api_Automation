Feature: Login

Scenario: login with valid credential

Given user set base uri
And user added header
|Content-Type|application/json|

# And user added header for ContentType
# And the added body as "john.doe@example.com" and "password123"
And user added body
|email|john.doe@example.com|
|password|password123|

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


Scenario: create a user
Given user set base uri
And user added header
|Content-Type|application/json|

And user added body
|username|pawan2|
|email|pawan2@example.com|
|password|password123|
|firstName|pawan2|
|lastName|p2|
|role|SALES_REP|
|department|Sales|
|phone|+1234567890|

And user added header for Authorization
When user send post request with endpoint "/api/users"
Then validate the status code as 201



