package widgets.ui5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;




import webuita.general.Global;

import com.relevantcodes.extentreports.LogStatus;

public class Common {
public static void sleep(int seconds){
		
		try{
			Thread.sleep(seconds * 1000);
		}catch(Exception ex){
			
		}
	}
	


	
	public static void checkToastMessage(){
		try{
			sleep(1);
			String msg = Global.conn.getElementValue("xpath", "//div[contains(@class,'sapMMessageToast')]", "");
			if(msg.equals("")){
				Global.log.add(LogStatus.FAIL, "Operation failed.");
				Assert.assertEquals(true, false, "Operation failed.");
			}else{
				Global.log.add(LogStatus.PASS, msg);
			}
		}catch(Exception ex){
			Global.log.add(LogStatus.FAIL, "Operation faile.");
			Assert.assertEquals(true, false, "Operation failed.");
		}
		
	}
//	public static void pressTabKey(){
//		Keyboard keyboard = ((HasInputDevices) Global.driver).getKeyboard();
//		keyboard.pressKey(Keys.TAB);
//	}
//	
//	public static void pressEnterKey(){
//		Keyboard keyboard = ((HasInputDevices) Global.driver).getKeyboard();
//		keyboard.pressKey(Keys.ENTER);
//	}
	//the row index start from 1

	
	

}
