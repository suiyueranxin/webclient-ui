package webclient.DE.Sales.SalesOrderList.ToT_Gravity.ChartTest_DefaultChartRender;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import webclient.general.BaseWebClientTest;
import webclient.general.WebClient;
import webclient.modules.administration.Logon;
import webclient.modules.analytic.ChartTypes;
import webclient.modules.home.Home;
import webclient.modules.sales.SalesOrderList;

public class DefaultChartRender extends BaseWebClientTest {
	
    @Test
    public void openStandardVariant(ITestContext context) throws InterruptedException {
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
    }
}
