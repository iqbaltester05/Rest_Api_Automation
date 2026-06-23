package DriverManager;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

import java.io.File;
import java.util.List;
import java.util.Map;

public class RestDriver {

    private final RestManager restManager;

    public RestDriver() {
        this.restManager = new RestManager();
    }

    // =========================================================================
    // Setup
    // =========================================================================

    public void setBaseUri(String baseUri) {
        restManager.setBaseUri(baseUri);
    }

    public void createRequest() {
        restManager.createRequest();
    }

    public void enableLogging() {
        restManager.enableLogging();
    }

    public void setConfigForFormUrlencoded() {
        restManager.setConfigForFormUrlencoded();
    }

    // =========================================================================
    // Headers
    // =========================================================================

    public void setHeader(String key, String value) {
        restManager.setHeader(key, value);
    }

    public void setHeaders(Map<String, String> headers) {
        restManager.setHeaders(headers);
    }

    public void setContentType(String contentType) {
        restManager.setContentType(contentType);
    }

    public void setContentType(ContentType contentType) {
        restManager.setContentType(contentType);
    }

    public void setAccept(String accept) {
        restManager.setAccept(accept);
    }

    // =========================================================================
    // Authentication
    // =========================================================================

    public void setBearerToken(String token) {
        restManager.setBearerToken(token);
    }

    public void setBasicAuth(String username, String password) {
        restManager.setBasicAuth(username, password);
    }

    public void setOAuth2(String accessToken) {
        restManager.setOAuth2(accessToken);
    }

    public void setOAuth1(String consumerKey, String consumerSecret,
                          String accessToken, String tokenSecret) {
        restManager.setOAuth1(consumerKey, consumerSecret, accessToken, tokenSecret);
    }

    // =========================================================================
    // Request Body
    // =========================================================================

    public void setBody(String body) {
        restManager.setBody(body);
    }

    public void setBody(Object pojo) {
        restManager.setBody(pojo);
    }

    public void setBody(Map<String, ?> body) {
        restManager.setBody(body);
    }

    public void setBody(File body) {
        restManager.setBody(body);
    }

    public void setBody(byte[] body) {
        restManager.setBody(body);
    }

    // =========================================================================
    // Parameters
    // =========================================================================

    public void setFormParam(String key, String value) {
        restManager.setFormParam(key, value);
    }

    public void setFormParams(Map<String, String> params) {
        restManager.setFormParams(params);
    }

    public void setMultiPart(String controlName, File file) {
        restManager.setMultiPart(controlName, file);
    }

    public void setMultiPart(String controlName, String contentBody, String mimeType) {
        restManager.setMultiPart(controlName, contentBody, mimeType);
    }

    public void setQueryParam(String key, String value) {
        restManager.setQueryParam(key, value);
    }

    public void setQueryParams(Map<String, String> params) {
        restManager.setQueryParams(params);
    }

    public void setPathParam(String key, String value) {
        restManager.setPathParam(key, value);
    }

    public void setPathParams(Map<String, String> params) {
        restManager.setPathParams(params);
    }

    // =========================================================================
    // HTTP Methods
    // =========================================================================

    public Response get(String endpoint) {
        return restManager.get(endpoint);
    }

    public Response post(String endpoint) {
        return restManager.post(endpoint);
    }

    public Response put(String endpoint) {
        return restManager.put(endpoint);
    }

    public Response patch(String endpoint) {
        return restManager.patch(endpoint);
    }

    public Response delete(String endpoint) {
        return restManager.delete(endpoint);
    }

    public Response head(String endpoint) {
        return restManager.head(endpoint);
    }

    public Response options(String endpoint) {
        return restManager.options(endpoint);
    }

    public Response request(Method method, String endpoint) {
        return restManager.request(method, endpoint);
    }

    // =========================================================================
    // Response Accessors
    // =========================================================================

    public Response getResponse() {
        return restManager.getResponse();
    }

    public int getStatusCode() {
        return restManager.getStatusCode();
    }

    public String getStatusLine() {
        return restManager.getStatusLine();
    }

    public String getResponseBody() {
        return restManager.getResponseBody();
    }

    public String getPrettyResponseBody() {
        return restManager.getPrettyResponseBody();
    }

    public byte[] getResponseBodyAsBytes() {
        return restManager.getResponseBodyAsBytes();
    }

    public Map<String, String> getResponseHeaders() {
        return restManager.getResponseHeaders();
    }

    public String getResponseHeader(String name) {
        return restManager.getResponseHeader(name);
    }

    public String getContentType() {
        return restManager.getContentType();
    }

    public long getResponseTime() {
        return restManager.getResponseTime();
    }

    public String getSessionId() {
        return restManager.getSessionId();
    }

    public String getCookie(String name) {
        return restManager.getCookie(name);
    }

    public Map<String, String> getCookies() {
        return restManager.getCookies();
    }

    // =========================================================================
    // JSON Path Extraction
    // =========================================================================

    public String getJsonPathValue(String jsonPath) {
        return restManager.getJsonPathValue(jsonPath);
    }

    public <T> T getJsonPathValue(String jsonPath, Class<T> type) {
        return restManager.getJsonPathValue(jsonPath, type);
    }

    public <T> List<T> getJsonPathList(String jsonPath, Class<T> type) {
        return restManager.getJsonPathList(jsonPath, type);
    }

    public int getJsonPathInt(String jsonPath) {
        return restManager.getJsonPathInt(jsonPath);
    }

    public boolean getJsonPathBoolean(String jsonPath) {
        return restManager.getJsonPathBoolean(jsonPath);
    }

    // =========================================================================
    // Response Deserialization
    // =========================================================================

    public <T> T extractResponseAs(Class<T> responseClass) {
        return restManager.extractResponseAs(responseClass);
    }

    public <T> T extractResponseAs(Class<T> responseClass, String jsonPath) {
        return restManager.extractResponseAs(responseClass, jsonPath);
    }

    // =========================================================================
    // Validation
    // =========================================================================

    public void validateStatusCode(int expectedStatusCode) {
        restManager.validateStatusCode(expectedStatusCode);
    }

    public void validateStatusCodeMatchesAny(Integer... statusCodes) {
        restManager.validateStatusCodeMatchesAny(statusCodes);
    }

    public void validateResponseTime(long maxMillis) {
        restManager.validateResponseTime(maxMillis);
    }

    public void validateHeader(String headerName, String expectedValue) {
        restManager.validateHeader(headerName, expectedValue);
    }

    public void validateBodyContains(String expectedContent) {
        restManager.validateBodyContains(expectedContent);
    }

    public void validateBodyEqualTo(String expectedBody) {
        restManager.validateBodyEqualTo(expectedBody);
    }

    public void validateJsonPath(String jsonPath, Object expectedValue) {
        restManager.validateJsonPath(jsonPath, expectedValue);
    }

    public void validateResponseMatchesSchema(File schemaFile) {
        restManager.validateResponseMatchesSchema(schemaFile);
    }

    public void validateResponseMatchesSchema(String schemaFilePath) {
        restManager.validateResponseMatchesSchema(schemaFilePath);
    }

    public void validateWithResponseSpec() {
        restManager.validateWithResponseSpec();
    }

    public void validateWithResponseSpec(ResponseSpecification spec) {
        restManager.validateWithResponseSpec(spec);
    }

    // =========================================================================
    // Log Response
    // =========================================================================

    public void logResponse() {
        restManager.logResponse();
    }

    public void logResponseIfValidationFails() {
        restManager.logResponseIfValidationFails();
    }

    // =========================================================================
    // Static Convenience Methods
    // =========================================================================

    public static Response get(String baseUri, String endpoint) {
        return RestManager.get(baseUri, endpoint);
    }

    public static Response get(String baseUri, String endpoint, Map<String, String> headers) {
        return RestManager.get(baseUri, endpoint, headers);
    }

    public static Response post(String baseUri, String endpoint, Object body) {
        return RestManager.post(baseUri, endpoint, body);
    }

    public static Response post(String baseUri, String endpoint, Object body, Map<String, String> headers) {
        return RestManager.post(baseUri, endpoint, body, headers);
    }

    public static Response put(String baseUri, String endpoint, Object body) {
        return RestManager.put(baseUri, endpoint, body);
    }

    public static Response delete(String baseUri, String endpoint) {
        return RestManager.delete(baseUri, endpoint);
    }

    public static Response delete(String baseUri, String endpoint, Map<String, String> headers) {
        return RestManager.delete(baseUri, endpoint, headers);
    }
}
