package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialNinja.qa.base.BaseClass;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;
//HomePage-->RegisterPage-->AccountSuccessPage(after successful registration)
public class RegisterTest extends BaseClass {
	public WebDriver driver;//so that can be used by Listeners/ERs,do this in all the TestClasses.
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;

	public RegisterTest() {//load the properties file
		super();
	}
	@BeforeMethod
	public void setUp() {
		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		
		HomePage homePage=new HomePage(driver);
		     registerPage=homePage.navigateToRegisterPage();

//		homePage.clickOnMyAccount();
//	    registerPage=homePage.selectRegisterOption();
//	
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyRegisterAnAccountByProvidingMendatoryFields() {
		accountSuccessPage=registerPage.registerWithMendatoryFields(dataprop.getProperty("firstName")
				                                   ,dataprop.getProperty("lastName")
				                                   ,Utilities.generateEmailWithTimestamp()
				                                   ,dataprop.getProperty("telephoneNumber")
				                                   ,prop.getProperty("validPassword")
				                                   ,prop.getProperty("validPassword"));
		
//		 registerPage.enterFirstName(dataprop.getProperty("firstName"));
//		 registerPage.enterLastName(dataprop.getProperty("lastName"));
//		 registerPage.enterEmailAddress(Utilities.generateEmailWithTimestamp());
//		 registerPage.enterTelephone(dataprop.getProperty("telephoneNumber"));
//		 registerPage.enterPassword(prop.getProperty("validPassword"));
//		 registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
//		 registerPage.selectprivacyPolicy();		 
//		 accountSuccessPage= registerPage.clickOnContinueBUtton();

//after this we landed into Account-Success page::check heading of that page.
	
	   String actualSuccessHeading=accountSuccessPage.getAccountSuccessPageHeading();
	   Assert.assertEquals(actualSuccessHeading,dataprop.getProperty("accountSuccessfullyCreatedHeadingxpected"));

	}

	@Test(priority = 2)
	public void verifyRegisteringAccountByProvidingAllFields() {
	
		accountSuccessPage=	registerPage.registerWithMendatoryFields(dataprop.getProperty("firstName")
				                                                    , dataprop.getProperty("lastName")
				                                                    , Utilities.generateEmailWithTimestamp()
				                                                    , dataprop.getProperty("telephoneNumber")
				                                                    , prop.getProperty("validPassword")
				                                                    , prop.getProperty("validPassword"));
		

// extra feature.::
		//driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		
		 String actualSuccessHeading=accountSuccessPage.getAccountSuccessPageHeading();
		 Assert.assertEquals(actualSuccessHeading,dataprop.getProperty("accountSuccessfullyCreatedHeadingxpected"));

	}

	@Test(priority = 3)
	public void verifyRegisterAccountWithExistingEmailAddress() {
		registerPage.registerWithMendatoryFields(dataprop.getProperty("firstName")
                                                                  , dataprop.getProperty("lastName")
                                                                  , prop.getProperty("validEmail")
                                                                  , dataprop.getProperty("telephoneNumber")
                                                                  , prop.getProperty("validPassword")
                                                                  , prop.getProperty("validPassword"));
		
		//still on register page.no need to jump to other page
		 String actualWarning = registerPage.retrieveDuplicateEmailAddressWarning();
		Assert.assertEquals(actualWarning, dataprop.getProperty("duplicateEmailWarning"),"Able to register with same email id");
		// Assert.assertTrue(actualWarning.contains("Warning: E-Maillll Address is
		// already registered!"), "Msg:::Warning message about duplicate email address
		// is not displayed");

	}

	@Test(priority = 4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails() {
	
		registerPage.clickOnContinueBUtton();

		
		String actualPrivacyPolicy = registerPage.retrievePrivacyPolicyWarning();
		Assert.assertEquals(actualPrivacyPolicy,dataprop.getProperty("privacyPolicyWarning"),"Privacy Policy Warning message is not displayed.");

		String actualFirstNameWarning =registerPage.retrieveFirstNameWarning();
		Assert.assertEquals(actualFirstNameWarning,dataprop.getProperty("firstNameWarning"),"FirstName warning message is not displayed");

		String actualLastNameWarning = registerPage.retrieveLastNameWarning();
		Assert.assertEquals(actualLastNameWarning, dataprop.getProperty("lastNameWarning"),"LastName warning message is not displayed");

		String actualEmailWarning = registerPage.retrieveEmailAddressWarning();
		Assert.assertEquals(actualEmailWarning, dataprop.getProperty("emailAddressWarning"),"E-Mail Address does warning message is not displayed");

		String actualTelephoneWarning = registerPage.retrieveTelephoneWarning();
		Assert.assertEquals(actualTelephoneWarning, dataprop.getProperty("telephoneWarning"),"Telephone warning message is not displayed");

		String actualPasswordWarning = registerPage.retrievepasswordWarning();
		Assert.assertEquals(actualPasswordWarning, dataprop.getProperty("passwordWarning"),"Password warning message is not displayed");

	}

}