package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialNinja.qa.base.BaseClass;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;
//HomePage-->LoginPage-->AccountPage(on successful login)

public class LoginTest extends BaseClass { 
	public WebDriver driver;//so that can be used by Listeners/ERs,do this in all the TestClasses
	LoginPage loginPage;
	AccountPage accountPage;
	
	//loading the properties file 1st then do rest of thing after getting details from it.
	public LoginTest() {//load the properties file
		super();
	}

	
//HomePage::::
	@BeforeMethod
	public void setUp() {
	
		//loadPropertiesFile();//or do above super() method use.
		
        driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));//method from BaseClass
        HomePage homePage=new HomePage(driver);
                loginPage=homePage.navigateToLoginPage();
      
      
//        homePage.clickOnMyAccount();
//        loginPage = homePage.selectLoginOption();//after this we landed into LoginPage.       
/*As done in setUp() of Before method and we landed into LoginPage.
        //no need of this LoginPage object creation in other Test method
        
        //How to do that::::
        //for this the selectLoginOption() method should return new LoginPage-object with driver as arg.
        //This object-var should be declared at global level to accessed by other methods as well.
        
        //OR else,we need a method in HomePage which return LoginPage after this.
        //like ReUsable method for normal java actions.*/ 
        
   //we can create a reusable method for selecting LoginOption in 'HomePage' and navigating to LoginPage.
        
        
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	
//	@Test(priority = 1)
//	public void verifyLoginWithValidCredentials() {
// 
//		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
//		driver.findElement(By.xpath("//input[@value='Login']")).click();
//
//		// verify we landed on correct page or not:::
//		// if the below linkText is displayed::TC is passed or else failed.
//		// error message will appear when the TC is failed
//
//		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(),"Edit your account information link is Not diplayed");
//		}

	//read data from dataProvider:: for below Test-method.
	//read data from Excel-file::using method  readDataFromExcel() inside Utilities-class
	
//LoginPage::::
	@Test(priority = 1,dataProvider = "validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email,String password) {
		AccountPage accountPage=loginPage.loginApp(email, password);

		
//		loginPage.enterEmailAddress(email);
//		loginPage.enterPassword(password);
//	    AccountPage accountPage=loginPage.clickOnLoginButton();
//create a reusable method above for entering email,password and click on LoginButton,inside LoginPage.
		  //Now after login it landed into AccountsPage,so make new pageFactory for it.
		
	//we need this boolean status of this webElement.
	    Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),"Edit your account information link is Not diplayed");
		}
	
//supply the data to above test-method using DataProvider:::::::
	
	@DataProvider(name="validCredentialsSupplier")
	    public Object[][] supplyTestData() {
		//Object[][] data= {{"sonam.sharmabgh@gmail.com","1234567890"},{"sonam.sharmabgh2@gmail.com","1234567890"},{"sonam.sharmabgh3@gmail.com","1234567890"}};
		
		      Object[][] data=Utilities.getTestDataFromExcel("Login");           
	
		      return data;
		
	}
	


	// but when we use same invalid credential more than 5 times It got failed due
	// to security check.
	// we will get another warning message,your account has exceeded allowed number
	// of login attempts.please try again after 1 hour.
	// Overcome this by adding changing the email every-time we login.
	// done by adding/appending the time stamp with email-Id.
	// using Date-class object
//	@Test
//	public void verifyLoginWithInvalidCredentials() {
//		driver.findElement(By.xpath("//span[text()='My Account']")).click();
//		driver.findElement(By.linkText("Login")).click();
//		driver.findElement(By.id("input-email")).sendKeys("sonammmm.sharahahjsadhjsmabgh@gmail.com");
//		driver.findElement(By.id("input-password")).sendKeys("11111234578767890");
//		driver.findElement(By.xpath("//input[@value='Login']")).click(); 
//		
//		WebElement warningElement=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]"));
//		String actualWarnigMessage=warningElement.getText();
//	 	String expectedWarnigMessage="Warning: No match for E-Mail Address and/or Password.";
//		
//		//String expectedWarnigMessage="Warning: No matchhh for E-Mail Address and/or Password.";
//		
//		Assert.assertTrue(actualWarnigMessage.contains(expectedWarnigMessage),"Expected warning message is not displayed");

//	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {

	loginPage.loginApp(Utilities.generateEmailWithTimestamp(), dataprop.getProperty("invalidPassword"));
		
		String actualWarnigMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarnigMessage = dataprop.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarnigMessage.contains(expectedWarnigMessage),"Expected warning message is not displayed");

	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {

		loginPage.loginApp(Utilities.generateEmailWithTimestamp(), (prop.getProperty("validPassword")));

        String actualWarnigMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarnigMessage = dataprop.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarnigMessage.contains(expectedWarnigMessage),"Expected warning message is not displayed");

	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
	
		loginPage.loginApp(prop.getProperty("validEmail"), dataprop.getProperty("invalidPassword"));
		
		String actualWarnigMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarnigMessage = dataprop.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarnigMessage.contains(expectedWarnigMessage),"Expected warning message is not displayed");


	}

	@Test(priority = 5)
	public void verifyLoginWithoutCredentials() {
	
		loginPage.clickOnLoginButton();

		String actualWarnigMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarnigMessage = dataprop.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarnigMessage.contains(expectedWarnigMessage),"Expected warning message is not displayed");

	}


}
