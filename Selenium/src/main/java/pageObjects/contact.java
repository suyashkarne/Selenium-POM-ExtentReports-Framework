package pageObjects;

import java.util.concurrent.TimeoutException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commonMethods.commonMethods;
import utils.constants;
import utils.locators.contactLocators;

public class contact {
	
	commonMethods cm = new commonMethods();

	@FindBy(xpath = contactLocators.contact)
	private WebElement contact;
	
	@FindBy(xpath = contactLocators.email)
	private WebElement email;

	@FindBy(xpath = contactLocators.name)
	private WebElement name;

	@FindBy(xpath = contactLocators.message)
	private WebElement message;
	
	@FindBy(xpath=contactLocators.sendMessage)
	private WebElement sendMessage;
	
	public void sendMessage(WebDriver driver,String emailId,String nm,String msg) throws TimeoutException {
		driver.get(constants.url);
		cm.commonWaitToFindElement("xpath", contactLocators.contact);
		contact.click();
		
		cm.commonWaitToFindElement("xpath", contactLocators.email);
		email.sendKeys(emailId);
		name.sendKeys(nm);
		message.sendKeys(msg);
		
		cm.commonWaitToFindElement("xpath", contactLocators.sendMessage);
		sendMessage.click();
		
		cm.waitForAlert();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(),constants.messageSentAlert);
        alert.accept();
		
	}
}
