package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage extends Page {

	public SignInPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		super(driver, locators, waiter);

	}

	public WebElement getStoreSignIn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("store_sign_in_link")));
	}

	public WebElement getStoreUsername() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("store_username_field")));
	}

	public WebElement getStorePassword() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("store_password_field")));
	}

	public WebElement getStoreLogin() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("store_login_btn")));
	}

	public WebElement getStoreSignOut() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("store_sign_out_link")));
	}

	public void storeSignInClick() {
		this.getStoreSignIn().click();
	}

	public void setStoreUsername(String username) {
		this.getStoreUsername().clear();
		this.getStoreUsername().sendKeys(username);
	}

	public void setStorePassword(String password) {
		this.getStorePassword().clear();
		this.getStorePassword().sendKeys(password);
	}

	public void storeLoginClick() {
		this.getStoreLogin().click();

	}

	public void storeSignOutClick() {
		this.getStoreSignOut().click();
	}

	public boolean verifyLogin() {
		boolean result = false;

		try {
			WebElement link = this.getStoreSignOut();

			if (link.getText().equals("Sign Out")) {
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

}
