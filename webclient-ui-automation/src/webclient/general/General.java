package webclient.general;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;


import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import webuita.general.Global;
import webuita.locating.Column;
import webuita.locating.Locators;

public class General {

	
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
		String xpath = "//div[contains(@id,'DEFAULT-busyIndicator')]";
		sleep(2);
		WebDriver driver = Global.driver;
		WebDriverWait wait = new WebDriverWait(driver,50);
        if(Global.conn.isExistElement("xpath",xpath )){
        	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
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
	public static void pressTabKey(){
		Keyboard keyboard = ((HasInputDevices) Global.driver).getKeyboard();
		keyboard.pressKey(Keys.TAB);
	}
	
	public static void pressEnterKey(){
		Keyboard keyboard = ((HasInputDevices) Global.driver).getKeyboard();
		keyboard.pressKey(Keys.ENTER);
	}
	
	public static void setGridItemValue(String gridName, String colName, int rowIndex, 
			Locators locators, String value){
		Column colItem = locators.getColumnItem(gridName, colName);
		String byType = colItem.getByType();
		String byValue = colItem.getByValue();
		String xpath = "(" + byValue + "//input)[" + Integer.toString(rowIndex + 1) + "]";
		
		Global.conn.setElementValue(byType, xpath, value, 
				"Set grid: \"" + gridName + "\", column: \"" + colName + "\", row: \"" + rowIndex+ 
				"\" to \"" + value + "\"");
		Keyboard keyboard = ((HasInputDevices) Global.driver).getKeyboard();
		keyboard.pressKey(Keys.TAB);
		General.waitBusyIndicatorInvisible();
	}
	
	public static void compareGridItemValue(String gridName, String colName, int rowIndex,
			Locators locators, String expectedValue){
		
		if(expectedValue == "" || expectedValue.contains("\n")) return;
		
		Column colItem = locators.getColumnItem(gridName, colName);
		String type = colItem.getType();
		
		StringBuilder className = new StringBuilder("tc.widgets.");
		className.append(type);
		Class<?> widgetClass;
		try {
			widgetClass = Class.forName(className.toString());
			Object obj = widgetClass.getDeclaredConstructor(String.class, String.class, Locators.class)
					.newInstance(gridName, colName, locators);
			
			Method compare = widgetClass.getSuperclass().getDeclaredMethod("compare", new Class[]{Integer.TYPE, String.class});
			compare.invoke(obj, rowIndex, expectedValue);
			
			
		} catch (Exception e) {
			System.err.println("Failed to compare Grid\""+ gridName + "\", Column: \"" +
					colName + "\", Row: \"" + rowIndex + "\". with : \"" + e.toString() + "\"");
			
		}
		
		
		
	}
	
	public static void setGridItemValueFromCFL(String gridName, String colName, int rowIndex, 
			Locators locators, String value){
		Column colItem = locators.getColumnItem(gridName, colName);
		String byType = colItem.getByType();
		String byValue = colItem.getByValue();
		String xpath = "(" + byValue + "//input)[" + Integer.toString(rowIndex) + "]";
		
//		StringInput colItem = new StringInput(colName, byType, byValue);
//		colItem.chooseFromList(dlgName, colName, byValue);
	}
	public static boolean selectValueFromTable(String tableName, String colName, Locators locators, String value){
//		conn = Global.conn;
//		log = Global.log;
		boolean bSuccessed = true;
		try{
			Column colItem = locators.getColumnItem(tableName, colName);
			String byType = colItem.getByType();
			String byValue = colItem.getByValue();
			String xpath = "//tbody//tr" + byValue + "//span";
			
			
			//tbody//tr//div[contains(@id, 'COLUMN_CardCode_PROPERTY')]//span
			String tempValue = "";
			
			boolean bSelect = false;
			boolean bShowAll = false;
			int iPressTimes = 0;
			List<WebElement> eles = new ArrayList<WebElement>();
			while(true){
				eles = Global.conn.findElements("xpath", xpath); 
				if(eles.size() > 0){
					for(WebElement ele : eles){
						
						if(value.equals(ele.getText())) {
							ele.click();
							bSelect = true;
							break;
						}
						
					}
					if(!bSelect){
						WebElement e = Global.conn.findElement("xpath", "//div[@class='sapUiTableRowActionHeader']");
						Actions builder = new Actions(Global.driver);             
						builder.moveToElement(e).moveToElement(e).click().build().perform();
						Thread.sleep(500);
						Keyboard keyboard = ((HasInputDevices) Global.driver).getKeyboard();
						keyboard.pressKey(Keys.PAGE_DOWN);
						iPressTimes ++;
						Thread.sleep(500);
					}
					if(iPressTimes > 3 && !bSelect){
						eles = Global.conn.findElements("xpath", xpath); 
						if(!tempValue.equals(eles.get(0).getText())){
							tempValue = eles.get(0).getText();
						}else{
							bShowAll = true;
						}
					}
					
					
				}
				else{
					bSuccessed = false;
					break;
				}
				if(bShowAll && !bSelect){
					bSuccessed = false;
					break;
				}
				if(bSelect){
					break;
				}
			}
			
		}catch(Exception ex){
			
			Global.log.add(LogStatus.FAIL, "Failed to select value from table with: " + ex.toString());
		}
		return bSuccessed;
        // drag downwards
//      int numberOfPixelsToDragTheScrollbarDown = 1;
//      int tempSize = 0;
//      for(int i = 0; i < 1000; i ++)
//      {
//      	List<WebElement> els = findElements("xpath", "//tbody//tr");
//      	if(els.size()<= tempSize){break;}
          //dragger.moveToElement(e).clickAndHold().moveByOffset(0, numberOfPixelsToDragTheScrollbarDown).release(e).build().perform();
//         Thread.sleep(1500);
      	
      	
//      }
			
	}
		/**
		 * 
		 * @param tableName
		 * @param colName
		 * @param locators
		 * @param items : Split by ",", etc: "C20000,l10001,.."
		 * @return
		 */
//	public static boolean selectFilterItemsFromTable(String tableName, String colName, Locators locators, String items){
////		conn = Global.conn;
////		log = Global.log;
//		boolean bSuccessed = true;
//		try{
//			Column colItem = locators.getColumnItem(tableName, colName);
//			String byType = colItem.getByType();
//			String byValue = colItem.getByValue();
//			String xpath = "//tbody//tr" + byValue + "//span";
//			
//			//(//tr[contains(@class,'sapUiTableColHdrTr')])[1]//bdi
//			//tbody//tr//div[contains(@id, 'COLUMN_CardCode_PROPERTY')]//span
//			String tempValue = "";
//			
//			boolean bSelect = false;
//			boolean bShowAll = false;
//			int iPressTimes = 0;
//			List<WebElement> eles = new ArrayList<WebElement>();
//			while(true){
//				eles = Global.conn.findElements("xpath", xpath); 
//				if(eles.size() > 0){
//					for(WebElement ele : eles){
//						
//						if(value.equals(ele.getText())) {
//							ele.click();
//							bSelect = true;
//							break;
//						}
//						
//					}
//					if(!bSelect){
//						WebElement e = Global.conn.findElement("xpath", "//div[@class='sapUiTableRowActionHeader']");
//						Actions builder = new Actions(Global.driver);             
//						builder.moveToElement(e).moveToElement(e).click().build().perform();
//						Thread.sleep(500);
//						Keyboard keyboard = ((HasInputDevices) Global.driver).getKeyboard();
//						keyboard.pressKey(Keys.PAGE_DOWN);
//						iPressTimes ++;
//						Thread.sleep(500);
//					}
//					if(iPressTimes > 3 && !bSelect){
//						eles = Global.conn.findElements("xpath", xpath); 
//						if(!tempValue.equals(eles.get(0).getText())){
//							tempValue = eles.get(0).getText();
//						}else{
//							bShowAll = true;
//						}
//					}
//					
//					
//				}
//				else{
//					bSuccessed = false;
//					break;
//				}
//				if(bShowAll && !bSelect){
//					bSuccessed = false;
//					break;
//				}
//				if(bSelect){
//					break;
//				}
//			}
//			
//		}catch(Exception ex){
//			
//			Global.log.add(LogStatus.FAIL, "Failed to select value from table with: " + ex.toString());
//		}
//		return bSuccessed;
//        // drag downwards
////      int numberOfPixelsToDragTheScrollbarDown = 1;
////      int tempSize = 0;
////      for(int i = 0; i < 1000; i ++)
////      {
////      	List<WebElement> els = findElements("xpath", "//tbody//tr");
////      	if(els.size()<= tempSize){break;}
//          //dragger.moveToElement(e).clickAndHold().moveByOffset(0, numberOfPixelsToDragTheScrollbarDown).release(e).build().perform();
////         Thread.sleep(1500);
//      	
//      	
////      }
//			
//	}
	
	   public static void compare(String actual, String expected, boolean expectedEqual, String compareDetails) {
	       boolean pass = expected.equals(actual) == expectedEqual;
	       LogStatus status = pass? LogStatus.PASS : LogStatus.FAIL;
	       String logMsg = String.format(compareDetails + " %s, actual: \"%s\", expected: \"%s\".", status.toString(), actual, expected);
	       Global.log.add(status, logMsg);
	       Assert.assertEquals(pass, true, logMsg);
	   }

}
