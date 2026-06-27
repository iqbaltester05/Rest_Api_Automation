package gherkin_style.framework_style;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilClass.PropertiesHandler;

import static io.restassured.config.EncoderConfig.encoderConfig;

public class OauthTokenTest {

    @Test
    public void getOauthToken() {
        PropertiesHandler properties=new PropertiesHandler();
        RestAssured.baseURI = properties.getPropertiesValueByKey("commonData", "BaseUri");

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
        String token=response.body().jsonPath().getString("access_token");
        System.out.println(token);
        System.out.println(response.asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
