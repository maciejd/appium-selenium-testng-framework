package pl.maciejd.framework.driver.config;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.openqa.selenium.remote.CapabilityType.PROXY;

public enum DriverType implements DriverSetup {

	FIREFOX {
		public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			// TODO Uncomment the capability settings below to use Marionette
			 capabilities.setCapability("marionette", true);
			// capabilities.setCapability("binary",
			// System.getProperty("webdriver.gecko.driver"));
			return addProxySettings(capabilities, proxySettings);
		}

		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			return new FirefoxDriver(capabilities);
		}

		@Override
		public WebDriver getWebDriverObject(URL gridURL, DesiredCapabilities desiredCapabilities) {
			return new FirefoxDriver(desiredCapabilities);
		}
	},
	CHROME {
		public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
			HashMap<String, String> chromePreferences = new HashMap<String, String>();
			chromePreferences.put("profile.password_manager_enabled", "false");
			capabilities.setCapability("chrome.prefs", chromePreferences);
			return addProxySettings(capabilities, proxySettings);
		}

		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			return new ChromeDriver(capabilities);
		}

		@Override
		public WebDriver getWebDriverObject(URL gridURL, DesiredCapabilities desiredCapabilities) {
			return null;
		}
	},
	IE {
		public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
			capabilities.setCapability("requireWindowFocus", true);
			return addProxySettings(capabilities, proxySettings);
		}

		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			return new InternetExplorerDriver(capabilities);
		}

		@Override
		public WebDriver getWebDriverObject(URL gridURL, DesiredCapabilities desiredCapabilities) {
			return null;
		}
	},
	SAFARI {
		public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
			DesiredCapabilities capabilities = DesiredCapabilities.safari();
			capabilities.setCapability("safari.cleanSession", true);
			return addProxySettings(capabilities, proxySettings);
		}

		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			return new SafariDriver(capabilities);
		}

		@Override
		public WebDriver getWebDriverObject(URL gridURL, DesiredCapabilities desiredCapabilities) {
			return null;
		}
	},
	OPERA {
		public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
			DesiredCapabilities capabilities = DesiredCapabilities.operaBlink();
			return addProxySettings(capabilities, proxySettings);
		}

		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			return new OperaDriver(capabilities);
		}

		@Override
		public WebDriver getWebDriverObject(URL gridURL, DesiredCapabilities desiredCapabilities) {
			return null;
		}
	},
	ANDROID {
		public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			return addProxySettings(capabilities, proxySettings);
		}

		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			return null;
		}

		@Override
		public WebDriver getWebDriverObject(URL gridURL, DesiredCapabilities desiredCapabilities) {
			return new AndroidDriver(gridURL, desiredCapabilities);
		}
	},
	IOS {
		public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			return addProxySettings(capabilities, proxySettings);
		}

		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			return null;
		}

		@Override
		public WebDriver getWebDriverObject(URL gridURL, DesiredCapabilities desiredCapabilities) {
			return new IOSDriver<>(gridURL, desiredCapabilities);
		}

	};

	protected DesiredCapabilities addProxySettings(DesiredCapabilities capabilities, Proxy proxySettings) {
		if (null != proxySettings) {
			capabilities.setCapability(PROXY, proxySettings);
		}

		return capabilities;
	}

	protected List<String> applyPhantomJSProxySettings(List<String> cliArguments, Proxy proxySettings) {
		if (null == proxySettings) {
			cliArguments.add("--proxy-type=none");
		} else {
			cliArguments.add("--proxy-type=http");
			cliArguments.add("--proxy=" + proxySettings.getHttpProxy());
		}
		return cliArguments;
	}

}
