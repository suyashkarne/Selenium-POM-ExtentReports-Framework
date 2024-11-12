package tests;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;

import commonMethods.baseClass;
import pageObjects.homepage;

public class homepageTest extends baseClass {

	@BeforeMethod
	@Parameters({"browser"})
	public void launchBrowser(String browser,Method method) throws Exception
	{
		browserinit(browser);
		String methodName = method.getName();
        initializeExtentTest(methodName);
	}
	@Test
	public void homepageValidation()
	{
		homepage homepageObject=PageFactory.initElements(driver,homepage.class);
		homepageObject.homepageValidation(driver);
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
		aftertest();
}
}
