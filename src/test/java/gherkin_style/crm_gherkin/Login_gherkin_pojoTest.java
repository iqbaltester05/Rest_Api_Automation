package gherkin_style.crm_gherkin;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import pojo.LoginPojo;

public class Login_gherkin_pojoTest {

//    @Test
    public void loginTest() {

        LoginPojo request = new LoginPojo();

        request.setEmail("myrtle.streich9@yahoo.com");
        request.setPassword("password123");

        Response response =
                given().baseUri("http://localhost:3000")
                        .contentType("application/json")
                        .body(request)
                .when()
                        .post("/api/login");

        System.out.println(response.asPrettyString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}