package webclient.Sales.SalesOrderList.ToT_Gravity.ChartTest;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import webclient.filters.Filters;
import webclient.general.BaseWebClientTest;
import webclient.general.WebClient;
import webclient.modules.administration.Logon;
import webclient.modules.home.Home;
import webclient.modules.sales.SalesOrderList;
import widgets.ui5.ChartTypes;


public class EChartMainTestsBK extends BaseWebClientTest{
	
    @Test
    public void changeDefaultMeasureNDimensionsNSaveVariant(ITestContext context) throws InterruptedException {
        WebClient tc = new WebClient(context);
        String schemaName = tc.restoreInitialCompany("SBODEMOUS", "manager", "manager", "SBODEMOUS_UI", "SBODEMOUS.tar.gz");
        tc.initialB1A(schemaName);
        tc.open();
        
        Logon logon = new Logon();
        logon.logon("manager", "manager", schemaName);
        
        Home home = new Home();
        home.navigateTo("Sales->Sales Orders");
        //home.navigateToFromTiles("Sales->Sales Orders");
        
        SalesOrderList sol = new SalesOrderList();
        sol.clickChartView();
        
        //Case1:Check default chart render.
        sol.checkChartShow(ChartTypes.line, true);
        
        
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
        sol.checkChartShow(ChartTypes.line, true);
        
        //Go back home and re-access
        home.backHome();
        home.navigateTo("Sales->Sales Orders");
        filter.compareDefaultViewName(standardVariant);
        
        //Switch to chart variant
        filter.selectView(variantName);
        sol.checkChartShow(ChartTypes.line, true);

        
        
        //Update the measure and dimensions
        sol.setChartDimensions("Document Total", "Customer Code", "Status");
        sol.checkChartShow(ChartTypes.line, true);
        
        //Case3:Switch chart type
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
        
        
        //Switch back to column chart and save to default variant.
        sol.selectChartType(ChartTypes.column);
        String defVariantName = "echart-newMeaNDims-def-v";
        
        //Case4:Save as default public variant and check variants switch.
        filter.saveAsView(defVariantName, true, true);
        filter.compareDefaultViewName(defVariantName);
        
        
        //Switch Variant in the current page
        filter.selectView(standardVariant);
        filter.compareDefaultViewName(standardVariant);
        filter.selectView(defVariantName);
        sol.checkChartShow(ChartTypes.column, true);
        
        //Go back home and re-access
        home.backHome();
        home.navigateTo("Sales->Sales Orders");
        filter.compareDefaultViewName(defVariantName);
        
        sol.checkChartShow(ChartTypes.column, true);
    }
}