package gherkin_style.framework_style;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.config.EncoderConfig.encoderConfig;

public class OauthTokenTest {
    static final String BASE_URL = "http://localhost:3000";

    @Test
    public void getOauthToken() {
        RestAssured.baseURI = BASE_URL;

        RestAssured.config = RestAssured.config()
                .encoderConfig(encoderConfig()
                        .defaultContentCharset("UTF-8")
                        .defaultCharsetForContentType("UTF-8", "application/x-www-form-urlencoded"));

        RequestSpecification requestSpec = RestAssured.given();
        requestSpec.contentType("application/x-www-form-urlencoded");
        requestSpec.formParam("grant_type", "password");
        requestSpec.formParam("client_id", "crm-mock-consumer");
        requestSpec.formParam("client_secret", "crm-mock-consumer-secret");
        requestSpec.formParam("username", "jdoe");
        requestSpec.formParam("password", "password123");
        requestSpec.formParam("refresh_token", "550e8400-e29b-41d4-a716-446655440030");
        requestSpec.formParam("scope", "openid profile email");

        Response response = requestSpec.post("/api/oauth/token");

        System.out.println(response.asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
