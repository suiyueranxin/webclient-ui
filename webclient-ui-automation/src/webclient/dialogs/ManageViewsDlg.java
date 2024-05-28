package webclient.dialogs;

import java.io.File;
import java.util.List;

import org.openqa.selenium.WebElement;

import webuita.general.General;
import webuita.locating.Item;
import widgets.html.Input;
import widgets.ui5.Table;

public class ManageViewsDlg extends BaseDlg {

	public ManageViewsDlg(){
		super("Dialogs" + File.separator + "ManageViewsDlg_mapping.xml");
	}
	
	public void open(){
		conn.clickElement("xpath", "//div[@title = 'Select View']","");
		General.sleep(1);
		conn.clickElement("xpath", "//button[contains(@id,'-manage')]", "");
		General.sleep(1);
	}
	public void search(String searchValue){
		conn.setElementValue("xpath", "//div[@role='dialog']//input[@type='search']", searchValue, 
				String.format("Search %s.", searchValue));
	}
	
	public void setDefault(String viewName){
		open();
		search(viewName);
		Table t = new Table("Management Table",locators);
		t.clickCell(0, 3);
		clickOK();
	}
	
	public void delete(String viewName){
		open();
		search(viewName);
		Table t = new Table("Management Table",locators);
		t.clickCell(0, 5);
		clickOK();
	}
	
	public void rename(String originalViewName, String targetViewName) {
	    open();
	    search(originalViewName);
	    Input input = new Input("ViewName",locators);
	    input.setValue(targetViewName);
	    clickOK();
	}
}
