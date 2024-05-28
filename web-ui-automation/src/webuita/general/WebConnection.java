package webuita.general;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import com.relevantcodes.extentreports.LogStatus;

import webuita.general.Browser;
import webuita.general.Global;


public class WebConnection {

	private WebDriver driver;
	private int iWaitSecond;
		
	public WebConnection(){
		if(null != Global.driver){
			Global.driver.quit();
		}
		Global.driver = Browser.getBrowser(Global.browserType, Global.driverPath);
		Global.driver.manage().window().maximize();
		driver = Global.driver;
		iWaitSecond = Integer.parseInt(Global.waitSeconds);
	}
	
	public WebConnection(Browser.features feature){
		if(null != Global.driver){
			Global.driver.quit();
		}
		Global.driver = Browser.getBrowser(Global.browserType, Global.driverPath, feature);
		Global.driver.manage().window().maximize();
		driver = Global.driver;
		iWaitSecond = Integer.parseInt(Global.waitSeconds);
	}
	
	public void setDriver(){
		this.driver = Global.driver;
	}

	public void gotoUrl(String urlValue) {
		driver.get(urlValue);
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public By getElementBy(String byType, String byValue) throws Exception {
		// Find by xpath
		if (byType.equalsIgnoreCase("XPATH")) {
			return By.xpath(byValue);
		}
		// find by id
		else if (byType.equalsIgnoreCase("ID")) {

			return By.id(byValue);

		}
		// find by class
		else if (byType.equalsIgnoreCase("CLASSNAME")) {

			return By.className(byValue);

		}
		// find by name
		else if (byType.equalsIgnoreCase("NAME")) {

			return By.name(byValue);

		}
		// Find by css
		else if (byType.equalsIgnoreCase("CSS")) {

			return By.cssSelector(byValue);

		}
		// find by link
		else if (byType.equalsIgnoreCase("LINK")) {

			return By.linkText(byValue);

		}
		// find by partial link
		else if (byType.equalsIgnoreCase("PARTIALLINK")) {

			return By.partialLinkText(byValue);

		} else {
			throw new Exception("Wrong element by type and value");
		}
	}

	public WebElement findElement(String byType, String byValue, String desc) {
		if("" != desc && null != desc){
			Global.log.add(LogStatus.INFO, desc);
		}
		
		WebElement e = null;
		try {
			By by = getElementBy(byType, byValue);
			WebDriverWait wait = new WebDriverWait(driver, iWaitSecond);
			e = wait.until(ExpectedConditions.visibilityOfElementLocated(by));

			if (e == null) {
				Global.log.add(LogStatus.FAIL, "Could not find element by: " + by.toString(), Global.isStopRunAfterFail);
				
			}
		} catch (Exception ex) {
			Global.log.add(LogStatus.FAIL, "Could not find element with: " + ex.toString(), Global.isStopRunAfterFail);
			

		}

		return e;
	}
	
	public WebElement findElement(String byType, String byValue) {
		
		WebElement e = null;
		try {
			By by = getElementBy(byType, byValue);
			WebDriverWait wait = new WebDriverWait(driver, iWaitSecond);
			e = wait.until(ExpectedConditions.visibilityOfElementLocated(by));

			if (e == null) {
				Global.log.add(LogStatus.FAIL, "Could not find element by: " + by.toString(), Global.isStopRunAfterFail);
				
			}
		} catch (Exception ex) {
			Global.log.add(LogStatus.FAIL, "Could not find element with: " + ex.toString(), Global.isStopRunAfterFail);
			

		}

		return e;
	}
	
public WebElement findHiddenElement(String byType, String byValue) {
		
		WebElement e = null;
		try {
			By by = getElementBy(byType, byValue);
			WebDriverWait wait = new WebDriverWait(driver, iWaitSecond);
			e = wait.until(ExpectedConditions.presenceOfElementLocated(by));

			if (e == null) {
				Global.log.add(LogStatus.FAIL, "Could not find element by: " + by.toString(), Global.isStopRunAfterFail);
				
			}
		} catch (Exception ex) {
			Global.log.add(LogStatus.FAIL, "Could not find element with: " + ex.toString(), Global.isStopRunAfterFail);
			

		}

		return e;
	}

	public boolean isExistElement(String byType, String byValue){
		WebElement e = null;
		try {
			By by = getElementBy(byType, byValue);
			WebDriverWait wait = new WebDriverWait(driver, iWaitSecond);
			e = wait.until(ExpectedConditions.presenceOfElementLocated(by));

			if (e == null) {
				return false;
			}
		} catch (Exception ex) {
			return false;

		}

		return true;
	}
	
	public boolean isExistElement(WebElement e, String byType, String byValue){
		WebElement ele = null;
		try {
			By by = getElementBy(byType, byValue);
			ele = e.findElement(by);
			if (null == ele) {
				return false;
			}
		} catch (Exception ex) {
			return false;

		}

		return true;
	}
	
	public String getElementAttribute(String byType, String byValue, String attributeName){
		String value = "";
		try{
			WebElement e = findElement(byType, byValue);
			value = e.getAttribute(attributeName);
			
		}catch(Exception ex){
			Global.log.add(LogStatus.FAIL, "Could not get element's attribe \"" + attributeName + "\" with: ", ex.toString(), Global.isStopRunAfterFail);
			
		}
		
		return value;
	}
	
	public List<WebElement> findElements(String byType, String byValue) {
		
		List<WebElement> els = null;
		try {
			By by = getElementBy(byType, byValue);
			WebDriverWait wait = new WebDriverWait(driver, iWaitSecond);
			els = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 0));

			if (els == null) {
				Global.log.add(LogStatus.FAIL, "Could not find element by: " + by.toString(), Global.isStopRunAfterFail);
				
			}
		} catch (Exception ex) {
			//Global.log.add(LogStatus.FAIL, "Could not find element with: " + ex.toString(), Global.isStopRunAfterFail);
			

		}

		return els;
	}

	public WebElement findElement(WebElement el, String byType, String byValue) {
		
		WebElement e = null;
		try {
			By by = getElementBy(byType, byValue);
			e =el.findElement(by);

			if (e == null) {
				Global.log.add(LogStatus.FAIL, "Could not find element by: " + by.toString(), Global.isStopRunAfterFail);
				
			}
		} catch (Exception ex) {
			Global.log.add(LogStatus.FAIL, "Could not find element with: " + ex.toString(), Global.isStopRunAfterFail);
			

		}

		return e;
	}

	
//	/**
//	 * wait until ui-api ready
//	 * @param driver
//	 * @param iSeconds
//	 */
//	public void waitUiapiReady(int iSeconds){
//		try{
//			WebElement e = Global.driver.findElement(By.tagName("Body"));
//			WebDriverWait wait = new WebDriverWait(Global.driver, iSeconds);
//			wait.until(ExpectedConditions.attributeContains(e, "data-ta-status", "ready"));
//			
//		}catch(Exception ex){
//			
//		}
//		
//	}
	

	public void clickElement(String byType, String byValue, String desc) {
		if("" != desc && null != desc){
			Global.log.add(LogStatus.INFO, desc);
		}
		
		WebElement e = findElement(byType, byValue);
		try {
			e.click();
			

		} catch (Exception ee) {
			
			try {
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", e);
				
			} catch (Exception ex) {
				try {
					System.out.println("x: " + e.getRect().getX() + " Y: " + e.getRect().getY());
					Actions builder = new Actions(Global.driver);
					builder.moveToElement(e).click().build().perform();
					
				} catch (Exception exc) {
					Global.log.add(LogStatus.FAIL, "Failed to click element with: " + exc.toString(), Global.isStopRunAfterFail);
					
				}
				
			}

		}

	}
	/***
	 * For getting hidden element value.
	 * @param element
	 * @return String value.
	 */
	public String getText(String byType, String byValue, String desc){
		if("" != desc && null != desc){
			Global.log.add(LogStatus.INFO, desc);
		}
		String value = "";
		WebElement e = findHiddenElement(byType, byValue);
		try {
			  value = (String) ((JavascriptExecutor) Global.driver).executeScript(
				        "return jQuery(arguments[0]).text();", e);
		} catch (Exception ex) {
			Global.log.add(LogStatus.FAIL, "Failed to get element value with: " + ex.toString(), Global.isStopRunAfterFail);
			
		}
		
	  return value;
	}
	
	public String getElementValue(String byType, String byValue, String desc) {
		if("" != desc && null != desc){
			Global.log.add(LogStatus.INFO, desc);
		}
		String value = "";
		WebElement e = findElement(byType, byValue);
		try {
			value = e.getText();
		} catch (Exception ex) {
			Global.log.add(LogStatus.FAIL, "Failed to get element value with: " + ex.toString(), Global.isStopRunAfterFail);
			
		}
		return value;
	}

	public String getElementValue(WebElement el, String byType, String byValue, String desc) {
		if("" != desc && null != desc){
			Global.log.add(LogStatus.INFO, desc);
		}
		String value = "";
		WebElement e = findElement(el, byType, byValue);
		try {
			value = e.getText();
		} catch (Exception ex) {
			Global.log.add(LogStatus.FAIL, "Failed to get element value with: " + ex.toString(), Global.isStopRunAfterFail);
			
		}
		return value;
	}
	
	public void setElementValue(String byType, String byValue, String value, String desc) {
		if("" != desc && null != desc){
			Global.log.add(LogStatus.INFO, desc);
		}
		WebElement e = findElement(byType, byValue);
		try {
			e.clear();
			e.sendKeys(value);
			
		} catch (Exception ex) {
			Global.log.add(LogStatus.FAIL, "Failed to set element value with: "+ ex.toString(), Global.isStopRunAfterFail);
			
		}

	}
	
	public void setElementValueWithPressTab(String byType, String byValue, String value, String desc) {
		if("" != desc && null != desc){
			Global.log.add(LogStatus.INFO, desc);
		}
		WebElement e = findElement(byType, byValue);
		try {
			e.clear();
			e.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			e.sendKeys(value);
			e.sendKeys(Keys.TAB);
		} catch (Exception ex) {
			Global.log.add(LogStatus.FAIL, "Failed to set element value with: "+ ex.toString(), Global.isStopRunAfterFail);
			
		}

	}
	public void setElementValueWithPressEnter(String byType, String byValue, String value, String desc) {
		if("" != desc && null != desc){
			Global.log.add(LogStatus.INFO, desc);
		}
		WebElement e = findElement(byType, byValue);
		try {
			e.clear();
			e.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			e.sendKeys(value);
			e.sendKeys(Keys.ENTER);
		} catch (Exception ex) {
			Global.log.add(LogStatus.FAIL, "Failed to set element value with: "+ ex.toString(), Global.isStopRunAfterFail);
			
		}

	}
	public void setElementValue(WebElement el, String byType, String byValue, String value, String desc) {
		if("" != desc && null != desc){
			Global.log.add(LogStatus.INFO, desc);
		}
		WebElement e = findElement(el, byType, byValue);
		try {
			e.clear();
			e.sendKeys(value);
		
		} catch (Exception ex) {
			Global.log.add(LogStatus.FAIL, "Failed to set element value with: " + ex.toString(), Global.isStopRunAfterFail);
			
		}

	}
	
	public boolean isInteger(String text) {
		try {
			Integer.parseInt(text);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isPresence(WebDriver driver, By byType) {

		try {
			WebElement e = driver.findElement(byType);

			if (e != null && e.isDisplayed()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {

			return false;

		}

	}

	
	//for temporary demo use
	public void compareElementValue(String byType, String byValue, String expectedValue, String desc) {
		if("" != desc && null != desc){
			Global.log.add(LogStatus.INFO, desc);
		}
		String value = "";
		WebElement e = findElement(byType, byValue);
		try {
			value = e.getText();
			if(expectedValue.equals(value)){
				Global.log.add(LogStatus.PASS, desc + "Actual: \"" + value +"\", expected: \"" + expectedValue + "\"");
			}else{
				Global.log.add(LogStatus.FAIL,   "Failed to " +  desc + "Actual: \"" + value +"\", expected: \"" + expectedValue + "\""
						, Global.isStopRunAfterFail);
				
			}
		} catch (Exception ex) {
			Global.log.add(LogStatus.FAIL, "Failed to get element value with: " + ex.toString(), Global.isStopRunAfterFail);
			
		}
		
	}


	public ArrayList<String> getWindowHandles(){
		ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
		return windows;
	}
	
	
}
