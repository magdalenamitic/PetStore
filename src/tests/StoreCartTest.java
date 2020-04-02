package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.CartPage;
import pages.SignInPage;
import pages.StoreItemPage;
import utils.ExcelUtils;

public class StoreCartTest extends TestTemplate {
	@Test(priority = 1)
	public void storeAddToCartTest() throws InterruptedException {
		StoreItemPage sip = new StoreItemPage(driver, locators, waiter);
		CartPage cp = new CartPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();

		cp.clearCart();

		ExcelUtils eu = new ExcelUtils();
		eu.setExcell(this.locators.getProperty("xlsx_data_source"));
		eu.setWorkSheet(0);

		for (int i = 1; i < eu.getRowNumber(); i++) {
			String itemURL = eu.getDataAt(i, 1);
			this.driver.navigate().to(itemURL);
			sip.clickAddToCart();
		}

		for (int i = 1; i < eu.getRowNumber(); i++) {
			String itemId = eu.getDataAt(i, 0);
			sa.assertTrue(cp.verifyItemInCart(itemId), "Failed 'verifyItemInCart', ID: " + itemId);
		}

		sa.assertAll();
	}

	@Test(priority = 2)
	public void storeCookieCartTest() throws InterruptedException {
		StoreItemPage sip = new StoreItemPage(driver, locators, waiter);
		CartPage cp = new CartPage(driver, locators, waiter);

		cp.clearCart();

		ExcelUtils eu = new ExcelUtils();
		eu.setExcell(this.locators.getProperty("xlsx_data_source"));
		eu.setWorkSheet(0);

		for (int i = 1; i < eu.getRowNumber(); i++) {
			String itemURL = eu.getDataAt(i, 1);
			this.driver.navigate().to(itemURL);
			sip.clickAddToCart();
		}

		cp.clickCartLink();

		this.driver.manage().deleteAllCookies();
		this.driver.navigate().refresh();
		Assert.assertTrue(cp.verifyEmptyCart(), "Failed 'verifyEmptyCart'");
	}

	@Test(priority = 3)
	public void storeCartPriceTest() throws InterruptedException, ParseException {
		StoreItemPage sip = new StoreItemPage(driver, locators, waiter);
		CartPage cp = new CartPage(driver, locators, waiter);

		cp.clearCart();

		ExcelUtils eu = new ExcelUtils();
		eu.setExcell(this.locators.getProperty("xlsx_data_source"));
		eu.setWorkSheet(0);

		for (int i = 1; i < eu.getRowNumber(); i++) {
			String itemURL = eu.getDataAt(i, 1);
			this.driver.navigate().to(itemURL);
			sip.clickAddToCart();
		}

		cp.clickCartLink();
		Assert.assertTrue(cp.verifyCartPrice(), "Failed 'verifyCartPrice'");

	}

	@Test(priority = 4)
	public void storeCartSubTotalTest() throws InterruptedException, ParseException {
		StoreItemPage sip = new StoreItemPage(driver, locators, waiter);
		CartPage cp = new CartPage(driver, locators, waiter);

		cp.clearCart();

		ExcelUtils eu = new ExcelUtils();
		eu.setExcell(this.locators.getProperty("xlsx_data_source"));
		eu.setWorkSheet(0);

		for (int i = 1; i < eu.getRowNumber(); i++) {
			String itemURL = eu.getDataAt(i, 1);
			this.driver.navigate().to(itemURL);
			sip.clickAddToCart();
		}

		cp.clickCartLink();
		Assert.assertTrue(cp.verifyItemsSubTotal(), "Failed 'verifyItemsSubTotal'");
	}
}
