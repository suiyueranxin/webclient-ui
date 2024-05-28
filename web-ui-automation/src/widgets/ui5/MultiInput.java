package widgets.ui5;

import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

import webuita.general.Global;
import webuita.general.WebConnection;
import webuita.locating.Item;
import webuita.locating.Locators;
import webuita.log.Logger;

public class MultiInput {
	private WebConnection conn;
	private String byType;
	private String byValue;
	private String name;
	private String type;
	private String description;
	private Logger log;
	public MultiInput(String itemName, Locators locator) {
		conn = Global.conn;
		log = Global.log;
		Item item = locator.getItem(itemName);
		byType = item.getByType();
		byValue = item.getByValue();
		this.name = itemName;
		type = item.getType();
		this.description = "Set \"" + itemName + "\" to ";
	}

	public void clear(){
		try{
			String tokenXpath = byValue + "//span[contains(@class,'sapMTokenText')]/..//span[contains(@id,'icon')]";
			java.util.List<WebElement> tokens = conn.findElements("xpath", tokenXpath);
			if(null != tokens){
				int countConditions = tokens.size();
				for(int i = 0; i < countConditions; i++){
					tokens = conn.findElements("xpath", tokenXpath);
					tokens.get(tokens.size() - 1).click();
				}
			}
			
		}catch(Exception ex){
			log.add(LogStatus.FAIL, String.format("Fail to clear the \"%s\".", name));
		}
		
	}
	
	public void select(String value){
		description = description + "\"" + value + "\".";
		
		try{
			String[] values = value.split(";");

			clear();
			String xpathArrow = byValue + "//div[@class='sapMInputBaseIconContainer']";
			conn.clickElement("xpath", byValue, "");
			conn.clickElement(byType, xpathArrow, "");
			
			String xpathItem = "";
			for(String val : values){
				xpathItem = String.format("//li//div[text()='%s']/../../..//div[@role='checkbox']", val);
				conn.clickElement("xpath", xpathItem, "");
			}
			conn.clickElement(byType, xpathArrow, "");
		}catch(Exception ex){
			log.add(LogStatus.FAIL, String.format("Fail to set \"%s\" value to \"%s\", with: \"%s\""
					, name, value, ex.toString()));
		}
	}

}
