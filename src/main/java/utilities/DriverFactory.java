package utilities;



//import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
	
	public static RemoteWebDriver driver;
	String nodeURL;
	public static ThreadLocal<RemoteWebDriver> tlDriver= new ThreadLocal<RemoteWebDriver>();
	
	public RemoteWebDriver int_driver(String Port)  throws MalformedURLException {
		
		if(Port.equalsIgnoreCase("4546"))
        {
            nodeURL = "http://127.0.0.1:4444/wd/hub";
            System.out.println("Chrome Browser Initiated");
            ChromeOptions options = new ChromeOptions();
            
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();            
            capabilities.setBrowserName("chrome");
            //capabilities.setPlatform(Platform.LINUX);
            options.merge(capabilities);
            
            driver = new RemoteWebDriver(new URL(nodeURL),options);
           tlDriver.set(driver);
            
        } 
		else
            if(Port.equalsIgnoreCase("5566"))
            {
                nodeURL = "http://127.0.0.1:4444/wd/hub";
                System.out.println("Firefox Browser Initiated");
                DesiredCapabilities capabilities1 = DesiredCapabilities.firefox();
                capabilities1.setBrowserName("firefox");
                driver = new RemoteWebDriver(new URL(nodeURL),capabilities1);
            	tlDriver.set(driver);
            }

	
		getDriver().manage().deleteAllCookies();
		//getDriver().manage().window().maximize();
		
		return getDriver();
	}
	
	
	public static synchronized RemoteWebDriver getDriver() {
		return tlDriver.get();	
	}

}
