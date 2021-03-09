package parallel;

import java.net.MalformedURLException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import utilities.DriverFactory;

//import utilities.ConfigReader;


public class ApplicationHooks {

	
	public DriverFactory driverFactory;
	private WebDriver driver;
//	private ConfigReader configReader;
//	Properties prop;
	
//	@Before(order = 0)
//	public void getProperty() {
//		configReader = new ConfigReader();
//		prop=configReader.init_prop();
//	}

	@Before(order = 0) //order = 1
	public void launchBrowser() throws MalformedURLException {
		//String port=prop.getProperty("port");
		driverFactory = new DriverFactory();	
		driver = driverFactory.int_driver("4546");
	}
	
//	@Before(order = 1)
//	public void getProperty(Scenario scenario) {
//		scenarioDef = baseUtil.features.createNode(scenario.getName());
//	}
	
	@After (order = 0)
	public void quitBrowser() {
		driver.quit();
	}
	
//	@After (order = 1)
//	public void tearDown(Scenario scenario) {
////		if(scenario.isFailed()) {
////			
////			String screenshotName=scenario.getName().replaceAll(" ", "_");
////			byte[] sourcePath =((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
////			
////			scenario.attach(sourcePath, "image/png", screenshotName);
////		}
//	}
}
