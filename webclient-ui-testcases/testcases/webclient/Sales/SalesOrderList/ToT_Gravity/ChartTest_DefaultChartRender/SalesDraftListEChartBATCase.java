package webclient.Sales.SalesOrderList.ToT_Gravity.ChartTest_DefaultChartRender;

import org.testng.annotations.Test;

import webclient.general.BaseWebClientTest;
import webclient.modules.home.Home;
import webclient.modules.sales.SalesDraftList;
import widgets.ui5.ChartTypes;

public class SalesDraftListEChartBATCase extends BaseWebClientTest{
	
	@Test
    public void testDefaultChartRender(){
    	Home home = new Home();
    	home.navigateToFromTiles("Sales->Sales Drafts");
        SalesDraftList sdl = new SalesDraftList();
        sdl.clickChartView();
        sdl.checkChartShow(ChartTypes.line, true);
        home.backHome();
    }

}
