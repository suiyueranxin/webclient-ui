package webuita.general;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

public class Browser {
	
	public static WebDriver driver;
	public static enum features{WebClientTool};
	
	public static WebDriver getBrowser(String browserType, String driverPath){
		DesiredCapabilities dc = null;
		switch(browserType){
		
		case "firefox":
			dc = DesiredCapabilities.firefox();
		    dc.setCapability("marionette", true);
		    dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		   	System.setProperty("webdriver.gecko.driver", driverPath+File.separator+"geckodriver.exe");
			return driver = new FirefoxDriver(dc);
		case "chrome":
			System.setProperty("webdriver.chrome.driver",driverPath+File.separator+"chromedriver.exe");
			return driver = new ChromeDriver();
		case "IE":
			System.setProperty("webdriver.ie.driver", driverPath+File.separator+"IEDriverServer.exe");
			return	driver = new InternetExplorerDriver();
		case "edge":
			System.setProperty("webdriver.chrome.driver", driverPath+File.separator+"msedgedriver.exe");
			return	driver = new ChromeDriver();
		default:
			System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
			return driver = new FirefoxDriver();
		}
	}
		
		public static WebDriver getBrowser(String browserType, String driverPath, features feature){
			DesiredCapabilities dc = null;
			switch(browserType){
			
			case "firefox":
				dc = DesiredCapabilities.firefox();
			    dc.setCapability("marionette", true);
			    dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			   	System.setProperty("webdriver.gecko.driver", driverPath+File.separator+"geckodriver.exe");
				return driver = new FirefoxDriver(dc);
			case "chrome":
				if(feature.toString() == features.WebClientTool.toString()){
					ChromeOptions options = new ChromeOptions();
					String webClientToolFile = getWebClientToolFile();
					if(webClientToolFile == null)
					{
						Global.log.add(LogStatus.FAIL, "WebClientTool crx file is not found!");
						return null;
					}
					options.addExtensions(new File(webClientToolFile));
					options.addArguments("auto-open-devtools-for-tabs");
					System.setProperty("webdriver.chrome.driver",driverPath+File.separator+"chromedriver.exe");
					return driver= new ChromeDriver(options);
				}else{
				System.setProperty("webdriver.chrome.driver",driverPath+File.separator+"chromedriver.exe");
				return driver = new ChromeDriver();
				}
			case "IE":
				System.setProperty("webdriver.ie.driver", driverPath+File.separator+"IEDriverServer.exe");
				return	driver = new InternetExplorerDriver();
			case "edge":
				System.setProperty("webdriver.edge.driver", driverPath+File.separator+"MicrosoftWebDriver.exe");
				return	driver = new EdgeDriver();
			default:
				System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
				return driver = new FirefoxDriver();
			}
	}
		
		public static String getWebClientToolFile(){
			String filePath = null;
			String fileName;
			try{
				File file = new File(Global.webClientToolPath);
				if(!file.exists() || !file.isDirectory())
				{
					Global.log.add(LogStatus.FAIL, "WebClientTool file path is not found!");
					Assert.assertTrue(false, "WebClientTool file path is not found!");
				}
				File[] files = file.listFiles();
				for(int i=files.length-1; i>=0; i--){
					fileName = files[i].getName();
					if(fileName.endsWith(".crx")){
						filePath = files[i].getPath();
						return filePath;
					}
				}
				
			}catch(Exception ex){
				Global.log.add(LogStatus.FAIL, "Failed to get WebClientTool with: \"" + ex.toString() + "\"");
				Assert.assertTrue(false, "Failed to get WebClientTool with: \"" + ex.toString() + "\"");
			}
			
			return filePath;
		}
}
