package pl.maciejd.framework.utils;

import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import pl.maciejd.framework.driver.DriverBase;
import ru.yandex.qatools.allure.annotations.Attachment;

public class Utils {

	@Attachment(value = "Page screenshot", type = "image/png")
	public static byte[] takeScreenshot() {
		return ((TakesScreenshot) DriverBase.getDriver()).getScreenshotAs(OutputType.BYTES);
	}
	
}
