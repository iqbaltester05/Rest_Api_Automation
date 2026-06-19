package DriverManager;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class RestManager {

    RequestSpecification requestSpecification;
    
    public void addBaseURIToTheRequest(String URI){
        RestAssured.baseURI=URI;
    }

    public void baseRequest(){
        requestSpecification=RestAssured.given();
    }

}
