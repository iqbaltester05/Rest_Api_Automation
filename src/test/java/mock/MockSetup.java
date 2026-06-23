package mock;

import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MockSetup {
    WireMockServer wireMockServer;

    @BeforeTest
    public void mockSetup() {
        // 1. Start the WireMock server on port 8089
        wireMockServer = new WireMockServer(8089);
        wireMockServer.start();

        // 2. Define a basic Stub (Rule)
        wireMockServer.stubFor(get(urlEqualTo("/api/hello"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/plain")
                        .withBody("Hello, Student!")));

        System.out.println("Mock Server is running on port 8089...");
        // After tests, stop it using: wireMockServer.stop();
    }

    @Test
    public void testmock(){
        given().baseUri("http://localhost").port(8089)
        .when().get("/api/hello")
        .then().log().all();
    }

    @AfterTest
    public void tearMock(){
        wireMockServer.stop();
    }
}