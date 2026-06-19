package gherkin_style.crm_gherkin;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import model.response.LoginResponseJava;
import model.response.LoginResponseLombok;

public class Login_gherkin_deserialization {
    public static void main(String[] args) {
        Map<String, Object> body = new HashMap<>();
        RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("https://crm-backend-6vcr.onrender.com")
            .setContentType("application/json")
            // .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6Ijg3NDQyNTY2LWY5OGYtNDVmYS1hYWZmLTUxNDAwNjhmMTU5ZSIsImVtYWlsIjoibXlydGxlLnN0cmVpY2g5QHlhaG9vLmNvbSIsInJvbGUiOiJBRE1JTiIsInVzZXJuYW1lIjoibXlydGxlLnN0cmVpY2gxIiwiaWF0IjoxNzgxMDM1MTM1LCJleHAiOjE3ODEwMzg3MzV9.l3HRIK760gqTn_ASgNIQOSAWy2Y_VVxE6lHXbaAgU04")
            .build();

        body.put("email", "john.doe@example.com");
        body.put("password", "password123");

        LoginResponseLombok loginData = given()
            .spec(requestSpec)
            .body(body)
            .when()
            .post("/api/login")
            .then().log().all()
            .extract().as(LoginResponseLombok.class);

        System.out.println(loginData.getData().getAccessToken());
        System.out.println(loginData.getData().getExpiresIn());
    }
}
