package gherkin_style.crm_gherkin;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Login_gherkin_jsonFile {

 //   @Test
    public void loginTest() {

        File body = new File("src/test/java/json/login_file.json");

        Response response =
                given().baseUri("http://localhost:3000")
                        .contentType("application/json")
                        .body(body)
                .when()
                        .post("/api/login");

        System.out.println(response.asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 200);
    }
}