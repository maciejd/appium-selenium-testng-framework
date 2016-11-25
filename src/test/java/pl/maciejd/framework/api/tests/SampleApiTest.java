package pl.maciejd.framework.api.tests;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class SampleApiTest extends BaseApiTest {

	@Test
	public void userTest() {
		given().contentType("application/json")
				.get("/users")
				.then()
				.statusCode(200)
				.body("find{it.name==\"dfg\"}.email", equalTo("q@q.com"));
	}
	
	@Test
	public void alwaysFailTest() {
		given().contentType("application/json")
				.get("/users")
				.then()
				.statusCode(500);
	}

}
