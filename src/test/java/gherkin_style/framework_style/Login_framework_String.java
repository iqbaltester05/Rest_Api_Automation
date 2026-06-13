package gherkin_style.framework_style;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Login_framework_String {
    static final String BASE_URL="http://localhost:3000";
    public static void main(String[] args) {
        RestAssured.baseURI=BASE_URL;
        RequestSpecification requestspec=RestAssured.given();
        
        String body="{\n" + //
                        "  \"email\": \"myrtle.streich9@yahoo.com\",\n" + //
                        "  \"password\": \"password123\"\n" + //
                        "}";
        requestspec.header("Content-Type","application/json");
        requestspec.body(body);

        Response response=requestspec.post("/api/login");

    }
}
