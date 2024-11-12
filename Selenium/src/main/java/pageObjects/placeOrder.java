package pageObjects;

import java.util.concurrent.TimeoutException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commonMethods.commonMethods;
import utils.constants;
import utils.locators.placeOrderLocators;
import utils.locators.placeOrderLocators;
import utils.locators.placeOrderLocators;

public class placeOrder {

	commonMethods cm = new commonMethods();
	
	@FindBy(xpath = placeOrderLocators.placeOrder)
	private WebElement placeOrder;
	
	@FindBy(xpath = placeOrderLocators.purchase)
	private WebElement purchase;

	@FindBy(xpath = placeOrderLocators.name)
	private WebElement name;

	@FindBy(xpath = placeOrderLocators.country)
	private WebElement country;
	
	@FindBy(xpath = placeOrderLocators.city)
	private WebElement city;
	
	@FindBy(xpath = placeOrderLocators.creditCard)
	private WebElement creditCard;

	@FindBy(xpath = placeOrderLocators.month)
	private WebElement month;

	@FindBy(xpath = placeOrderLocators.year)
	private WebElement year;
	
	@FindBy(xpath = placeOrderLocators.successMsg)
	private WebElement successMsg;
	
	@FindBy(xpath = placeOrderLocators.okButton)
	private WebElement okButton;
	
	public void placeOrderViaCart(WebDriver driver) throws TimeoutException {
		cm.commonWaitToFindElement("xpath", placeOrderLocators.placeOrder);
		placeOrder.click();
		cm.commonWaitToFindElement("xpath", placeOrderLocators.purchase);
		purchase.click();
		cm.waitForAlert();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(),constants.purchaseWithoutDetailsAlert);
		alert.accept();
		name.sendKeys(constants.name);
		country.sendKeys(constants.country);
		city.sendKeys(constants.city);
		creditCard.sendKeys(constants.creditCard);
		month.sendKeys(constants.month);
		year.sendKeys(constants.year);
		purchase.click();
		
		Assert.assertEquals(successMsg.getText(), constants.orderPlacedMsg);
		
		okButton.click();
	}

}
