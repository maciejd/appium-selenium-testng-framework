package pl.maciejd.framework.driver;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import pl.maciejd.framework.driver.config.DriverFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//@Listeners(CustomListener.class)
public class DriverBase {
	
	final static Logger LOGGER = LoggerFactory.getLogger(DriverBase.class);

    private static List<DriverFactory> webDriverThreadPool = Collections.synchronizedList(new ArrayList<DriverFactory>());
    private static ThreadLocal<DriverFactory> driverFactory;

    @BeforeSuite(alwaysRun = true)
    public static void instantiateDriverObject() {
        driverFactory = new ThreadLocal<DriverFactory>() {
            @Override
            protected DriverFactory initialValue() {
                DriverFactory driverFactory = new DriverFactory();
                webDriverThreadPool.add(driverFactory);
                return driverFactory;
            }
        };
    }

    public static WebDriver getDriver() {
        try {
			return driverFactory.get().getDriver();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error("Failed to initialize driver!");
			e.printStackTrace();
			return null;
		}
    }

    @AfterMethod(alwaysRun = true)
    public static void clearCookies() throws Exception {
        getDriver().manage().deleteAllCookies();
    }

    @AfterSuite(alwaysRun = true)
    public static void closeDriverObjects() {
        for (DriverFactory driverFactory : webDriverThreadPool) {
            driverFactory.quitDriver();
        }
    }
}