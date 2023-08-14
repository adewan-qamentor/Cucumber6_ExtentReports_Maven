package com.stepDefinition;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverInstance;
import com.utils.UtilityClass;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DocStringSteps {

	WebDriver driver;
	WebDriverWait wait;
	public Scenario scenario;
	
	{
		DriverInstance instance = DriverInstance.getInstance();
		driver = instance.getDriver();
		wait = instance.getWait();
		this.scenario = CucumberHooks.scenario;
	}

	@When("user creates a new bookmark with url {string}")
	public void userCreatesANewBookmarkWithUrl(String url) {
		// Write code here that turns the phrase above into concrete actions
		// ****************waiting for attribute******************************
		wait.until(ExpectedConditions.attributeToBe(By.cssSelector("button[data-resin-target='unifiednewmenubutton']"),
				"class", "btn create-dropdown-menu-toggle-button"));
		// *******************************************************************
		WebElement newDropdownMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("button[class*=\"create-dropdown-menu-toggle-button\"]:not([class*='upload-button'])")));
		System.out.println("aaaaaaa" + newDropdownMenu.getText());
		newDropdownMenu.click();
		WebElement bookmarkCreationLink = wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("li[aria-label=\"Create a new Bookmark\"]")));
		bookmarkCreationLink.click();
		WebElement titleBookmark = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h2[class=\"modal-title\"]")));
		System.out.println(titleBookmark.getText());
		WebElement urlTextField = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-resin-target=\"urlinput\"]")));
		
		scenario.log("Creating new bookmark with name " + url);
		urlTextField.sendKeys(url);
		WebElement createButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-resin-target=\"create\"]")));
		createButton.click();
	}

	@When("user enters the bookmark description as:")
	public void userEntersTheBookmarkDescriptionAs(String docString) {
		// Write code here that turns the phrase above into concrete actions
		   WebElement descTextField = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-resin-target=\"descriptioninput\"]")));
		   descTextField.sendKeys(docString);
	}

	@Then("user verifies the following success message:")
	public void userVerifiesTheFollowingSuccessMessage(String docString) throws Exception {
		// Write code here that turns the phrase above into concrete actions
		WebElement succcessMessageNotification = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class*=\"notification info wrap\"]")));
		Thread.sleep(1000);
		System.out.println("printing text "+succcessMessageNotification.getText());
		
		Assert.assertTrue("bookmark not created succcessfully", succcessMessageNotification.getText().equals(docString));
		scenario.log("Bookmark success message text verified");
		scenario.attach(UtilityClass.takeByteScreenshot(driver), "image/png", "bookmark verified");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class*=\"notification info wrap\"]")));
	}

}
