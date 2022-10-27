package tests.testAPI.RESTfulBookerAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class RESTfulBookerAPI {

    private String userName = "admin";
    private String pass = "password123";
    private String authToken;

    RequestSpecification requstSpec = given()
            .baseUri("https://restful-booker1.herokuapp.com/")
            .contentType("application/json");

    @BeforeMethod
    public void enableLogging(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void Auth(){
        Response authResponse = given().spec(requstSpec)
                .body("{\"username\" : \""+userName+"\",\"password\" : \""+pass+"\"}")
                .when()
                .post("auth")
                .then()
                .extract().response();
        authResponse.then().statusCode(200);
        authResponse.then().body("token", notNullValue());
        authToken = authResponse.path("token").toString();
    }




}
