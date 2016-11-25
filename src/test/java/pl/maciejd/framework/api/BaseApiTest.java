package pl.maciejd.framework.api;

import org.testng.annotations.BeforeSuite;

import com.jayway.restassured.RestAssured;

public class BaseApiTest {

	private static final String proxyHost = System.getProperty("proxyHost");
	private static final Integer proxyPort = Integer.parseInt(System.getProperty("proxyPort"));

	@BeforeSuite
	public static void setUp() {
		RestAssured.baseURI = System.getProperty("apiBaseUrl");
		RestAssured.proxy(proxyHost, proxyPort);
		RestAssured.port = 80;
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}
}
