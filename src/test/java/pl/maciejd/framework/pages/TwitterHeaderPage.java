package pl.maciejd.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import pl.maciejd.framework.utils.Utils;
import ru.yandex.qatools.allure.annotations.Step;

public class TwitterHeaderPage extends BasePage {

	private By userMenuButton = By.id("user-dropdown");

	@Step("Check if user is logged in")
	public boolean userIsLoggedIn() {
		Utils.takeScreenshot();
		try {
			getDriver().findElement(userMenuButton);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
