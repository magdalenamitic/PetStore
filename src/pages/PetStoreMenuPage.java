package pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PetStoreMenuPage extends Page {
	public PetStoreMenuPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		super(driver, locators, waiter);
	}

	public WebElement getStoreMenuLeft() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("store_menu_left_holder")));
	}

	public WebElement getStoreMenuImg() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("store_menu_img_holder")));
	}

	public WebElement getStoreMenuTop() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("store_menu_top_holder")));
	}

	public WebElement getStoreSignIn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("store_sign_in_link")));
	}

	public WebElement getStoreCategoryTitle() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("store_category_title")));
	}
	
	public WebElement getStoreCategoryBackLink() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("store_category_back_link")));
	}
	
	public WebElement getStoreLoginText() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("store_login_text")));
	}
	
	public boolean verifyStoreMenuURLStatus(WebElement linkHolder, String tagName) {
		boolean result = true;
		
		try {
			List<WebElement> links = linkHolder.findElements(By.tagName(tagName));
			
			if (links.size() == 0) {
				result = false;
			}
			
			for (int i=0; i<links.size(); i++) {
				String urlString = links.get(i).getAttribute("href");
				if (this.verifyURLStatus(urlString) > 400) {
					result = false;
				}
			}
		}
		catch (Exception e) {
			result = false;
		}
		
		return result;
	}
	
	public boolean verifyStoreMenuLeftURLStatus() {
		WebElement holder = this.getStoreMenuLeft();
		return this.verifyStoreMenuURLStatus(holder, "a");
	}
	
	public boolean verifyStoreMenuTopURLStatus() {
		WebElement holder = this.getStoreMenuTop();
		return this.verifyStoreMenuURLStatus(holder, "a");
	}
	
	public boolean verifyStoreMenuImgURLStatus() {
		WebElement holder = this.getStoreMenuImg();
		return this.verifyStoreMenuURLStatus(holder, "area");
	}

	public boolean verifySignInURLStatus() {
		boolean result = true;
		
		try {
			WebElement link = this.getStoreSignIn();

			String urlString = link.getAttribute("href");
			if (this.verifyURLStatus(urlString) > 400) {
				result = false;
			}
		}
		catch (Exception e) {
			result = false;
		}
		
		return result;
	}

	public boolean validMenuNavigation(WebElement link) {
		boolean result = true;
		
		try {
			String urlString = link.getAttribute("href");
			String urlCategoryName = this.getLastWordAfterCharacter(urlString, "=");
			
			link.click();
			
			WebElement categoryTitle = this.getStoreCategoryTitle();
			if (!categoryTitle.getText().toUpperCase().equals(urlCategoryName.toUpperCase())) {
				result = false;
			}
			
			this.getStoreCategoryBackLink().click();
		}
		catch (Exception e) {
			result = false;
		}
		
		return result;
	}
	
	public boolean verifyStoreMenuLeftURLPage() {
		boolean result = true;
		
		try {
			WebElement holder = this.getStoreMenuLeft();
			List<WebElement> links = holder.findElements(By.tagName("a"));
			
			if (links.size() == 0) {
				result = false;
			}
			
			for (int i=0; i<links.size(); i++) {
				boolean validPage = this.validMenuNavigation(links.get(i));
				
				if (validPage == false) {
					result = false;
				}
				
				holder = this.getStoreMenuLeft();
				links = holder.findElements(By.tagName("a"));
			}
		}
		catch (Exception e) {
			result = false;
		}
		
		return result;
	}
	
	public boolean verifyStoreMenuTopURLPage() {
		boolean result = true;
		
		try {
			WebElement holder = this.getStoreMenuTop();
			List<WebElement> links = holder.findElements(By.tagName("a"));
			
			if (links.size() == 0) {
				result = false;
			}
			
			for (int i=0; i<links.size(); i++) {
				boolean validPage = this.validMenuNavigation(links.get(i));
				
				if (validPage == false) {
					result = false;
				}
				
				holder = this.getStoreMenuTop();
				links = holder.findElements(By.tagName("a"));
			}
		}
		catch (Exception e) {
			result = false;
		}
		
		return result;
	}
	
	public boolean verifyStoreMenuImgURLPage() {
		boolean result = true;
		
		try {
			WebElement holder = this.getStoreMenuImg();
			List<WebElement> links = holder.findElements(By.tagName("area"));
			
			if (links.size() == 0) {
				result = false;
			}
			
			for (int i=0; i<links.size(); i++) {
				boolean validPage = this.validMenuNavigation(links.get(i));
				
				if (validPage == false) {
					result = false;
				}
				
				holder = this.getStoreMenuLeft();
				links = holder.findElements(By.tagName("a"));
			}
		}
		catch (Exception e) {
			result = false;
		}
		
		return result;
	}
	
	public boolean verifySignInPage() {
		boolean result = true;
		
		try {
			this.getStoreSignIn().click();
			
			if (!this.getStoreLoginText().getText().contains("Please enter your username and password.")) {
				result = false;
			}
		}
		catch (Exception e) {
			result = false;
		}
		
		return result;
	}
	
	public int verifyURLStatus(String urlString) {
		int status = 404;
		try {
			URL link = new URL(urlString);
			HttpURLConnection hConn = null;
			hConn = (HttpURLConnection) link.openConnection();
			hConn.setRequestMethod("GET");
			hConn.connect();
			status = hConn.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
	}

	public String getLastWordAfterCharacter(String str, String ch) {
		String strArr[] = str.split(ch); 
		return strArr[strArr.length - 1];
	}
}

