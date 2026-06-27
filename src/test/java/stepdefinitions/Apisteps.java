package stepdefinitions;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import com.github.javafaker.Faker;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pojo.LoginPojo;
import utilClass.GenerateToken;
import utilClass.PropertiesHandler;
import DriverManager.RestDriver;

public class Apisteps {

    RestDriver restDriver = new RestDriver();
    static String token;

    // =========================================================================
    // Setup / Configuration
    // =========================================================================

    @Given("user set base uri")
    public void user_set_base_uri() {
        String env = System.getProperty("environment");
        restDriver.setBaseUri(PropertiesHandler.getPropertiesValueByKey("commonData", "BaseUri"));
        restDriver.createRequest();
    }

    @Given("user set base uri as {string}")
    public void user_set_base_uri_as(String baseUri) {
        restDriver.setBaseUri(baseUri);
        restDriver.createRequest();
    }

    @Given("user set content type as {string}")
    public void user_set_content_type(String contentType) {
        restDriver.setContentType(contentType);
    }

    @Given("user enable logging")
    public void user_enable_logging() {
        restDriver.enableLogging();
    }

    // =========================================================================
    // Headers
    // =========================================================================

    @Given("user added header for ContentType")
    public void user_added_header_for_contenttype() {
        System.out.println("adding contenttype on header "
                + PropertiesHandler.getPropertiesValueByKey("commonData", "ContentType"));
        restDriver.setHeader("Content-Type", PropertiesHandler.getPropertiesValueByKey("commonData", "ContentType"));
    }

    @Given("user added header")
    public void user_added_header(DataTable table) {
        Map<String, String> headers = table.asMap(String.class, String.class);
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            restDriver.setHeader(entry.getKey(), entry.getValue());
        }
    }

    @Given("user added header {string} with value {string}")
    public void user_added_header_with_value(String name, String value) {
        restDriver.setHeader(name, value);
    }

    @Given("user added header for Authorization")
    public void user_added_header_for_authorization() {
        restDriver.setBearerToken(GenerateToken.getToken());
    }

    @Given("user set bearer token {string}")
    public void user_set_bearer_token(String value) {
        restDriver.setBearerToken(value);
    }

    @Given("user set basic auth with username {string} and password {string}")
    public void user_set_basic_auth(String username, String password) {
        restDriver.setBasicAuth(username, password);
    }

    // =========================================================================
    // Request Body
    // =========================================================================

    @Given("the added body as {string} and {string}")
    public void the_added_body_as_and(String email, String password) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("email", email);
        body.put("password", password);
        restDriver.setBody(body);
    }

    @Given("user added body")
    public void user_added_body(DataTable table) {
        Map<String, Object> body = new LinkedHashMap<>();
        Map<String, String> tableData = table.asMap(String.class, String.class);
        for (Map.Entry<String, String> entry : tableData.entrySet()) {
            body.put(entry.getKey(), entry.getValue());
        }
        restDriver.setBody(body);
    }

    @Given("user registeration with random data")
    public void createuserwithRandomData() {
        Map<String, Object> user = new LinkedHashMap<>();
        Faker faker=new Faker();
        String username="pawan"+faker.number().digits(5);
        String email=faker.internet().emailAddress();
        String firstName=faker.name().firstName();
        String lastName=faker.name().lastName();
        String phone=faker.number().digits(10);
        user.put("username", username);
        user.put("email", email);
        user.put("password", "password123");
        user.put("firstName", firstName);
        user.put("lastName", lastName);
        user.put("role", "SALES_REP");
        user.put("department", "Sales");
        user.put("phone", phone);
    
        restDriver.setBody(user);
    }

    @Given("user registeration with random data pojo")
    public void createuserwithRandomDataPojo() {
        Map<String, Object> user = new LinkedHashMap<>();
        Faker faker=new Faker();
        String username="pawan"+faker.number().digits(5);
        String email=faker.internet().emailAddress();
        String firstName=faker.name().firstName();
        String lastName=faker.name().lastName();
        String phone=faker.number().digits(10);
        user.put("username", username);
        user.put("email", email);
        user.put("password", "password123");
        user.put("firstName", firstName);
        user.put("lastName", lastName);
        user.put("role", "SALES_REP");
        user.put("department", "Sales");
        user.put("phone", phone);

        LoginPojo pojo=new LoginPojo();
        pojo.setEmail(email);
    
        restDriver.setBody(user);
    }

    @Given("user added body as json string")
    public void user_added_body_as_json_string(String body) {
        restDriver.setBody(body);
    }

    @Given("user added body from file {string}")
    public void user_added_body_from_file(String filePath) {
        restDriver.setBody(new File(filePath));
    }

    // =========================================================================
    // Query / Path / Form Parameters
    // =========================================================================

    @Given("user added query param {string} with value {string}")
    public void user_added_query_param(String key, String value) {
        restDriver.setQueryParam(key, value);
    }

    @Given("user added query params")
    public void user_added_query_params(DataTable table) {
        Map<String, String> params = table.asMap(String.class, String.class);
        restDriver.setQueryParams(params);
    }

    @Given("user added path param {string} with value {string}")
    public void user_added_path_param(String key, String value) {
        restDriver.setPathParam(key, value);
    }

    @Given("user added form param {string} with value {string}")
    public void user_added_form_param(String key, String value) {
        restDriver.setFormParam(key, value);
    }

    @Given("user added form params")
    public void user_added_form_params(DataTable table) {
        Map<String, String> params = table.asMap(String.class, String.class);
        restDriver.setFormParams(params);
    }

    // =========================================================================
    // HTTP Methods
    // =========================================================================

    @When("the user provide with email and password")
    public void the_user_provide_with_email_and_password() {
        restDriver.post("/api/login");
    }

    @When("user send get request with endpoint {string}")
    public void user_send_get_request_with_endpoint(String endpoint) {
        restDriver.get(endpoint);
    }

    @When("user send post request with endpoint {string}")
    public void user_send_post_request_with_endpoint(String endpoint) {
        restDriver.post(endpoint);
    }

    @When("user send put request with endpoint {string}")
    public void user_send_put_request_with_endpoint(String endpoint) {
        restDriver.put(endpoint);
    }

    @When("user send patch request with endpoint {string}")
    public void user_send_patch_request_with_endpoint(String endpoint) {
        restDriver.patch(endpoint);
    }

    @When("user send delete request with endpoint {string}")
    public void user_send_delete_request_with_endpoint(String endpoint) {
        restDriver.delete(endpoint);
    }

    // =========================================================================
    // Token / Response Storage
    // =========================================================================

    @When("user store the accesstoken")
    public void user_storer_access_token() {
        token = restDriver.getJsonPathValue("data.accessToken");
        PropertiesHandler.setProperties("commonData", "access_token", token);
        System.out.println("=============================" + token + "===============================");
    }

    @When("user store json path {string} as {string}")
    public void user_store_json_path_as(String jsonPath, String propertyKey) {
        String value = restDriver.getJsonPathValue(jsonPath);
        PropertiesHandler.setProperties("commonData", propertyKey, value);
        System.out.println("Stored " + jsonPath + " = " + value);
    }

    // =========================================================================
    // Validation
    // =========================================================================

    @Then("validate the status code")
    public void validate_the_status_code() {
        System.out.println(restDriver.getPrettyResponseBody());
        restDriver.validateStatusCode(200);
    }

    @Then("validate the status code as {int}")
    public void validate_the_status_code_as(Integer expectedStatusCode) {
        System.out.println(restDriver.getPrettyResponseBody());
        restDriver.validateStatusCode(expectedStatusCode);
    }

    @Then("validate schema from json file {string}")
    public void validate_schema_from_json_file(String path) {
        restDriver.validateResponseMatchesSchema("src/test/resources/"+path+".json");
    }

    @Then("validate json path {string} is {string}")
    public void validate_json_path_value(String jsonPath, String expectedValue) {
        restDriver.validateJsonPath(jsonPath, expectedValue);
    }

    @Then("validate json path {string} is {int}")
    public void validate_json_path_int(String jsonPath, Integer expectedValue) {
        restDriver.validateJsonPath(jsonPath, expectedValue);
    }

    @Then("validate response body contains {string}")
    public void validate_response_body_contains(String content) {
        restDriver.validateBodyContains(content);
    }

    @Then("validate response header {string} is {string}")
    public void validate_response_header(String headerName, String expectedValue) {
        restDriver.validateHeader(headerName, expectedValue);
    }

    @Then("validate response time less than {int} ms")
    public void validate_response_time(long maxMillis) {
        restDriver.validateResponseTime(maxMillis);
    }

    @Then("print response")
    public void print_response() {
        System.out.println(restDriver.getPrettyResponseBody());
    }
}
