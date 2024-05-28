package widgets.html;

import org.openqa.selenium.WebElement;

import webuita.general.General;
import webuita.general.Global;
import webuita.general.WebConnection;
import webuita.locating.Item;
import webuita.locating.Locators;
import webuita.log.Logger;

public class Select {
	protected WebConnection conn;
	protected Logger log; 
	protected String byType;
	protected String byValue;
	protected String itemName;
	protected String type;
	
	
	public Select(String itemName, Locators locator){
		conn = Global.conn;
		log = Global.log;
		
		Item item = locator.getItem(itemName);
		byType = item.getByType();
		byValue = item.getByValue();
		this.itemName = itemName;
		type = item.getType();
		
	}
	public void select(String value){
		WebElement eSel = conn.findElement(byType, byValue);
		org.openqa.selenium.support.ui.Select sel = new org.openqa.selenium.support.ui.Select(eSel);
		sel.selectByValue(value);
	}
}
