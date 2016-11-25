package pl.maciejd.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.yandex.qatools.allure.annotations.Step;

public class TwitterLoginPage extends BasePage {
	
	final static Logger LOGGER = LoggerFactory.getLogger(TwitterLoginPage.class);

	private static By loginField = By.id("signin-email");
	private static By passwordField = By.id("signin-password");

	@Step("Open login page")
	public TwitterLoginPage open() {
		getDriver().get("http://twitter.com");
		return new TwitterLoginPage();
	}

	@Step("Enter login {0}")
	public TwitterLoginPage enterLogin(String login) {
		WebElement field = getDriver().findElement(loginField);
		field.clear();
		field.sendKeys(login);
		return this;
	}

	@Step("Enter password {0}")
	public TwitterLoginPage enterPasswd(String passwd) {
		WebElement field = getDriver().findElement(passwordField);
		field.clear();
		field.sendKeys(passwd);
		return this;
	}

	@Step("Submit login form")
	public TwitterHeaderPage submit() {
		getDriver().findElement(passwordField)
				.submit();
		return new TwitterHeaderPage();
	}

}
