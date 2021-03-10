package parallel;

import java.net.MalformedURLException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;



import io.cucumber.java.After;
import io.cucumber.java.Before;
import utilities.ConfigReader;
import utilities.DriverFactory;


public class ApplicationHooks {

	
	public DriverFactory driverFactory;
	private WebDriver driver;
	private ConfigReader configReader;
	Properties prop;
	
	@Before(order = 0)
	public void getProperty() {
		configReader = new ConfigReader();
		prop=configReader.init_prop();
	}

	@Before(order = 1) //order = 1
	public void launchBrowser() throws MalformedURLException {
		String port=prop.getProperty("port");
		driverFactory = new DriverFactory();	
		driver = driverFactory.int_driver(port);
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
