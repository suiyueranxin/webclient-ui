package webclient.dialogs;

import webuita.general.Global;
import webuita.general.WebConnection;
import webuita.locating.Locators;
import webuita.log.Logger;
import widgets.ui5.Button;

public class BaseDlg {
	protected WebConnection conn;
	protected Logger log;
	protected Locators locators;
	
	public BaseDlg(){
		conn = Global.conn;
		log = Global.log;
	}
	
	public BaseDlg(String uuidfilename){
		conn = Global.conn;
		log = Global.log;
		locators = new Locators(uuidfilename, log);
	}
	
	public void clickOK(){
		Button btn = new Button("OK", "(//footer[@class='sapMDialogFooter']//button)[1]");
		btn.click();
	}
	
	public void clickCancel(){
		Button btn = new Button("OK", "(//footer[@class='sapMDialogFooter']//button)[2]");
		btn.click();
	}
	
}
