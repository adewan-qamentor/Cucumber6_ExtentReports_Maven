package com.stepDefinition;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverInstance;



public class NewDataTableSteps {

	WebDriver driver;
	WebDriverWait wait;
	int bookmarkVal; 
	String bookmarkEntry;
	List<String> noOfBookmarks;
	WebElement succcessMessageNotification;
	
	public  NewDataTableSteps() {
		DriverInstance instance = DriverInstance.getInstance();
		driver = instance.getDriver();
		wait = instance.getWait();
	}
	


}
