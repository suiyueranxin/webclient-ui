package webclient.modules.administration;

import java.io.File;

import webclient.general.General;
import webclient.general.GlobalWebClient;
import webuita.general.Global;
import webuita.general.WebConnection;
import webuita.locating.Locators;
import webuita.log.Logger;
import widgets.html.*;


import com.relevantcodes.extentreports.LogStatus;

public class Logon {
	private Logger log;
	private Locators locs;
	
	public Logon(){
		this.log =Global.log;
		locs = new Locators("Home" + File.separator + "Home_mapping.xml", log);
	}
	
	public void selectCompany(String companyName){
		try{
			Select sel = new Select("Company",locs);
			sel.select(companyName);
		}catch(Exception ex){
			
		}
	
	}
	
	public void setUsername(String username){
		Input user = new Input("Username", locs);
		user.setValue(username);
	}
	
	public void setPassword(String password){
		Input user = new Input("Password", locs);
		user.setValue(password);
	}
	
	public void clickLogin(){
		Button btn = new Button("Login", locs);
		btn.click();
	}
	public void logon(String username, String password, String companyName){
		if(null != GlobalWebClient.companyDbName && !GlobalWebClient.companyDbName.equals("")){
			companyName = GlobalWebClient.companyDbName;
		}
		General.waitRefreshComplete();
		selectCompany(companyName);
		setUsername(username);
		setPassword(password);
		clickLogin();
		log.add(LogStatus.PASS, "Logon Web Client Successfully!");
		General.waitRefreshComplete();
	}
	
}
