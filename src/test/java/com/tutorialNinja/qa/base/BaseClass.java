package com.tutorialNinja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.qa.utils.Utilities;
//Things which are common for all the Tests are kept in properties file.
//Individual/uncommon data related to different Tests should not be kept here.(kept in excel file)
//we do not want to hard-code this browser name and url.
//We need to load the properties-file for this:::create a method for it.

//steps:::
//create a Properties-class object(prop) from javaUtil.
//create a file with config-file-path using above object.
//enter this above config-file into fileInputStream(fis).
//load this fis using load() with prop-object.

//System.getProperty("user.dir")::give current project.
//*********How to access the valued from properties file********************
//using Properties-class object-ref and getProperty("key from prop-file") method
//or make this code available under Base-class default constructor.
//inside the default constructor of other class,use super() method to call parent/base class constructor.
//inside other class,
//we should call/load this PropertiesFile 1st.(using method/constructor calling)
//call initializeBrowserAndOpenApplicationURL() method which return driver as o/p.
//then do any operation like login,register,search...and so on.
public class BaseClass {
	WebDriver driver;
	public Properties prop;
	public Properties dataprop;
	
	// public void loadPropertiesFile() {
	public BaseClass() {

		// properties file for common data::
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\Config.properties");

		// properties file for test Data::
        dataprop=new Properties();
		File datapropFile = new File(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
		
		try {
			FileInputStream dataFis = new FileInputStream(datapropFile);
			dataprop.load(dataFis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		
		
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	public WebDriver initializeBrowserAndOpenApplicationURL(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));//from Utilities-class
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));//from Properties file.
		return driver;

	}
}
