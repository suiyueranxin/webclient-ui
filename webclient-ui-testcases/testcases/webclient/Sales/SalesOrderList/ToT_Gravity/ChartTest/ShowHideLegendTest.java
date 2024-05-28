package webclient.Sales.SalesOrderList.ToT_Gravity.ChartTest;

import org.testng.annotations.Test;

import webclient.general.BaseWebClientTest;
import webclient.modules.sales.SalesOrderList;
import widgets.ui5.ChartTypes;



public class ShowHideLegendTest extends BaseWebClientTest{
	
	@Test
    public void testShowHideLegend(){

		SalesOrderList sol = new SalesOrderList();
		sol.selectChartType(ChartTypes.line);
		//sol.setChartDimensions("Document Total", "Customer Code", "Document No.");
		
        //By default,show legend
        sol.checkLegendShow(true);
        
        //hide legend
        sol.clickChartLegend();
        sol.checkLegendShow(false);
        
        //show legend
        sol.clickChartLegend();
        sol.checkLegendShow(true);
        
    }
}