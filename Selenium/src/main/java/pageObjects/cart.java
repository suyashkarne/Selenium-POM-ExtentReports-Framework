package pageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commonMethods.commonMethods;
import utils.constants;
import utils.locators.cartLocators;
import utils.locators.cartLocators;

public class cart {

	commonMethods cm = new commonMethods();
	static int sum=0;
	
	@FindBy(xpath = cartLocators.addToCart)
	private WebElement addToCart;
	
	@FindBy(xpath = cartLocators.home)
	private WebElement home;

	@FindBy(xpath = cartLocators.cart)
	private WebElement cart;

	@FindBy(xpath = cartLocators.noOfProducts)
	private WebElement noOfProducts;
	
	@FindBy(xpath = cartLocators.productPrice)
	private WebElement productPrice;
	
	@FindBy(xpath = cartLocators.total)
	private WebElement total;

	@FindBy(xpath = cartLocators.rowsInCart)
	private WebElement rowsInCart;

	@FindBy(xpath = cartLocators.cartDelete)
	private WebElement cartDelete;
	
	@FindBy(xpath=cartLocators.productPriceInTable)
	private WebElement productPriceInTable;
	
	public void addProductToCart(WebDriver driver, String product1, String product2) throws TimeoutException, InterruptedException {
		driver.get(constants.url);
		sum=0;
		cm.generateAndClickDynamicXpath(product1);
		cm.commonWaitToFindElement("xpath", cartLocators.productPrice);
		String text=productPrice.getText();
		cm.commonWaitToFindElement("xpath", cartLocators.addToCart);
		addToCart.click();
		cm.waitForAlert();
		Alert alert = driver.switchTo().alert();
        alert.accept(); 
		sum=sum+cm.removeNonDigitChar(text);
		int product1Price=cm.removeNonDigitChar(text);
		cm.commonWaitToFindElement("xpath", cartLocators.home);
		home.click();
		
		cm.generateAndClickDynamicXpath(product2);
		cm.commonWaitToFindElement("xpath", cartLocators.productPrice);
		text=productPrice.getText();
		cm.commonWaitToFindElement("xpath", cartLocators.addToCart);
		addToCart.click();
		cm.waitForAlert();
		alert = driver.switchTo().alert();
        alert.accept();
		sum=sum+cm.removeNonDigitChar(text);
		int product2Price=cm.removeNonDigitChar(text);
		cm.commonWaitToFindElement("xpath", cartLocators.addToCart);
		addToCart.click();
		cm.waitForAlert();
		alert = driver.switchTo().alert();
        alert.accept();
		Thread.sleep(1000);
		sum=sum+cm.removeNonDigitChar(text);
		cm.commonWaitToFindElement("xpath", cartLocators.cart);
		cart.click();
		Thread.sleep(3000);
	}
	public void validateCart(WebDriver driver) throws TimeoutException, InterruptedException {
		List<WebElement> listOfProducts=cm.returnFindElements("xpath",cartLocators.noOfProducts);
		Assert.assertEquals(listOfProducts.size(),3);
		cm.commonWaitToFindElement("xpath", cartLocators.total);
		Thread.sleep(3000);
		Assert.assertEquals(cm.removeNonDigitChar(total.getText()), sum);
		
	}
	public void deleteFromCart(WebDriver driver) throws TimeoutException, InterruptedException {
		List<WebElement> listOfProducts=cm.returnFindElements("xpath",cartLocators.noOfProducts);
		WebElement first=listOfProducts.get(0);
		List<WebElement> listOfProductsElements=cm.returnFindElements("tagName","td");
		int product2Price=cm.removeNonDigitChar(listOfProductsElements.get(2).getText());
		cartDelete.click();
		cm.commonWaitToFindElement("xpath", cartLocators.cartDelete);
		cm.commonWaitToFindElement("xpath", cartLocators.total);
		sum=sum-product2Price;
		Thread.sleep(5000);
		Assert.assertEquals(cm.removeNonDigitChar(total.getText()), sum);

	}
}
