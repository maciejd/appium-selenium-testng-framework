package pl.maciejd.framework.tests;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.Test;

import pl.maciejd.framework.pages.TwitterHeaderPage;
import pl.maciejd.framework.pages.TwitterLoginPage;
import pl.maciejd.framework.utils.CustomId;

public class TwitterLoginTest extends BaseTest {

	private TwitterLoginPage login = new TwitterLoginPage();
	private TwitterHeaderPage header;

	@Test(description = "Successful login test")
	@CustomId(id = "T1.1")
	public void successfulLoginTest() {
		header = login.open()
				.enterLogin(testData.getString("user.login"))
				.enterPasswd(testData.getString("user.password"))
				.submit();
		assertTrue(header.userIsLoggedIn());
	}

	@Test(description = "Unsuccesful login test")
	@CustomId(id = "T1.2")
	public void incorrectLoginTest() {
		header = login.open()
				.enterLogin("Praktykant7")
				.enterPasswd("ZleHaslo")
				.submit();
		assertFalse(header.userIsLoggedIn());
	}

}
