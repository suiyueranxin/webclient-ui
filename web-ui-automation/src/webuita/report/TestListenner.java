package webuita.report;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.xml.XmlTest;

import com.relevantcodes.extentreports.LogStatus;
import com.testautomationguru.ocular.Ocular;

import webuita.general.General;
import webuita.general.Global;
import webuita.log.Logger;

public class TestListenner extends TestListenerAdapter {
	String outPath = "";
	File chartCompareResultPath = null;
	//get the out-put path
	@Override
	public void onStart(ITestContext testContext){
		outPath = testContext.getOutputDirectory();
		Global.suitName = testContext.getSuite().getName();
		XmlTest xml = testContext.getCurrentXmlTest();
		Map<String, String> parameters = xml.getAllParameters();
		Global.browserType = parameters.get("browserType");
		Global.driverPath = parameters.get("driverPath");
	
		Global.similarity = parameters.get("similarity");
		Global.waitSeconds = parameters.get("waitSeconds");
		
		String tmpStopRunAfterFail = parameters.get("stopRunAfterFail");
		Global.isStopRunAfterFail = (null != tmpStopRunAfterFail && tmpStopRunAfterFail.equalsIgnoreCase("yes")) ? true: false;
	}
	@Override
	public void onTestStart(ITestResult result){
				
		Object currentClass = result.getInstance();
		String testClassName = General.getTestClassName(result.getInstanceName().trim());
		String tsPath = General.convertTestClassNamt2Path(result.getInstanceName().trim());
		File file = new File(outPath+File.separator+tsPath);
		if(!file.exists()){
			file.mkdir();
		}
		Global.log = new Logger(outPath+File.separator+tsPath,testClassName );
		
		//chart compare configuration
		chartCompareResultPath = new File(outPath+File.separator+tsPath + File.separator + "result");
		if(!chartCompareResultPath.exists()){
			chartCompareResultPath.mkdir();
		}
		String snapPath = "testcases" + File.separator + tsPath + File.separator + "snap" + File.separator + Global.browserType;
		if(null == Global.similarity){ Global.similarity = "100";}
		Ocular.config().resultPath(chartCompareResultPath.toPath())
					   .snapshotPath(Paths.get(snapPath))
					   .globalSimilarity(Integer.parseInt(Global.similarity));
			
	}
	
	@Override
	public void onTestFailure(ITestResult result){
		System.err.println("**** Error "+ result.getName() +" test has failed ****");

		String tsPath = General.convertTestClassNamt2Path(result.getInstanceName().trim());
		String testMethodName = result.getName().toString().trim();
		String screenShotName = testMethodName + ".png";
		
		if(Global.bAttachedCompResult){
			String chartResultFile = chartCompareResultPath.toString() + File.separator + Global.chartResultFileName;
			if(Files.exists(Paths.get(chartResultFile))){
				String tmpResultPath = "result" + File.separator +Global.chartResultFileName;
				Global.log.add(LogStatus.FAIL, "Chart compare result as below :", tmpResultPath, true);
			}
		}
		
		
		String path = outPath + File.separator + tsPath;
		if(Global.driver != null){
			String imagePath = path + File.separator +"Screenshots"
					+File.separator + General.takeScreenShot(Global.driver, path , screenShotName);
			String tmpPath = "Screenshots" + File.separator + screenShotName;
			Global.log.add(LogStatus.FAIL, "Screenshot below :",tmpPath, true);
			
		}else{
			//input testng throw exception...
			Global.log.add(LogStatus.FAIL, result.getThrowable());
		}
		
	}
	@Override
	public void onFinish(ITestContext testContext) {
		
		Global.log.end();
	}
}
