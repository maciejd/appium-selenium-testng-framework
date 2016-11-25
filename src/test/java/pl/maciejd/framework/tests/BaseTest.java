package pl.maciejd.framework.tests;

import java.util.ResourceBundle;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import pl.maciejd.framework.driver.DriverBase;
import pl.maciejd.framework.model.User;

public class BaseTest extends DriverBase {

	protected static ResourceBundle testData;
	
	protected User u;

	@BeforeSuite
	public void setUp() throws Exception {
		String FILENAME = "testdata/users";
		testData = ResourceBundle.getBundle(FILENAME);
	}

}
