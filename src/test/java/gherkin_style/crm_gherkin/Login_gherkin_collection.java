package gherkin_style.crm_gherkin;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Login_gherkin_collection {
    public static void main(String[] args) {
        Map<String, Object> body = new HashMap<>();
        RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost:3000")
            .setContentType("application/json")
            // .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6Ijg3NDQyNTY2LWY5OGYtNDVmYS1hYWZmLTUxNDAwNjhmMTU5ZSIsImVtYWlsIjoibXlydGxlLnN0cmVpY2g5QHlhaG9vLmNvbSIsInJvbGUiOiJBRE1JTiIsInVzZXJuYW1lIjoibXlydGxlLnN0cmVpY2gxIiwiaWF0IjoxNzgxMDM1MTM1LCJleHAiOjE3ODEwMzg3MzV9.l3HRIK760gqTn_ASgNIQOSAWy2Y_VVxE6lHXbaAgU04")
            .build();

        ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

        body.put("email", "john.doe@example.com");
        body.put("password", "password123");

        given()
            .spec(requestSpec)
            .body(body)
            .when()
            .post("/api/login")
            .then()
            .spec(responseSpec)
            .log().all();
    }
}
