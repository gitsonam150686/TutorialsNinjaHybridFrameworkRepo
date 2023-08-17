package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;
	
	
	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id="input-email")
	private WebElement emailAddressField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id="input-confirm")
	private WebElement passwordConfirmField;
	
	@FindBy(name="agree")
	private WebElement privacyPolicyField;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	
	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement yesNewsLetterOption;
	
	@FindBy(xpath="//div[contains(@class,' alert-dismissible')]")
	private WebElement duplicateEmailAddressWarning;
	
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarning;
	
	
	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;
	
	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailAddressWarning;
	
	@FindBy(xpath ="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		
		PageFactory.initElements(driver,this);
		
	}
	
	 public String retrievepasswordWarning() {
		 String passwordWarningText=passwordWarning.getText();
		return passwordWarningText;
		 
	 }
	
	
	public String retrieveTelephoneWarning() {
		String TelephoneWarningText=telephoneWarning.getText();
		return TelephoneWarningText;
	}
	
	public String retrieveEmailAddressWarning1() {
		String emailAddressWarningText=emailAddressWarning.getText();
		return emailAddressWarningText;
	}
	
	
	public String retrieveEmailAddressWarning() {
		String emailAddressWarningText=emailAddressWarning.getText();
		return emailAddressWarningText;
	}
	
	public String retrieveLastNameWarning() {
		String lastNameWarningText=lastNameWarning.getText();
		return lastNameWarningText;
	}
	public String retrieveFirstNameWarning() {
		String firstNameWarningText= firstNameWarning.getText();
		return firstNameWarningText;
	}

	public String retrievePrivacyPolicyWarning() {
		String privacyPolicyWarningText=privacyPolicyWarning.getText();
		return privacyPolicyWarningText;
	}
	
	public String retrieveDuplicateEmailAddressWarning() {
		String duplicateEmailAddressWarnignText=duplicateEmailAddressWarning.getText();
	
		return duplicateEmailAddressWarnignText;
	
	}
	public void selectYesNewsLetterOption() {
		yesNewsLetterOption.click();
	}
	
	
	public void enterFirstName(String firstNameText) {
	    firstNameField.sendKeys(firstNameText);
	 }
	
	public void enterLastName(String lastNameText) {
		lastNameField.sendKeys(lastNameText);
	}
	
	public void enterEmailAddress(String emailText) {
		emailAddressField.sendKeys(emailText);
		
	}
	
	public void enterTelephone(String telephoneText) {
		telephoneField.sendKeys(telephoneText);
		
	}
	
	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
		
	}
	
	public void enterConfirmPassword(String passwordConfirmText) {
		passwordConfirmField.sendKeys(passwordConfirmText);
	}
	
	public void selectprivacyPolicy() {
		privacyPolicyField.click();
		
	}
	
	public AccountSuccessPage clickOnContinueBUtton() {
		continueButton.click();
		return new AccountSuccessPage(driver);
		
	}
	
	public AccountSuccessPage registerWithMendatoryFields(String firstNameText,String lastNameText ,String emailText,String telephoneText,String passwordText,String passwordConfirmText) {
		  firstNameField.sendKeys(firstNameText);
		  lastNameField.sendKeys(lastNameText);
		  emailAddressField.sendKeys(emailText);
		  telephoneField.sendKeys(telephoneText);
		  passwordField.sendKeys(passwordText);
		  passwordConfirmField.sendKeys(passwordConfirmText);
		  privacyPolicyField.click();
	      continueButton.click();
		 
	      return new AccountSuccessPage(driver);
			
			
		  
	}

}
	

