package mock;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

public class MockTest {
    WireMockServer server;
    // @BeforeClass
    public void setupMockTest(){
       server=new WireMockServer(options().port(8099));
      server.start();

      server.stubFor(post(urlEqualTo("/api/v2/login"))
      .withHeader("Content-Type",equalTo("application/json"))
      .withRequestBody(equalToJson("{\n" + //
                "  \"email\": \"john.doe@example.com\",\n" + //
                "  \"password\": \"password123\"\n" + //
                "}"))
      .willReturn(aResponse().withStatus(200).withBody("this is the response from mock server")));
        
    }

    // @Test
    public void loginTest(){
      Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", "john.doe@example.com");
        requestBody.put("password", "password123");
      given().baseUri("http://localhost").port(8099)
      .header("Content-Type","application/json")
      
      .body("{\n" + //
                "  \"email\": \"john.doe@example.com\",\n" + //
                "  \"password\": \"password123\"\n" + //
                "}")
      .when().post("/api/v2/login")
      .then().log().all();
    }

    // @AfterClass
    public void stopeServer(){
      server.stop();
    }
}
