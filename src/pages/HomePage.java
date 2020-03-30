package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends Page {
	public HomePage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		super(driver,locators,waiter);
	}

	public WebElement getStoreLink() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("home_page_store_link")));
	}
	
	public void clickStoreLink() {
		this.getStoreLink().click();
	}
	
	public WebElement getStoreMenuLeft() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("store_menu_left_holder")));
	}
	
	public boolean enteredStore() {
		boolean result = true;
		
		try {
			this.getStoreMenuLeft();
		}
		catch (Exception e) {
			result = false;
		}
		
		return result;
	}
}
