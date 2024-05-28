package webclient.Sales.SalesOrderList.ToT_Gravity.ChartTest_DefaultChartRender;

import org.testng.annotations.Test;

import webclient.general.BaseWebClientTest;
import webclient.modules.home.Home;
import webclient.modules.sales.ItemList;
import widgets.ui5.ChartTypes;

public class ItemListEChartBATCase extends BaseWebClientTest{
	
	@Test
    public void testDefaultChartRender(){
    	Home home = new Home();
    	home.navigateToFromTiles("Sales->Items");
        ItemList il = new ItemList();
        il.clickChartView();
        il.checkChartShow(ChartTypes.line, true);
        home.backHome();
    }

}