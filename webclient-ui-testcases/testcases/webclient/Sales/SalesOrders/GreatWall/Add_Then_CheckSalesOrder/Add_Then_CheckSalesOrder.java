package webclient.Sales.SalesOrders.GreatWall.Add_Then_CheckSalesOrder;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import webclient.dialogs.DlgNames;
import webclient.general.BaseWebClientTest;
import webclient.general.General;
import webclient.general.MessageBox;
import webclient.general.TabNames;
import webclient.general.WebClient;
import webclient.modules.administration.Logon;
import webclient.modules.home.Home;
import webclient.modules.sales.SalesOrder;
import webclient.modules.sales.SalesOrderList;

public class Add_Then_CheckSalesOrder extends BaseWebClientTest  {
	
	@Test
	public void add_then_CheckSalesOrder(ITestContext context) throws InterruptedException {
		
		WebClient tc = new WebClient(context);
		//String schemaName = tc.restoreInitialCompany("SBODEMOUS", "manager", "manager", "SBODEMOUS_UI", "SBODEMOUS.tar.gz");
		String schemaName = "SBODEMOUS_UI0921417";
		tc.open();
		
		Logon logon = new Logon();
		logon.logon("manager", "manager", schemaName);
		
//		MessageBox.click(MessageBox.Buttons.OK.toString());
//		
//		Home home = new Home();
//		home.navigateTo("Sales->Sales Orders");
		
		SalesOrderList soList = new SalesOrderList();
		//soList.selectDocFromList(1178);
		soList.clickCreate();
		
		SalesOrder so = new SalesOrder();
		
		/*so.selectTab(TabNames.General);
		
		//so.setCustomer("C20000");
		so.chooseFromList("Customer", DlgNames.BusinessPartner, "BP Code", "C20000");
		
		so.setSeriesNo("Manual");
		
		so.setRemarks("test remarks");
		so.setPostingDate("11082018");
		so.setDeliveryDate("11082018");
		so.setSalesEmployee("2");
		so.setOwner("2");
		
		so.selectTab(Tabs.Contents);
		so.setDiscount("10");
		so.setItemServiceType("Item");
		so.setGridItemValue("Item Details", "Item No", 0, "A00001");*/
		//so.compareGridItemValue("Item Details", "Item No", 0, "A00004");
		
		//so.chooseFromList("Customer", DlgNames.BusinessPartner, "BP Code", "L10001");

//		SalesEmployeeDlg seDlg = new SalesEmployeeDlg();
//		seDlg.clickAdapFilter();
//		
//		AdaptFiltersDlg afDlg = new AdaptFilters();
//		afDlg.setSearch();
		
	}
}
