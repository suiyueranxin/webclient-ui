package widgets.ui5;

import webuita.general.General;
import webuita.general.Global;
import webuita.general.WebConnection;
import webuita.locating.Item;
import webuita.locating.Locators;

public class Tab {
	protected WebConnection conn;
	protected String byType;
	protected String byValue;
	protected String name;
	protected String type;
	
	
	public Tab(String tabName){
		conn = Global.conn;
		byType = "xpath";
		byValue =  "//div[@role='tab']//div[@class='sapMITBText' and text()='" + tabName + "']";
		name = tabName;
		}
	public Tab(String name, String xpath){
		conn = Global.conn;
		byType = "xpath";
		this.name = name;
		byValue = xpath;
	}
	
	public boolean isExist(){
		return conn.isExistElement("xpath", byValue);
	}

	public Tab(String itemName, Locators locator){
		conn = Global.conn;
		Item item = locator.getItem(itemName);
		byType = item.getByType();
		byValue = item.getByValue();
		this.name = itemName;
		type = item.getType();
	}
	
	public void select(int index){
		byValue = String.format("(%s)[%d]", byValue, index);
		conn.clickElement("xpath", byValue, "Select tab \"" + name + "\" with index " + index);
	}
	public void select(){
		conn.clickElement("xpath", byValue, "Select tab \"" + name + "\".");
		General.waitBusyIndicatorInvisible();
	}
}
