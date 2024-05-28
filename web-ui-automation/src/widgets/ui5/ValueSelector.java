package widgets.ui5;

import java.lang.reflect.Method;

import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import webuita.general.General;
import webuita.general.Global;
import webuita.locating.Locators;

public class ValueSelector extends Input {

	private String valueHelpXpath = "";
	private String description = "";
	public ValueSelector(String itemName, Locators loc) {
		super(itemName, loc);
		this.valueHelpXpath = byValue + "//div[@class='sapMInputBaseIconContainer']";
		
	}
	
	
	
	public ValueSelector(String colName, String cellXpath, String desc) {
		super(colName, cellXpath, desc);
		this.valueHelpXpath = byValue + "//div[@class='sapMInputBaseIconContainer']";
		description =  desc;
	}
//	
//	public ValueSelector(String gridName, String colName, Locators loc){
//		super(gridName, colName, loc);
//		this.valueHelpXpath = byValue + "//div[@class='sapMInputBaseIconContainer']";
//	}
	public void openValueHelp(){
		conn.clickElement("xpath", byValue, "");
		conn.clickElement(byType, this.valueHelpXpath, "Open \"" + name +"\" 's InputValueHelp.");
		General.waitBusyIndicatorInvisible();;
	}
	
	public void chooseFromList(String packageName, String dlgName, String colName, String value){
		try{
			boolean bSuccessed = false;
			openValueHelp();
			StringBuilder className = new StringBuilder(packageName);
			className.append(dlgName);
			Class<?> dlgClass = Class.forName(className.toString());
			Object obj = dlgClass.newInstance();
			Class<?>[] paramTypes = {String.class, String.class};
			Method cfl = dlgClass.getDeclaredMethod("chooseFromList",paramTypes);
			bSuccessed = (boolean)cfl.invoke(obj, colName, value);
			if(bSuccessed){
				Global.log.add(LogStatus.INFO, "Set \"" + colName + "\"" + ", value: \"" + value + "\"");
			}else{
				Global.log.add(LogStatus.FAIL, "Couldn't find \"" + colName + "\"" + ", value: \"" + value + "\"", Global.isStopRunAfterFail);
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Global.log.add(LogStatus.FAIL, "Couldn't find \"" + colName + "\"" + ", value: \"" + value + "\", with: " + e.toString(), Global.isStopRunAfterFail);
		}
		
	}
	public void choose(String value){
		String dlgXpath = "//div[contains(@class,'sapMDialog') and @role='dialog']";
		String searchTextXpath = dlgXpath + "//input[@type='search']";
		String searchIconXpath = dlgXpath + "//div[@title='Search']";
		
		openValueHelp();
		conn.setElementValue("xpath", searchTextXpath, value, "Set search: " + value + ".");
		conn.clickElement("xpath", searchIconXpath, "Click search icon.");
		General.waitRefreshComplete();
		String firstRow = dlgXpath + "//div[contains(@class, 'sapUiTableCCnt')]//table//tbody//tr[1]";
		conn.clickElement("xpath", firstRow, "Click the first row.");
		General.waitBusyIndicatorInvisible();
	}

	public void select(String value){
		String optionXpath = String.format("//li[text()='%s']", value);
		openValueHelp();
		conn.clickElement("xpath", optionXpath, "Select option "+ value);
	}
}
