package webuita.testcase;


import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import webuita.general.General;
import webuita.general.Global;
import webuita.log.Logger;

import com.relevantcodes.extentreports.LogStatus;

public class BaseTest {
	
	
	@AfterMethod
	public void tearDownDriver(){
		
		try{
			if(Global.driver != null){
				Global.driver.quit();
			}
			Global.log.end();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@AfterTest
	public void killWebDriverProcess(){
		try {

			Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
			Runtime.getRuntime().exec("taskkill /F /IM ChromeDriver.exe");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
