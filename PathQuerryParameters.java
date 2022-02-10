package RestAssured_API_testing_BDD.phase01.foundations.projects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.*;

public class PathQuerryParameters {
	
	ValidatableResponse response;
	
	@Before
	public void setUp() {
		System.out.println("*** Run test: ***");
		RestAssured.baseURI = "https://reqres.in/api";
	}
	
	@After
	public void tearDown() {
		System.out.println("*** Test run completed ***");
	}
	
	@Test
	public void test01WithoutPathParameters() {
		response = RestAssured.
				given().
					contentType(ContentType.JSON).
					accept(ContentType.JSON).log().all().
				when().
					get("/users/3").
				then().
					contentType(ContentType.JSON).
					assertThat().statusCode(is(equalTo(200))).log().all();
	}
	
	@Test
	public void test02WithPathParameters() {
		response = RestAssured.
				given().
					contentType(ContentType.JSON).
					accept(ContentType.JSON).
					pathParam("userId", 3).
					log().all().
				when().
					get("/users/{userId}").
				then().
					contentType(ContentType.JSON).
					assertThat().statusCode(is(equalTo(200))).log().all();
	}
	
	@Test
	public void test03WithoutQuerryParameters() {
		response = RestAssured.
				given().
					contentType(ContentType.JSON).
					accept(ContentType.JSON).log().all().
				when().
					get("/users?page=2").
				then().
					contentType(ContentType.JSON).
					assertThat().statusCode(is(equalTo(200))).log().all();
	}
	
	@Test
	public void test04WithQuerryParameters() {
		response = RestAssured.
				given().
					contentType(ContentType.JSON).
					accept(ContentType.JSON).
					queryParam("page", 2).
					log().all().
				when().
					get("/users?/").
				then().
					contentType(ContentType.JSON).
					assertThat().statusCode(is(equalTo(200))).log().all();
	}
}
