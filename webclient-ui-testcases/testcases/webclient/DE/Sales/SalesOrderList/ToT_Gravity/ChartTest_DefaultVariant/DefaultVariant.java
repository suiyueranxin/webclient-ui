package webclient.DE.Sales.SalesOrderList.ToT_Gravity.ChartTest_DefaultVariant;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import webclient.filters.Filters;
import webclient.general.BaseWebClientTest;
import webclient.general.WebClient;
import webclient.modules.administration.Logon;
import webclient.modules.analytic.ChartTypes;
import webclient.modules.home.Home;
import webclient.modules.sales.SalesOrderList;

public class DefaultVariant extends BaseWebClientTest{
	
    @Test
    public void changeDefaultMeasureNDimensionsNSaveVariant(ITestContext context) throws InterruptedException {
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
        
        //Case1:Check default chart render.
        sol.checkChartShow(ChartTypes.Line, true);
        
        
        //Case2:First Save as non-default variant and check variant switch
        String standardVariant = "Standard";
        String myStandardVariant = "My Standard";
        String variantName = "echart-nondef-v";
        
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

        
        
        //Update the measure and dimensions
        sol.setChartDimensions("Document Total", "Customer Code", "Status");
        sol.checkChartShow(ChartTypes.Line, true);
        
        //Case3:Switch chart type
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
        
        
        //Switch back to column chart and save to default variant.
        sol.selectChartType(ChartTypes.Column);
        String defVariantName = "echart-newMeaNDims-def-v";
        
        //Case4:Save as default public variant and check variants switch.
        filter.saveAsView(defVariantName, true, true);
        filter.compareDefaultViewName(defVariantName);
        
        
        //Switch Variant in the current page
        filter.selectView(standardVariant);
        filter.compareDefaultViewName(standardVariant);
        filter.selectView(defVariantName);
        sol.checkChartShow(ChartTypes.Column, true);
        
        //Go back home and re-access
        home.backHome();
        home.navigateTo("Sales->Sales Orders");
        filter.compareDefaultViewName(defVariantName);
        
        sol.checkChartShow(ChartTypes.Column, true);
    }
}
