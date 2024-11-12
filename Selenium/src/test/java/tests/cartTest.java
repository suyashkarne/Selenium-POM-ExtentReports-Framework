package tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;

import commonMethods.baseClass;
import pageObjects.aboutUs;
import pageObjects.cart;
import utils.constants;

public class cartTest extends baseClass {
	@BeforeClass
	@Parameters({"browser"})
	public void launchBrowser(String browser) throws Exception
	{
		browserinit(browser);
	}
	@BeforeMethod
	public void initExtentReport(Method method) {
		String methodName = method.getName();
        initializeExtentTest(methodName);
	}
	@Test(priority=1)
	public void addToCart() throws TimeoutException, InterruptedException
	{
		cart cartObject=PageFactory.initElements(driver,cart.class);
		cartObject.addProductToCart(driver,constants.product1,constants.product2);
	}
	@Test(priority=2)
	public void validateCart() throws TimeoutException, InterruptedException
	{
		cart cartObject=PageFactory.initElements(driver,cart.class);
		cartObject.validateCart(driver);
	}
	@Test(priority=3)
	public void deleteFromCart() throws TimeoutException, InterruptedException
	{
		cart cartObject=PageFactory.initElements(driver,cart.class);
		cartObject.deleteFromCart(driver);
	}
	@AfterMethod
	public void closeBrowser(ITestResult result) throws IOException
	{
		if (ITestResult.FAILURE == result.getStatus()) {
	
        try {
            String screenshotPath = captureScreenshot(result.getName());
            test.fail("Test failed. See the screenshot below", 
                      MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
	@AfterClass
	public void closeBrowser() throws IOException
	{
		aftertest();
	}
}
