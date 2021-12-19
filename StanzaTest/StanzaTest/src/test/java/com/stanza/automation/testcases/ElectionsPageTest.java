package com.stanza.automation.testcases;

import static com.stanza.automation.ui.util.TestData.Expected_County_Name;
import static com.stanza.automation.ui.util.TestData.Expected_Dropdown_Expanded_Attribute;
import static com.stanza.automation.ui.util.TestData.Expected_State_Name;
import static com.stanza.automation.ui.util.TestData.Invalid_Special_Character_Sequence;
import static com.stanza.automation.ui.util.TestData.Passed_County_Name;
import static com.stanza.automation.ui.util.TestData.Passed_State_Name;
import static com.stanza.automation.ui.util.TestData.Specific_Complete_Address;
import static com.stanza.automation.ui.util.TestData.Valid_Auto_Suggest_Data;

import java.text.ParseException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.stanza.automation.ui.basetest.TestBase;
import com.stanza.automation.ui.pages.ElectionsPage;

public class ElectionsPageTest extends TestBase{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ElectionsPageTest.class);
	
	ElectionsPage addComputerPage;

	private int updatedComputerCount;
	private int existingComputerCount ;


	public ElectionsPageTest(){
		super();

	} 
	

	@Test(priority=1)
	/**
	* This test case will verify Elections Page fOr Address suggestions AutoPopulation and 
	* check for Special characters support.
	* @author  Prabin Nayak
	*/
	public void checkAddressesAutoPopulation() throws InterruptedException, ParseException
	{ 
		addComputerPage = new ElectionsPage();
		
		LOGGER.info("Checking if Elections page has loaded.");
		addComputerPage.scrollToFindElement(addComputerPage.electioncallendarText);
		Assert.assertEquals("Election Calendar", addComputerPage.getElectionsPageHeader(),
				"The Elections Page header matches the expected value.");
		
		Assert.assertTrue(addComputerPage.checkAddressFieldIsEditable(),"The Address textbox is editable");
		
		LOGGER.info("Entering String to check auto suggestions");
		addComputerPage.enterAddressInAddressField(Valid_Auto_Suggest_Data);
		Assert.assertTrue(addComputerPage.checkForAddressesSuggestiosForSpecificNumbers(5),
				"The total number of suggestions are 5");
		
		LOGGER.info("Clearing Adress Text box");
		addComputerPage.clearAddressTextBox();
		
		LOGGER.info("Entering invalid special characters");
		addComputerPage.enterAddressInAddressField(Invalid_Special_Character_Sequence);
		Assert.assertTrue(addComputerPage.checkForAddressesSuggestiosForSpecialCharacters(),
				"No auto suggestions are being populated for invalid characters.");

	} 
	
	@Test(priority=2)
	/**
	* This test case enter specific Address and Will check in Add to calendar button redirects to google sign in page.
	* @author  Prabin Nayak
	*/
	public void enterDesiredAddressAndAddToCalendar() throws InterruptedException, ParseException
	{ 
		addComputerPage = new ElectionsPage();
		
		LOGGER.info("Pointing browser screen to specific element on the page.");
		addComputerPage.scrollToFindElement(addComputerPage.electionsTextBox);
		
		LOGGER.info("Clearing Adress Text box");
		addComputerPage.clearAddressTextBox();
		LOGGER.info("Entering Specific Address to check auto suggestions");
		addComputerPage.enterAddressInAddressField(Specific_Complete_Address);
		
		Assert.assertEquals(addComputerPage.chooseSuggestedAddressAndWaitForConfirmation(Passed_State_Name), Expected_State_Name,
				"State check passed");
		Assert.assertEquals(addComputerPage.checkForCountyName(Passed_County_Name), Expected_County_Name,
				"County check passed");
		Assert.assertTrue(addComputerPage.checkForFederalElectionSchedule(),"Federal Election status passed");
		Assert.assertTrue(addComputerPage.checkForStateElectionSchedule(),"State Election status passed");
		Assert.assertTrue(addComputerPage.checkForLocalElectionSchedule(),"Local Election status passed");
		Assert.assertTrue(addComputerPage.clickAddToCalendar(),
				"Add to calendar button is enabled and page redirected to sign in page");

	} 
	
	@Test(priority=3)
	/**
	* This test case will check the FAQ drop down functionality.
	* @author  Prabin Nayak
	*/
	public void checkFAQDropDownFunctionality() throws InterruptedException, ParseException
	{ 
		addComputerPage = new ElectionsPage();
		
		LOGGER.info("Pointing browser screen to specific element on the page.");
		addComputerPage.scrollToFindElement(addComputerPage.FAQHeading);
		
		LOGGER.info("Verifying FAQ header");
		Assert.assertEquals(addComputerPage.checkFAQAvailableOnPage(), "Frequently Asked Questions",
				"FAQ header is displayed as expected");
		
		LOGGER.info("Getting all dropdown elements ");
		List<WebElement> allFAQDropDownElements = addComputerPage
				.checkAllFAQDropDownsAreCollapsedExceptTheFirstInitially();
		
		LOGGER.info("Checking if the first drop down is expanded by default.");
		Assert.assertTrue(allFAQDropDownElements.get(0).getAttribute("style")
				.contains("transform: rotate(90deg); cursor: pointer;"),
				"The dropdown expand check passed");
		
		LOGGER.info("Checking if the rest all drop down are collapsed.");
		for(int collapsedDropDowns = 1; collapsedDropDowns<allFAQDropDownElements.size(); collapsedDropDowns++)
		{ 
			Assert.assertFalse(allFAQDropDownElements.get(collapsedDropDowns).getAttribute("style")
					.contains(Expected_Dropdown_Expanded_Attribute),
					"Dropdown collapse check passed");
		}
		
		LOGGER.info("Checking manual expand function of the dropdown.");
		Assert.assertTrue(addComputerPage.checkDropDownExpandFuntionality(),"Manual dropdown expand check passed."); 
		
		LOGGER.info("Getting list of all other dollapsed dropdowns");
		List<WebElement> listOfAllCollapsedDropDownApartFromTheExpandedOne = addComputerPage
				.verifyOnlyOneDropDownFunctionality(allFAQDropDownElements);
		LOGGER.info("Checking if all other dropdowns are collapsed, when one dropdown is expanded.");
		for(WebElement dropDowns: listOfAllCollapsedDropDownApartFromTheExpandedOne)
		{
			Assert.assertFalse(dropDowns.getAttribute("style")
					.contains(Expected_Dropdown_Expanded_Attribute),"Auto dropdown collapse check passed.");
		}

	} 
	
}
