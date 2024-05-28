package widgets.ui5;

import com.relevantcodes.extentreports.LogStatus;

import webuita.general.Global;
import webuita.general.WebConnection;
import webuita.locating.Item;
import webuita.locating.Locators;
import webuita.log.Logger;

public class CheckBox {
	private WebConnection conn;
	private Logger log; 
	private String byType;
	private String byValue;
	private String name;
	private String type;
	
	
	public CheckBox(String chkName, Locators locator){
		conn = Global.conn;
		log = Global.log;
		
		Item item = locator.getItem(chkName);
		byType = item.getByType();
		byValue = item.getByValue();
		name = chkName;
		type = item.getType();
		
	}
	
	public boolean isChecked(){
		String state = conn.getElementAttribute(byType, byValue, "aria-checked");
		return "true".equals(state);
	}
	
	public void check(boolean isChecked){
		if(isChecked && !isChecked()){
			conn.clickElement(byType, byValue,"");
		}
		if(!isChecked && isChecked()){
			conn.clickElement(byType, byValue,"");
		}
	}
	
	public void compare(boolean expectedValue){
		String state = conn.getElementAttribute(byType, byValue, "aria-checked");
		boolean bState = Boolean.valueOf(state);
		if(expectedValue == bState){
			log.add(LogStatus.PASS, String.format("Comapre CheckBox \"%s\", expected value: \"%s\", actual value: \"%s\"", 
					this.name, Boolean.toString(expectedValue), state));
					
		}else{
			log.add(LogStatus.FAIL, "", Global.isStopRunAfterFail);
		}
	}
}
