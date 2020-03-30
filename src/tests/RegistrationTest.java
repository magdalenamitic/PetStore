package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.RegistrationPage;
import pages.SignInPage;
import utils.ExcelUtils;

public class RegistrationTest extends TestTemplate {
	@Test
	public void storeSignInTest() throws InterruptedException {
		driver.navigate().to(this.locators.getProperty("register_form_url"));
		
		SoftAssert sa = new SoftAssert();
		
		RegistrationPage rp = new RegistrationPage(driver, locators, waiter);
		
		Random rand = new Random();
		 
		ExcelUtils eu = new ExcelUtils();
		eu.setExcell(this.locators.getProperty("xlsx_data_source"));
		eu.setWorkSheet(1);
		
		for (int i = 1; i < eu.getRowNumber(); i++) {
			int randInt = rand.nextInt(1000000)+1; 
			String username = String.valueOf(randInt); 
			String password = eu.getDataAt(i, 1);
			String firstName = eu.getDataAt(i, 2);
			String lastName = eu.getDataAt(i, 3);
			String email = eu.getDataAt(i, 4);
			String phone = eu.getDataAt(i, 5);
			String address1 = eu.getDataAt(i, 6);
			String address2 = eu.getDataAt(1, 7);
			String city = eu.getDataAt(i, 8);
			String state = eu.getDataAt(i, 9);
			String zip = eu.getDataAt(i, 10);
			String country = eu.getDataAt(i, 11);
			String language = eu.getDataAt(i, 12);
			String category = eu.getDataAt(i, 13);
			String myList = eu.getDataAt(i, 14);
			String myBanner = eu.getDataAt(i, 15);		
			
			rp.fillUserInformation(username, password, password);
			rp.fillAccountInformation(firstName, lastName, email, phone, address1, address2, city, state, zip, country);
		    rp.fillProfileInformation(language, category, Boolean.parseBoolean(myList), Boolean.parseBoolean(myBanner));
		    
		    rp.clickSaveAccountInfotmation();
		    
		    sa.assertTrue(rp.verifyRegistration(), "Failed Registration. Excel row: " + i);
		    
		    driver.navigate().to(this.locators.getProperty("register_form_url"));
		}
		
		sa.assertAll();

	}
}
