package com.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverInstance {

	private static DriverInstance instance;
	private WebDriver driver;
	private WebDriverWait wait;

	private DriverInstance(String browser) {
		if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		}
		wait = new WebDriverWait(driver, 60);
		driver.manage().window().maximize();

	}

	public static void setInstance(String browser) {
		instance = new DriverInstance(browser);
	}

	public static DriverInstance getInstance() {
		return instance;
	}

	public WebDriver getDriver() {
		return driver;
	}
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriverWait getWait() {
		return wait;
	}

}
