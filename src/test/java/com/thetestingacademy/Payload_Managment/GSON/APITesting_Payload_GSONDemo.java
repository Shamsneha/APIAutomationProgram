package com.thetestingacademy.Payload_Managment.GSON;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting_Payload_GSONDemo {
    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;
    @Test
    public void testPositive(){
        // Step1 - POST
        // URL -> Base URI + base Path
        // HEADER
        // BODY
        // Auth - NO
        // Step 2
        // prepare the Payload ( Object -> JSON String)
        // send the request
        //Step 3
        // Validate Response ( JSON String -> Object)
        // FirstName,
        // Status Code
        // Time Response
        BookingResponse booking = new BookingResponse();
        booking.setFirstname("Pramod");
        booking.setLastname("Dutta");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);
        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-01");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");
        System.out.println(booking);
        Gson gson = new Gson();
        // Object -> JSON String (GSON) - Ser
        String jsonStringBooking = gson.toJson(booking);
        System.out.println(jsonStringBooking);
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonStringBooking).log().all();
        Response response = requestSpecification.when().post();
        String jsonResponseString  = response.asString();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        // Case1 - extract(), jsonPath().getString() - Response is small
        // Case 2- Response -> Compex JSON  - Huge JSON
        // String - Object  - De Ser
        BookingRespons bookingRespons = gson.fromJson(jsonResponseString, BookingRespons.class);
        assertThat(bookingRespons.getBookingid()).isNotZero().isNotNull();
        assertThat(bookingRespons.getBooking().getFirstname()).isEqualTo("Pramod").isNotNull().isNotEmpty();
    }
}


