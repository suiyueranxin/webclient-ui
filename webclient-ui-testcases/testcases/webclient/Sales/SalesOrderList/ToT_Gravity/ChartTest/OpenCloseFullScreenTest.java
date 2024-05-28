package webclient.Sales.SalesOrderList.ToT_Gravity.ChartTest;

import org.testng.annotations.Test;

import webclient.general.BaseWebClientTest;
import webclient.modules.sales.SalesOrderList;

public class OpenCloseFullScreenTest extends BaseWebClientTest{
	
	@Test
    public void testOpenCloseFullScreen(){

		SalesOrderList sol = new SalesOrderList();
		
        //By default close full screen.
        sol.checkChartFullScreen(false);
        
        //open full screen
        sol.openChartFullScreen();
        sol.checkChartFullScreen(true);
        
        //close full screen
        sol.closeFullScreen();
        sol.checkChartFullScreen(false);
        
    }
}
