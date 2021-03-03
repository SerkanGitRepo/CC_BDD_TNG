package com.qa.factory;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public 	WebDriver driver;
	String nodeURL;
	public static ThreadLocal<WebDriver> tlDriver= new ThreadLocal<>();
	
	public WebDriver int_driver(String Port)  throws MalformedURLException {
		
		//if(Port.equalsIgnoreCase("4546"))
        //{
//            nodeURL = "http://127.0.0.1:4444/wd/hub";
//            System.out.println("Chrome Browser Initiated");
//            ChromeOptions options = new ChromeOptions();
//            
//            DesiredCapabilities capabilities = DesiredCapabilities.chrome();            
//            capabilities.setBrowserName("chrome");
//            //capabilities.setPlatform(Platform.LINUX);
//            options.merge(capabilities);
//            
//            driver = new RemoteWebDriver(new URL(nodeURL),options);
//            System.setProperty("webdriver.chrome.driver","src/test/resources/webdriver/windows/chromedriver.exe");
//            tlDriver.set(new ChromeDriver());
            
        //} 
//		else
//            if(Port.equalsIgnoreCase("5566"))
//            {
                nodeURL = "http://127.0.0.1:4444/wd/hub";
                System.out.println("Firefox Browser Initiated");
                DesiredCapabilities capabilities1 = DesiredCapabilities.firefox();
                capabilities1.setBrowserName("firefox");
//                capabilities1.setVersion("85");
//                capabilities1.setPlatform(Platform.LINUX);
                //driver = new RemoteWebDriver(new URL(nodeURL),capabilities1);   
                //System.setProperty("webdriver.gecko.driver","src/test/resources/webdriver/windows/geckodriver.exe");
            	tlDriver.set(new RemoteWebDriver(new URL(nodeURL),capabilities1));
//            }
//            else
//                
//                if(Port.equalsIgnoreCase("4547"))
//                {
//                    nodeURL = "http://10.0.0.22:4444/wd/hub";
//                    System.out.println("Internet Browser Initiated");
//                    DesiredCapabilities capabilities2 = DesiredCapabilities.internetExplorer();
//                    capabilities2.setBrowserName("internet explorer");
//                    capabilities2.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
//                    capabilities2.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
//                    capabilities2.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//                    capabilities2.setCapability("ignoreProtectedModeSettings", true);
//                    capabilities2.setCapability("nativeEvents", false);
//                    capabilities2.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "");
//                    capabilities2.setCapability(InternetExplorerDriver.LOG_LEVEL, "DEBUG");
//                     
//                    
//                    capabilities2.setPlatform(Platform.WINDOWS);
//                    
//                    driver = new RemoteWebDriver(new URL(nodeURL),capabilities2);
//                    
//                    driver.get("https://www.apple.com/");
//                    driver.manage().window().maximize();    
//                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//                    
//                    
//                }
		
		
//		if(browser.equals("chrome")) {
//			WebDriverManager.chromedriver().setup();
//			tlDriver.set(new ChromeDriver());
//		} else if(browser.equals("firefox")) {
//			WebDriverManager.firefoxdriver().setup();
//			tlDriver.set(new FirefoxDriver());
//		} else if(browser.equals("safari")) {
//			tlDriver.set(new SafariDriver());
//		} else {
//			System.out.println("L�tfen do�ru taray�c� de�eri girin " + browser);
//		}
		
		getDriver().manage().deleteAllCookies();
		//getDriver().manage().window().maximize();
		
		return getDriver();
	}
	
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
		
	}

}
