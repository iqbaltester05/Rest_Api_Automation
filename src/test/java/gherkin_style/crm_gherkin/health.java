package gherkin_style.crm_gherkin;

import io.restassured.RestAssured;

public class health {
    public static void main(String[] args) {
        RestAssured.given()
        .baseUri("http://localhost:3000")
        .when()
        .get("/api/health")
        .then()
        .log().all();
    }
}
