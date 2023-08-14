package com.executor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;


public class ReportMainRunner {

	public static void main(String[] args) {
		  File reportOutputDirectory = new File("reports/Masterthought");
          List<String> jsonFiles = new ArrayList<>();
          String jsonPath = System.getProperty("user.dir")+"\\reports\\report.json";
          System.out.println(jsonPath);
          jsonFiles.add(jsonPath);
//          File file = new File(System.getProperty("user.dir")+"/reports/report.json");
//          jsonFiles.add(file.getAbsolutePath());
          

          String buildNumber = "1";
          String projectName = "Box Project";

          Configuration configuration = new Configuration(reportOutputDirectory, projectName);
          // optional configuration - check javadoc for details
//          configuration.addPresentationModes(PresentationMode.RUN_WITH_JENKINS);
          // do not make scenario failed when step has status SKIPPED
//          configuration.setNotFailingStatuses(Collections.singleton(Status.SKIPPED));
          configuration.setBuildNumber(buildNumber);
          configuration.setParallelTesting(false);
	         configuration.setRunWithJenkins(false);
          // addidtional metadata presented on main page
//          configuration.addClassifications("Platform", "Windows");
//          configuration.addClassifications("Browser", "chrome");
//          configuration.addClassifications("Branch", "release/1.0");

          // optionally add metadata presented on main page via properties file
//          List<String> classificationFiles = new ArrayList<>();
//          classificationFiles.add("properties-1.properties");
//          classificationFiles.add("properties-2.properties");
//          configuration.addClassificationFiles(classificationFiles);

          // optionally specify qualifiers for each of the report json files
//          configuration.addPresentationModes(PresentationMode.PARALLEL_TESTING);
//          configuration.setQualifier("cucumber-report-1", "Report");
//          configuration.setQualifier("cucumber-report-2", "Second report");

          ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
          Reportable result = reportBuilder.generateReports();
          // and here validate 'result' to decide what to do if report has failed
	}
}
