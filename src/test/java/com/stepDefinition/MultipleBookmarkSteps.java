package com.stepDefinition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverInstance;
import com.utils.UtilityClass;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.When;

public class MultipleBookmarkSteps {

	WebDriver driver;
	WebDriverWait wait;
	int bookmarkVal; 
	String bookmarkEntry;
	List<String> noOfBookmarks;
	WebElement succcessMessageNotification;
	Scenario scenario;
	
	 {
		DriverInstance instance = DriverInstance.getInstance();
		driver = instance.getDriver();
		wait = instance.getWait();
		this.scenario = CucumberHooks.scenario;
	}
	 
	 @When("user create multiple new bookmark and verifies the \"A bookmark for \"bookmarkEntry\" was created successfully.\" success message as below:")
	 public void createMultipleNewBookmarks(DataTable dataTable) throws Exception {
		
			WebElement newDropdownMenu = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[class*=\"create-dropdown-menu-toggle-button\"]:not([class*='upload-button'])")));
//			 List<String> dataList = dataTable.asList(String.class);
			List<List<String>> dataList = dataTable.asLists();
			noOfBookmarks = new ArrayList<String>();
			Thread.sleep(3000);
			for(List<String> row:dataList){
				System.out.println("The size of row is :"+row.size());
			
			System.out.println("aaaaaaa"+newDropdownMenu.getText());
			newDropdownMenu.click();
			WebElement bookmarkCreationLink = wait.until(
					ExpectedConditions.elementToBeClickable(By.cssSelector("li[aria-label=\"Create a new Bookmark\"]")));
			bookmarkCreationLink.click();
				WebElement titleBookmark = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h2[class=\"modal-title\"]")));
			System.out.println("title bookmark:"+titleBookmark.getText());
			
		  
		   WebElement urlTextField = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-resin-target=\"urlinput\"]")));
		   WebElement descTextField = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-resin-target=\"descriptioninput\"]")));
		   
			   urlTextField.clear();
//				bookmarkVal = (int)(Math.random()*((1000-1)+1))+1;
				bookmarkEntry = "www."+ row.get(0);
				scenario.log("Creating new bookmark with name "+bookmarkEntry);
				noOfBookmarks.add(bookmarkEntry);
				System.out.println("bookmark entry-"+bookmarkEntry);
				urlTextField.sendKeys(bookmarkEntry);
				descTextField.clear();
				descTextField.sendKeys(row.get(1));
				scenario.attach(UtilityClass.takeByteScreenshot(driver), "image/png", bookmarkEntry);
				
				WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-resin-target=\"create\"]")));
				createButton.click();
				Thread.sleep(2000);

				succcessMessageNotification = wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class*=\"notification info wrap\"]")));
				System.out.println(succcessMessageNotification.getText());
				String successMessageText = "A bookmark for \""+bookmarkEntry+"\" was created successfully.";
				System.out.println("The success message :"+successMessageText);
				Assert.assertTrue("bookmark not created succcessfully", succcessMessageNotification.getText().equals(successMessageText));
				scenario.log("Bookmark success message text verified");
				scenario.attach(UtilityClass.takeByteScreenshot(driver), "image/png", bookmarkEntry+" verified");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class*=\"notification info wrap\"]")));
		   }
		   
		}
		

	 @When("user deletes multiple bookmarks and verifies the {string} deletion message")
		public void deleteMultipleBookmarks(String arg1) throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
 Collections.sort(noOfBookmarks, Collections.reverseOrder());
		 
		 for(String str:noOfBookmarks) {
			 Actions act = new Actions(driver);
				WebElement rowElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='row' and .//a[text()='"+str+"']]")));
				act.moveToElement(rowElement).build().perform();
//				Thread.sleep(2000);
				wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(rowElement, By.xpath("//input[contains(@id,'checkbox')]"))).click();
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[data-resin-target=\"trash\"]"))).click();
//				wait.until(ExpectedConditions.textToBe(By.xpath("//button[contains(@class,'btn-primary')]//span"), "Okay"));
				scenario.attach(UtilityClass.takeByteScreenshot(driver), "image/png", "deleting bookmark "+str);
				Thread.sleep(1000);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-resin-target='primarybutton']//span[text()='Okay']"))).click();
				succcessMessageNotification = wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class*=\"notification info wrap\"]")));
				System.out.println(succcessMessageNotification.getText());
				Assert.assertTrue("bookmark not deleted succcessfully", succcessMessageNotification.getText().contains(arg1));
				scenario.log("Deleting new bookmark with name "+str);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class*=\"notification info wrap\"]")));
		 }
		}
	 
	 @When("user create multiple new bookmark with name and verifies the \"A bookmark for \"bookmarkEntry\" was created successfully.\" success message as below:")
	 public void userCreateMultipleBookmarkWithName(DataTable dataTable) throws Exception {

			WebElement newDropdownMenu = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[class*=\"create-dropdown-menu-toggle-button\"]:not([class*='upload-button'])")));
//			 List<String> dataList = dataTable.asList(String.class);
			List<Map<String,String>> dataList = dataTable.asMaps();
			noOfBookmarks = new ArrayList<String>();
			Thread.sleep(3000);
			for(Map<String,String> row:dataList){
				System.out.println("The size of row is :"+row.size());
			
			System.out.println("aaaaaaa"+newDropdownMenu.getText());
			newDropdownMenu.click();
			WebElement bookmarkCreationLink = wait.until(
					ExpectedConditions.elementToBeClickable(By.cssSelector("li[aria-label=\"Create a new Bookmark\"]")));
			bookmarkCreationLink.click();
				WebElement titleBookmark = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h2[class=\"modal-title\"]")));
			System.out.println("title bookmark:"+titleBookmark.getText());
			
		  
		   WebElement urlTextField = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-resin-target=\"urlinput\"]")));
		   WebElement nameTextField = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-resin-target='nameinput']")));
		   WebElement descTextField = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-resin-target=\"descriptioninput\"]")));
		   
			   urlTextField.clear();
			   scenario.log("Creating new bookmark with name "+row.get("name"));
//				bookmarkVal = (int)(Math.random()*((1000-1)+1))+1;
				bookmarkEntry = "www."+ row.get("url");
				System.out.println("bookmark entry-"+bookmarkEntry);
				urlTextField.sendKeys(bookmarkEntry);
				nameTextField.sendKeys(row.get("name"));
				
				descTextField.clear();
				descTextField.sendKeys(row.get("description"));
				noOfBookmarks.add(row.get("name"));
				scenario.attach(UtilityClass.takeByteScreenshot(driver), "image/png", row.get("name"));
				WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-resin-target=\"create\"]")));
				createButton.click();
				Thread.sleep(2000);

				succcessMessageNotification = wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class*=\"notification info wrap\"]")));
				System.out.println(succcessMessageNotification.getText());
				String successMessageText = "\""+row.get("name")+"\" was created successfully.";
				System.out.println("The success message :"+successMessageText);
				Assert.assertTrue("bookmark not created succcessfully", succcessMessageNotification.getText().equals(successMessageText));
				scenario.log("Bookmark success message text verified");
				scenario.attach(UtilityClass.takeByteScreenshot(driver), "image/png", row.get("name")+" verified");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class*=\"notification info wrap\"]")));
		   }
		 
	 }
}
