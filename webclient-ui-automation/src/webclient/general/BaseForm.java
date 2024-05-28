package webclient.general;

import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import webuita.general.Global;
import webuita.general.WebConnection;
import webuita.locating.Locators;
import webuita.log.Logger;
import widgets.ui5.Button;


public class BaseForm {
	protected WebConnection conn;
	protected Logger log;
	protected Locators locators;
	
	public BaseForm(){}
	public BaseForm(String uuidfilename){
		conn = Global.conn;
		log = Global.log;
		locators = new Locators(uuidfilename, log);
	
	}

//	
//	public void clickAddAndView(){
//		CreateButton widget = new CreateButton("Add And View", locators);
//		widget.click();
//		General.checkToastMessage();
//	}
//	public void clickAddAndNew(){
//		CreateButton widget = new CreateButton("Add And New", locators);
//		widget.click();
//		General.checkToastMessage();
//	}
//	public void clickAddAndBack(){
//		CreateButton widget = new CreateButton("Add And Back", locators);
//		widget.click();
//		General.checkToastMessage();
//	}
//	public void clickUpdate(){
//		SaveButton widget = new SaveButton("Update", locators);
//		widget.click();
//		General.checkToastMessage();
//	}
//	public void clickCancel(){
//		CancelButton widget = new CancelButton("Cancel", locators);
//		widget.click();
//		General.checkToastMessage();
//	}
//	public void clickEdit(){
//		EditButton widget = new EditButton("Edit", locators);
//		widget.click();
//	}
//	public void clickNew(){
//		Button widget = new Button("New", locators);
//		widget.click();
//	}
//	public void clickRemove(){
//		Button widget = new Button("Remove", locators);
//		widget.click();
//		General.checkToastMessage();
//	}
//	public void clickPreview(){
//		PrintButton widget = new PrintButton("Preview", locators);
//		widget.click();
//	}
	public void clickFollowUp(){
		Button widget = new Button("Follow Up", locators);
		widget.click();
	}
	public void clickClose(){
		Button widget = new Button("Close", locators);
		widget.click();
	}
	public void clickReopen(){
		Button widget = new Button("Reopen", locators);
		widget.click();
	}
	public void clickDuplicate(){
		Button widget = new Button("Duplicate", locators);
		widget.click();
	}
	public void clickFirstDataRecord(){
		Button widget = new Button("First Data Record", locators);
		widget.click();
	}
	public void clickPreviousDataRecord(){
		Button widget = new Button("Previous Data Record", locators);
		widget.click();
	}
	public void clickNextDataRecord(){
		Button widget = new Button("Next Data Record", locators);
		widget.click();
	}
	public void clickLastDataRecord(){
		Button widget = new Button("Last Data Record", locators);
		widget.click();
	}
	
	
	
	public void clickSaveAsDraftAndNew(){
		Button btn = new Button("Save as Draft and New", locators);
		btn.click();
	}
	
	public void clickSaveAsDraftAndView(){
		Button btn = new Button("Save as Draft and View", locators);
		btn.click();
	}
	
//	public void selectTab(String tabName){
//		Section sec = new Section(tabName, locators);
//		sec.select();
//	}
//	
//	public void chooseFromList(String itemName, String dlgName, String colName, String value){
//		StringInput item = new StringInput(itemName, locators);
//		item.chooseFromList(dlgName, colName, value);
//		General.waitBusyIndicatorInvisible();
//	}
//	
//	public void openValueHelp(String itemName){
//		StringInput item = new StringInput(itemName, locators);
//		item.openValueHelp();
//				
//	}
	
	public void setGridItemValue(String gridName, String colName, int rowIndex, String value){
	
		General.setGridItemValue(gridName, colName, rowIndex, locators, value);
	}
	
	public void compareGridItemValue(String gridName, String colName, int rowIndex, String expectedValue){
		General.compareGridItemValue(gridName, colName, rowIndex, locators, expectedValue);
	}
}
