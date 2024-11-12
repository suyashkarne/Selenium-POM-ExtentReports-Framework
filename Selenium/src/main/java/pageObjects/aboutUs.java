package pageObjects;

import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commonMethods.commonMethods;
import utils.constants;
import utils.locators.aboutUsLocators;

public class aboutUs {
	
	commonMethods cm = new commonMethods();
	static Logger log = Logger.getLogger("aboutUs.class");
	
	@FindBy(xpath = aboutUsLocators.aboutUs)
	private WebElement aboutUs;
	
	@FindBy(xpath = aboutUsLocators.aboutUsPopup)
	private WebElement aboutUsPopup;

	@FindBy(xpath = aboutUsLocators.aboutUsClose)
	private WebElement aboutUsClose;

	@FindBy(xpath = aboutUsLocators.aboutUsVideo)
	private WebElement aboutUsVideo;
	
	public void aboutUsValidation(WebDriver driver) throws TimeoutException {
		driver.get(constants.url);
		cm.commonWaitToFindElement("xpath", aboutUsLocators.aboutUs);
		aboutUs.click();
		cm.commonWaitToFindElement("xpath", aboutUsLocators.aboutUsPopup);
		Assert.assertTrue(aboutUsPopup.isDisplayed());
		Assert.assertTrue(aboutUsVideo.isDisplayed());
		cm.commonWaitToFindElement("xpath", aboutUsLocators.aboutUsClose);
		aboutUsClose.click();
		
	}

}
