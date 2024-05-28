package webuita.log;

import java.io.File;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import webuita.general.Global;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Logger {
	ExtentReports extent = null;
	ExtentTest test = null;
	
	public Logger(String path, String fileName){
		extent  = new ExtentReports(path + File.separator+ fileName+ "_report.htm",true);
		test = extent.startTest(fileName);
	}
	
	public boolean isPassed(){
		LogStatus status = test.getRunStatus();
		return status.equals(LogStatus.PASS);
	}
	public void add(LogStatus status, String stepDetail, boolean isStopRunAfterFail){
		test.log(status, stepDetail);
		if(isStopRunAfterFail){
			Assert.assertTrue(false, stepDetail);
		}
	}
	public void add(LogStatus status, String stepDetail){
		test.log(status, stepDetail);
		
	}
	public void add(LogStatus status, Throwable t){
		test.log(status, t);
	}
	
	public void add(LogStatus status, String stepName, String stepDetail){
		test.log(status, stepName, stepDetail);
	}
	
	
	public void add(LogStatus status, String stepName, Throwable t){
		test.log(status, stepName, t);
	}
	
	public void add(LogStatus status, String message,String snapshotPath, boolean bSnap){
		
		test.log(status, message + test.addScreenCapture(snapshotPath));
	}
	
	public void end(){
		//add browser info to report.
		try{
			Capabilities cap = ((RemoteWebDriver) Global.driver).getCapabilities();
			String browserType = cap.getBrowserName();
			String browserVersion = cap.getVersion();
			extent.addSystemInfo("Browser", browserType);
			extent.addSystemInfo("Browser Version", browserVersion);
		}catch(Exception ex){}
		extent.endTest(test);
		extent.flush();
	}
}
