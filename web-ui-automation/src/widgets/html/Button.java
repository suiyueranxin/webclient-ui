package widgets.html;

import org.openqa.selenium.WebElement;

import webuita.general.Global;
import webuita.general.WebConnection;
import webuita.locating.Item;
import webuita.locating.Locators;
import webuita.log.Logger;

public class Button {
	protected WebConnection conn;
	protected Logger log; 
	protected String byType;
	protected String byValue;
	protected String itemName;
	protected String type;
	
	
	public Button(String itemName, Locators locator){
		conn = Global.conn;
		log = Global.log;
		
		Item item = locator.getItem(itemName);
		byType = item.getByType();
		byValue = item.getByValue();
		this.itemName = itemName;
		type = item.getType();
		
	}
	public void click(){
		conn.clickElement(byType, byValue, "");
	}
}
