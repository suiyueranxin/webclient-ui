package webclient.dialogs;

import webclient.general.General;

public class BusinessPartnerDlg extends BaseDlg {

	public BusinessPartnerDlg(){
		super("BusinessPartnerDlg_mapping.xml");
	}
	
	public boolean chooseFromList(String colName, String value){
		return General.selectValueFromTable("All", colName, locators, value);
	}
}
