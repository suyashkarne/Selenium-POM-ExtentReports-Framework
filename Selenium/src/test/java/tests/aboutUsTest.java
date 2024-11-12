package tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import commonMethods.baseClass;
import pageObjects.aboutUs;
import pageObjects.homepage;

public class aboutUsTest extends baseClass{
	
	@BeforeMethod
	@Parameters({"browser"})
	public void launchBrowser(String browser,Method method) throws Exception
	{
		browserinit(browser);
		String methodName = method.getName();
        initializeExtentTest(methodName);
	}
	@Test
	public void aboutUsValidation() throws TimeoutException
	{
		aboutUs aboutUsObject=PageFactory.initElements(driver,aboutUs.class);
		aboutUsObject.aboutUsValidation(driver);
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
