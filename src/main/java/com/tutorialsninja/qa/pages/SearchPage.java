package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	WebDriver driver;
	
	@FindBy(linkText="HP LP3065")
	private WebElement validHPProduct;
	
	@FindBy(xpath="//p[contains(text(),'There is no product that matches the search criteria.')]")
	private WebElement noProductAvailableMessage;
	
	
	
	
	public SearchPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String retrieveNoProductAvailableMessageText() {
		String noProductAvailableMessageText=noProductAvailableMessage.getText();
		return noProductAvailableMessageText;
	}
	
	public boolean displayStatusOfvalidHPProduct() {
	
		boolean displayStatus=validHPProduct.isDisplayed();
		return displayStatus;
		}
	
	
	
	
	
	
	
	
	
	
	
	

}
