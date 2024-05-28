package webclient.Sales.SalesOrderList.ToT_Gravity.ChartTest;

import org.testng.annotations.Test;

import webclient.filters.Filters;
import webclient.general.BaseWebClientTest;
import webclient.modules.home.Home;
import webclient.modules.sales.SalesOrderList;
import widgets.ui5.ChartTypes;

public class NonDefaultVariantTest extends BaseWebClientTest{
	
	@Test
    public void testSave2NonDefaultVariantNSwitch(){

		Home home = new Home();
        SalesOrderList sol = new SalesOrderList();
        sol.selectChartType(ChartTypes.line);
        sol.setChartDimensions("Document Total", "Customer Code", "All");
        
        Filters filter = new Filters();
        
        //Check the default variant
        filter.compareDefaultViewName(VariantNames.myOpenSalesOrders);
        
        //Save as non-default private variant.
        filter.saveAsView(VariantNames.variantName, false, false);
        filter.compareDefaultViewName(VariantNames.variantName);
        
        //Switch Variant in the current page
        filter.selectView(VariantNames.allSalesOrders);
        
        filter.compareDefaultViewName(VariantNames.allSalesOrders);
        filter.selectView(VariantNames.variantName);
        sol.checkChartShow(ChartTypes.line, true);
        
        //Go back home and re-access
        home.backHome();
        home.navigateTo("Sales->Sales Orders");
        filter.compareDefaultViewName(VariantNames.myOpenSalesOrders);
        
        //Switch to chart variant
        filter.selectView(VariantNames.variantName);
        sol.checkChartShow(ChartTypes.line, true);

    }

}
