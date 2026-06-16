package stepdefinitions;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import utilClass.PropertiesHandler;

public class Apisteps {
    static final String BASE_URL = "http://localhost:3000";
    RequestSpecification requestspec;
    Response response;
    static String token;
    PropertiesHandler properties=new PropertiesHandler();

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

    @Given("user set base uri")
    public void user_set_base_uri() {
        RestAssured.baseURI = properties.getPropertiesValueByKey("commonData", "BaseUri");

        requestspec = RestAssured.given();
    }

    @Given("user added header for ContentType")
    public void user_added_header_as_and() {
        System.out.println("adding contenttyep on header"+properties.getPropertiesValueByKey("commonData", "ContentType"));
        requestspec.header("Content-Type", properties.getPropertiesValueByKey("commonData", "ContentType"));
    }

     @Given("user added header for Authorization")
    public void user_adand() {
        requestspec.header("Authorization", "Bearer "+properties.getPropertiesValueByKey("commonData", "access_token"));
        
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
        System.out.println(requestspec.log().all());
        response = requestspec.get(endpoint);
    }

     @When("user store the accesstoken")
    public void user_storer_access_token() {
        token=response.jsonPath().getString("data.accessToken");
        properties.setProperties("commonData", "access_token", token);
        System.out.println("============================="+token+"===============================");
    }

    @Then("validate the status code as {int}")
    public void validate_the_status_code_as(Integer int1) {
        System.out.println(response.asPrettyString());
        Assert.assertTrue(response.getStatusCode() == int1, "status code does not match" +response.getStatusCode() );
    }

    @Then("validate schema from json file {string}")
    public void validate_schema_from_json_file(String string) {
        response.then().assertThat().body(matchesJsonSchema(new File("src/test/resources/payload/loginSchema.json")));
}
}
