package commonMethods;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.constants;

public class commonMethods {
	
	WebDriver driver = baseClass.getdriver();
	private static final Logger log = Logger.getLogger(commonMethods.class);


	public void commonWaitToFindElement(String findElementBy, String expr)
			throws TimeoutException {
		WebElement element = null;
		// Element is Click able - it is Displayed and Enabled.
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(constants.explicitWait));

		if (findElementBy.equals("xpath")) {
			element = wait.until(ExpectedConditions.elementToBeClickable(org.openqa.selenium.By.xpath(expr)));
		} else if (findElementBy.equals("id")) {
			element = wait.until(ExpectedConditions.elementToBeClickable(org.openqa.selenium.By.id(expr)));
		} else if (findElementBy.equals("cssSelector")) {
			element = wait.until(ExpectedConditions.elementToBeClickable(org.openqa.selenium.By.cssSelector(expr)));
		} else if (findElementBy.equals("className")) {
			element = wait.until(ExpectedConditions.elementToBeClickable(org.openqa.selenium.By.className(expr)));
		} else if (findElementBy.equals("linkText")) {
			element = wait.until(ExpectedConditions.elementToBeClickable(org.openqa.selenium.By.linkText(expr)));
		}

	}
	
	public void generateAndClickDynamicXpath(String expr) {
		String xpath="//*[text()='"+expr+"']";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(constants.explicitWait));
		WebElement element=wait.until(ExpectedConditions.elementToBeClickable(org.openqa.selenium.By.xpath(xpath)));
		element.click();
	}
	
	public int removeNonDigitChar(String text) {
		return Integer.parseInt(text.replaceAll("\\D+",""));
	}
	
	public List<WebElement> returnFindElements(String locator, String expr){
		List<WebElement>li=new ArrayList<>();
		if(locator.equals("xpath"))
		{
		 li= driver.findElements(By.xpath(expr));
		return li;
		}
		else if(locator.equals("tagName"))
		{
		   li= driver.findElements(By.tagName(expr));
			return li;
		}
		return li;
	}
	public void waitForAlert() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(constants.explicitWait));
		wait.until(ExpectedConditions.alertIsPresent());
	}
}
