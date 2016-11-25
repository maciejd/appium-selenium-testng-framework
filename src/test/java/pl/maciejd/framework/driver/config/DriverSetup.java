package pl.maciejd.framework.driver.config;

import java.net.URL;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public interface DriverSetup {

    WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities);
    
    WebDriver getWebDriverObject(URL gridURL,DesiredCapabilities desiredCapabilities);

    DesiredCapabilities getDesiredCapabilities(Proxy proxySettings);
}