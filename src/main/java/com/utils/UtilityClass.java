package com.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class UtilityClass {
	
	public static byte[] takeByteScreenshot(WebDriver driver) {
		TakesScreenshot scDriver = (TakesScreenshot)driver;
		byte[] scBytes = scDriver.getScreenshotAs(OutputType.BYTES);
		return scBytes;
	}

}
