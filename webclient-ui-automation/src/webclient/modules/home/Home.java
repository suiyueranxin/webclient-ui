package webclient.modules.home;

import java.io.File;

import com.relevantcodes.extentreports.LogStatus;

import webclient.general.BaseForm;
import webclient.general.General;
import webuita.general.Global;
import webuita.general.WebConnection;
import webuita.locating.Locators;
import webuita.log.Logger;
import widgets.ui5.Button;
import widgets.ui5.NavigationBar;
import widgets.ui5.Tile;

public class Home{
	private WebConnection conn;
	private Logger log;
	private Locators locators;
	public Home(){
		conn = Global.conn;
		log = Global.log;
		locators = new Locators("Home" + File.separator + "Home_Mapping.xml", log);
	}
	
	public void navigateTo(String modulePath){
		//notification was closed
		Notification.bOpenNotification = false;
		
		String[] names = modulePath.split("->");
		log.add(LogStatus.INFO, "Navigate to \"" + modulePath + "\".");
		//click menu button
		conn.clickElement(locators.getItem("MainMenu").getByType(), locators.getItem("MainMenu").getByValue(), "");
		//sleep
		General.sleep(1);
		for(int i = 0; i < names.length; i++){
			
			conn.clickElement("xpath", "//ul[contains(@class,'sapTntNavLI')]//span[text()='" + names[i] + "']", "");
			General.sleep(2);
		}
		General.waitRefreshComplete();
		
	}
	
	public void navigateToFromTiles(String modulePath){
		//notification was closed
		Notification.bOpenNotification = false;
				
		String[] names = modulePath.split("->");
		log.add(LogStatus.INFO, "Navigate to \"" + modulePath + "\".");
		//click navigation bar
		NavigationBar.click(names[0]);
		Tile tile = new Tile(names[1]);
		tile.open();
	}
	
	public void backHome(){
		conn.clickElement("id","homeBtn", "Back \"Home\".");
		General.waitPageLoadComplete();
	}
	
	public void goBack(){
		conn.clickElement("id", "backBtn", "Go \"Back\"");
		General.waitPageLoadComplete();
	}
	
	public void clickMeArea(){
		conn.clickElement("id", "meAreaHeaderButton", "");
		General.waitPageLoadComplete();
	}
	
	public void openNotification(){
		Button btn = new Button("Notification", locators);
		btn.click();
	}
}
