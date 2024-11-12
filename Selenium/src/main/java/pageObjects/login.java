package pageObjects;

import java.util.concurrent.TimeoutException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commonMethods.commonMethods;
import utils.constants;
import utils.locators.loginLocators;
import utils.locators.loginLocators;

public class login {

	commonMethods cm = new commonMethods();
	
	@FindBy(xpath = loginLocators.login)
	private WebElement login;
	
	@FindBy(xpath = loginLocators.username)
	private WebElement username;

	@FindBy(xpath = loginLocators.password)
	private WebElement password;

	@FindBy(xpath = loginLocators.loginPopup)
	private WebElement loginPopup;
	
	@FindBy(xpath = loginLocators.logout)
	private WebElement logout;
	
	@FindBy(xpath = loginLocators.welcomeMsg)
	private WebElement welcomeMsg;
	
	public void loginApp(WebDriver driver) throws TimeoutException {
		driver.get(constants.url);
		cm.commonWaitToFindElement("xpath", loginLocators.login);
		login.click();
		cm.commonWaitToFindElement("xpath", loginLocators.username);
		username.sendKeys(constants.username);
		cm.commonWaitToFindElement("xpath", loginLocators.password);
		password.sendKeys(constants.password);
		cm.commonWaitToFindElement("xpath", loginLocators.loginPopup);
		loginPopup.click();
		cm.commonWaitToFindElement("xpath", loginLocators.welcomeMsg);
		String msg=welcomeMsg.getText();
		Assert.assertTrue(msg.contains("Welcome "+constants.username));
	}

	public void logoutApp(WebDriver driver) throws TimeoutException {
		cm.commonWaitToFindElement("xpath", loginLocators.logout);
		logout.click();
	}
}
