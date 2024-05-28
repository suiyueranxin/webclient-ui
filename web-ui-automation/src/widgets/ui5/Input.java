package widgets.ui5;

import java.awt.event.KeyEvent;

import org.openqa.selenium.Keys;

import com.relevantcodes.extentreports.LogStatus;

import webuita.general.Global;
import webuita.general.WebConnection;
import webuita.locating.Item;
import webuita.locating.Locators;

public class Input {
	protected WebConnection conn;
	protected String byType;
	protected String byValue;
	protected String name;
	protected String type;
	protected String description;
	public Input(String itemName, String xpath, String desc){
		conn = Global.conn;
		byType = "xpath";
		byValue = xpath;
		this.name = itemName;
		this.description = desc;
	
	}
	public Input(String itemName, Locators locator){
		conn = Global.conn;
		Item item = locator.getItem(itemName);
		byType = item.getByType();
		byValue = item.getByValue();
		this.name = itemName;
		type = item.getType();
		this.description = "Set \"" + itemName + "\" to ";
	}
	
	public void setValue(String value){
		String desc = "";
		if(!this.description.equals("")){
			desc =  this.description  + "\"" + value + "\".";
		}
		conn.setElementValue("xpath", byValue + "//input", value, desc);
		
	}
	
	public void setValueWithPressEnter(String value){
		
		conn.setElementValueWithPressEnter("xpath", byValue + "//input", value, this.description  + "\"" + value + "\".");
		
	}
	
	public void pressTab(){
		conn.findElement(byType,  byValue + "//input").sendKeys(Keys.TAB);
	}
	public String getValue(){
		
		return conn.getElementAttribute("xpath", byValue + "//input", "value");	
	}
	
	public void compareValue(String expectVal){
		String desc = "Compare value for \"" + name + "\", ";
		String actualVal = this.getValue();
		if(expectVal.equals(actualVal)){
			Global.log.add(LogStatus.PASS, desc + "Actual: \"" + actualVal +"\", expected: \"" + expectVal + "\"");
		}else{
			Global.log.add(LogStatus.FAIL, desc + "Actual: \"" + actualVal +"\", expected: \"" + expectVal + "\"", Global.isStopRunAfterFail);			
		}
	}

}
