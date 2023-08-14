package com.stepDefinition;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverInstance;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class LoginBoxSteps {
	WebDriver driver;
	WebDriverWait wait;
	static DriverInstance instance;
	
	{
		instance = DriverInstance.getInstance();
		driver = instance.getDriver();
		wait = instance.getWait();
	}
	
	@Given("^user navigates to the Box App$")
	public void launchBoxApp() throws Throwable {
		driver.get("https://app.box.com");
	}	
	
	
	@When("user enter the correct/wrong/credentials {string} and {string} and Login to the app")
	public void loginToApp(String uName, String pwd) throws Throwable {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='login-email']"))).sendKeys(uName);
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[id='login-submit']"))).click();
	   Thread.sleep(1500);
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id='password-login']"))).sendKeys(pwd);
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[id='login-submit-password']"))).click();
	}

	@When("user enter the username and password as below and Login to the app")
	public void enterWithDataTable(DataTable data) throws Throwable {
		List<List<String>> dataList = data.asLists();
		int count =0;
		for(List<String> row : dataList) {
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='login-email']"))).sendKeys(row.get(0));
			   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[id='login-submit']"))).click();
			   Thread.sleep(1500);
			   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id='password-login']"))).sendKeys(row.get(1));
				if(count!=(dataList.size()-1))	   
			   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[id=\"back-arrow\"]"))).click();
				
				count++;
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[id='login-submit-password']"))).click();
	}
	
	@Then("^user verify the error message$")
	public void user_verify_the_error_message() throws Throwable {
	
	}
	
}
