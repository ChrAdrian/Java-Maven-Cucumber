package RestAssured_API_testing_BDD.phase01.foundations.basics;

import org.junit.Test;

import static org.junit.Assert.assertThat;

import org.junit.Assert;
import org.junit.Before;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

public class RestAssured_StatusCode {

	@Before
	public void setUp() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		RestAssured.basePath = "/users/1";
	}
	
	@Test
	public void testStatusCodeJunit() {
		try {
			Response response =
					RestAssured.
						given().
							contentType(ContentType.JSON).
							headers("Accept", ContentType.JSON).
						when().
							get().
						then().
							contentType(ContentType.JSON).
							extract().
							response();
			
			Assert.assertEquals(200, response.getStatusCode());
			
			System.out.println("Test Passed");				
		}	catch (AssertionError e) {
			System.out.println("Test Failed");
			Assert.fail();
		}
	}
	
	@Test
	public void testStatusCodeHamcrest() {
		try {
			Response response =
					RestAssured.
						given().
							contentType(ContentType.JSON).
							headers("Accept", ContentType.JSON).
						when().
							get().
						then().
							contentType(ContentType.JSON).
							extract().
							response();
			response.
				then().
					assertThat().
						statusCode(is(equalTo(200)));
			
			assertThat(response.getStatusCode(), is(equalTo(200)));
			
			System.out.println("Test Passed");				
		}	catch (AssertionError e) {
			System.out.println("Test Failed");
			Assert.fail();
		}
	}
}
