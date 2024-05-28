package widgets.ui5;

import webuita.general.General;
import webuita.locating.Locators;

public class Select extends Input {
	
	private static String xpathIconSuffix = "//span[@class='sapMSltArrow']";
	
	public Select(String itemName, Locators locator) {
		super(itemName, locator);
	}

	public Select(String itemName, String xpath){
		
		super(itemName, xpath, "");
	}
	public void select(String itemName){
		conn.clickElement(byType, byValue + xpathIconSuffix, "");
		General.sleep(1);
		conn.clickElement(byType,"//div[contains(@style,'visibility: visible')]//li[text()='" + itemName + "']", "");
		General.sleep(1);
	}
}
