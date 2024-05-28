package widgets.ui5;

import webuita.general.General;
import webuita.locating.Locators;

public class MenuButton extends Button {
	
	public MenuButton(String btnName, Locators locator) {
		super(btnName, locator);
	}

	public void select(String itemName){
		conn.clickElement(byType, byValue, "");
		General.waitBusyIndicatorInvisible();
		conn.clickElement(byType,"//li/div[@class='sapUiMnuItmTxt' and text()='" + itemName + "']", 
				"Select \"" + itemName + "\", from menu button \"" + btnName + "\"");
		General.waitBusyIndicatorInvisible();
		
	}

}
