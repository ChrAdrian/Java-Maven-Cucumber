package RestAssured_API_testing_BDD.phase01.foundations.projects;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import RestAssured_API_testing_BDD.phase01.foundation.projects.pojos.JsonPlaceholder;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.junit.Assert;

public class Technique02_objectMapper extends BaseTest {
	
	String URI = null;
	Response response;
	JsonPlaceholder jsonPlaceholder;
	JsonPlaceholder[] jsonPlaceholderArray;
	
	public JsonPlaceholder getJsonPlaceholder(Response response) { 
		jsonPlaceholder = response.as(JsonPlaceholder.class, ObjectMapperType.GSON);
		
		return jsonPlaceholder;
	}
	
	public JsonPlaceholder[] getJsonPlaceholderArray(Response response) { 
		jsonPlaceholderArray = response.as(JsonPlaceholder[].class, ObjectMapperType.GSON);
		
		return jsonPlaceholderArray;
	} 
	
	@Test
	public void test01ParsingJsonIndividualElementString() {
		
		URI = "https://jsonplaceholder.typicode.com/users/1";
		
		response = getResponse(URI);
		
		System.out.println("Response: " + response.getBody().prettyPrint());

		getJsonPlaceholder(response);
		
		String website = jsonPlaceholder.getWebsite();
		
		System.out.println("Website: " + website);
	}
	
	@Test
	public void test02ParsingJsonIndividualElementInteger() {
		
		URI = "https://jsonplaceholder.typicode.com/users/1";
		
		response = getResponse(URI);
		
		System.out.println("Response: " + response.getBody().prettyPrint());
		
		getJsonPlaceholder(response);
		
		int website = jsonPlaceholder.getId();
		
		System.out.println("Id: " + website);
	}
	
	@Test
	public void test03ParsingJsonGetListSize() {
		
		URI = "https://jsonplaceholder.typicode.com/users";
		
		int users = 10;
		
		try {
			response = getResponse(URI);
			
			System.out.println("Response: " + response.getBody().prettyPrint());
			
			getJsonPlaceholderArray(response);
			
			List<JsonPlaceholder> jsonPlaceholderList = Arrays.asList(jsonPlaceholderArray);
			
			Assert.assertEquals(users, jsonPlaceholderList.size());
			
			System.out.println("Test passed");
		} catch (AssertionError e) {
			System.out.println("Test failed");
			Assert.fail();
		}
	}
	
	@Test
	public void test04ParsingJsonVerifyCatchPhraseIsNotEmpty() {
		
		URI = "https://jsonplaceholder.typicode.com/users";
		
		String catchPhrase;
		
		try {
			response = getResponse(URI);
			
			System.out.println("Response: " + response.getBody().prettyPrint());
			
			getJsonPlaceholderArray(response);
			
			for (JsonPlaceholder jsonPlaceholder : jsonPlaceholderArray) {
				catchPhrase = jsonPlaceholder.getCompany().getCatchPhrase();
				
				System.out.println("Catchphrase: " + catchPhrase);
				
				Assert.assertTrue(!catchPhrase.isEmpty());
			}
			
			System.out.println("Test passed");
		} catch (AssertionError e) {
			System.out.println("Test failed");
			Assert.fail();
		}
	}
}
