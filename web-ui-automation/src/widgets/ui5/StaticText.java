package widgets.ui5;

import com.relevantcodes.extentreports.LogStatus;

import webuita.general.Global;
import webuita.general.WebConnection;
import webuita.locating.Item;
import webuita.locating.Locators;

public class StaticText extends Text{

	public StaticText(String itemName, Locators locator) {
		super(itemName, locator);
	}
	public StaticText(String itemName, String xpath, String desc){
		super(itemName, xpath, desc);
	}
}
