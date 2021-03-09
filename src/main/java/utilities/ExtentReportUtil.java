package utilities;

import java.io.File;
import java.io.IOException;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;


import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportUtil {
	
	static ExtentReports extent;

//	public static ExtentReports extentReportGenerator() 
//	{
//		String path = System.getProperty("user.dir")+"\\reports\\index.html";
//		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
//		reporter.config().setReportName("Web Automation Report");
//		reporter.config().setDocumentTitle("Test Results");
//		
//		extent = new ExtentReports();
//		extent.attachReporter(reporter);
//		extent.setSystemInfo("Tester", "Serkan Aksut");
//		return extent;
//	}
	
	public static String ExtentReportScreenshot(WebDriver driver,String TestCaseName) throws IOException{
//		File src = ((TakesScreenshot)Driver).getScreenshotAs(OutputType.FILE);
		//driver = new Augmenter().augment(driver);
		driver =  new Augmenter().augment(driver);
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				
		String destPath = System.getProperty("user.dir")+"\\reports\\"+ TestCaseName + ".png";
		File file = new File(destPath);
		FileUtils.copyFile(source, file);
		return destPath;
	}
	
	public static void FlushReport() {
		extent.flush();
	}

}
