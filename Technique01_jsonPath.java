package RestAssured_API_testing_BDD.phase01.foundations.projects;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;
import java.util.Map;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Technique01_jsonPath extends BaseTest {

		String URI = null;
		String path = null;
		
		@Test
		public void test01ParsingJsonIndividualElementString() {
			URI = "https://jsonplaceholder.typicode.com/users/1";
			path = "website";
			
			Response response = getResponse(URI);
			
			System.out.println("Response: " + response.getBody().asString());
			
			JsonPath jsonPath = response.getBody().jsonPath();
			
			String website = jsonPath.getString(path);
			
			System.out.println("Website: " + website);
		}
		
		@Test
		public void test02ParsingJsonIndividualElementIdInteger() {
			URI = "https://jsonplaceholder.typicode.com/users/1";
			path = "id";
			
			Response response = getResponse(URI);
			
			System.out.println("Response: " + response.getBody().asString());
			
			JsonPath jsonPath = response.getBody().jsonPath();
			
			int id = jsonPath.getInt(path);
			
			System.out.println("Id: " + id);
		}
		
		@Test
		public void test03ParsingJsonGetList() {
			URI = "https://jsonplaceholder.typicode.com/users";
			path = "$";
			
			try {
				int expected = 10;
				
				Response response = getResponse(URI);
				
				System.out.println("Response: " + response.getBody().asString());
				
				JsonPath jsonPath = response.getBody().jsonPath();
				
				List<Object> allUsers = jsonPath.getList(path);
				
				System.out.println("allUsers: " + allUsers);
				
				Assert.assertEquals(expected, allUsers.size());
				
				System.out.println("Test passed");
			} catch (AssertionError e) {
				System.out.println("Test failed");
				Assert.fail();
			}
		}
			
		@Test
		public void test04ParsingJsonGetListAllObjects() {
			URI = "https://jsonplaceholder.typicode.com/users";
			path = "$";
				
			try {
				int expected = 10;
					
				Response response = getResponse(URI);
					
				System.out.println("Response: " + response.getBody().asString());
					
				JsonPath jsonPath = response.getBody().jsonPath();
					
				List<Object> allUsers = jsonPath.getJsonObject(path);
					
				for (Object user : allUsers) {
						
					System.out.println("Each user: "+ user);
				}
					
				System.out.println("allUsers: " + allUsers);
					
				Assert.assertEquals(expected, allUsers.size());
					
				System.out.println("Test passed");
			} catch (AssertionError e) {
				System.out.println("Test failed");
				Assert.fail();
			}
		}
		
		@Test
		public void test05ParsingJsonGetMapCompanyElementOneUser() {
			URI = "https://jsonplaceholder.typicode.com/users/1";
			path = "company";
			
			try {
				int expectedSize = 3;
				
				Response response = getResponse(URI);
				
				System.out.println("Response: " + response.getBody().asString());
				
				JsonPath jsonPath = response.getBody().jsonPath();
				
				Map<String, String> companyMap = jsonPath.getMap(path);
				
				System.out.println("CompanyMap size: " + companyMap.size());
				
				Assert.assertEquals(expectedSize, companyMap.size());
				
				System.out.println("Test passed");
			} catch (AssertionError e) {
				System.out.println("Test failed");
				Assert.fail();
			}
		}
		
		@Test
		public void test06ParsingJsonGetMapVerifyCatchPhraseIsNotEmpty() {
			URI = "https://jsonplaceholder.typicode.com/users";
			path = "company";
			String key = "catchPhrase";
			
			try {
				
				Response response = getResponse(URI);
				
				System.out.println("Response: " + response.getBody().asString());
				
				JsonPath jsonPath = response.getBody().jsonPath();
				
				List<Map<String, String>> companyMap = jsonPath.getList(path);
				
				for(Map<String, String> map : companyMap) {
					System.out.println("CathcPhrase: " + map.get(key));
					Assert.assertTrue(!map.get(key).isEmpty());
				}
				
				System.out.println("Test passed");
			} catch (AssertionError e) {
				System.out.println("Test failed");
				Assert.fail();
			}
		}
			
}
