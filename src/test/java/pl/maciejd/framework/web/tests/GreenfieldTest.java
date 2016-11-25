package pl.maciejd.framework.web.tests;

import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.Test;

import pl.maciejd.framework.utils.CustomId;
import pl.maciejd.framework.web.pages.greenfield.SuiteListPage;

public class GreenfieldTest extends BaseTest {
	
	private SuiteListPage suites = new SuiteListPage();
	
	@Test(description = "Create new suite test")
	@CustomId(id = "1")
	public void createNewSuiteTest() {
		String title = "Suite_"+ System.currentTimeMillis();
		suites.open().addSuite(title);
		assertTrue(suites.isSuiteOnList(title));
	}
}
