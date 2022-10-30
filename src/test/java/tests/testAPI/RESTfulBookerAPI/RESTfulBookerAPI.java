package tests.testAPI.RESTfulBookerAPI;


import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
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
    private String bookingid;

    RequestSpecification requestSpec = given()
            .baseUri(BASE_URI)
            .contentType("application/json");

    ResponseSpecification responseSpec = given()
            .expect()
            .statusCode(200);

    @Test(description = "Creates a new auth token")
    public void Auth(){
        Response authResponse = given().spec(requestSpec)
                .body("{\"username\" : \""+userName+"\",\"password\" : \""+pass+"\"}")
                .when()
                .post(AUTH)
                .then()
                .extract().response();
        authResponse.then().spec(responseSpec)
                .body("token", notNullValue());
        authToken = authResponse.path("token").toString();
    }

    @Test(description = "Creates a new booking in the API")
    public void CreateBooking(){
        Response createResponse = given().spec(requestSpec)
                .body("{\n" +
                        "    \"firstname\" : \"Jim\",\n" +
                        "    \"lastname\" : \"Brown\",\n" +
                        "    \"totalprice\" : 111,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2018-01-01\",\n" +
                        "        \"checkout\" : \"2019-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Breakfast\"\n" +
                        "}")
                .when()
                .post(BOOKING)
                .then()
                .extract().response();
        createResponse.then().spec(responseSpec)
                .body("bookingid", notNullValue());
        bookingid = createResponse.path("bookingid").toString();
    }







}
