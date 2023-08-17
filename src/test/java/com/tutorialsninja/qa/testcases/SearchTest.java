 package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialNinja.qa.base.BaseClass;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends BaseClass {
	public WebDriver driver;//so that can be used by Listeners/ERs,do this in all the TestClasses
	SearchPage searchPage;
	HomePage homePage;
	

	public SearchTest() {//load the properties file
		super();
	}
	@BeforeMethod
	public void setUp() {
		
		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		//above will land into homePage
		homePage=new HomePage(driver);
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	//for all the we can create a method for searching a product in homePage

	@Test(priority = 1)
	public void verifySearchWithExistingProduct() {
    
		searchPage=homePage.searchForProductName(dataprop.getProperty("validProduct"));
//		homePage.enterProductNameIntoSearchboxField(dataprop.getProperty("validProduct"));
//		searchPage=homePage.clickOnTheSearchButton();//landed into serachResult Page.
		
		//we landed into serachResult Page..create it now.
		Assert.assertTrue(searchPage.displayStatusOfvalidHPProduct(),"valid product is not displayed");
    }
	
	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {
		
		searchPage=homePage.searchForProductName(dataprop.getProperty("invalidProduct"));
//		homePage.enterProductNameIntoSearchboxField(dataprop.getProperty("invalidProduct"));
//	    searchPage=homePage.clickOnTheSearchButton();//landed into serachResult Page
		
		//we landed into searchResult Page..create it now.
	
        //String actualSearchMessage=searchPage.retrieveNoProductAvailableMessageText();
        //Assert.assertEquals(actualSearchMessage, dataprop.getProperty("NoProductTextInSearchResult"),"message for No products is not displayed.");
		//String expectedSearchMessage="There is no product that matches the search criteria.";
		Assert.assertEquals("There is no product that matches the search criteria.", "abcd","message for No products is not displayed.");
		
	}

	@Test(priority = 3,dependsOnMethods = {"verifySearchWithInvalidProduct","verifySearchWithExistingProduct"})//can give any no of method names.
	public void verifySearchWithoutAnyProduct() {
		
		// homePage.enterProductNameIntoSearchboxField("");
		searchPage = homePage.clickOnTheSearchButton();

		// we landed into serachResult Page....create it now.
        String actualSearchMessage = searchPage.retrieveNoProductAvailableMessageText();

		// String expectedSearchMessage="There is no product that matches the search criteria.";
		Assert.assertEquals(actualSearchMessage, dataprop.getProperty("NoProductTextInSearchResult"),
				                                                      "message for No products is not displayed. ");

	}
	

}
