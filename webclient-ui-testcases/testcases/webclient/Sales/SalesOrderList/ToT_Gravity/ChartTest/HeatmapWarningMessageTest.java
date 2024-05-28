package webclient.Sales.SalesOrderList.ToT_Gravity.ChartTest;

import org.testng.annotations.Test;

import webclient.general.BaseWebClientTest;
import webclient.modules.sales.SalesOrderList;
import widgets.ui5.ChartTypes;

public class HeatmapWarningMessageTest extends BaseWebClientTest{
	
	@Test
    public void testHeatmapWarningMessage(){

		SalesOrderList sol = new SalesOrderList();
		
		sol.selectChartType(ChartTypes.heatMap);
        sol.checkHeatmapShowWarningMessage("MissDimension", true);
        
        sol.selectChartType(ChartTypes.line);
        sol.setChartDimensions("Document Total", "Posting Date", "Delivery Date");
        
        sol.selectChartType(ChartTypes.heatMap);
        sol.checkHeatmapShowWarningMessage("BigDataSet", true);

    }
}