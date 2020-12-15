package com.qa.rest.tests;

import org.joda.time.Instant;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CuurencyRate {
	
	//verify status code
	@Test(priority=1)
	public void getExchangeRate() {
		given().when().get("https://api.ratesapi.io/api/latest ")
		.then().statusCode(200);
	}
	
	//verify response from body
	
	@Test(priority=2)
	public void verifyParticularCountryExchangeValue() {
		given().when().get("https://api.ratesapi.io/api/latest ")
		.then().assertThat().statusCode(200)
		.body("rates.INR", equalTo(89.34f));
	}
    //verify past date exchange rates
	@Test(priority=3)
	public void verifyParticularDateExchangeRate() {
		given().when().get("https://api.ratesapi.io/api/2010-01-12")
		.then().assertThat().statusCode(200)
		.body("date", equalTo("2010-01-12")).log().all();
	}
		
	
	@Test(priority=4)
	public void verifyfutureDateExchangeRate() {
		Instant instant = Instant.now();
		String GMTTime=instant.toString();
		String OnlyGMTDate=GMTTime.substring(0, 10);
		System.out.println("GMT time: "+OnlyGMTDate);
		given().when().get("https://api.ratesapi.io/api/2510-01-10")
		.then().assertThat().statusCode(200)
		.body("date", equalTo(OnlyGMTDate)).log().all();
	}

}
