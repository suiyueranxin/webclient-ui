package webclient.Sales.SalesOrderList.ToT_Gravity.ChartTest_DefaultChartRender;

import org.testng.annotations.Test;

import webclient.general.BaseWebClientTest;
import webclient.modules.home.Home;
import webclient.modules.sales.SalesQuotationList;
import widgets.ui5.ChartTypes;

public class SalesQuotationListEChartBATCase extends BaseWebClientTest{
	
	@Test
    public void testDefaultChartRender(){
    	Home home = new Home();
    	home.navigateToFromTiles("Sales->Sales Quotations");
        SalesQuotationList sql = new SalesQuotationList();
        sql.clickChartView();
        sql.checkChartShow(ChartTypes.line, true);
        home.backHome();
    }

}