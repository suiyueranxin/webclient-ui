package webclient.DE.Sales.SalesOrderList.ToT_Gravity.ChartTest_ChartTypeSwitch;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import webclient.filters.Filters;
import webclient.general.BaseWebClientTest;
import webclient.general.WebClient;
import webclient.modules.administration.Logon;
import webclient.modules.analytic.ChartTypes;
import webclient.modules.home.Home;
import webclient.modules.sales.SalesOrderList;

public class ChartTypeSwitch extends BaseWebClientTest{
	
    @Test
    public void chartTypeSwitchTest(ITestContext context) throws InterruptedException {
        WebClient tc = new WebClient(context);
        String schemaName = tc.restoreInitialCompany("SBODEMODE", "manager", "manager", "SBODEMODE_UI", "SBODEMODE.tar.gz");
        //tc.initialB1A(schemaName);
        tc.open();
        
        Logon logon = new Logon();
        //logon.logon("manager", "manager", schemaName);
        logon.logon("manager", "manager", "US_I073369_2180_12_27");
        
        Home home = new Home();
        home.navigateTo("Sales->Sales Orders");
        
        SalesOrderList sol = new SalesOrderList();
        sol.clickChartView();
        sol.checkChartShow(ChartTypes.Line, true);
 
        sol.setChartDimensions("Document Total", "Customer Code", "Status");
        sol.checkChartShow(ChartTypes.Line, true);
        
        sol.selectChartType(ChartTypes.Column);
        sol.checkChartShow(ChartTypes.Column, true);
        
        sol.selectChartType(ChartTypes.Bar);
        sol.checkChartShow(ChartTypes.Bar, true);
        
        sol.selectChartType(ChartTypes.StackedColumn);
        sol.checkChartShow(ChartTypes.StackedColumn, true);
        
        sol.selectChartType(ChartTypes.StackedBar);
        sol.checkChartShow(ChartTypes.StackedBar, true);
        
        sol.selectChartType(ChartTypes.Pie);
        sol.checkChartShow(ChartTypes.Pie, true);
        
        sol.selectChartType(ChartTypes.Donut);
        sol.checkChartShow(ChartTypes.Donut, true);
        
        sol.selectChartType(ChartTypes.HeatMap);
        sol.checkChartShow(ChartTypes.HeatMap, true);
    }
}
