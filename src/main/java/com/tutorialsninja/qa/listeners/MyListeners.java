package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.Utilities;
//After creating this if we execute its XML file,
//These methods/events are fired by the test,but nothing will be printed from these method.
//as no body is there to listen/invoke these events.
//make connection between Tests/XML files and Listeners.
//create a Listeners tag with class name in XML file at suite level.

//How to integrate Extent report with Listener::::
//before starting Test-execution,create an ExtentReport using generateExtentReport() method.
//depending on the status of TC-execution, rest of Listener methods are called and give output.
//after finishing TC-execution,generate the report at showing log-details.


//generate ER-->create Test using ER-object-->log status(info/pass/fail/skip) of test using ExtentTest-object.

public class MyListeners implements ITestListener{
	
	ExtentReports extentReport;
	ExtentTest extentTest;
	
	
	@Override
	public void onStart(ITestContext context) {
		System.out.println("Execution of Project Tests started");
//To generate ER,call the ER generating method using className as it return ExtentReport object.
		extentReport=ExtentReporter.generateExtentReport();
	}

//Below methods will get invoked when each and every Test starts executing.
	//create an test/ExtentTest using executedTest-name from ExtentReport,using ER-object with createTest() method
	//using ExtentTest object and log() write the log in ER.
	//getName()-->createTest(testName)-->log(Status.INFO)
	@Override
	public void onTestStart(ITestResult result) {//need extentReport object.so make it global.
		
		String testName=result.getName();
		extentTest=extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName+" started executing.");
	}

	@Override
	public void onTestSuccess(ITestResult result) {//need extentTest object.so make it global.
		String testName=result.getName();
		extentTest.log(Status.PASS, testName+" got successfully executed.");
	}

	@Override
	public void onTestFailure(ITestResult result) {//it get invoked when any test failed and we need Screenshot of that. 
		String testName=result.getName();
		System.out.println("ScreenShot taken for failed TestCase");
		
		WebDriver driver = null;
//code for taking Screenshot.::
		//Get the driver 1st,which we get from failed TC which return driver as output.
		//that driver must be stored in 'result' of ITestResult interface.
		
		//for this in all the TC driver should be public so that accessed by everywhere in the project.
	
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		String destinationScreenshotPath= Utilities.captureScreenshot(driver,testName);
		
		//add screenshot from path to ER:
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO, result.getThrowable());//print all the exception details/reason for failure.
		extentTest.log(Status.FAIL, testName+" got failed.");//log result in ER


	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		String testName=result.getName();
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.SKIP, testName+" got skipped.");//use depends on FAILED test.
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		System.out.println("Execution of Project Tests Finished");
		
		String pathOfExtentReport=System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		
		File extentReport=new File(pathOfExtentReport);		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
