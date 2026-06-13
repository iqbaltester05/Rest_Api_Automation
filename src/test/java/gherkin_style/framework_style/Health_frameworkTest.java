package gherkin_style.framework_style;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Health_frameworkTest {
    static final String BASE_URL="http://localhost:3000";

    // @Test
    public void healthStatus() {
        RestAssured.baseURI=BASE_URL;

        RequestSpecification requestSpecification=RestAssured.given();
        Response response=requestSpecification.get("/api/health");
       
        int statuscode=response.statusCode();
        System.out.println(response.asPrettyString());

        String statusmessage=response.jsonPath().get("status");
        int opertunitynumbers=response.jsonPath().get("database.opportunities");

        Assert.assertTrue(opertunitynumbers==1000,"opertunity does not match expected numbers");
        Assert.assertEquals(statusmessage, "healthy");
        Assert.assertTrue(statuscode==200,"status code is not 200");
    }
}
