package widgets.ui5;

import com.relevantcodes.extentreports.LogStatus;

import webuita.general.Global;
import webuita.general.WebConnection;
import webuita.locating.Item;
import webuita.locating.Locators;

public class Text {
	protected WebConnection conn;
	protected String byType;
	protected String byValue;
	protected String name;
	protected String type;
	protected String description;
	public Text(String itemName, String xpath, String desc){
		conn = Global.conn;
		byType = "xpath";
		byValue = xpath;
		this.name = itemName;
		this.description = desc;
	
	}
	public Text(String itemName, Locators locator){
		conn = Global.conn;
		Item item = locator.getItem(itemName);
		byType = item.getByType();
		byValue = item.getByValue();
		this.name = itemName;
		type = item.getType();
		this.description = "Set \"" + itemName + "\" to ";
	}
	
	public String getValue(){		
		String val = conn.getElementValue("xpath", byValue + "//*[@class='sapBUiText']", "Get value from \"" + name + "\"");
		return val;
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
