package webclient.general;

import java.io.File;

import webuita.general.General;
import webuita.general.Global;
import widgets.ui5.Input;
import widgets.ui5.Tab;
import widgets.ui5.ValueSelector;

public class MarketDocument extends BaseForm {
	
	public MarketDocument(){
		super("General" + File.separator + "MarketDocument_Mapping.xml");	
	}

	public void selectTab(String tabName){
		Tab tab = new Tab(tabName, this.locators);
		tab.select();
	}
	//-----------General Tab----------
	public void setCustomer(String value)
	{
		ValueSelector customer = new ValueSelector("Customer", locators);
		customer.choose(value);
	}
	public void setContactPerson(String value){
		ValueSelector contactPerson = new ValueSelector("Contact Person", locators);
		contactPerson.choose(value);
	}
	//-----------General Tab----------
	//-----------Contents Tab----------
	public void setCurrency(String value){
		Input currency = new Input("Currency", locators);
		currency.setValue(value);;
		webclient.general.General.pressTabKey();
		General.waitBusyIndicatorInvisible();
	}
	public void setItemServiceType(String value){
		ValueSelector type = new ValueSelector("Item Service Type", locators);
		type.select(value);
		General.waitBusyIndicatorInvisible();
	}
	//-----------Contents Tab----------
}
