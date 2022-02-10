package RestAssured_API_testing_BDD.phase01.foundations.basics;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

public class RestAssuredBasics {
	
	public Response getResponse() { 
		Response response =
				
				RestAssured.
					given().
						contentType(ContentType.JSON).
					when().
						get().
					then().
						contentType(ContentType.JSON).
					and().
						extract().
						response();
		
		return response;
	}
	
	@Before
	public void setUp() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		RestAssured.basePath = "/users/1";
	}
	
	@Test
	public void RestAssuredChaining() {
		
		String URL = "https://jsonplaceholder.typicode.com/users";
		
		Response response =
				
		RestAssured.
			given().
				contentType(ContentType.JSON).
			when().
				get(URL).
			then().
				contentType(ContentType.JSON).
			and().
				extract().
				response();
		
		System.out.println("Response: " + response.getBody().asString());
	}
	
	@Test
	public void RestAssuredBaseURIPath() {
		
		System.out.println("Response: " + getResponse().getBody().asString());		
	}

	@Test
	public void RestAssuredHeaders() {
		
		Headers header = getResponse().getHeaders();
		String header_name = "Content-Type";
		
		System.out.println("Headers: " + header);
		System.out.println("ContentType: " + header.getValue(header_name));
	}
	
	@Test
	public void RestAssuredJunitAssertionsJunit() {
		
		try {
			String expected = "hildegard.org";
			
			String body_string_value = getResponse().getBody().asString();
			
			System.out.println("Response: " + body_string_value);
			
			Assert.assertTrue(body_string_value.contains(expected));
			
			System.out.println("Test Passed");
		}	catch (AssertionError e) {
			System.out.println("Test Failed");
			Assert.fail();
		}			
	}
	
	@Test
	public void RestAssuredJunitAssertionsHamcrest() {
		
		try {
			String expected = "hildegard.org";
			
			String body_string_value = getResponse().getBody().asString();
			
			System.out.println("Response: " + body_string_value);
			
			Assert.assertThat(body_string_value, containsString(expected));
			
			System.out.println("Test Passed");
		}	catch (AssertionError e) {
			System.out.println("Test Failed");
			Assert.fail();
		}			
	}
	
}
