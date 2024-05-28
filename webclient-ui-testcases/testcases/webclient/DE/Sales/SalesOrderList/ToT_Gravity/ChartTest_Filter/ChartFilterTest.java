package webclient.DE.Sales.SalesOrderList.ToT_Gravity.ChartTest_Filter;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import webclient.filters.Filters;
import webclient.filters.Operators;
import webclient.general.BaseWebClientTest;
import webclient.general.WebClient;
import webclient.modules.administration.Logon;
import webclient.modules.analytic.ChartTypes;
import webclient.modules.home.Home;
import webclient.modules.sales.SalesOrderList;

public class ChartFilterTest extends BaseWebClientTest{
	
    @Test
    public void chartFiltersTest(ITestContext context) throws InterruptedException {
        WebClient tc = new WebClient(context);
        String schemaName = tc.restoreInitialCompany("SBODEMODE", "manager", "manager", "SBODEMODE_UI", "SBODEMODE.tar.gz");
        tc.initialB1A(schemaName);
        tc.open();
        
        Logon logon = new Logon();
        logon.logon("manager", "manager", schemaName);
        
        Home home = new Home();
        home.navigateTo("Sales->Sales Orders");
        
        SalesOrderList sol = new SalesOrderList();
        sol.clickChartView();
        sol.checkChartShow(ChartTypes.Line, true);
        sol.setChartDimensions("Document Total", "Delivery Date", "Status");
        
        Filters filter = new Filters();

        filter.setFilterConditions("Delivery Date", new String[][]{{Operators.EqualTo, "05/08/2016...06/08/2016"}});////01/01/2016...01/31/2016
        filter.go();
        sol.compareChart(ChartTypes.Line, "lineChartDateRangle_20160508_20160608.png");
        
        filter.setFilterConditions("Delivery Date", new String[][]{{Operators.EqualTo, "Today"}});
        filter.go();
        
        filter.setFilterConditions("Delivery Date", new String[][]{{Operators.EqualTo, "- 720 Day...Today"}});
        filter.go();
        
        filter.setFilterConditions("Delivery Date", new String[][]{{Operators.EqualTo, "Today...+ 1 Day"}});
        filter.go();
        
        filter.setFilterConditions("Delivery Date", new String[][]{{Operators.EqualTo, "Week:Begin...Week:End"}});
        filter.go();
        
        filter.setFilterConditions("Delivery Date", new String[][]{{Operators.EqualTo, "- 96 week...Today"}});
        filter.go();
        
        filter.setFilterConditions("Delivery Date", new String[][]{{Operators.EqualTo, "Today...+ 1 Week"}});
        filter.go();
        
        filter.setFilterConditions("Delivery Date", new String[][]{{Operators.EqualTo, "Month:Begin...Month:End"}});
        filter.go();
        
        filter.setFilterConditions("Delivery Date", new String[][]{{Operators.EqualTo, "- 25 Month...Today"}});
        filter.go();
        
        filter.setFilterConditions("Delivery Date", new String[][]{{Operators.EqualTo, "Today...+ 1 Month"}});
        filter.go();
        
        filter.setFilterConditions("Delivery Date", new String[][]{{Operators.EqualTo, "Quarter:Begin...Quarter:End"}});
        filter.go();
        
        filter.setFilterConditions("Delivery Date", new String[][]{{Operators.EqualTo, "- 2 Year...Today"}});
        filter.go();
        
        filter.setFilterConditions("Delivery Date", new String[][]{{Operators.EqualTo, "Today...+ 1 Year"}});
        filter.go();
        
        filter.setFilterConditions("Delivery Date", new String[][]{{Operators.EqualTo, "-1 Year -2 Quarter:begin -3 Day...+1 Week"}});
        filter.go();
        
    }
}
