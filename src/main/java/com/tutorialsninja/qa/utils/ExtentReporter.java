package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

//method for generating Extent report.
//Integrate this report with listeners.java.
//so when the Test got executed by XML file==>Listener method will also get called==>hence report is also generated for test Execution
public class ExtentReporter {
	
	public static ExtentReports generateExtentReport()   {
		
		ExtentReports extentReport=new ExtentReports();
		File extentReportFile=new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkReporter=new ExtentSparkReporter(extentReportFile);
		
		//make the extentReport attractive:::
		//sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("TutorialsNinja Test Automation Result");
		sparkReporter.config().setDocumentTitle("TutorialsNinja Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/YYYY hh:mm:ss");
		
		//attach the extentReport with sparkReporter.
		extentReport.attachReporter(sparkReporter);

//what details we want in ExtentReport::::::
//we need some other info from properties-file and attach it to Extent report.
		//like System URL,Java Version, from configProperties file(use its path).
		
		Properties configProp=new Properties();
		File configPropFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\Config.properties");
		
		try {
			FileInputStream fisConfigProp = new FileInputStream(configPropFile);
			configProp.load(fisConfigProp);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		//get the info(URL,browserName,email,password) from properties file.
		extentReport.setSystemInfo("Application URL ", configProp.getProperty("url"));
		extentReport.setSystemInfo("Browser Name", configProp.getProperty("browserName"));
		extentReport.setSystemInfo("Email", configProp.getProperty("validEmail"));
		extentReport.setSystemInfo("Password", configProp.getProperty("validPassword"));
		
		//get the System-info(operating system,user name,java version)::: 
		extentReport.setSystemInfo("Operating system", System.getProperty("os.name"));
		extentReport.setSystemInfo("UserName", System.getProperty("user.name"));
	    extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
     
	       return extentReport;
	
	}

}
