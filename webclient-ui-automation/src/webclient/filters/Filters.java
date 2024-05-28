package webclient.filters;

import java.io.File;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import webclient.dialogs.FilterConditionDlg;
import webclient.dialogs.ManageViewsDlg;
import webclient.general.ButtonNames;
import webclient.general.General;
import webclient.general.MessageBox;
import webuita.general.Global;
import webuita.general.WebConnection;
import webuita.locating.Item;
import webuita.locating.Locators;
import webuita.log.Logger;
import widgets.ui5.Button;
import widgets.ui5.CheckBox;
import widgets.html.Input;
import widgets.ui5.MultiComboBox;
import widgets.ui5.MultiInput;
import widgets.ui5.Select;


public class Filters {
	private WebConnection conn;
	private Logger log;
	private Locators locator;
	public Filters(){
		conn = Global.conn;
		log = Global.log;
		locator = new Locators("General" + File.separator + "Filters_Mapping.xml", log);
	}
	
	public void search(String value){
		Item item = locator.getItem("Search");
		String byType = item.getByType();
		String byValue = item.getByValue();
		
		String inputXpath = byValue + "//input";
		String btnSearch = byValue + "//div[2]";
		log.add(LogStatus.INFO, "Search ...");
		conn.setElementValue(byType, inputXpath, value, "Set \"" + "Search" + "\" value to: \"" + value + "\"." );
		conn.clickElement(byType, btnSearch, "Search \"" + value + "\" on Filters Header.");
	}
	
	public void compareDefaultViewName(String expectedValue){
		String nameXpath = "//div[@title = 'Select View']//span[contains(@id, 'text-inner')]";
		String name = "Default View Name";
		try{
			if(expectedValue == "" || expectedValue.contains("\n")) return;
			String actualValue = conn.getElementValue("xpath", nameXpath,"");
			
			boolean bEqual =  expectedValue.equals(actualValue);
			//test
			if(bEqual){
				Global.log.add(LogStatus.PASS, "Compare \""+ name + "\" value, actual: \""+ actualValue 
						+ "\" expected: \"" + expectedValue +"\" passed");
			}else{
				Global.log.add(LogStatus.FAIL, "Compare \""+ name + "\" value, actual: \""+ actualValue 
						+ "\" expected: \"" + expectedValue + "\" failed");
				Assert.assertTrue(false, "Compare \""+ name + "\" value, actual: \""+ actualValue 
						+ "\" expected: \"" + expectedValue + "\" failed");
			}
			
		}catch(Exception ex){
			Global.log.add(LogStatus.FAIL, "Comapre \""+name + "\" value failed");
			Assert.assertTrue(false, "Comapre \""+name + "\" value failed");
		}
	}
	
	public void saveAsView(String viewName, boolean isSetAsDefault, boolean isPublic){
		conn.clickElement("xpath", "//div[@title = 'Select View']","");
		
		Button btnSaveAs = new Button("SaveAs", locator);
		btnSaveAs.click();
		
		String nameXpath = "//div[contains(@id, 'savedialog')]//input[contains(@id, 'name')]";
		
		conn.setElementValue("xpath", nameXpath, viewName, "Set View Name to: \"" + viewName + "\"");
		CheckBox chkDefault = new CheckBox("SetAsDefault", locator);
		CheckBox chkPublic = new CheckBox("Public", locator);
		
		chkDefault.check(isSetAsDefault);
		chkPublic.check(isPublic);
		
		Button btnOK = new Button("OK", locator);
		btnOK.click();
	}
	

	public void setAsDefault(String viewName){
		
		ManageViewsDlg dlg = new ManageViewsDlg();
		dlg.setDefault(viewName);
	}
	
	public void deleteView(String viewName){
		ManageViewsDlg dlg = new ManageViewsDlg();
		dlg.delete(viewName);
	}
	
	public void saveView() {
	    conn.clickElement("xpath", "//div[@title = 'Select View']","");
        
        Button btnSave = new Button("Save", locator);
        btnSave.click();
	}
	
	public void deleteAllViews() {
	    ManageViewsDlg dlg = new ManageViewsDlg();
	    dlg.open();
        Item delete = this.locator.getItem("delete");
        List<WebElement> deletes = conn.findElements(delete.getByType(), delete.getByValue());
        for (WebElement btn : deletes) {
            btn.click();
        }
        dlg.clickOK();
	}
	
	public void renameView(String original, String target) {
	    ManageViewsDlg dlg = new ManageViewsDlg();
        dlg.rename(original, target);
	}
	/**
	 * Such as Posting Date
	 * @param fieldName
	 * @param conditions new String[][] = [{operator01, value01}, {operator02, value02}]
	 */
	public void setFilterConditions(String fieldName, String[][] conditions){
		log.add(LogStatus.INFO, "Set filter coditions on: \"" + fieldName +"\".");
		for(int i = 0; i < conditions.length; i++){
			if(conditions[i].length != 2){
				System.err.println("Please check the condions format.");
			}else{
				Item item = locator.getItem(fieldName);
				String byType = item.getByType();
				String byValue = item.getByValue();
				
				String inputXpath = byValue + "//input";
				String valueHelpXpath = byValue + "//div[contains(@class,'sapMInputBaseIconContainer')]";
				
				//clear input
				conn.findElement("xpath", inputXpath).clear();
				conn.clickElement(byType, valueHelpXpath, "");
				
				int iSize = 1;
				List<WebElement> eles = conn.findElements("xpath", "//div[contains(@id,'grid')]");
				if(null != eles){
					iSize = eles.size();
				}
				if(conn.isExistElement("xpath", 
						"(//div[contains(@role, 'dialog')]//section)[4]//span[contains(@class,'sapMTokenText')]")){
					conn.clickElement("xpath", "//button[@title='Add']", "");
					iSize += 1;
					
				}
				System.out.println("test");
				String gridXpath = "(//div[contains(@id,'grid')])[" + iSize + "]";
				String tmpXpath = "(" + gridXpath + "//span[@class='sapMSltArrow'])[2]";
				//((//div[contains(@id,'grid')])[1]//span[@class='sapMSltArrow'])[2]
				conn.clickElement("xpath",tmpXpath,"");
				General.sleep(1);
				List<WebElement> lis = conn.findElements("xpath", "//ul//li[text()='" + conditions[i][0] + "']");
				lis.get(lis.size() -1).click();
				General.sleep(1);
				String values[] = conditions[i][1].split(";");
				for (int inputIndex =0; inputIndex<values.length; inputIndex++) {
				    String inputParamXpath = "(" + gridXpath + "//input[contains(@id,'-inner')])[" + (inputIndex+1) + "]";
				    conn.setElementValue("xpath", inputParamXpath, values[inputIndex],"");
				    conn.findElement("xpath", inputParamXpath).sendKeys(Keys.TAB);
				}
				General.pressTabKey();
			}
		}
		General.sleep(1);
		//press OK button.
		conn.clickElement("xpath","//button//bdi[text()='" + ButtonNames.OK + "']/..", "");
		General.waitRefreshComplete();
	}
	
	/***
	 * Compare filter condition value, if has more values, join the values with ','.
	 * such as:"Gross Profit (LC), NetSales Amount (SC)"
	 * @param fieldName
	 * @param expectedValue
	 */
	public void compareFilterCondition(String fieldName, String expectedValue){
		try{
			String actualValue = "";
			Item item = locator.getItem(fieldName);
			String byType = item.getByType();
			String byValue = item.getByValue();
			String spanXpath = byValue + "//span[@class='sapMTokenText']";
			//handle case: value will be changed to "n Items" when content is too long
			String inputXpath = byValue + "//input";
			conn.clickElement(byType, inputXpath, "");
			
			List<WebElement> els = conn.findElements(byType, spanXpath);
			if(null != els && els.size() >= 1){
				for(int i = 0; i < els.size(); i++){
					if(actualValue.equals("")){
						actualValue = els.get(i).getText();
					}else{
						actualValue += "," + els.get(i).getText();
					}
					
				}
			}else{
				actualValue = "";
				
			}
			if(expectedValue.equals(actualValue)){
				log.add(LogStatus.PASS, "Compare filter condition actual: \""
						+ actualValue + "\", expected: \"" + expectedValue + "\"  passed.");
			}else{
				log.add(LogStatus.FAIL, "Failed to compare filter condition, actual: \""
						+ actualValue + "\", expected: \"" + expectedValue + "\".");
				Assert.assertEquals(true, false,"Failed to compare filter condition, actual: \""
						+ actualValue + "\", expected: \"" + expectedValue + "\".");
			}
			
		}catch(Exception ex){
			log.add(LogStatus.FAIL, "Failed to compare filter condition with: " + ex.toString());
			Assert.assertEquals(true, false,"Failed to compare filter condition with: " + ex.toString());
		}
		
	}
	public void selectView(String viewName){
		try{
			
			String dropDwnXpath = "//div[@class='sapFDynamicPageTitleMainHeading']//button";
			conn.clickElement("xpath", dropDwnXpath, "");
			General.sleep(1);
			String selItemXpath = "//div[@class='sapMPopoverCont']//li[text()='"+ viewName +"']";
			conn.clickElement("xpath", selItemXpath, "");
			General.waitPageLoadComplete();
		}catch(Exception ex){
			
		}
	}
	/**
	 * Select filter conditions from list. for MultiComboBox, such as "Business Partner Code"
	 * @param fieldName Field Name.
	 * @param colName column name of list.
	 * @param value filter conditions, if has more value, split with ";", etc."12;37;50".
	 */
	public void selectFilterConditionsFromList(String fieldName, String colName, String value){
		Global.log.add(LogStatus.INFO, "Select Filter Conditions \"" + fieldName + "\"" + ", value: \"" + value + "\"");
		MultiComboBox mcb = new MultiComboBox(fieldName,locator);
		mcb.clear();
		mcb.checkValuesFromList("webclient.dialogs.", "FilterConditionDlg", colName, value);
		
	}
	/***
	 * Select filter conditions from list.for MultiInput, such as "Document Type Display Name"
	 * @param fieldName FilterFields
	 * @param value filter conditions, if has more value, split with ";", etc."12;37;50".
	 */
	public void selectFilterConditions(String fieldName, String value){
		Global.log.add(LogStatus.INFO, "Select Filter Conditions \"" + fieldName + "\"" + ", value: \"" + value + "\"");
		MultiInput mInput = new MultiInput(fieldName, locator);
		mInput.select(value);
		
	}
	
	/***
	 * Define filter conditions by Time Period.
	 * @param fieldName
	 * @param conditions
	 *  filters.defineFilterByTimePeriod("Document Number", new String[][]{{Operators.EqualTo, TimePeriodType.Today}, 
	 *																 {Operators.EqualTo, TimePeriodType.lastXDays,"20"}, MM/DD/YYYY
	 *																 {Operators.EqualTo, TimePeriodType.dataRange,"01/20/2020;01/25/2020"}});
	 */
	public void defineFilterByTimePeriod(String fieldName, String[][] conditions){
		MultiComboBox mcb = new MultiComboBox(fieldName,locator);
		
		mcb.openValueHelp();
		FilterConditionDlg dlg = new FilterConditionDlg();
		dlg.defineFilterByTimePeriod(fieldName, conditions);
	
	}
	/**
	 * define filter conditions.
	 * @param fieldName
	 * @param conditions new String[][] = [{operator01, value01}, {operator02, value02}]
	 * filters.setFilterConditions("Document Number", new String[][]{{Operators.EqualTo, "10"}, 
	 *																 {Operators.EqualTo, "20"}, 
	 *																 {Operators.EqualTo, "30"}});
	 */
	public void defineFilterConditions(String fieldName, String[][] conditions){
		MultiComboBox mcb = new MultiComboBox(fieldName,locator);
		mcb.clear();
		mcb.openValueHelp();
		FilterConditionDlg dlg = new FilterConditionDlg();
		dlg.defineConditions(fieldName, conditions);
		General.waitRefreshComplete();
	}
	
	public void go(){
		Button btn = new Button("Go", locator);
		btn.click();
		General.waitRefreshComplete();
	}

	
	public void removeCondition(String fieldName){
		Item item = locator.getItem(fieldName);
		String byValue = item.getByValue();
		log.add(LogStatus.INFO, String.format("Remove the \"%s\" filter condition.", fieldName));
		try{
			String tokenXpath = byValue + "//span[contains(@class,'sapMTokenText')]/..//span[contains(@id,'icon')]";
			java.util.List<WebElement> tokens = conn.findElements("xpath", tokenXpath);
			int countConditions = tokens.size();
			for(int i = 0; i < countConditions; i++){
				tokens = conn.findElements("xpath", tokenXpath);
				tokens.get(tokens.size() - 1).click();
			}
			
		}catch(Exception ex){
			log.add(LogStatus.FAIL, String.format("Fail to remove the \"%s\" filter condition.", fieldName));
		}
		
	}
	/***
	 * Share the Filter, Save as Tile.
	 * @param title
	 * @param subtitle
	 * @param desc
	 * @param group
	 * @param msgContent - Expected message box content.
	 */
	public void share(String title, String subtitle, String desc, String group, String msgContent){
		conn.clickElement("xpath", "//button[@title='Share']", "Click \"Share\".");
		webuita.general.General.sleep(1);
		conn.clickElement("xpath", "//div[contains(@style,'visibility: visible;') and @role='dialog']//button",
				"Click \"Save as Tile\"");
		
		conn.setElementValue("xpath", "//input[@id='bookmarkTitleInput-inner']", title, "");
		conn.setElementValue("xpath", "//input[@id='bookmarkSubTitleInput-inner']", title, "");
		conn.setElementValue("xpath", "//input[@id='bookmarkInfoInput-inner']", title, "");
		Select selGroup = new Select("Group","//div[@id='groupsSelect']");
		selGroup.select(group);
		Button btnOk = new Button("OK", "//button[@id='bookmarkOkBtn']");
		btnOk.click();
		MessageBox.compareMessage(msgContent);
	}

	//Amy: variant
	//check current filter labels in filter bar
	//limitation: text related and cannot support multi language
	public void compareFilters(List<String> filters) {
	    Item domPath = this.locator.getItem("FilterLabel");
	    List<WebElement> uiFilters = conn.findElements(domPath.getByType(), domPath.getByValue());
	    String actualFilters = "";
	    if (uiFilters.size() == filters.size()) {
	        for (int index=0; index < uiFilters.size(); index++) {
	            actualFilters += uiFilters.get(index).getText();
	            if (index < uiFilters.size()-1) {
	                actualFilters += ",";
	            }
	        }
	    }
	    String expectedFilters = String.join(",", filters);
	    General.compare(actualFilters, expectedFilters, true, "Check current visible filters");
	}
	
	public void clearFilter(String fieldName) {
	    Item item = locator.getItem(fieldName);
        String byType = item.getByType();
        String byValue = item.getByValue();
        String deleteXpath = byValue + "//span[contains(@id,'icon')]";
        String inputXpath = byValue + "//input";
        conn.clickElement(byType, inputXpath, "");
        
        List<WebElement> deleteIcons = conn.findElements(byType, deleteXpath);
        for (WebElement icon:deleteIcons) {
            icon.click();
        }
        this.go();
	}
	
	public void addFilter(String fieldName) {
	    Button adaptFilters = new Button("Adapt Filters", locator);
	    adaptFilters.click();
	    Input search = new Input("Search for Filters", locator);
	    search.setValue(fieldName);
	    CheckBox cb = new CheckBox("Show on Filter Bar", locator);
	    cb.check(true);
	    Button go = new Button("Adapt Filters Go", locator);
	    go.click();
	}
}
