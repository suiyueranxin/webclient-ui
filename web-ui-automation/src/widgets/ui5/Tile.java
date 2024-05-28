package widgets.ui5;

import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

import webuita.general.General;
import webuita.general.Global;
import webuita.general.WebConnection;
import webuita.log.Logger;

public class Tile {
	private WebConnection conn;
	private Logger log; 
	private String name;
	
	public Tile(String name){
		conn = Global.conn;
		log = Global.log;
		this.name = name;
	}
	
	public void open(){
		try{
			String navItemXpath = "//div[@class='sapUshellTileContainerContent']//li//span[contains(@id,'title-inner')]";
			java.util.List<WebElement> els = conn.findElements("xpath", navItemXpath);
			for(WebElement e : els){
				String title = e.getText().replace("\u00AD", "");
				if(title.equals(name)){
					e.click();
				}
			}
			
			General.waitRefreshComplete();
			log.add(LogStatus.PASS, "Open Tile: \"" + name  + "\".");
		}catch(Exception ex){
			log.add(LogStatus.FAIL, "Fail to open Tile: \"" + name  + "\".",Global.isStopRunAfterFail);
		}
		
	}
	
	public void compareContent(){}
}
