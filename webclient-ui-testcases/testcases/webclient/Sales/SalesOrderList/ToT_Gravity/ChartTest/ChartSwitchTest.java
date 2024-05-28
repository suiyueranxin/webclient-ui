package webclient.Sales.SalesOrderList.ToT_Gravity.ChartTest;

import org.testng.annotations.Test;

import webclient.general.BaseWebClientTest;
import webclient.modules.sales.SalesOrderList;
import widgets.ui5.ChartTypes;

public class ChartSwitchTest extends BaseWebClientTest{
	
	@Test
    public void testChartSwitch(){

		SalesOrderList sol = new SalesOrderList();
        //sol.clickChartView();
        //sol.checkChartShow(ChartTypes.line, true);
 
        //Update dimension and measure
        //sol.setChartDimensions("Document Total", "Customer Code", "All");
        //sol.checkChartShow(ChartTypes.heatMap, true);

        //Switch chart type
        sol.selectChartType(ChartTypes.column);
        sol.checkChartShow(ChartTypes.column, true);
        
        sol.selectChartType(ChartTypes.bar);
        sol.checkChartShow(ChartTypes.bar, true);
        
        sol.selectChartType(ChartTypes.stackedColumn);
        sol.checkChartShow(ChartTypes.stackedColumn, true);
        
        sol.selectChartType(ChartTypes.stackedBar);
        sol.checkChartShow(ChartTypes.stackedBar, true);
        
        sol.selectChartType(ChartTypes.pie);
        sol.checkChartShow(ChartTypes.pie, true);
        
        sol.selectChartType(ChartTypes.donut);
        sol.checkChartShow(ChartTypes.donut, true);

        sol.selectChartType(ChartTypes.heatMap);
        sol.checkChartShow(ChartTypes.heatMap, true);
        
    }

}
