package widgets.ui5;

import webuita.general.General;
import webuita.general.Global;


public class NavigationBar {
	public static void click(String itemName){
		String navBarXpath = "//ul[@class='sapUshellAnchorNavigationBarItemsScroll']//div[text()='" +itemName + "']";
		Global.conn.clickElement("xpath", navBarXpath, "");
		//sleep
		General.sleep(1);
	}
}
