package api.utilities;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;




public class ExtentReportManager implements ITestListener{

	public static ExtentReports extent	;
	public static ExtentSparkReporter sparkReporter;
	public static ExtentTest test;

	String repName;

	public void onStart(ITestContext testContext) {

	//	SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"); 
	//	Date dt = new Date(); String currentdatetimeStamp = df.format(dt);


		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time Stamp

		repName = "Test-Report-"+timeStamp +".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+ repName);//Specify the Location of the Report

		sparkReporter.config().setDocumentTitle("RsestAssuredAutomationProject");//title of the Report
		sparkReporter.config().setReportName("PET Store Users API");// name of the Report
		sparkReporter.config().setTheme(Theme.DARK);	

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "PET Store Users API");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("SubModule","Customers");
		extent.setSystemInfo("UserName",System.getProperty("user.name"));
		extent.setSystemInfo("Enviornment","QA");



		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);


		String BrowserName = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", BrowserName);

		List<String>includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}	

	public void onTestSuccess(ITestResult result)	{

		test =extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+ "got Successfully Excecuted");
	}



	public void onTestFailure(ITestResult result) {
		test =extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName()+ "got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());	
		//try {	


		//catch(IOException e1) {
		//	e1.printStackTrace();

	}

	public void onTestSkipped(ITestResult result)	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP ,result.getName()+ "got Skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());	
	}


	public void onFinish(ITestResult result)	{	
		extent.flush();
	
		
	}
}

