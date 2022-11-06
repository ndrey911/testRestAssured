package testAPI;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONObject;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@Listeners(IMyTestListeners.class)
@Test(groups = "RESTfulBookerAPI")
public class RESTfulBookerAPI {

    private String userName = "admin";
    private String pass = "password123";
    private String authToken;
    private String bookingid;

    RequestSpecification requestSpec = given()
            .baseUri(BookerAPIUrls.BASE_URI)
            .contentType("application/json");

    ResponseSpecification responseSpec = given()
            .expect()
            .statusCode(200);

    @Test(description = "Creates a new auth token")
    public void Auth(){
        JSONObject authBody = new JSONObject()
                .put("username",userName)
                .put("password",pass);
        Response authResponse = given().spec(requestSpec)
                .body(authBody.toString())
                .when()
                .post(BookerAPIUrls.AUTH)
                .then()
                .extract().response();
        authResponse.then().spec(responseSpec)
                .body("token", notNullValue());
        authToken = authResponse.path("token").toString();
    }

    @Test(description = "Creates a new booking in the API")
    public void CreateBooking(){
        JSONObject requrstBookingdates = new JSONObject()
                .put("checkin","2018-01-01")
                .put("checkout","2019-01-01");
        JSONObject requestBody = new JSONObject()
                .put("firstname","Jim")
                .put("lastname","Brown")
                .put("totalprice",111)
                .put("depositpaid",true)
                .put("bookingdates",requrstBookingdates)
                .put("additionalneeds","Breakfast");

        Response createResponse = given().spec(requestSpec)
                .body(requestBody.toString())
                .when()
                .post(BookerAPIUrls.BOOKING)
                .then()
                .extract().response();
        createResponse.then().spec(responseSpec)
                .body("bookingid", notNullValue());
        bookingid = createResponse.path("bookingid").toString();

    }







}
