package pageObjects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import utils.constants;
import utils.locators.homepageLocators;

public class homepage {

	@FindBy(xpath = homepageLocators.logoHeader)
	private WebElement logoHeader;
	
	@FindBy(xpath = homepageLocators.logoFooter)
	private WebElement logoFooter;

	@FindBy(xpath = homepageLocators.productCategory)
	private WebElement productCategory;

	@FindBy(xpath = homepageLocators.products)
	private WebElement products;

	@FindBy(xpath = homepageLocators.nextButton)
	private WebElement nextButton;

	@FindBy(xpath = homepageLocators.prevButton)
	private WebElement prevButton;

	@FindBy(xpath = homepageLocators.headerMenu)
	private WebElement headerMenu;

	public void homepageValidation(WebDriver driver) {
		driver.get(constants.url);
		Assert.assertEquals(constants.title,driver.getTitle());
		Assert.assertTrue(logoHeader.isDisplayed());
		Assert.assertTrue(logoFooter.isDisplayed());
		Assert.assertTrue(nextButton.isDisplayed());
		Assert.assertTrue(prevButton.isDisplayed());
		
		List<WebElement> headerMenuList= driver.findElements(By.xpath(homepageLocators.headerMenu));
		
		for(WebElement item:headerMenuList) {
			Arrays.asList(constants.headerMenu).stream().anyMatch(str -> str.contains(item.getText()));			
		}

		List<WebElement> productCategoryList= driver.findElements(By.xpath(homepageLocators.productCategory));
		
		for(WebElement item:productCategoryList) {
			Arrays.asList(constants.categoryOptions).stream().anyMatch(str -> str.contains(item.getText()));			
		}
		
		List<WebElement> productsList=driver.findElements(By.xpath(homepageLocators.products));
		Assert.assertEquals(productsList.size(), 9);
	}
	
}

