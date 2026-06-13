package gherkin_style.crm_gherkin;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Login_gherkin_jsonSting {

    @Test
    public void loginTest() {

        String requestBody = """
                {
                  "email": "myrtle.streich9@yahoo.com",
                  "password": "password123"
                }
                """;

        Response response =
                given().baseUri("http://localhost:3000")
                        .contentType("application/json")
                        .body(requestBody)
                .when()
                        .post("/api/login");

        System.out.println(response.asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
