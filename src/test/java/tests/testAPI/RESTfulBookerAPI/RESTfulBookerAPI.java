package tests.testAPI.RESTfulBookerAPI;


import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static tests.testAPI.RESTfulBookerAPI.BookerAPIUrls.*;

@Listeners(IMyTestListeners.class)
public class RESTfulBookerAPI {

    private String userName = "admin";
    private String pass = "password123";
    private String authToken;

    RequestSpecification requstSpec = given()
            .baseUri("https://restful-booker.herokuapp.com/")
            .contentType("application/json");

    @Test(description = "Creates a new auth token")
    public void Auth(){
        Response authResponse = given().spec(requstSpec)
                .body("{\"username\" : \""+userName+"\",\"password\" : \""+pass+"\"}")
                .when()
                .post(AUTH)
                .then()
                .extract().response();
        authResponse.then().statusCode(200);
        authResponse.then().body("token", notNullValue());
        authToken = authResponse.path("token").toString();
    }







}
