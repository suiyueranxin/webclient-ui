package widgets.ui5;

import webuita.general.General;
import webuita.locating.Locators;

public class ComboBox extends Input {

	private static String xpathIconSuffix = "//div[@role='combobox']//div[@class='sapMInputBaseIconContainer']";
	
	public ComboBox(String cmbName, Locators locator){
		super(cmbName, locator);
		
	}
	
	public ComboBox(String colName, String cellXpath, String desc) {
		super(colName, cellXpath, desc);
	}
	
	public void select(String value){
		conn.clickElement(byType, byValue + xpathIconSuffix, "");
		General.waitRefreshComplete();
		conn.clickElement(byType,"//div[contains(@style, 'visibility: visible')]//ul//li//div[text()='" + value + "']", this.description + value + ".");
		General.sleep(1);
	}
	
	public String getValue(){
		
		return conn.getElementAttribute("xpath", byValue + "//div[@role='combobox']//input", "value");	
	}
	
}
