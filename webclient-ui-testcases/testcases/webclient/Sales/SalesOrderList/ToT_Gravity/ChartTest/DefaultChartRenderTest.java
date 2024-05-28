package webclient.Sales.SalesOrderList.ToT_Gravity.ChartTest;

import org.testng.annotations.Test;

import webclient.general.BaseWebClientTest;
import webclient.modules.home.Home;
import webclient.modules.sales.SalesOrderList;
import widgets.ui5.ChartTypes;

public class DefaultChartRenderTest extends BaseWebClientTest{
	
	@Test
    public void testDefaultChartRender(){

		Home home = new Home();
        //home.navigateTo("Sales->Sales Orders");
        home.navigateToFromTiles("Sales->Sales Orders");
        
        SalesOrderList sol = new SalesOrderList();
        sol.clickChartView();
        
        //Check default chart render.
        sol.checkChartShow(ChartTypes.line, true);
        
    }
}
