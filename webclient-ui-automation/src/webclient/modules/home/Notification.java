package webclient.modules.home;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import webclient.general.General;
import webuita.general.Global;
import webuita.general.WebConnection;
import webuita.locating.Item;
import webuita.locating.Locators;
import webuita.log.Logger;
import widgets.ui5.Button;

public class Notification {
	private WebConnection conn;
	private Logger log;
	private Locators locators;
	
	public static boolean bOpenNotification = false;
	
	private final static String expandBtnXpath = "//li//div[contains(@class,'sapMNLB-SubHeader')]//button";
	private final static String itemHeaderXpath = "/li//span[contains(@id,'title-inner')]";
	private final static String itemBodyXpath = "/li//span[contains(@id,'body-inner')]";
	private final static String itemCloseBtnXpath = "/li//button[contains(@id, 'closeButton')]";
	
	private final static String subItemHeaderXpath = "/li//li//span[contains(@id,'title-inner')]";
	private final static String subItemBodyXpath = "/li//li//span[contains(@id,'body-inner')]";
	private final static String subItemCloseBtnXpath = "/li//li//button[contains(@id, 'closeButton')]";
	
	public Notification(){
		conn = Global.conn;
		log = Global.log;
		locators = new Locators("Notification_mapping.xml", log);
		
		bOpenNotification = false;
	}
	
	public void openNotification(){
		//open notifications
		Button btn = new Button("Notifications", locators);
		btn.click();
		General.waitPageLoadComplete();
	}
	private void selectAll(){
		Button btn = new Button("All", locators);
		btn.click();
	}
	
	private void selectByDate(){
		Button btn = new Button("By Date", locators);
		btn.click();
	
	}
	
	private void selectByPriority(){
		Button btn = new Button("By Priority", locators);
		btn.click();
	}
	
	private String getContainerXpathByType(NotificationBy by){
		Item item = locators.getItem("Notifications");
		String xpath = "";
		switch(by){
		case All:
			item = locators.getItem("Notification List - All");
			xpath = item.getByValue();
			selectAll();
			break;
		case Date:
			item = locators.getItem("Notification List - ByDate");
			xpath = item.getByValue();
			selectByDate();
			break;
		case Priority:
			item = locators.getItem("Notification List - ByPriority");
			xpath = item.getByValue();
			selectByPriority();
			break;
		}
		return xpath;
	}
	
	public void compareNewNotificationCount(int expectedCount){
		try{
			int actualCount = 0;
			Item item = locators.getItem("Notifications");
			String strCount = conn.getElementAttribute(item.getByType(), item.getByValue(), "data-counter-content");
			if(null != strCount){
				actualCount = Integer.parseInt(strCount);
			}
			if(expectedCount == actualCount){
				log.add(LogStatus.PASS, "Compare new notifications' count passed, actual: \"" +
						actualCount + "\", expected: \"" +expectedCount + "\".");
			}else{
				log.add(LogStatus.FAIL, "Failed to compare new notifications' count, actual: \"" +
						actualCount + "\", expected: \"" +expectedCount + "\".");
				Assert.assertEquals(true, false, "Failed to compare new notifications' count, actual: \"" +
						actualCount + "\", expected: \"" +expectedCount + "\".");
			}	
		}catch(Exception ex){
			log.add(LogStatus.FAIL, "Failed to compare new notifications' count with: " + ex.toString());
			Assert.assertEquals(true, false, "Failed to compare new notifications' count with: " + ex.toString());
		}
	
	}
	
	public void checkItemHeaderCotains(NotificationBy by, int index, String expectedValue){
		try{
			if(!bOpenNotification){
				openNotification();
				bOpenNotification = true;
			}
			
			String actualValue = "";
			List<WebElement> els = conn.findElements("xpath",  getContainerXpathByType(by) + itemHeaderXpath);
			actualValue = els.get(index).getText();
			if(actualValue.contains(expectedValue)){
				log.add(LogStatus.PASS, "Notification item header: \"" + by.toString() +"\", item index: \"" + index + "\", actual value: \"" + actualValue 
						+ "\", contains  expected value: \"" + expectedValue + "\".");
			}else{
				log.add(LogStatus.FAIL, "Notification item header: \"" + by.toString() +"\", item index: \"" + index + "\", actual value: \"" + actualValue 
						+ "\", not contains  expected value: \"" + expectedValue + "\".");
				Assert.assertEquals(true, false,"Notification item header: \"" + by.toString() +"\", item index: \"" + index + "\", actual value: \"" + actualValue 
						+ "\", not contains  expected value: \"" + expectedValue + "\".");
			}
		}catch(Exception ex){
			log.add(LogStatus.FAIL, "Failed to compare notification item header: \"" + by.toString() +"\", item index: \"" + index + "\" with: " + ex.toString());
			Assert.assertEquals(true, false,"Failed to compare notification item header: \"" + by.toString() +"\", item index: \"" + index + "\" with: " + ex.toString());
		}
	}
	
	public void checkItemBodyContains(NotificationBy by, int index, String expectedValue){
		try{
			if(!bOpenNotification){
				openNotification();
				bOpenNotification = true;
			}
			String actualValue = "";
			List<WebElement> els = conn.findElements("xpath",  getContainerXpathByType(by) + itemBodyXpath);
			actualValue = els.get(index).getText();
			if(actualValue.contains(expectedValue)){
				log.add(LogStatus.PASS, "Notification item body: \"" + by.toString() +"\", item index: \"" + index + "\", actual value: \"" + actualValue 
						+ "\", contains  expected value: \"" + expectedValue + "\".");
			}else{
				log.add(LogStatus.FAIL, "Notification item body: \"" + by.toString() +"\", item index: \"" + index + "\", actual value: \"" + actualValue 
						+ "\", not contains  expected value: \"" + expectedValue + "\".");
				Assert.assertEquals(true, false,"Notification item body: \"" + by.toString() +"\", item index: \"" + index + "\", actual value: \"" + actualValue 
						+ "\", not contains  expected value: \"" + expectedValue + "\".");
			}
		}catch(Exception ex){
			log.add(LogStatus.FAIL, "Failed to compare notification item body : \"" + by.toString() +"\", item index: \"" + index + "\"with: " + ex.toString());
			Assert.assertEquals(true, false,"Failed to compare notification item body : \"" + by.toString() +"\", item index: \"" + index + "\"with: " + ex.toString());
		}
	}
	
	public void closeItem(NotificationBy by, int index){
		try{
			if(!bOpenNotification){
				openNotification();
				bOpenNotification = true;
			}
			log.add(LogStatus.INFO, "Close notification: \"" + by.toString() +"\", item index: \"" + index + "\".");
			
			List<WebElement> els = conn.findElements("xpath",  getContainerXpathByType(by) + itemCloseBtnXpath);
			els.get(index).click();
			General.waitPageLoadComplete();
		}catch(Exception ex){
			log.add(LogStatus.FAIL, "Failed to close notification: \"" + by.toString() +"\", item index: \"" + index 
					+ "\" with: " + ex.toString());
			Assert.assertEquals(true, false,"Failed to close notification: \"" + by.toString() +"\", item index: \"" 
					+ index + "\" with: " + ex.toString());
		}
		
	}
	
	//compare sub-Items
	
	//ul[@id='sapUshellNotificationsListType-listUl']
	public void checkSubItemHeaderContains(NotificationBy by, int gourpIndex, int subItemIndex, String expectedValue){
		try{
			if(!bOpenNotification){
				openNotification();
				bOpenNotification = true;
			}
			
			String actualValue = "";
			List<WebElement> els = conn.findElements("xpath", getContainerXpathByType(by)  + "/li");
			String attrGroupItem  = els.get(gourpIndex).getAttribute("class");
			if(attrGroupItem.contains("-Collapsed")){
				els.get(gourpIndex).findElement(By.xpath(expandBtnXpath)).click();
				General.sleep(1);
			}
			List<WebElement> subItems = conn.findElements("xpath",getContainerXpathByType(by) + subItemHeaderXpath);
			actualValue = subItems.get(subItemIndex).getText();
			if(actualValue.contains(expectedValue)){
				log.add(LogStatus.PASS, "Notification: \"" + by.toString() +"\", gourp \"" + gourpIndex + "\", sub item \"" + subItemIndex + "\" header, actual value: \"" + actualValue 
						+ "\", contains  expected value: \"" + expectedValue + "\".");
			}else{
				log.add(LogStatus.FAIL, "Notification: \"" + by.toString() +"\", gourp \"" + gourpIndex + "\", sub item \"" + subItemIndex + "\" header, actual value: \"" + actualValue 
						+ "\", not contains  expected value: \"" + expectedValue + "\".");
				Assert.assertEquals(true, false,"Notification: \"" + by.toString() +"\", gourp \"" + gourpIndex + "\", sub item \"" + subItemIndex + "\" header, actual value: \"" + actualValue 
						+ "\", not contains  expected value: \"" + expectedValue + "\".");
			}
		}catch(Exception ex){
			log.add(LogStatus.FAIL, "Failed to compare notification: \"" + by.toString() +"\", gourp \"" + gourpIndex + "\", sub item \"" + subItemIndex + "\" header with: " + ex.toString());
			Assert.assertEquals(true, false,"Failed to compare notification: \"" + by.toString() +"\", gourp \"" + gourpIndex + "\", sub item \"" + subItemIndex + "\" header with: " + ex.toString());
		}
	}
	
	public void checkSubItemBodyContains(NotificationBy by,  int gourpIndex, int subItemIndex, String expectedValue){
		try{
			if(!bOpenNotification){
				openNotification();
				bOpenNotification = true;
			}
		
			String actualValue = "";
			List<WebElement> els = conn.findElements("xpath", getContainerXpathByType(by) + "/li");
			String attrGroupItem  = els.get(gourpIndex).getAttribute("class");
			if(attrGroupItem.contains("-Collapsed")){
				List<WebElement> es = conn.findElements("xpath", expandBtnXpath);
				es.get(gourpIndex).click();
				General.sleep(1);
			}
			List<WebElement> subItems = conn.findElements("xpath",getContainerXpathByType(by) + subItemBodyXpath);
			actualValue = subItems.get(subItemIndex).getText();
			if(actualValue.contains(expectedValue)){
				log.add(LogStatus.PASS, "Notification: \"" + by.toString() +"\", gourp \"" + gourpIndex + "\", sub item \"" + subItemIndex + "\" body, actual value: \"" + actualValue 
						+ "\", contains  expected value: \"" + expectedValue + "\".");
			}else{
				log.add(LogStatus.FAIL, "Notification: \"" + by.toString() +"\", gourp \"" + gourpIndex + "\", sub item \"" + subItemIndex + "\" body, actual value: \"" + actualValue 
						+ "\", not contains  expected value: \"" + expectedValue + "\".");
				Assert.assertEquals(true, false,"Notification: \"" + by.toString() +"\", gourp \"" + gourpIndex + "\", sub item \"" + subItemIndex + "\" body, actual value: \"" + actualValue 
						+ "\", not contains  expected value: \"" + expectedValue + "\".");
			}	
		}catch(Exception ex){
			log.add(LogStatus.FAIL, "Failed to compare notification gourp: \"" + by.toString() +"\", \"" + gourpIndex + "\", sub item \"" + subItemIndex + "\" header with: " + ex.toString());
			Assert.assertEquals(true, false,"Failed to compare notification gourp: \"" + by.toString() +"\", \"" + gourpIndex + "\", sub item \"" + subItemIndex + "\" header with: " + ex.toString());
		}
	}
	
	public void closeSubItem(NotificationBy by,  int gourpIndex, int subItemIndex){
		try{
			if(!bOpenNotification){
				openNotification();
				bOpenNotification = true;
			}
			log.add(LogStatus.INFO, "Close notification: \"" + by.toString() +"\", gourp \"" + gourpIndex + "\", sub item \"" + subItemIndex + "\".");
		
			List<WebElement> els = conn.findElements("xpath", getContainerXpathByType(by) + "/li");
			String attrGroupItem  = els.get(gourpIndex).getAttribute("class");
			if(attrGroupItem.contains("-Collapsed")){
				List<WebElement> es = conn.findElements("xpath", expandBtnXpath);
				es.get(gourpIndex).click();
			}
			List<WebElement> subItems = conn.findElements("xpath",getContainerXpathByType(by) + subItemCloseBtnXpath);
			subItems.get(subItemIndex).click();
			General.waitPageLoadComplete();
		}catch(Exception ex){
			log.add(LogStatus.FAIL, "Failed to close notification: \"" + by.toString() +"\", gourp \"" + gourpIndex + "\", sub item \"" + subItemIndex + "\" with: " + ex.toString());
			Assert.assertEquals(true, false,"Failed to close notification: \"" + by.toString() +"\", gourp \"" + gourpIndex + "\", sub item \"" + subItemIndex + "\" with: " + ex.toString());
		}
	}
	
	private static final String preItemXpath = "//div[@id='notifications-preview-container']//span[contains(@id,'title-inner')]";
	private static final String preItemPriorityXpath = "//div[@id='notifications-preview-container']//div[contains(@class,'sapMNLB-Priority')]";
	private static final String preItemCloseBtnXpath = "//div[@id='notifications-preview-container']//Button[contains(@id,'closeButton')]";
	//preview notification item
	public void closePreviewItem(int index){
		try{
			if(bOpenNotification){
				openNotification();
				bOpenNotification = false;
			}
			log.add(LogStatus.INFO, "Close notification preview item, item index: \"" + index + "\".");
			List<WebElement> els = conn.findElements("xpath",  preItemCloseBtnXpath);
			els.get(index).click();
			General.waitPageLoadComplete();
		}catch(Exception ex){
			log.add(LogStatus.FAIL, "Failed to close notification preview item, item index: \"" + index 
					+ "\" with: " + ex.toString());
			Assert.assertEquals(true, false,"Failed to close notification preview item, item index: \"" 
					+ index + "\" with: " + ex.toString());
		}
	}
	
	private NotificationPriority convertItemPriorityFromClassAttr(String attr){
		
		if(attr.contains("sapMNLB-Medium"))
		{
			return NotificationPriority.Normal;
		}
		if(attr.contains("sapMNLB-High"))
		{
			return NotificationPriority.High;
		}
		if(attr.contains("sapMNLB-Low"))
		{
			return NotificationPriority.Low;
		}
		return null;
	}
	public void comparePreviewItemPriority(int index, NotificationPriority expectedPriority){
		try{
			if(bOpenNotification){
				openNotification();
				bOpenNotification = false;
			}
			List<WebElement> els = conn.findElements("xpath", preItemPriorityXpath);
			NotificationPriority actPriority = convertItemPriorityFromClassAttr(els.get(index).getAttribute("class"));
			if(expectedPriority == actPriority){
				log.add(LogStatus.PASS, "Compare notification preview item pririoty: item index: \"" + index + "\", actual value: \"" + actPriority.toString() 
						+ "\", expected value: \"" + expectedPriority.toString() + "\".");
			}else{
				log.add(LogStatus.FAIL, "Failed to compare notification preview item pririoty: item index: \"" + index + "\", actual value: \"" + actPriority.toString() 
						+ "\", expected value: \"" + expectedPriority.toString() + "\".");
				Assert.assertEquals(true, false,"Failed to compare notification preview item pririoty: item index: \"" + index + "\", actual value: \"" + actPriority.toString() 
						+ "\", expected value: \"" + expectedPriority.toString() + "\".");
			}
		}catch(Exception ex){
			log.add(LogStatus.FAIL, "Failed to compare notification preview item pririoty: item index: \"" + index + "\" with: " + ex.toString());
			Assert.assertEquals(true, false,"Failed to compare notification ipreview item pririoty: item index: \"" + index + "\" with: " + ex.toString());
		}
	}
	
	public void checkPreviewItemHeaderContains(int index, String expectedValue){
		try{
			if(bOpenNotification){
				openNotification();
				bOpenNotification = false;
			}
			String actualValue = "";
			List<WebElement> els = conn.findElements("xpath",  preItemXpath);
			actualValue = els.get(index).getText();
			if(actualValue.contains(expectedValue)){
				log.add(LogStatus.PASS, "Notification preview item contents: item index: \"" + index + "\", actual value: \"" + actualValue 
						+ "\", contains  expected value: \"" + expectedValue + "\".");
			}else{
				log.add(LogStatus.FAIL, "Notification preview item contents: item index: \"" + index + "\", actual value: \"" + actualValue 
						+ "\", not contains  expected value: \"" + expectedValue + "\".");
				Assert.assertEquals(true, false,"Notification preview item contents: item index: \"" + index + "\", actual value: \"" + actualValue 
						+ "\", not contains  expected value: \"" + expectedValue + "\".");
			}
		}catch(Exception ex){
			log.add(LogStatus.FAIL, "Failed to compare notification preview item contents: item index: \"" + index + "\" with: " + ex.toString());
			Assert.assertEquals(true, false,"Failed to compare notification preview item contents: item index: \"" + index + "\" with: " + ex.toString());
		}
	}
													  
	private static final String floatItemHeaderXpath = "//div[@id='right-floating-container']//li//span[contains(@id,'title-inner')]";
	private static final String floatItemBodyXpath = "//div[@id='right-floating-container']//li//span[contains(@id,'datetime')]";
	
	public void checkFloatItemHeaderContains(int waitMinutes, int index, String expectedValue){
		try{
			if(bOpenNotification){
				openNotification();
				bOpenNotification = false;
			}
			int loopCount = 0;
			do{
				if(conn.isExistElement("xpath", floatItemHeaderXpath))
				{
					break;
				}
				loopCount ++;
				General.sleep(30);
			}while(loopCount <= 2*waitMinutes);
			String actualValue = "";
			List<WebElement> els = conn.findElements("xpath",  floatItemHeaderXpath);
			actualValue = els.get(index).getText();
			//actualValue = actualValue.replace("^(\\d\\d:\\d\\d)", "--:--").replace("[A-Z][a-z]{2}\s\d{2},\s\d{4}", "MMM dd, yyyy");
			
			if(actualValue.contains(expectedValue)){
				log.add(LogStatus.PASS, "Notification float item header: item index: \"" + index + "\", actual value: \"" + actualValue 
						+ "\", contains expected value: \"" + expectedValue + "\".");
			}else{
				log.add(LogStatus.FAIL, "Notification float item header: item index: \"" + index + "\", actual value: \"" + actualValue 
						+ "\", not contains expectedValue: \"" + expectedValue + "\".");
				Assert.assertEquals(true, false,"Notification float item header: item index: \"" + index + "\", actual value: \"" + actualValue 
						+ "\", not contains  expected value: \"" + expectedValue + "\".");
			}
		}catch(Exception ex){
			log.add(LogStatus.FAIL, "Failed to compare notification float item header: item index: \"" + index + "\" with: " + ex.toString());
			Assert.assertEquals(true, false,"Failed to compare notification float item header: item index: \"" + index + "\" with: " + ex.toString());
		}
	}
	
	public void checkFloatItemBodyContains(int waitMinutes, int index, String expectedValue){
		try{
			if(bOpenNotification){
				openNotification();
				bOpenNotification = false;
			}
			int loopCount = 0;
			
			do{
				if(conn.isExistElement("xpath", floatItemBodyXpath))
				{
					break;
				}
				loopCount ++;
				General.sleep(30);
			}while(loopCount <= 2*waitMinutes);
			String actualValue = "";
			List<WebElement> els = conn.findElements("xpath",  floatItemBodyXpath);
			actualValue = els.get(index).getText();
			if(actualValue.contains(expectedValue)){
				log.add(LogStatus.PASS, "Notification float item body: item index: \"" + index + "\", actual value: \"" + actualValue 
						+ "\", contains  expected value: \"" + expectedValue + "\".");
			}else{
				log.add(LogStatus.FAIL, "Ntification float item body: item index: \"" + index + "\", actual value: \"" + actualValue 
						+ "\", not contains  expected value: \"" + expectedValue + "\".");
				Assert.assertEquals(true, false,"Notification float item body: item index: \"" + index + "\", actual value: \"" + actualValue 
						+ "\", not contains  expected value: \"" + expectedValue + "\".");
			}
		}catch(Exception ex){
			log.add(LogStatus.FAIL, "Failed to compare notification float item body: item index: \"" + index + "\" with: " + ex.toString());
			Assert.assertEquals(true, false,"Failed to compare notification float item body: item index: \"" + index + "\" with: " + ex.toString());
		}
	}
}
