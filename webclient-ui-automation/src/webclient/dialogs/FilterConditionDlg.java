package webclient.dialogs;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

import webclient.filters.TimePeriodType;
import webclient.general.ButtonNames;
import webclient.general.General;
import webuita.general.Global;
import webuita.locating.Item;
import webuita.locating.Locators;
import widgets.ui5.Button;
import widgets.ui5.Input;
import widgets.ui5.Select;
import widgets.ui5.Tab;
import widgets.ui5.Table;
import widgets.ui5.ValueSelector;

public class FilterConditionDlg extends BaseDlg {

	
	public FilterConditionDlg(){
		super("Dialogs" + File.separator + "FilterConditionDlg_mapping.xml");
	}
	public void clickAdd(){
		Button btn = new Button("Add", locators);
	}
	
	public void defineFilterByTimePeriod(String fieldName, String[][] conditions){
		try{
			Global.log.add(LogStatus.INFO, "Define Filter Conditions \"" + fieldName + "\"" + ", value: \""
					+ Arrays.deepToString(conditions) + "\"");
			Tab tab = new Tab("DEFINE CONDITIONS", "(//div[@role='dialog']//div[@role='tab'])[2]");
			if(tab.isExist()){
				tab.select();
			}
			Button btn = new Button("","//header[@class='sapMPanelWrappingDiv']//span[@role='button']");
			if(btn.isExist()){
				btn.click();
			}
		
			for(int i = 0; i < conditions.length; i++){
				if(conditions[i].length != 2){
					int iSize = 1;
					List<WebElement> eles = conn.findElements("xpath", "//div[contains(@id,'grid')]");
					if(null != eles){
						iSize = eles.size();
					}
					if(conn.isExistElement("xpath", 
							"//div[contains(@role, 'dialog')]//span[contains(@class,'sapMTokenText')]")){
						conn.clickElement("xpath", "//button[@title='Add']", "");
						iSize += 1;
						
					}
					System.out.println("test");
					String gridXpath = "(//div[contains(@id,'grid')])[" + iSize + "]";
					String tmpXpath = "(" + gridXpath + "//div[contains(@id, 'select')])[2]";
					Select selOperator = new Select("Operator",tmpXpath);
					selOperator.select(conditions[i][0]);
					
					ValueSelector vs = new ValueSelector("TimePeriod", gridXpath + "//div[@class='sapMInputBaseContentWrapper']", "");
					vs.openValueHelp();
					Select sel = new Select("Time Period", 
							"(//div[contains(@style, 'visibility: visible')]//div[@class='sapMPopoverCont']//div[contains(@id, 'data')])[2]");
					sel.select(conditions[i][1]);
					switch(conditions[i][1]){
						case "Today": 
						case "This week":
						case "This month":
						case "This quarter":
						case "This year":
							
							break;
						case "Last X days":
						case "Next X days":
						case "Last X weeks":
						case "Next X weeks":
						case "Last X months":
						case "Next X months":
						case "Last X years":
						case "Next X years":
							Input input = new Input("Value", 
									"(//div[contains(@style, 'visibility: visible')]//div[@class='sapMPopoverCont']//div[contains(@id, 'data')])[3]","");
							input.setValue(conditions[i][2]);
							break;
						case "Date range":
							String[] values = conditions[i][2].split(";");
							
							Input input1 = new Input("Value", 
									"(//div[contains(@style, 'visibility: visible')]//div[@class='sapMPopoverCont']//div[contains(@id, 'data')])[4]","");
							input1.setValue(values[0]);
							input1 = new Input("Value", 
									"(//div[contains(@style, 'visibility: visible')]//div[@class='sapMPopoverCont']//div[contains(@id, 'data')])[6]","");
							input1.setValue(values[1]);
							break;
						default:
							Global.log.add(LogStatus.FAIL, "UnKnow Time Period Type.", Global.isStopRunAfterFail);
							break;
							
					}
		
				}
					
				conn.clickElement("xpath", "//header[@class='sapMDialogTitle']", "");
			}
		
			General.sleep(1);
			//press OK button.
			clickOK();
			General.waitRefreshComplete();
		}catch(Exception ex){
			Global.log.add(LogStatus.FAIL, ex.toString(), Global.isStopRunAfterFail);
		}
	
	}
	public void defineConditions(String fieldName, String[][] conditions){
		
			Global.log.add(LogStatus.INFO, "Define Filter Conditions \"" + fieldName + "\"" + ", value: \""
					+ Arrays.deepToString(conditions) + "\"");
			Tab tab = new Tab("DEFINE CONDITIONS", "(//div[@role='dialog']//div[@role='tab'])[2]");
			if(tab.isExist()){
				tab.select();
			}
			Button btn = new Button("","//header[@class='sapMPanelWrappingDiv']//span[@role='button']");
			if(btn.isExist()){
				btn.click();
			}
			for(int i = 0; i < conditions.length; i++){
				if(conditions[i].length != 2){
					System.err.println("Please check the condions format.");
				}else{
			
					int iSize = 1;
					List<WebElement> eles = conn.findElements("xpath", "//div[contains(@id,'grid')]");
					if(null != eles){
						iSize = eles.size();
					}
					if(conn.isExistElement("xpath", 
							"//div[contains(@role, 'dialog')]//span[contains(@class,'sapMTokenText')]")){
						conn.clickElement("xpath", "//button[@title='Add']", "");
						iSize += 1;
						
					}
					System.out.println("test");
					String gridXpath = "(//div[contains(@id,'grid')])[" + iSize + "]";
					String tmpXpath = "(" + gridXpath + "//div[contains(@id, 'select')])[2]";
					Select selOperator = new Select("DEFINE CONDITIONS",tmpXpath);
					selOperator.select(conditions[i][0]);
					Input inputValue = new Input("Value", "(" + gridXpath + "//div[contains(@id, 'input')])[2]","");
					inputValue.setValue(conditions[i][1]);
					inputValue.pressTab();
					
				}
			}
			General.sleep(1);
			//press OK button.
			clickOK();
			General.waitRefreshComplete();
		
	
	
	}
	
	public boolean checkValuesFromTable(String colName, String values){
		boolean bSuccess = false;
		try{
			Table table = new Table("SelectList",locators);
			bSuccess = table.checkValuesFromTable(colName, values);
			clickOK();
		}catch(Exception ex){
			
		}
		return bSuccess;
		
	}
}
