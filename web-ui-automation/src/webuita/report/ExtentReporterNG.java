package webuita.report;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import webuita.general.General;
import webuita.general.Global;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReporterNG implements IReporter {

	private ExtentReports extent;
	private String outPath = "";
	
	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
			String outputDirectory) {
		
		
		extent = new ExtentReports(outputDirectory + File.separator + "extent_report.html",true);
		
		for(ISuite suite : suites){
			
			Map<String, ISuiteResult> result = suite.getResults();
			
			for(ISuiteResult r : result.values()){
				ITestContext context = r.getTestContext();
				outPath = context.getOutputDirectory();
				buildTestTestNodes(context.getPassedTests(),LogStatus.PASS);
				buildTestTestNodes(context.getFailedTests(),LogStatus.FAIL);
				buildTestTestNodes(context.getSkippedTests(),LogStatus.SKIP);
				
				
			}
		}
		//add browser info to report.
		try{
			Capabilities cap = ((RemoteWebDriver) Global.driver).getCapabilities();
			String browserType = cap.getBrowserName();
			String browserVersion = cap.getVersion();
			extent.addSystemInfo("Browser", browserType);
			extent.addSystemInfo("Browser Version", browserVersion);
		}catch(Exception ex){}
		
		extent.flush();
		extent.close();
	
	}
	
	private void buildTestTestNodes(IResultMap tests, LogStatus status){
		ExtentTest test;
		
		if(tests.size() > 0){
			for(ITestResult result : tests.getAllResults()){
				test = extent.startTest(result.getMethod().getMethodName());
				
				test.setStartedTime(getTime(result.getStartMillis()));
				test.setEndedTime(getTime(result.getEndMillis()));
				
				for(String group : result.getMethod().getGroups()){
					test.assignCategory(group);
				}
				
				if(result.getThrowable() != null){
					test.log(status, result.getThrowable());
					String tsPath = General.convertTestClassNamt2Path(result.getInstanceName().trim());
					String testMethodName = result.getName().toString().trim();
					String screenShotName = testMethodName + ".png";
					
					String path = outPath+File.separator+tsPath+File.separator
							+"Screenshots"+File.separator+screenShotName;
					
					File screenShots = new File(path);
					if( screenShots.exists()){
						String tmpPath = Global.suitName +  File.separator + tsPath + File.separator + "Screenshots" + File.separator + screenShotName;
						test.log(LogStatus.FAIL ,"SnapShot below : "+test.addScreenCapture(tmpPath));
					}
					System.out.println("You can find report from: "+ outPath+File.separator+tsPath);
				}else{
					if(!Global.log.isPassed()){
						test.log(LogStatus.FAIL, "Test failed");
					}else{
						test.log(status, "Test "+status.toString().toLowerCase()+"ed");
					}
					
				}
				extent.endTest(test);
			}
		}
		
	}
	
	private Date getTime(long millis){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

}
