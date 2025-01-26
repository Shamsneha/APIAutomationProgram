package com.thetestingacademy.Integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APITesting011_TCIntegration {
    //Create a booking
    //Perform a put request
    //Verify that Put Request is success by Get request
    //Delete the ID
    //verify it dosn't exist Get request
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
return null;
    }
        @Test
        public void test_update_request_put(){
        String token = getToken();
            System.out.println(token);

        }

        @Test
        public void test_update_request_get () {



        }

            @Test
            public void test_delete_booking () {
                System.out.println(booking_id);
                System.out.println(token);
            }
            @Test
            public void test_after_delete_request_get () {
                System.out.println(booking_id);

            }
        }
