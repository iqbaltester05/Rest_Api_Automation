package mock;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

public class Login {
    WireMockServer server;
    // @BeforeTest
    public void mockSetup(){
         server = new WireMockServer(options().port(8089).httpsPort(8090));
        server.start();
    }

    // @Test
    public void login(){
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", "teacher");
        requestBody.put("password", "wrongPassword123");
        given().baseUri("https://localhost").port(8090)
        .body(requestBody)
        .when().post("/api/v1/login")
        .then().log().all();
    }

    //  @AfterTest
    public void tearMock(){
        server.stop();
    }

}
