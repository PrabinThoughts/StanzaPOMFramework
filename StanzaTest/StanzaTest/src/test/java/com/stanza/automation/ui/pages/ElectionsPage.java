package com.stanza.automation.ui.pages;

import static com.stanza.automation.ui.util.TestData.*;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stanza.automation.testcases.ElectionsPageTest;
import com.stanza.automation.ui.basetest.TestBase;


public class ElectionsPage extends TestBase {

	@FindBy(xpath = "//span[contains(text(),'Election Calendar')]")
	public WebElement electioncallendarText; 
	
	@FindBy(id="search-input")
	public WebElement electionsTextBox;
	
	@FindBy(xpath = "//div[@class='autocomplete-dropdown-container']/"
			+ "div[@class='suggestionItem']")
	By addressSuggestions;
	
	@FindBy(xpath = "//span[@class='rmq-1789b78f rmq-88ff45c']")
	public WebElement addToCalendar;
	
	@FindBy(xpath = "//div[@class='rmq-cd58530d']/span")
	public WebElement getInTouch;
	
	@FindBy(xpath = "//div[contains(@data-item-index,'0')]")
	public WebElement chooseAccount;

	@FindBy(css = "div.error")
	public WebElement errorHighlight;

	@FindBy(id="introduced")
	public WebElement introducedTextBox;

	@FindBy(id="discontinued")
	public WebElement discontinuedTextBox;

	@FindBy(id = "company")
	public WebElement companyDropDown;

	@FindBy(css = "input.btn.primary")
	public WebElement createComputerButton;

	@FindBy(css = "a.btn")
	public WebElement cancelButton; 
	
	@FindBy(xpath="//span[contains(text(),'We found your county:')]")
	public WebElement countryFound;
	
	@FindBy(xpath="//span[contains(text(),'Federal Elections')]")
	public WebElement FederalElections;
	
	@FindBy(xpath="//span[contains(text(),'Federal Elections')]//parent::span//parent::p//parent::div//parent::div/img")
	public WebElement FederalElectionsCheck;
	
	@FindBy(xpath="//span[contains(text(),'State Elections')]")
	public WebElement StateElections;
	
	@FindBy(xpath="//span[contains(text(),'State Elections')]//parent::span//parent::p//parent::div//parent::div/img")
	public WebElement StateElectionsCheck;
	
	@FindBy(xpath="//span[contains(text(),'Local Elections')]")
	public WebElement LocalElections;
	
	@FindBy(xpath="//span[contains(text(),'Local Elections')]//ancestor::div[@data-radium='true']//"
			+ "following-sibling::div/p//span[text()='Coming Soon']")
	public WebElement LocalElectionsCheck;
	
	@FindBy(xpath="//span[text()='ADD TO CALENDAR']")
	public WebElement AddToCalendarButton;
	
	@FindBy(xpath="//span[text()='Frequently Asked Questions']")
	public WebElement FAQHeading;
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ElectionsPageTest.class);

	// Initializing the Page Objects:
	public ElectionsPage() {
		LOGGER.info("Loading all webelements... "); 
		PageFactory.initElements(driver, this);
	}
	
	public void scrollToFindElement(WebElement wb) throws InterruptedException{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView()", wb); 
	}

	public String getElectionsPageHeader(){
		return electioncallendarText.getText();
	}
	
	public boolean checkAddressFieldIsEditable(){ 
		return electionsTextBox.isEnabled();
	}
	
	public void enterAddressInAddressField(String addressToBeEntered){
		electionsTextBox.sendKeys(addressToBeEntered);
	}
	
	public String chooseSuggestedAddressAndWaitForConfirmation(String country){
		WebElement suggestedCountry = driver.findElement(By.xpath("//div[@class='suggestionItem--active']"));
		suggestedCountry.click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
	     return wait.until(driver -> {
	    	 countryFound.isDisplayed();
	        return driver.findElement(By.xpath("//span[contains(text(),'"+country+"')]")).getText();
	    });
	}
	
	public String checkForCountyName(String countyName){
		return driver.findElement(By.xpath("//span[contains(text(),'"+countyName+" County')]")).getText();
	}
	
	public boolean checkForFederalElectionSchedule(){
		return FederalElectionsCheck.getAttribute("src").contains("check-mark");
	}
	
	public boolean checkForStateElectionSchedule(){
		return StateElectionsCheck.getAttribute("src").contains("check-mark");
	}
	
	public boolean checkForLocalElectionSchedule(){
		return LocalElectionsCheck.getText().contains("COMING SOON"); 
	}
	
	public boolean clickAddToCalendar() throws InterruptedException{ 
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView()", AddToCalendarButton); 
		Thread.sleep(3000);
		boolean buttonEnabled = AddToCalendarButton.isEnabled();
		AddToCalendarButton.click();
		return buttonEnabled;
	}
	
	public void clearAddressTextBox() throws InterruptedException
	{
		electionsTextBox.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
	}
	
	public boolean checkForAddressesSuggestiosForSpecificNumbers(int expectedNumberOfSuggestions) 
			throws InterruptedException{ 
		WebDriverWait wait = new WebDriverWait(driver, 10);
			    return wait.until(driver -> {
			        List<WebElement> e=driver.findElements(By.xpath("//div[@class='suggestionItem']"));  
			        e.add(driver.findElement(By.xpath("//div[@class='suggestionItem--active']")));
			        return e.size() == expectedNumberOfSuggestions;
			    });
	}
	
	public boolean checkForAddressesSuggestiosForSpecialCharacters(){
		try {
		driver.findElement(By.xpath("//div[@class='suggestionItem']"));
		return false;
		}
		catch(NoSuchElementException e)
		{
			return true;
		}
	}
	
	public String checkFAQAvailableOnPage()
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView()", FAQHeading); 
		return FAQHeading.getText();
	}
	
	public List checkAllFAQDropDownsAreCollapsedExceptTheFirstInitially()
	{ 
		List<WebElement> allFAQDropdown = driver.findElements(By.xpath("//div[@data-radium ='true']/"
				+ "img[@src='/cal/assets/images/thick_arrow.png']"));
		return allFAQDropdown;
	}
	
	public boolean checkDropDownExpandFuntionality() throws InterruptedException
	{
		List<WebElement> allFAQDropdown = driver.findElements(By.xpath("//div[@data-radium ='true']/"
				+ "img[@src='/cal/assets/images/thick_arrow.png']"));
		allFAQDropdown.get(0).click();
		Random rand = new Random();
        WebElement random = allFAQDropdown.get(rand.nextInt(allFAQDropdown.size()));
        scrollToFindElement(random);
        random.click();
		return random.getAttribute("style").contains("transform: rotate(90deg); cursor: pointer;");
	}

	public List verifyOnlyOneDropDownFunctionality(List<WebElement> webElements) throws InterruptedException
	{
		Random rand = new Random();
        WebElement random = webElements.get(rand.nextInt(webElements.size()));
        scrollToFindElement(random);
        random.click();
        WebElement random1 = webElements.get(rand.nextInt(webElements.size()));
        scrollToFindElement(random);
        random1.click();
        List<WebElement> newList = webElements;
        newList.remove(random1);
        return newList;
        
        
	}

}