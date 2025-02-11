package com.thetestingacademy.Integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APITesting011_TCIntegration {
    //Create Token
    //Create a booking
    //Perform a put request
    //Verify that Put Request is success by GET request
    //Delete the ID
    //verify it doesn't exist Get request
    RequestSpecification requestSpecification;
    Response responce;
    ValidatableResponse validatableResponse;
    String token;
    String booking_id;

    public String getToken() {
        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        requestSpecification= RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON).log().all();
        requestSpecification.body(payload);
        Response response = requestSpecification.when().post();
        ValidatableResponse validatableResponse = responce.then();

        validatableResponse.statusCode(200);
        //Extract the token
        token = responce.jsonPath().getString("token");
        System.out.println(token);
        return token;
    }

    public String getBookingID() {

        String payload_POST="\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        requestSpecification= RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload_POST).log().all();
        Response response = requestSpecification.when().post();
        ValidatableResponse validatableResponse = responce.then().log().all();

        validatableResponse.statusCode(200);
        //Extract the token
        booking_id = responce.jsonPath().getString("bookingid");
        System.out.println(booking_id);
        return booking_id;

    }
        @Test(priority = 1)
        public void test_update_request_put(){
        token = getToken();
        booking_id=getBookingID();
            System.out.println(token);
            System.out.println(booking_id);
            String payload_PUT="{\n" +
                    "    \"firstname\" : \"Neha\",\n" +
                    "    \"lastname\" : \"Shams\",\n" +
                    "    \"totalprice\" : 111,\n" +
                    "    \"depositpaid\" : true,\n" +
                    "    \"bookingdates\" : {\n" +
                    "        \"checkin\" : \"2018-01-01\",\n" +
                    "        \"checkout\" : \"2019-01-01\"\n" +
                    "    },\n" +
                    "    \"additionalneeds\" : \"Breakfast\"\n" +
                    "}";

            requestSpecification = RestAssured.given();
            requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
            requestSpecification.basePath("/booking/"+booking_id);
            requestSpecification.contentType(ContentType.JSON);
            requestSpecification.cookie("token",token);
            requestSpecification.body(payload_PUT).log().all();
            Response response = requestSpecification.when().put();
            validatableResponse = response.then().log().all();
            validatableResponse.statusCode(200);

        }

        @Test(priority = 2)
        public void test_update_request_get() {
            System.out.println(booking_id);
            requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
            requestSpecification.basePath("/booking/"+booking_id);
            Response response = requestSpecification.when().log().all().get();
            requestSpecification.then().log().all().statusCode(200);
            String firstname = response.jsonPath().getString("firstname");
            Assert.assertEquals(firstname,"Anshul");

        }

            @Test(priority = 3)
            public void test_delete_booking() {
                System.out.println(booking_id);
                System.out.println(token);
                requestSpecification = RestAssured.given();
                requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
                requestSpecification.basePath("/booking/"+booking_id);
                requestSpecification.contentType(ContentType.JSON);
                requestSpecification.cookie("token",token);
                Response response = requestSpecification.when().delete();
                validatableResponse = response.then().log().all();
                validatableResponse.statusCode(201); // #TODO #1 -Delete Bug
            }
    @Test(priority = 4)
    public void test_after_delete_request_get() {
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking/"+booking_id);
        Response response = requestSpecification.when().log().all().get();
        requestSpecification.then().log().all().statusCode(404);
    }

}
