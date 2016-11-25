package pl.maciejd.framework.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import pl.maciejd.framework.utils.Utils;


public class CustomListener extends TestListenerAdapter {

	final static Logger LOGGER = LoggerFactory.getLogger(CustomListener.class);

	@Override
	public void onTestStart(ITestResult result) {
		String id = result.getMethod()
				.getConstructorOrMethod()
				.getMethod()
				.getAnnotation(pl.maciejd.framework.utils.CustomId.class)
				.id();
		LOGGER.info("Starting test id: " + id);
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		Utils.takeScreenshot();
	}
	
	

}
