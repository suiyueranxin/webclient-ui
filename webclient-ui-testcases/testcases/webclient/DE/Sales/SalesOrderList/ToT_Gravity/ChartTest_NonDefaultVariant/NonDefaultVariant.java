package webclient.DE.Sales.SalesOrderList.ToT_Gravity.ChartTest_NonDefaultVariant;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import webclient.filters.Filters;
import webclient.general.BaseWebClientTest;
import webclient.general.WebClient;
import webclient.modules.administration.Logon;
import webclient.modules.analytic.ChartTypes;
import webclient.modules.home.Home;
import webclient.modules.sales.SalesOrderList;

public class NonDefaultVariant extends BaseWebClientTest{
	
    @Test
    public void defaultMeasureNDimensionsNSaveVariant(ITestContext context) throws InterruptedException {
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
        
        String standardVariant = "Standard";
        String myStandardVariant = "My Standard";
        String variantName = "echart-def-v";
        
        Filters filter = new Filters();
        //Check the default variant
        filter.compareDefaultViewName(standardVariant);
        
        //Save as non-default private variant.
        filter.saveAsView(variantName, false, false);
        filter.compareDefaultViewName(variantName);
        
        //Switch Variant in the current page
        filter.selectView(myStandardVariant);
        filter.compareDefaultViewName(myStandardVariant);
        filter.selectView(variantName);
        sol.checkChartShow(ChartTypes.Line, true);
        
        //Go back home and re-access
        home.backHome();
        home.navigateTo("Sales->Sales Orders");
        filter.compareDefaultViewName(standardVariant);
        
        //Switch to chart variant
        filter.selectView(variantName);
        
        sol.checkChartShow(ChartTypes.Line, true);
        
    }
}
