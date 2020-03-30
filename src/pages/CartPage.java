package pages;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends Page {

	public CartPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		super(driver, locators, waiter);

	}

	public WebElement getCartLink() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("item_cart_link")));
	}

	public void clickCartLink() {
		this.getCartLink().click();
	}

	public boolean verifyItemInCart(String itemId) {
		boolean result = false;

		try {
			WebElement holder = this.driver.findElement(By.xpath(this.locators.getProperty("item_cart_table")));
			List<WebElement> items = holder.findElements(By.tagName("a"));
			for (int i = 0; i < items.size(); i++) {
				if (items.get(i).getText().equals(itemId)) {
					result = true;
					break;
				}

			}

		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean verifyEmptyCart() {
		boolean result = false;
		try {
			WebElement text = this.driver.findElement(By.xpath(this.locators.getProperty("item_empty_cart_text")));
			if (text.getText().equals("Your cart is empty.")) {
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean verifyCartPrice() {
		boolean result = false;
		try {
			WebElement holder = this.driver.findElement(By.xpath(this.locators.getProperty("item_cart_table")));
			List<WebElement> trows =  holder.findElements(By.tagName("tr"));
			
			double subTotal = 0;
			for(int i = 1; i < trows.size()-1; i++) {
				WebElement totalCost = trows.get(i).findElement(By.xpath("td[7]"));
				
				DecimalFormat format = new DecimalFormat("$#.##");
				double totalCostNum = format.parse(totalCost.getText()).doubleValue();
				
				subTotal += totalCostNum;
			}
			String priceText = String.format("%.2f", subTotal);
			
			WebElement lastRowText = trows.get(trows.size()-1).findElement(By.xpath("td[1]"));
			if(lastRowText.getText().contains(String.valueOf(priceText))) {
				result = true;
			}
		}
		catch(Exception e) {
			result = false;
		}
		return result;
	}
	
	public boolean verifyItemsSubTotal() {
		boolean result = true;
		try {
			WebElement holder = this.driver.findElement(By.xpath(this.locators.getProperty("item_cart_table")));
			List<WebElement> trows =  holder.findElements(By.tagName("tr"));
			
			double totalPrice = 0;
			for(int i = 1; i < trows.size()-1; i++) {
				WebElement totalCost = trows.get(i).findElement(By.xpath("td[7]"));
				WebElement Quantity =  trows.get(i).findElement(By.xpath("td[5]/input"));
				WebElement ListPrice = trows.get(i).findElement(By.xpath("td[6]"));
				
				DecimalFormat format = new DecimalFormat("$#.##");
				double totalCostNum = format.parse(totalCost.getText()).doubleValue();
				double QuantityNum = Double.parseDouble(Quantity.getAttribute("value"));
				double ListPriceNum = format.parse(ListPrice.getText()).doubleValue();
				
				if(Math.round(QuantityNum*ListPriceNum) != Math.round(totalCostNum)) {
					result = false;
				}
			}
		}
		catch(Exception e) {
			result = false;
		}
		
		return result;
				
	}
	
	public void clearCart() {
		this.driver.navigate().to(this.locators.getProperty("item_cart_url"));
		this.driver.manage().deleteAllCookies();
	}
	
}
