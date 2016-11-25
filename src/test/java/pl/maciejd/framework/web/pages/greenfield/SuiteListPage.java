package pl.maciejd.framework.web.pages.greenfield;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import pl.maciejd.framework.web.pages.BasePage;
import ru.yandex.qatools.allure.annotations.Step;

public class SuiteListPage extends BasePage {

	private By suiteTileField = By.id("title");
	private By suiteListTable = By.xpath("//table");
	
	@Step("Open suite list")
	public SuiteListPage open() {
		getDriver().get("http://" + appUrl+ ":8080/greenfield/suite/");
		return this;
	}
	
	@Step("Create new test suite")
	public SuiteListPage addSuite(String title) {
		WebElement textField = getDriver().findElement(suiteTileField);
		textField.clear();
		textField.sendKeys(title);
		textField.submit();
		return this;
	}
	
	@Step("Verify if test suite has been added")
	public boolean isSuiteOnList(String title) {
		return getDriver().findElement(suiteListTable).getText().contains(title);
	}
}
