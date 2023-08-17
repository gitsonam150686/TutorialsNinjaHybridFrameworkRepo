package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//We need driver,locators as webElement,(Objects of page)
//constructor to initialize driver again using 'this' keyword as this.globalVar=localVar; 
//initialize the elements (driver and locators) of the webPage automatically using pageFactory-method,
//means webElement will be attached to the locator,
//When the object of this class is created means constructor of it is called.

//this is done to overcome the StaleElementException::: 
//which occur when we move from one page to other and back to 1st page again as,
//link between the driver and webElements is lost.

//methods to perform actions on webElement.

//PageObject design pattern(not from Selenium ,used by anyone) supported by PageFactory design pattern

//Very-Important::::
//when we complete all the action in homePage,it will take us to LoginPage.
//now make design pattern for LoginPage as a new class.

public class HomePage {
	
	WebDriver driver;
	
	//Objects::
	
	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(linkText = "Login")
	private WebElement loginOption;
	
	@FindBy(linkText = "Register")
	private WebElement registerOption;
	
	@FindBy(name="search")
	private WebElement searchBoxField;
	
	@FindBy(xpath = "//span[@class='input-group-btn']/descendant::button")
	private WebElement searchButton;
	
	//Constructor::
	public HomePage(WebDriver driver) {
		this.driver=driver;
		
		PageFactory.initElements(driver, this);//this means Homepage-class
		//PageFactory.initElements(driver, HomePage.class);
	
		}
		
	//Actions on webElements::
	
	//for code reduction::if a particular object is required for multiple test,use global declaration of vars
	
	public SearchPage clickOnTheSearchButton() {
		searchButton.click();
		return new SearchPage(driver);
	}
	
	public void enterProductNameIntoSearchboxField(String productNameText) {
		searchBoxField.sendKeys(productNameText);
		
	}
	
//for searching an product in searchField and click search button.
	
	public SearchPage searchForProductName(String productNameText) {
		searchBoxField.sendKeys(productNameText);
		searchButton.click();
		return new SearchPage(driver);
		
		
	}
	
	
	
	public void clickOnMyAccount() {
       
		myAccountDropMenu.click();

	}
	
	public LoginPage selectLoginOption() {
		
		loginOption.click();
		return new LoginPage(driver);//which the landed into LoginPage.
	
	}
	
	//reusable method for navigating to LoginPage.
	public LoginPage navigateToLoginPage() {
		myAccountDropMenu.click();
		loginOption.click();
		return new LoginPage(driver);
		
	}
	
//Make one method  to click on MyAccount and select login-option from the dropDown,
		//which the landed into LoginPage.
	
	public RegisterPage selectRegisterOption() {
		registerOption.click();
		return new RegisterPage(driver);	//which the landed into LoginPage.
		
	}
	
	//reusable method for navigating to RegisterPage

	public RegisterPage navigateToRegisterPage() {
		myAccountDropMenu.click();
		registerOption.click();
		return new RegisterPage(driver);

	}
	
	
	
	
	
	
	
	
	
	
	
	
}
