package gherkin_style.crm_gherkin;

import static io.restassured.RestAssured.given;

public class health {
    public static void main(String[] args) {
        given()
        .baseUri("http://localhost:3000")
        .when()
        .get("/api/health")
        .then()
        .log().all();
    }
}
