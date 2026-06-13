package stepdefinitions;

import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Apisteps {
    static final String BASE_URL = "http://localhost:3000";
    RequestSpecification requestspec;
    Response response;
    static String token;

    @Given("user set the restassured")
    public void user_set_the_restassured() {
        RestAssured.baseURI = BASE_URL;

        requestspec = RestAssured.given();

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("email", "myrtle.streich9@yahoo.com");
        body.put("password", "password123");
        requestspec.header("Content-Type", "application/json");
        requestspec.body(body);
    }

    @When("the user provide with email and password")
    public void the_user_provide_with_email_and_password() {
        response = requestspec.post("/api/login");
    }

    @Then("validate the status code")
    public void validate_the_status_code() {
        System.out.println(response.asPrettyString());
        Assert.assertTrue(response.getStatusCode() == 200, "status code does not match");
    }

    @Given("user set base uri {string}")
    public void user_set_base_uri(String url) {
        RestAssured.baseURI = url;

        requestspec = RestAssured.given();

       
    }

    @Given("user added header as {string} and {string}")
    public void user_added_header_as_and(String key, String value) {
        requestspec.header(key, value);
        
    }

     @Given("user added header for Authorization")
    public void user_adand() {
        requestspec.header("Authorization", "Bearer "+token);
        
    }

    @Given("the added body as {string} and {string}")
    public void the_added_body_as_and(String email, String password) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("email", email);
        body.put("password", password);
        requestspec.body(body);
    }

    @When("user send post request with endpoint {string}")
    public void user_send_post_request_with_endpoint(String endpoint) {
       
        response = requestspec.post(endpoint);
        
    }

     @When("user send get request with endpoint {string}")
    public void user_get_post_request_with_endpoint(String endpoint) {
         requestspec.log().all();
        response = requestspec.get(endpoint);
    }

     @When("user store the accesstoken")
    public void user_storer_access_token() {
        token=response.jsonPath().getString("data.accessToken");
        System.out.println("============================="+token+"===============================");
    }

    @Then("validate the status code as {int}")
    public void validate_the_status_code_as(Integer int1) {
        System.out.println(response.asPrettyString());
        Assert.assertTrue(response.getStatusCode() == 200, "status code does not match" +response.getStatusCode() );
    }
}
