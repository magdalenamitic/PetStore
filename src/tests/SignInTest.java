package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.PetStoreMenuPage;
import pages.SignInPage;
import utils.ExcelUtils;

public class SignInTest extends TestTemplate {
	@Test
	public void storeSignInTest() throws InterruptedException {
		driver.navigate().to(this.locators.getProperty("store_menu_page_url"));
		
		SoftAssert sa = new SoftAssert();
		SignInPage sp = new SignInPage(driver, locators, waiter);
		
		sp.storeSignInClick();
		 
		ExcelUtils eu = new ExcelUtils();
		eu.setExcell(this.locators.getProperty("xlsx_data_source"));
		eu.setWorkSheet(1);
		
		for (int i = 1; i < eu.getRowNumber(); i++) {
			
			String username = eu.getDataAt(i, 0); 
			String password = eu.getDataAt(i, 1);
	
			sp.setStoreUsername(username);
			sp.setStorePassword(password);
			sp.storeLoginClick();
			
			boolean validLogin = sp.verifyLogin();
			
			sa.assertTrue(validLogin, "Failed Sign In. Username: " + username + ", Password: " + password);
			
			if (validLogin) {
				sp.storeSignOutClick();
				sp.storeSignInClick();
			}
		}
		
		sa.assertAll();

	}
}
