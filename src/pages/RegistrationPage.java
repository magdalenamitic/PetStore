package pages;

import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage extends Page {

	public RegistrationPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		super(driver, locators, waiter);

	}

	public WebElement getRegisterUserId() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("register_user_id")));
	}

	public void setRegisterUserId(String userId) {
		this.getRegisterUserId().clear();
		this.getRegisterUserId().sendKeys(userId);
	}

	public WebElement getNewPassword() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("register_new_password")));
	}

	public void setNewPassword(String newPassword) {
		this.getNewPassword().clear();
		this.getNewPassword().sendKeys(newPassword);
	}

	public WebElement getRepeatPassword() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("register_repeat_password")));
	}

	public void setRepeatPassword(String repeatPassword) {
		this.getRepeatPassword().clear();
		this.getRepeatPassword().sendKeys(repeatPassword);
	}

	public WebElement getFirstName() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("register_first_name")));
	}

	public void setFirstName(String firstName) {
		this.getFirstName().clear();
		this.getFirstName().sendKeys(firstName);
	}

	public WebElement getLastName() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("register_last_name")));
	}

	public void setLastName(String lastName) {
		this.getLastName().clear();
		this.getLastName().sendKeys(lastName);
	}

	public WebElement getEmail() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("register_email")));
	}

	public void setEmail(String email) {
		this.getEmail().clear();
		this.getEmail().sendKeys(email);
	}

	public WebElement getPhone() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("register_phone")));
	}

	public void setPhone(String phone) {
		this.getPhone().clear();
		this.getPhone().sendKeys(phone);
	}

	public WebElement getAddress1() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("register_address_1")));
	}

	public void setAddress1(String address) {
		this.getAddress1().clear();
		this.getAddress1().sendKeys(address);
	}

	public WebElement getAddress2() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("register_address_2")));
	}

	public void setAddress2(String address) {
		this.getAddress2().clear();
		this.getAddress2().sendKeys(address);
	}

	public WebElement getCity() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("register_city")));
	}

	public void setCity(String city) {
		this.getCity().clear();
		this.getCity().sendKeys(city);
	}

	public WebElement getState() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("register_state")));
	}

	public void setState(String state) {
		this.getState().clear();
		this.getState().sendKeys(state);
	}

	public WebElement getZip() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("register_zip")));
	}

	public void setZip(String zip) {
		this.getZip().clear();
		this.getZip().sendKeys(zip);
	}

	public WebElement getCountry() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("register_country")));
	}

	public void setCountry(String country) {
		this.getCountry().clear();
		this.getCountry().sendKeys(country);
	}

	public WebElement getLanguagePreference() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("register_language_preference")));
	}

	public void setLanguagePreference(String language) {
		WebElement e = this.getLanguagePreference();
		Select sel = new Select(e);
		sel.selectByVisibleText(language);
	}

	public WebElement getFavouriteCategory() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("register_favourite_category")));
	}

	public void setFavouriteCategory(String category) {
		WebElement e = this.getFavouriteCategory();
		Select sel = new Select(e);
		sel.selectByVisibleText(category);
	}

	public WebElement getEnableMyList() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("register_enable_mylist")));
	}

	public void setEnableMyList() {
		this.getEnableMyList().click();
	}

	public WebElement getEnableMyBanner() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("register_enable_mybanner")));
	}

	public void setEnableMyBanner() {
		this.getEnableMyBanner().click();
	}

	public WebElement getSaveAccountInformation() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("save_account_information")));
	}

	public void clickSaveAccountInfotmation() {
		this.getSaveAccountInformation().click();
	}

	public WebElement getStoreMenuLeft() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("store_menu_left_holder")));
	}

	public void fillUserInformation(String userId, String newPassword, String repeatPassword) {
		this.setRegisterUserId(userId);
		this.setNewPassword(newPassword);
		this.setRepeatPassword(repeatPassword);
	}

	public void fillAccountInformation(String firstName, String lastName, String email, String phone, String address1,
			String address2, String city, String state, String zip, String country) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPhone(phone);
		this.setAddress1(address1);
		this.setAddress2(address2);
		this.setCity(city);
		this.setState(state);
		this.setZip(zip);
		this.setCountry(country);

	}

	public void fillProfileInformation(String language, String category, boolean myList, boolean myBanner) {
		this.setLanguagePreference(language);
		this.setFavouriteCategory(category);
		if (myList) {
			this.setEnableMyList();
		}
		if (myBanner) {
			this.setEnableMyBanner();
		}
	}

	public boolean verifyRegistration() {
		boolean result = false;

		try {
			this.getStoreMenuLeft();
			result = true;
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

}
