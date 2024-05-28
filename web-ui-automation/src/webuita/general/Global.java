package webuita.general;

import org.openqa.selenium.WebDriver;

import webuita.log.Logger;

public class Global {
	public static WebDriver driver;
	public static Logger log;
	public static String classPath;
	public static String suitName;
	
	//browser setting
	public static String homePage;
	public static String hostUrlAppium;
	public static String deviceName;
	public static String osVersion;
	public static String devicePlatform;
	public static String browserType;
	public static String driverPath;
	
	public static boolean isStopRunAfterFail =false;
	
	//chart compare
	public static String similarity;
	public static String chartResultFileName;
	public static boolean bAttachedCompResult = false;
	
	public static String waitSeconds;
	
	public static WebConnection conn;
	
	public static String webClientToolPath;

}
