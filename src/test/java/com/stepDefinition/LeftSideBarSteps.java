package com.stepDefinition;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverInstance;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;

public class LeftSideBarSteps {

	WebDriver driver;
	WebDriverWait wait;
	 {
		DriverInstance instance = DriverInstance.getInstance();
		driver = instance.getDriver();
		wait = instance.getWait();
	}
	 
	 @When("user verifies the following left sidebar links:")
	 public void verifiesLeftSidebarLinks(DataTable dataTable) {
		 List<WebElement> linksList = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("a[class*='CollapsibleSidebarMenuItem__StyledLink']"),5));
		 List<String> dataList = dataTable.asList();
		 for(String elementText: dataList) {
			 for(WebElement element: linksList) {
				 String elementName = element.getText();
//				 System.out.println("Text from data table "+elementText);
//				 System.out.println("text from web element "+elementName);
				 if(elementText.equals(elementName)) {
					 System.out.println(elementText+" verified");
					 break;
				 }
			 }
		 }
	 }
}
