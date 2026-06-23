package DriverManager;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class RestManager {

    private RequestSpecification requestSpec;
    private ResponseSpecification responseSpec;
    private Response response;

    // =========================================================================
    // Base URI
    // =========================================================================

    public void setBaseUri(String baseUri) {
        RestAssured.baseURI = baseUri;
    }

    public String getBaseUri() {
        return RestAssured.baseURI;
    }

    // =========================================================================
    // Request Specification
    // =========================================================================

    public void createRequest() {
        requestSpec = given();
    }

    public void setRequestSpec(RequestSpecification spec) {
        this.requestSpec = spec;
    }

    public RequestSpecification getRequestSpec() {
        return requestSpec;
    }

    public RequestSpecBuilder getRequestSpecBuilder() {
        return new RequestSpecBuilder();
    }

    public void buildRequest(RequestSpecBuilder builder) {
        requestSpec = builder.build();
    }

    // =========================================================================
    // Response Specification
    // =========================================================================

    public void setResponseSpec(ResponseSpecification spec) {
        this.responseSpec = spec;
    }

    public ResponseSpecification getResponseSpec() {
        return responseSpec;
    }

    public ResponseSpecBuilder getResponseSpecBuilder() {
        return new ResponseSpecBuilder();
    }

    public void buildResponseSpec(ResponseSpecBuilder builder) {
        responseSpec = builder.build();
    }

    // =========================================================================
    // Configuration
    // =========================================================================

    public void setConfigForFormUrlencoded() {
        RestAssured.config = RestAssured.config()
                .encoderConfig(encoderConfig()
                        .defaultContentCharset("UTF-8")
                        .defaultCharsetForContentType("UTF-8", "application/x-www-form-urlencoded"));
    }

    public void setEncoderConfig(String charset, String contentType) {
        RestAssured.config = RestAssured.config()
                .encoderConfig(encoderConfig()
                        .defaultContentCharset(charset)
                        .defaultCharsetForContentType(charset, contentType));
    }

    // =========================================================================
    // Logging
    // =========================================================================

    public void enableRequestLogging() {
        requestSpec.filter(new RequestLoggingFilter());
    }

    public void enableResponseLogging() {
        requestSpec.filter(new ResponseLoggingFilter());
    }

    public void enableLogging() {
        requestSpec.filter(new RequestLoggingFilter());
        requestSpec.filter(new ResponseLoggingFilter());
    }

    // =========================================================================
    // Headers
    // =========================================================================

    public void setHeader(String key, String value) {
        requestSpec.header(key, value);
    }

    public void setHeaders(Map<String, String> headers) {
        requestSpec.headers(headers);
    }

    public void setContentType(String contentType) {
        requestSpec.contentType(contentType);
    }

    public void setContentType(ContentType contentType) {
        requestSpec.contentType(contentType);
    }

    public void setAccept(String accept) {
        requestSpec.accept(accept);
    }

    // =========================================================================
    // Authentication
    // =========================================================================

    public void setBearerToken(String token) {
        requestSpec.header("Authorization", "Bearer " + token);
    }

    public void setBasicAuth(String username, String password) {
        requestSpec.auth().preemptive().basic(username, password);
    }

    public void setOAuth2(String accessToken) {
        requestSpec.auth().oauth2(accessToken);
    }

    public void setOAuth1(String consumerKey, String consumerSecret,
                          String accessToken, String tokenSecret) {
        requestSpec.auth().oauth(consumerKey, consumerSecret, accessToken, tokenSecret);
    }

    // =========================================================================
    // Request Body
    // =========================================================================

    public void setBody(String body) {
        requestSpec.body(body);
    }

    public void setBody(Object pojo) {
        requestSpec.body(pojo);
    }

    public void setBody(Map<String, ?> body) {
        requestSpec.body(body);
    }

    public void setBody(File body) {
        requestSpec.body(body);
    }

    public void setBody(byte[] body) {
        requestSpec.body(body);
    }

    // =========================================================================
    // Form Parameters (x-www-form-urlencoded)
    // =========================================================================

    public void setFormParam(String key, String value) {
        requestSpec.formParam(key, value);
    }

    public void setFormParams(Map<String, String> params) {
        requestSpec.formParams(params);
    }

    // =========================================================================
    // Multi-part
    // =========================================================================

    public void setMultiPart(String controlName, File file) {
        requestSpec.multiPart(controlName, file);
    }

    public void setMultiPart(String controlName, String contentBody, String mimeType) {
        requestSpec.multiPart(controlName, contentBody, mimeType);
    }

    // =========================================================================
    // Query Parameters
    // =========================================================================

    public void setQueryParam(String key, String value) {
        requestSpec.queryParam(key, value);
    }

    public void setQueryParams(Map<String, String> params) {
        requestSpec.queryParams(params);
    }

    // =========================================================================
    // Path Parameters
    // =========================================================================

    public void setPathParam(String key, String value) {
        requestSpec.pathParam(key, value);
    }

    public void setPathParams(Map<String, String> params) {
        requestSpec.pathParams(params);
    }

    // =========================================================================
    // HTTP Methods
    // =========================================================================

    public Response get(String endpoint) {
        response = requestSpec.get(endpoint);
        return response;
    }

    public Response post(String endpoint) {
        response = requestSpec.post(endpoint);
        return response;
    }

    public Response put(String endpoint) {
        response = requestSpec.put(endpoint);
        return response;
    }

    public Response patch(String endpoint) {
        response = requestSpec.patch(endpoint);
        return response;
    }

    public Response delete(String endpoint) {
        response = requestSpec.delete(endpoint);
        return response;
    }

    public Response head(String endpoint) {
        response = requestSpec.head(endpoint);
        return response;
    }

    public Response options(String endpoint) {
        response = requestSpec.options(endpoint);
        return response;
    }

    public Response request(Method method, String endpoint) {
        response = requestSpec.request(method, endpoint);
        return response;
    }

    // =========================================================================
    // Response Accessors
    // =========================================================================

    public Response getResponse() {
        return response;
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public String getStatusLine() {
        return response.getStatusLine();
    }

    public String getResponseBody() {
        return response.getBody().asString();
    }

    public String getPrettyResponseBody() {
        return response.asPrettyString();
    }

    public byte[] getResponseBodyAsBytes() {
        return response.getBody().asByteArray();
    }

    public Map<String, String> getResponseHeaders() {
        return response.getHeaders().asList().stream()
                .collect(Collectors.toMap(
                        Header::getName,
                        Header::getValue,
                        (v1, v2) -> v2));
    }

    public String getResponseHeader(String name) {
        return response.getHeader(name);
    }

    public String getContentType() {
        return response.getContentType();
    }

    public long getResponseTime() {
        return response.getTime();
    }

    public String getSessionId() {
        return response.getSessionId();
    }

    public String getCookie(String name) {
        return response.getCookie(name);
    }

    public Map<String, String> getCookies() {
        return response.getCookies();
    }

    // =========================================================================
    // JSON Path Extraction
    // =========================================================================

    public String getJsonPathValue(String jsonPath) {
        return response.jsonPath().getString(jsonPath);
    }

    public <T> T getJsonPathValue(String jsonPath, Class<T> type) {
        return response.jsonPath().getObject(jsonPath, type);
    }

    public <T> List<T> getJsonPathList(String jsonPath, Class<T> type) {
        return response.jsonPath().getList(jsonPath, type);
    }

    public int getJsonPathInt(String jsonPath) {
        return response.jsonPath().getInt(jsonPath);
    }

    public boolean getJsonPathBoolean(String jsonPath) {
        return response.jsonPath().getBoolean(jsonPath);
    }

    // =========================================================================
    // Response Deserialization
    // =========================================================================

    public <T> T extractResponseAs(Class<T> responseClass) {
        return response.as(responseClass);
    }

    public <T> T extractResponseAs(Class<T> responseClass, String jsonPath) {
        return response.jsonPath().getObject(jsonPath, responseClass);
    }

    // =========================================================================
    // Validation
    // =========================================================================

    public void validateStatusCode(int expectedStatusCode) {
        org.hamcrest.MatcherAssert.assertThat(
                "Status code mismatch",
                response.getStatusCode(),
                org.hamcrest.Matchers.equalTo(expectedStatusCode));
    }

    public void validateStatusCodeMatchesAny(Integer... statusCodes) {
        org.hamcrest.MatcherAssert.assertThat(
                "Status code does not match any expected value",
                response.getStatusCode(),
                org.hamcrest.Matchers.in(statusCodes));
    }

    public void validateResponseTime(long maxMillis) {
        response.then().time(org.hamcrest.Matchers.lessThan(maxMillis));
    }

    public void validateHeader(String headerName, String expectedValue) {
        response.then().header(headerName, expectedValue);
    }

    public void validateBodyContains(String expectedContent) {
        response.then().body(org.hamcrest.Matchers.containsString(expectedContent));
    }

    public void validateBodyEqualTo(String expectedBody) {
        response.then().body(org.hamcrest.Matchers.equalTo(expectedBody));
    }

    public void validateJsonPath(String jsonPath, Object expectedValue) {
        response.then().body(jsonPath, org.hamcrest.Matchers.equalTo(expectedValue));
    }

    public void validateResponseMatchesSchema(File schemaFile) {
        response.then().assertThat().body(matchesJsonSchema(schemaFile));
    }

    public void validateResponseMatchesSchema(String schemaFilePath) {
        response.then().assertThat().body(matchesJsonSchema(new File(schemaFilePath)));
    }

    public void validateWithResponseSpec() {
        if (responseSpec != null) {
            response.then().spec(responseSpec);
        }
    }

    public void validateWithResponseSpec(ResponseSpecification spec) {
        response.then().spec(spec);
    }

    // =========================================================================
    // Log Response
    // =========================================================================

    public void logResponse() {
        response.then().log().all();
    }

    public void logResponseIfValidationFails() {
        response.then().log().ifValidationFails();
    }

    // =========================================================================
    // Static Convenience Methods (Fluent One-Liners)
    // =========================================================================

    public static Response get(String baseUri, String endpoint) {
        return given().baseUri(baseUri).when().get(endpoint).then().extract().response();
    }

    public static Response get(String baseUri, String endpoint, Map<String, String> headers) {
        return given().baseUri(baseUri).headers(headers).when().get(endpoint).then().extract().response();
    }

    public static Response post(String baseUri, String endpoint, Object body) {
        return given().baseUri(baseUri)
                .contentType(ContentType.JSON)
                .body(body)
                .when().post(endpoint)
                .then().extract().response();
    }

    public static Response post(String baseUri, String endpoint, Object body, Map<String, String> headers) {
        return given().baseUri(baseUri)
                .headers(headers)
                .body(body)
                .when().post(endpoint)
                .then().extract().response();
    }

    public static Response put(String baseUri, String endpoint, Object body) {
        return given().baseUri(baseUri)
                .contentType(ContentType.JSON)
                .body(body)
                .when().put(endpoint)
                .then().extract().response();
    }

    public static Response delete(String baseUri, String endpoint) {
        return given().baseUri(baseUri).when().delete(endpoint).then().extract().response();
    }

    public static Response delete(String baseUri, String endpoint, Map<String, String> headers) {
        return given().baseUri(baseUri).headers(headers).when().delete(endpoint).then().extract().response();
    }
}
