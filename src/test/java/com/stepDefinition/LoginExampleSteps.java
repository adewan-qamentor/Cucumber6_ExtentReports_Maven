package com.stepDefinition;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverInstance;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

	
public class LoginExampleSteps {
	WebDriver driver;
	WebDriverWait wait;
	
	public LoginExampleSteps() {
		DriverInstance instance = DriverInstance.getInstance();
		driver = instance.getDriver();
		wait = instance.getWait();
	}


	
	@When("user logout of the application")
	public void logoutApp() throws Throwable {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[data-resin-target=\"accountmenu\"]"))).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Log Out')]"))).click();
		System.out.println("Loggedout from application");
	}
		
	@Then("user verify the {string} in the app")
	public void verifyAppTitle(String title) throws Throwable {
		Thread.sleep(5000);
		Assert.assertTrue("Page title is wrong",wait.until(ExpectedConditions.titleIs(title)));
	}
	
	@When("user enter the {string}")
	public void user_enter_the(String inputText) throws Throwable {
		if(inputText.contains(".com")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='login-email']"))).sendKeys(inputText);
			   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[id='login-submit']"))).click();
		}
		else {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id='password-login']"))).sendKeys(inputText);
			  
		}
		   
	}

	@When("user login  into app")
	public void user_login_into_app() throws Throwable {
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[id='login-submit-password']"))).click();
	}

	@When("user enter the username as below and password {string} and Login to the app")
	public void dataLogin(String pwd, DataTable data) throws Throwable {
		List<String> dl = data.asList(String.class);
		WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='login-email']")));
		for(String str : dl) {
			Thread.sleep(2000);
			userField.clear();
			userField.sendKeys(str);
		}
//		userField.sendKeys(dl.get(0));
		//		.sendKeys(uName);
		   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[id='login-submit']"))).click();
		   Thread.sleep(2000);
		   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id='password-login']"))).sendKeys(pwd);
		   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[id='login-submit-password']"))).click();
	}

}
