package com.stanza.automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stanza.automation.testcases.ElectionsPageTest;
import com.stanza.automation.ui.basetest.TestBase;

import static com.stanza.automation.ui.util.TestData.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ApplicationHomePage extends TestBase {
	
	@FindBy(xpath = "//div[contains(@class,'header-logo')]")
	WebElement stanzaLogo; 
	
	@FindBy(xpath = "//div[contains(@class,'rmq-40d9607f')]")
	WebElement eventsHeader; 
	
	@FindBy(xpath = "//span[contains(text(),'Publish Your Events')]")
	WebElement publishEvents; 
	
	@FindBy(xpath = "//span[contains(text(),'Our Story')]")
	WebElement ourStory; 
	
	@FindBy(xpath = "//span[contains(text(),'Sign In')]")
	WebElement signIn; 
	
	@FindBy(xpath = "//p[contains(@class,'header-icon icon-hamburger')]")
	WebElement hamburgerMenu; 
	
	@FindBy(xpath = "//p[contains(text(),'Publish A Calendar')]")
	WebElement publishACalendar; 
	
	@FindBy(xpath = "//p[contains(text(),'Careers')]")
	WebElement careers; 
	
	@FindBy(xpath = "//p[contains(text(),'Contact Us')]")
	WebElement contactUs; 
	
	@FindBy(xpath = "//p[contains(text(),'Blog')]")
	WebElement blog; 
	
	@FindBy(xpath = "//p[contains(text(),'Terms')]")
	WebElement terms; 
	
	@FindBy(xpath = "//p[contains(text(),'Privacy')]")
	WebElement privacy; 
	
	@FindBy(xpath = "")
	WebElement selectCategory; 
	
	@FindBy(xpath = "")
	WebElement imageTileCalendar; 
	
	@FindBy(xpath = "")
	WebElement addToCalendarTile; 
	
	@FindBy(xpath = "//p[contains(text(),'How It Works')]")
	WebElement howItWorks; 
	
	@FindBy(xpath = "//p[contains(text(),'What Our Fans Say')]")
	WebElement ourFansSay; 
	
	@FindBy(xpath = "//a[contains(text(),'Home')]")
	WebElement footerHome; 
	
	@FindBy(xpath = "//a[contains(text(),'Contact')]")
	WebElement footerContact; 
	
	@FindBy(xpath = "//a[contains(text(),'Terms')]")
	WebElement footerTerms; 
	
	@FindBy(xpath = "//a[contains(text(),'About Us')]")
	WebElement footerAboutUs; 
	
	@FindBy(xpath = "//a[contains(text(),'Help')]")
	WebElement footerHelp; 
	
	@FindBy(xpath = "//a[contains(text(),'Privacy')]")
	WebElement footerPrivacy; 
	
	@FindBy(xpath = "//a[contains(text(),'Facebook')]")
	WebElement footerFacebook; 
	
	@FindBy(xpath = "//a[contains(text(),'Twitter')]")
	WebElement footerTwitter; 
	
	
	@FindBy(xpath = "//a[@href='/']")
	WebElement pageHeader; 
	
	@FindBy(xpath="//section[@id='main']/h1")
	WebElement numberOfComputersFound;
	
	@FindBy(id="searchbox")
	WebElement computerSearchBox;
	
	@FindBy(id="searchsubmit")
	WebElement computerSearchButton;
	
	@FindBy(id = "add")
	WebElement computerAddButton;
	
	@FindBy(xpath = "//section[@id='main']/table/thead/tr/th[1]/a")
	WebElement firstColumnHeader;
	
	@FindBy(xpath = "//section[@id='main']/table/thead/tr/th[2]/a")
	WebElement secondColumnHeader;
	
	@FindBy(xpath = "//section[@id='main']/table/thead/tr/th[3]/a")
	WebElement thirdColumnHeader;
	
	@FindBy(xpath = "//section[@id='main']/table/thead/tr/th[4]/a")
	WebElement fourthColumnHeader;
	
	@FindBy(xpath = "//section[@id='main']/table/thead/tr/th[5]/a")
	WebElement fifthColumnHeader;
	
	@FindBy(xpath = "//section[@id='main']/table/tbody/tr[1]/td[1]")
	WebElement firstComputerName;
	
	@FindBy(xpath = "//section[@id='main']/table/tbody/tr[1]/td[1]/a")
	WebElement firstComputerLink;
	
	@FindBy(xpath = "//section[@id='main']/table/tbody/tr[2]/td[1]")
	WebElement secondComputerName;
	
	@FindBy(xpath = "//section[@id='main']/table/tbody/tr[2]/td[1]/a")
	WebElement secondComputerLink;
	
	@FindBy(xpath = "//section[@id='main']/table/tbody/tr[1]/td[2]")
	WebElement firstIntroducedDate;
	
	@FindBy(xpath = "//section[@id='main']/table/tbody/tr[2]/td[2]")
	WebElement secondIntroducedDate;
	
	@FindBy(xpath = "//section[@id='main']/table/tbody/tr[1]/td[3]")
	WebElement firstDiscontinuedDate;
	
	@FindBy(xpath = "//section[@id='main']/table/tbody/tr[2]/td[3]")
	WebElement secondDiscontinuedDate;
	
	@FindBy(xpath = "//section[@id='main']/table/tbody/tr[1]/td[4]")
	WebElement firstCompanyName;
	
	@FindBy(xpath = "//section[@id='main']/table/tbody/tr[2]/td[4]")
	WebElement secondCompanyName; 
	
	@FindBy(xpath = "//div[@id='pagination']/ul/li[2]/a")
	WebElement paginationText; 
	
	@FindBy(xpath = "//section[@id='main']/div[2]/em")
	WebElement noComputerFound;
	
	@FindBy(xpath = "//section[@id='main']/table/tbody")
	WebElement table;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ElectionsPageTest.class);

	
	// Initializing the Page Objects:
	public ApplicationHomePage() {
		LOGGER.info("Loading all webelements... "); 
		PageFactory.initElements(driver, this);
	}

	
	
}