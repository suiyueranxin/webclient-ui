package webuita.general;

import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

public class General {
	public static String getTestClassName(String testName){
		String[] reqTestClassname = testName.split("\\.");
		int i = reqTestClassname.length - 1;
		System.out.println("Required Test Name :"+reqTestClassname);
		return reqTestClassname[i];
	}
	
	public static String convertTestClassNamt2Path(String testName){
		String[] reqTestClassname = testName.split("\\.");
		String path = reqTestClassname[0];
		for(int i = 1; i < reqTestClassname.length - 1; i++){
			path += File.separator + reqTestClassname[i];
		}
		return path;
	}
	public static String takeScreenShot(WebDriver driver, String path, String screenShotName){
		
		
		try{
			File file = new File(path+File.separator+"Screenshots");
			if(!file.exists()){
				file.mkdir();
			}
			
			File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File targetFile = new File(path+File.separator+"Screenshots",screenShotName);
			copyFile(screenshotFile, targetFile);
			
			return screenShotName;
			
		}catch(Exception ex){
			
			System.out.println("An exception occured while taking screenshot "+ex.getCause());
			return null;
		}
	}
	
	
	public static boolean copyFile(File srcFile,File destFile){
		
		int byteread = 0;
		InputStream in = null;
		OutputStream out = null;
		
		try{
			in = new FileInputStream(srcFile);
			if(!destFile.exists()){
				destFile.createNewFile();
			}
			out = new FileOutputStream(destFile);
			byte[] buff = new byte[1024];
			
			while((byteread = in.read(buff)) != -1){
				out.write(buff,0, byteread);
			}
			return true;
		}catch(Exception ex){
			return false;
		}
		finally{
			
				try {
					if(out != null){
						out.flush();
					out.close();
					}
					
					if(in != null){
						in.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			
		}
		
		
	}
	
	public static void sleep(int seconds){
		
		try{
			Thread.sleep(seconds * 1000);
		}catch(Exception ex){
			
		}
	}
	public static void waitPageLoadComplete(){
		int i = 0;
		int tmpSize = 0;
		while(i < 150){
			String str = Global.driver.getPageSource();
			int size = str.length();
			if(tmpSize == size){
				break;
			}else{
				tmpSize = size;
			}
			
			i++;
			sleep(1);
			
		}
	}
	public static void waitBusyIndicatorInvisible(){
		String xpath = "//div[contains(@class,'sapMBusyIndicator')]";
		sleep(3);
		if(Global.conn.isExistElement("xpath", xpath)){
			WebDriverWait wait = new WebDriverWait(Global.driver,180);
	        if(Global.conn.isExistElement("xpath",xpath )){
	        	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
	        }
		}

	}
	
	public static void waitRefreshComplete(){
		waitBusyIndicatorInvisible();
		waitReadyStateComplete();
		waitPageLoadComplete();
	}
	public static void waitReadyStateComplete(){
		WebDriver driver = Global.driver;
		WebDriverWait wait = new WebDriverWait(driver,50);
		wait.until((new ExpectedCondition<Boolean>(){
    	   public Boolean apply(WebDriver d){
    		  return ((JavascriptExecutor) d)
    		   .executeScript("return document.readyState").equals("complete");}}));
    	sleep(6);
	}
	
	public static void pressKey(int keyCode){
		try{
			 Robot robot = new Robot();
			 robot.keyPress(keyCode);
		}catch(Exception ex){
			Global.log.add(LogStatus.FAIL, "Fail to press key: \"" + keyCode + "\"");
		}
		
	}
	
	/***
	 * Compare two string whether equal.
	 * @param actual		  actual value.
	 * @param expected		  expected value.
	 * @param expectedEqual   true/false.
	 * @param compareDetails  description.
	 */
	public static void compare(String actual, String expected, boolean expectedEqual, String compareDetails) {
	       boolean pass = expected.equals(actual) == expectedEqual;
	       LogStatus status = pass? LogStatus.PASS : LogStatus.FAIL;
	       String logMsg = String.format(compareDetails + " %s, actual: \"%s\", expected: \"%s\".", status.toString(), actual, expected);
	       Global.log.add(status, logMsg);
	       Assert.assertEquals(pass, true, logMsg);
	 }
	
	public static void getPerfsLog(){
		JavascriptExecutor js =((JavascriptExecutor) Global.driver); 
		 Object val = js.executeScript("" +
	                "try{window.performance = window.performance || window.mozPerformance || window.msPerformance || window.webkitPerformance || {};" +
	                "return(parseInt(window.performance.timing.domContentLoadedEventEnd)-parseInt(window.performance.timing.navigationStart));}catch(e){alert(e);}");
	        System.out.println(val.toString());
	}
}
