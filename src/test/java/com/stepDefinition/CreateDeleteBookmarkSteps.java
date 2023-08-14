package com.stepDefinition;


import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverInstance;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class CreateDeleteBookmarkSteps {
	WebDriver driver;
	WebDriverWait wait;
	int bookmarkVal; 
	String bookmarkEntry;
	List<String> noOfBookmarks;
	WebElement succcessMessageNotification;
	public Scenario scenario;
	
	public  CreateDeleteBookmarkSteps() {
		DriverInstance instance = DriverInstance.getInstance();
		driver = instance.getDriver();
		wait = instance.getWait();
		this.scenario = CucumberHooks.scenario;
	}

	
	@When("user creates a new bookmark")
	public void createNewBookmark() throws Throwable {
		//****************waiting for attribute******************************
		wait.until(ExpectedConditions.attributeToBe(By.cssSelector("button[data-resin-target='unifiednewmenubutton']"), "class", "btn create-dropdown-menu-toggle-button"));
		//*******************************************************************		
		WebElement newDropdownMenu = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[class*=\"create-dropdown-menu-toggle-button\"]:not([class*='upload-button'])")));
		System.out.println("aaaaaaa"+newDropdownMenu.getText());
		newDropdownMenu.click();
		WebElement bookmarkCreationLink = wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("li[aria-label=\"Create a new Bookmark\"]")));
		bookmarkCreationLink.click();
			WebElement titleBookmark = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h2[class=\"modal-title\"]")));
		System.out.println(titleBookmark.getText());
		WebElement urlTextField = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-resin-target=\"urlinput\"]")));
		bookmarkVal = (int)(Math.random()*((1000-1)+1))+1;
		bookmarkEntry = "www."+ bookmarkVal +".com"; 
		scenario.log("Creating new bookmark with name "+bookmarkEntry);
		urlTextField.sendKeys(bookmarkEntry);
		WebElement createButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-resin-target=\"create\"]")));
		createButton.click();
	}

	@Then("user verifies the \"A bookmark for \"bookmarkEntry\" was created successfully.\" success message")
	public void verifySuccessMessage() throws Exception {
//		System.out.println("arg1 = "+arg1);		//A bookmark for
//		System.out.println("arg3 = "+arg3);		//was created successfully.
		succcessMessageNotification = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class*=\"notification info wrap\"]")));
		Thread.sleep(1000);
		System.out.println("printing text "+succcessMessageNotification.getText());
		String successMessageText = "A bookmark for \""+bookmarkEntry+"\" was created successfully.";
		System.out.println("The success message :"+successMessageText);
		Assert.assertTrue("bookmark not created succcessfully", succcessMessageNotification.getText().equals(successMessageText));
		scenario.log("Bookmark success message text verified");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class*=\"notification info wrap\"]")));
	}

	@When("user deletes a bookmark")
	public void deleteBookmark() throws Throwable {
		if(bookmarkEntry==null)
			bookmarkEntry="www.random.com";
		Actions act = new Actions(driver);
		WebElement rowElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='row' and .//a[text()='"+bookmarkEntry+"']]")));
		act.moveToElement(rowElement).build().perform();
//		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(rowElement, By.xpath("//input[contains(@id,'checkbox')]"))).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[data-resin-target=\"trash\"]"))).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-actions']/button[contains(@class,'btn-primary')]"))).click();
	}
	

	@Then("user verifies the {string} deletion message")
	public void verifyDeleteMessage(String arg1) throws Throwable {
		succcessMessageNotification = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class*=\"notification info wrap\"]")));
		String text =succcessMessageNotification.getText();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class*=\"notification info wrap\"]")));
		Assert.assertTrue("bookmark not deleted succcessfully", text.contains("Item successfully moved to trash."));
	}
	
	@When("user creates a new bookmark with data as below:")
	public void createNewBookmarkWithData(DataTable data) throws Throwable {
		//****************************#Waiting for element to get stable*********************************************************
				Thread.sleep(3000);
//				wait.until(ExpectedConditions.attributeToBe(By.cssSelector("div[class=\"action-bar-actions\"]"), "class", "action-bar-actions"));
//				wait.until(ExpectedConditions.textToBe(By.cssSelector("button[class*=\"create-dropdown-menu-toggle-button\"]:not([class*='upload-button'])"),"New"));
		//************************************************************************************************************************		
				WebElement newDropdownMenu = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[class*=\"create-dropdown-menu-toggle-button\"]:not([class*='upload-button'])")));
				System.out.println("aaaaaaa"+newDropdownMenu.getText());
				newDropdownMenu.click();
				WebElement bookmarkCreationLink = wait.until(
						ExpectedConditions.elementToBeClickable(By.cssSelector("li[aria-label=\"Create a new Bookmark\"]")));
				bookmarkCreationLink.click();
					WebElement titleBookmark = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h2[class=\"modal-title\"]")));
				System.out.println(titleBookmark.getText());
				
				List<String> dataList = data.asList(String.class);
				
				for(String str : dataList) {
					
					WebElement urlTextField = wait.until(
							ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-resin-target=\"urlinput\"]")));
					urlTextField.clear();
					bookmarkVal = (int)(Math.random()*((1000-1)+1))+1;
					bookmarkEntry = "www."+ bookmarkVal +str+".com"; 
					urlTextField.sendKeys(bookmarkEntry);
					Thread.sleep(2000);
					
				}
				
				
				WebElement createButton = wait
						.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-resin-target=\"create\"]")));
				createButton.click();
			}

}
