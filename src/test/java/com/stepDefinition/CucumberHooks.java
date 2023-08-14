package com.stepDefinition;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;

import com.driver.DriverInstance;
//import com.github.mkolisnyk.cucumber.reporting.CucumberDetailedResults;
//import com.github.mkolisnyk.cucumber.reporting.CucumberResultsOverview;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;

public class CucumberHooks {
	public static String browserName;
	private static boolean dunit = false;
	private static Set<WebDriver> driverSet = new HashSet<WebDriver>();
	static Scenario scenario;
	
//	@Given("^browser is \"([^\"]*)\"$")
	@Given("browser is {string}")
	public void browser_is(String browser) throws Throwable {
		if(browserName==null)
			DriverInstance.setInstance(browser);
		if(browserName!=null && !browserName.equals(browser)) 
			DriverInstance.setInstance(browser);	
	System.out.println("Browser is "+browser+"---------");
	driverSet.add(DriverInstance.getInstance().getDriver());
	browserName = browser;
		
	}
	
	@Before
	public void beforeEveryScenario(Scenario sc) {
		scenario = sc;
		System.out.println("scenarioname "+sc.getName());
		System.out.println("runs before background in every scenario and dunit value is "+dunit);
	}

	@Before
	   public void beforeAll() {
	       if(!dunit) {
	           Runtime.getRuntime().addShutdownHook(afterAllThread);
	           System.out.println("Will be executed when dunit is false and changing dunit to true");
	           dunit = true;
	       }
	   }
	
	Thread afterAllThread = new Thread() {
        public void run() {
            System.out.println("snake!"+" dunit value is: "+dunit);
            System.out.println(driverSet.size());
            for(WebDriver driver:driverSet)
         	   driver.quit();
            
            generateMasterThoughtReport();
        }
    };
	
	@After
	public void executeAtEndOfScenario(Scenario sc) {
		System.out.println("status is "+sc.getStatus());
		DriverInstance.getInstance().getDriver().manage().deleteAllCookies();
	}
	
	public void generateMasterThoughtReport() {
		File reportOutputDirectory = new File("reports/Masterthought");
        List<String> jsonFiles = new ArrayList<>();
        String jsonPath = System.getProperty("user.dir")+"\\reports\\report.json";
        System.out.println(jsonPath);
        jsonFiles.add(jsonPath);

        String buildNumber = "1";
        String projectName = "Box Project";

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        configuration.setBuildNumber(buildNumber);
        configuration.setParallelTesting(false);
         configuration.setRunWithJenkins(false);
        
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        Reportable result = reportBuilder.generateReports();
	}
	
	
}




