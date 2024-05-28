package webclient.Sales.SalesOrderList.ToT_Gravity.ChartTest;

import org.testng.annotations.Test;

import webclient.filters.Filters;
import webclient.general.BaseWebClientTest;
import webclient.modules.home.Home;
import webclient.modules.sales.SalesOrderList;
import widgets.ui5.ChartTypes;

public class DefaultVariantTest extends BaseWebClientTest{
	
	@Test
    public void testSave2DefaultVariant(){

		Home home = new Home();
        //home.navigateTo("Sales->Sales Orders");
        //home.navigateToFromTiles("Sales->Sales Orders");
        
        SalesOrderList sol = new SalesOrderList();

        Filters filter = new Filters();
        
        //Switch back to column chart and save to default variant.
        sol.selectChartType(ChartTypes.column);
        //String defVariantName = "echart-newMeaNDims-def-v";
        
        //Case4:Save as default public variant and check variants switch.
        filter.saveAsView(VariantNames.defVariantName, true, true);
        filter.compareDefaultViewName(VariantNames.defVariantName);
        
        
        //Switch Variant in the current page
        filter.selectView(VariantNames.myOpenSalesOrders);
        filter.compareDefaultViewName(VariantNames.myOpenSalesOrders);
        filter.selectView(VariantNames.defVariantName);
        sol.checkChartShow(ChartTypes.column, true);
        
        //Go back home and re-access
        home.backHome();
        home.navigateTo("Sales->Sales Orders");
        filter.compareDefaultViewName(VariantNames.defVariantName);
        
        sol.checkChartShow(ChartTypes.column, true);

    }

}
