package pl.maciejd.framework.driver.config;

import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import pl.maciejd.framework.driver.DriverBase;

import java.net.MalformedURLException;
import java.net.URL;

import static org.openqa.selenium.Proxy.ProxyType.MANUAL;
import static pl.maciejd.framework.driver.config.DriverType.FIREFOX;
import static pl.maciejd.framework.driver.config.DriverType.valueOf;

public class DriverFactory {

	private WebDriver webdriver;
	private DriverType selectedDriverType;

	private final DriverType defaultDriverType = FIREFOX;
	private final String browser = System.getProperty("browser", defaultDriverType.toString())
			.toUpperCase();
	private final String operatingSystem = System.getProperty("os.name")
			.toUpperCase();
	private final String systemArchitecture = System.getProperty("os.arch");
	private final boolean useRemoteWebDriver = Boolean.getBoolean("remoteDriver");
	private final boolean useMobileWebDriver = Boolean.getBoolean("mobileDriver");
	private final boolean proxyEnabled = Boolean.getBoolean("proxyEnabled");
	private final String proxyHostname = System.getProperty("proxyHost");
	private final String noProxy = System.getProperty("noProxy");
	private final Integer proxyPort = Integer.getInteger("proxyPort");
	private final String proxyDetails = String.format("%s:%d", proxyHostname, proxyPort);
	
	final static Logger LOGGER = LoggerFactory.getLogger(DriverFactory.class);

	public WebDriver getDriver() throws Exception {
		if (null == webdriver) {
			Proxy proxy = null;
			if (proxyEnabled) {
				proxy = new Proxy();
				proxy.setProxyType(MANUAL);
				proxy.setHttpProxy(proxyDetails);
				proxy.setSslProxy(proxyDetails);
				proxy.setNoProxy(noProxy);
			}
			determineEffectiveDriverType();
			DesiredCapabilities desiredCapabilities = selectedDriverType.getDesiredCapabilities(proxy);
			instantiateWebDriver(desiredCapabilities);
		}

		return webdriver;
	}

	public void quitDriver() {
		if (null != webdriver) {
			webdriver.quit();
		}
	}

	private void determineEffectiveDriverType() {
		DriverType driverType = defaultDriverType;
		try {
			driverType = valueOf(browser);
		} catch (IllegalArgumentException ignored) {
			System.err.println("Unknown driver specified, defaulting to '" + driverType + "'...");
		} catch (NullPointerException ignored) {
			System.err.println("No driver specified, defaulting to '" + driverType + "'...");
		}
		selectedDriverType = driverType;
	}

	private void instantiateWebDriver(DesiredCapabilities desiredCapabilities) throws MalformedURLException {
		System.out.println(" ");
		System.out.println("Current Operating System: " + operatingSystem);
		System.out.println("Current Architecture: " + systemArchitecture);
		System.out.println("Current Browser Selection: " + selectedDriverType);
		System.out.println(" ");

		if (useMobileWebDriver) {
			URL appiumGridURL = new URL(System.getProperty("gridURL"));
			String platform = System.getProperty("platform");
			String platformName = System.getProperty("platformName");
			String platformVersion = System.getProperty("platformVersion");
			String deviceName = System.getProperty("deviceName");
			String app = System.getProperty("app");
			desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
			desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
			desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
			desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			desiredCapabilities.setCapability(MobileCapabilityType.APP, app);
			desiredCapabilities.setCapability("autoAcceptAlerts", true);
			desiredCapabilities.setCapability("iosInstallPause", 30000);
			desiredCapabilities.setCapability("noReset", true);
			desiredCapabilities.setCapability("realDeviceLogger", "/usr/local/lib/node_modules/deviceconsole/deviceconsole");
			webdriver = selectedDriverType.getWebDriverObject(appiumGridURL, desiredCapabilities);
		}

		else if (useRemoteWebDriver) {
			URL seleniumGridURL = new URL(System.getProperty("gridURL"));
			String desiredBrowserVersion = System.getProperty("desiredBrowserVersion");
			String desiredPlatform = System.getProperty("desiredPlatform");

			if (null != desiredPlatform && !desiredPlatform.isEmpty()) {
				desiredCapabilities.setPlatform(Platform.valueOf(desiredPlatform.toUpperCase()));
			}

			if (null != desiredBrowserVersion && !desiredBrowserVersion.isEmpty()) {
				desiredCapabilities.setVersion(desiredBrowserVersion);
			}

			webdriver = new RemoteWebDriver(seleniumGridURL, desiredCapabilities);
		} else {
			webdriver = selectedDriverType.getWebDriverObject(desiredCapabilities);
		}
	}
}