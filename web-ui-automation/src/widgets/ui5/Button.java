package widgets.ui5;

import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import webuita.general.General;
import webuita.general.Global;
import webuita.general.WebConnection;
import webuita.log.Logger;
import webuita.locating.*;

public class Button {
	protected WebConnection conn;
	protected Logger log; 
	protected String byType;
	protected String byValue;
	protected String btnName;
	protected String type;
	
	
	public Button(String btnName, Locators locator){
		conn = Global.conn;
		log = Global.log;
		
		Item item = locator.getItem(btnName);
		byType = item.getByType();
		byValue = item.getByValue();
		this.btnName = btnName;
		type = item.getType();
		
	}
	
	public Button(String btnName, String xpath) {
		conn = Global.conn;
		log = Global.log;

		byType = "xpath";
		byValue = xpath;
		this.btnName = btnName;
	}
	
	public void click(){
		String disabled = conn.getElementAttribute(byType, byValue, "disabled");
		if("true".equals(disabled)){
			log.add(LogStatus.FAIL,"The button \"" + btnName + "\" is disabled.");
			Assert.assertEquals(true, false,"The button \"" + btnName + "\" is disabled.");
		}
		conn.clickElement(byType, byValue, "Click button \"" + btnName +"\"");
		General.waitBusyIndicatorInvisible();
	}
	
	public boolean isExist(){
		return conn.isExistElement(byType, byValue);
	}
}
