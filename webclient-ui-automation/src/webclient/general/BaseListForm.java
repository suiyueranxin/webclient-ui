package webclient.general;

import webclient.dialogs.FormSettingDlg;
import webuita.general.Global;
import webuita.general.WebConnection;
import webuita.locating.Locators;
import webuita.log.Logger;
import widgets.ui5.Button;

public class BaseListForm {
	protected WebConnection conn;
	protected Logger log;
	protected Locators locators;
	
	public BaseListForm(){}
	public BaseListForm(String uuidfilename){
		conn = Global.conn;
		log = Global.log;
		locators = new Locators(uuidfilename, log);
	
	}
	
	public void selectDocFromList(int docNumber){
		String docNum = Integer.toString(docNumber);
		General.selectValueFromTable("All", "Document Number", locators, docNum);
	}
	
//	public void openFormSetting(){
//		Button btn = new Button("All", "Settings", locators);
//		btn.click();
//		General.waitPageLoadComplete();
//	}
	
//	public void setFormSetting(String[] selectCols, String[] unSelectCols){
//		openFormSetting();
//		FormSettingDlg fsd = new FormSettingDlg();
//		fsd.setFormSetting(selectCols, unSelectCols);
//	}
	
}
