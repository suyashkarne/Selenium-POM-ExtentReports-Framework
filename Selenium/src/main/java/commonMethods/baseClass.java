package commonMethods;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class baseClass {

	public static WebDriver driver = null;
    protected static ExtentReports extent;
    protected static ExtentTest test;

	static Logger log = Logger.getLogger("baseClass.class");

    @BeforeSuite
    public void beforeSuite() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }
	
	public static void browserinit(String browser) throws Exception {

		if ( browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("start-maximized");
     		driver = new ChromeDriver(options);
		}

		if ( browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("start-maximized");
			driver = new EdgeDriver(options);

		}
		
	}

	public static void aftertest() throws IOException {
		driver.close();
		driver.quit(); 
	}

	public static WebDriver getdriver() {

		return driver;

	}
	
	public String captureScreenshot(String testName) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Create folder for screenshots if it doesn't exist
        File screenshotFolder = new File("target/screenshots");
        if (!screenshotFolder.exists()) {
            screenshotFolder.mkdirs();
        }

        File destinationFile = new File(screenshotFolder, testName + ".png");

        FileUtils.copyFile(screenshot, destinationFile);

        return destinationFile.getAbsolutePath();
    }
    public void initializeExtentTest(String methodName) {

        test = extent.createTest(methodName);
    }
    @AfterSuite
    public void afterSuite() {
        if (extent != null) {
            extent.flush();
        }
    }
	}


