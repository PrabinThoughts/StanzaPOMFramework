package com.stanza.automation.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stanza.automation.ui.basetest.TestBase;
import com.stanza.automation.ui.pages.ApplicationHomePage;

/**
* This class is to be used for writing test cases related to Application page.
* @author  Prabin Nayak
*/

public class ApplicationHomePageTest extends TestBase{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ElectionsPageTest.class);

	ApplicationHomePage appPage;



	public ApplicationHomePageTest(){
		super();

	} 
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
	}
 
	
}

