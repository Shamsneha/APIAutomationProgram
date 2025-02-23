package com.thetestingacademy.APITesting_PATCH;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting009_NonBDDStyle {
    @Description("Verify to PATCH Request:NonBDD Style")
    @Test
    public void test_NonBDDStyle(){
        String Token= "b30895c6f07e3a1";
        String Booking_ID=" 5313";
        String payload="{\n" +
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
        RequestSpecification r = RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/booking/1"+Booking_ID);
        r.contentType(ContentType.JSON);
        r.cookie("token",Token);
        r.body(payload).log().all();
        Response response=r.when().log().all().patch();
        ValidatableResponse validatableResponse= response.then().log().all();
        validatableResponse.statusCode(200);

    }
}
