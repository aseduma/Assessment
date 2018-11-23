/**
 * 
 */
package com.assessment.task2.config;

import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.assessment.configuration.Configuration;

/**
 * @author w5100593
 *
 */
public class DriverFactory extends Configuration {
	final static Logger logger = Logger.getLogger(DriverFactory.class);

	private WebDriver driver;

	private DriverFactory() {
		super();
	}

	private static class DriverFactoryHolder {
		static final DriverFactory instance = new DriverFactory();
	}

	public static DriverFactory instance() {
		return DriverFactoryHolder.instance;
	}

	public void init() {

		String browser = System.getProperty("browser");
		switch (browser.toLowerCase()) {
		case "chrome":
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
			driver = new ChromeDriver(options);
			break;

		case "ie":
			InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
			internetExplorerOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
			internetExplorerOptions.destructivelyEnsureCleanSession();
			driver = new InternetExplorerDriver(internetExplorerOptions);
			break;

		case "firefox":
			FirefoxOptions fOption = new FirefoxOptions();
			fOption.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
			fOption.setCapability("takesScreenshot", true);
			driver = new FirefoxDriver(fOption);
			break;

		case "firefox-headless":
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setHeadless(true);
			driver = new FirefoxDriver(firefoxOptions);
			break;
		case "chrome-headless":
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--disable-notifications");
			chromeOptions.addArguments("--no-sendbox");
			chromeOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
			chromeOptions.setHeadless(true);
			chromeOptions.addArguments("--enable-features=NetworkService");
			driver = new ChromeDriver(chromeOptions);
			break;
		default:
			String error = browser + " browser not supported";
			logger.error(error);
			throw new WebDriverException(error);
		}

		driver.manage().window().setSize(new Dimension(1024, 768));
		driver.manage().window().maximize();
		String url = System.getProperty("url");
		driver.get(url);

		try {
			int sleep = Integer.parseInt(System.getProperty("sleep"));
			new WebDriverWait(driver, sleep).until(webDriver -> ((JavascriptExecutor) webDriver)
					.executeScript("return document.readyState;").equals("complete"));
		} catch (Exception e) {
		}
	}

	public WebDriver getWebDriver() {
		return driver;
	}
}
