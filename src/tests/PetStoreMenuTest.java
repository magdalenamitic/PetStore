package tests;

import static org.testng.Assert.assertTrue;

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

import pages.HomePage;
import pages.PetStoreMenuPage;

public class PetStoreMenuTest extends TestTemplate {
	@Test
	public void petStoreMenuVerifyURLStatusTest() throws InterruptedException {
		driver.navigate().to(this.locators.getProperty("store_menu_page_url"));
		
		SoftAssert sa = new SoftAssert();
		
        PetStoreMenuPage psmp = new PetStoreMenuPage(driver, locators, waiter);
		
		sa.assertTrue(psmp.verifyStoreMenuLeftURLStatus(), "Failed 'verifyStoreMenuLeftURLStatus'"); 
		sa.assertTrue(psmp.verifyStoreMenuImgURLStatus(), "Failed 'verifyStoreMenuImgURLStatus'"); 
		sa.assertTrue(psmp.verifyStoreMenuTopURLStatus(), "Failed 'verifyStoreMenuTopURLStatus'"); 
		sa.assertTrue(psmp.verifySignInURLStatus(), "Failed 'verifySignInURLStatus'"); 

		sa.assertAll();
	}

	@Test
	public void petStoreMenuVerifyURLPageTest() throws InterruptedException {
		driver.navigate().to(this.locators.getProperty("store_menu_page_url"));
		
		SoftAssert sa = new SoftAssert();

		PetStoreMenuPage psmp = new PetStoreMenuPage(driver, locators, waiter);

		sa.assertTrue(psmp.verifyStoreMenuLeftURLPage(), "Failed 'verifyStoreMenuLeftURLPage'");
		sa.assertTrue(psmp.verifyStoreMenuTopURLPage(), "Failed 'verifyStoreMenuTopURLPage'");
		sa.assertTrue(psmp.verifyStoreMenuImgURLPage(), "Failed 'verifyStoreMenuImgURLPage'");
		
		sa.assertAll();
	}
	

	@Test
	public void signInPageTest() throws InterruptedException {
		driver.navigate().to(this.locators.getProperty("store_menu_page_url"));
		PetStoreMenuPage psmp = new PetStoreMenuPage(driver, locators, waiter);
		Assert.assertTrue(psmp.verifySignInPage());
	}
	
}
