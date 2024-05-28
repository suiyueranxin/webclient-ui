package webclient.Sales.SalesOrderList.ToT_Gravity.ChartTest;

import org.testng.annotations.Test;

import webclient.filters.Filters;
import webclient.filters.Operators;
import webclient.general.BaseWebClientTest;
import webclient.modules.sales.SalesOrderList;
import widgets.ui5.ChartTypes;

public class DateFilterTest extends BaseWebClientTest{
	
	@Test
    public void testStartDate2EndDateFilter(){

		SalesOrderList sol = new SalesOrderList();
		Filters filter = new Filters();
		
		sol.selectChartType(ChartTypes.line);
		sol.setChartDimensions("Document Total", "Delivery Date", "All");
		
        //Return a series of dates
        filter.setFilterConditions("Delivery Date", new String[][]{{Operators.EqualTo, "06/08/2016...06/30/2016"}});////01/01/2016...01/31/2016
        filter.go();
        sol.checkChartFilterResult("06/08/2016...06/30/2016", true);//Track a bug of the web client:06/08/2016...06/30/2016
        
        //No data for this filter
        filter.setFilterConditions("Delivery Date", new String[][]{{Operators.EqualTo, "06/08/2016...06/10/2016"}});////01/01/2016...01/31/2016
        filter.go();
        sol.checkChartFilterResult("06/08/2016...06/10/2016", true);
		
    }
}