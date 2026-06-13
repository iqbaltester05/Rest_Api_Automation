package gherkin_style.framework_style;

import java.util.LinkedHashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Login_framework_collection {
    static final String BASE_URL="http://localhost:3000";
    public static void main(String[] args) {
        RestAssured.baseURI=BASE_URL;
        RequestSpecification requestspec=RestAssured.given();
        
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("email", "myrtle.streich9@yahoo.com");
        body.put("password", "password123");
        requestspec.header("Content-Type","application/json");
        requestspec.body(body);

        Response response=requestspec.post("/api/login");
        System.out.println(response.asPrettyString());
    }
}
