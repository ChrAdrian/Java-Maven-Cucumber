package RestAssured_API_testing_BDD.phase01.foundations.projects;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BaseTest {
	
	Response respone;
	
	public Response getResponse(String URI) { 
		Response response =
				
				RestAssured.
					given().
						contentType(ContentType.JSON).
					when().
						get(URI).
					then().
						contentType(ContentType.JSON).
					and().
						extract().
						response();
		
		return response;
	}
}
