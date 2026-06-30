Feature: Login and User Management

  # Background:
  #    Given user set base uri
  #   And user set content type as "application/json"
  #   And user added body
  #     | email    | john.doe@example.com |
  #     | password | password123          |
  #   When user send post request with endpoint "/api/login"
  #   Then validate the status code as 200
  #   And validate json path "message" is "Login successful"
  #   And user store the accesstoken

  # @authentication @smoke @regression
  # Scenario: login with valid credential
  #   Given user set base uri
  #   And user set content type as "application/json"
  #   And user added body
  #     | email    | john.doe@example.com |
  #     | password | password123          |
  #   When user send post request with endpoint "/api/login"
  #   Then validate the status code as 200
  #   And validate json path "message" is "Login successful"
  #   And user store the accesstoken

  # Scenario: login with invalid credential
  #   Given user set base uri
  #   And user set content type as "application/json"
  #   And user added body
  #     | email    | wrong@email.com |
  #     | password | wrongpassword   |
  #   When user send post request with endpoint "/api/login"
  #   Then validate the status code as 401

  # Scenario: get current user detail
  #   Given user set base uri
  #   And user added header for ContentType
  #   And user added header for Authorization
  #   When user send get request with endpoint "/api/me"
  #   Then validate the status code as 200

  Scenario: create a user
    Given user set base uri
    And user set content type as "application/json"
    And user added header for Authorization
    And user set body from json file "users" as "validUser"
    When user send post request with endpoint "/api/users"
    Then validate the status code as 201
    And validate json path "message" is "users created successfully"
    And validate schema from json file "/payload/createUser"


  # Scenario: delete a user
  #   Given user set base uri
  #   And user added header for Authorization
  #   When user send delete request with endpoint "/api/users/me"
  #   Then validate the status code as 200

  # Scenario Outline: create a user
  #   Given user set base uri
  #   And user set content type as "application/json"
  #   And user added header for Authorization
  #   And user added body
  #     | username   | <username>   |
  #     | email      | <email>      |
  #     | password   | <password>   |
  #     | firstName  | <firstName>  |
  #     | lastName   | <lastName>   |
  #     | role       | <role>       |
  #     | department | <department> |
  #     | phone      | <phone>      |
  #   When user send post request with endpoint "/api/users"
  #   Then validate the status code as <statusCode>

  #   Examples:
  #     | username | email               | password     | firstName | lastName | role      | department | phone        | statusCode |
  #     | pawan2   | pawan2@example.com | password123  | pawan2    | p2       | SALES_REP | Sales      | +1234567890  | 201        |
