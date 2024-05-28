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

public class EChartMainTestCases extends BaseWebClientTest{
	
    @Test
    public void echartMainTestCases(ITestContext context) throws InterruptedException {
        WebClient tc = new WebClient(context);
        String schemaName = tc.restoreInitialCompany("SBODEMOUS", "manager", "manager", "SBODEMOUS_UI", "SBODEMOUS.tar.gz");
        tc.initialB1A(schemaName);
        tc.open();
        
        Logon logon = new Logon();
        logon.logon("manager", "manager", schemaName);
        //logon.logon("manager", "manager", "US_I073369_3905_12_24");
        
        //Default line chart render
        DefaultChartRenderTest dfcr = new DefaultChartRenderTest();
        dfcr.testDefaultChartRender();
       
        //Heatmap special warning message
        HeatmapWarningMessageTest heatwm = new HeatmapWarningMessageTest();
        heatwm.testHeatmapWarningMessage();
        
        //show/hide legend
        ShowHideLegendTest shlt = new ShowHideLegendTest();
        shlt.testShowHideLegend();
        
        //Save to non default variant and switch
        NonDefaultVariantTest ndvt = new NonDefaultVariantTest();
        ndvt.testSave2NonDefaultVariantNSwitch();
        
        //Switch chart type
        ChartSwitchTest cst = new ChartSwitchTest();
        cst.testChartSwitch();
        
        //Save to default variant test
        DefaultVariantTest dvt = new DefaultVariantTest();
        dvt.testSave2DefaultVariant();
        
        //Open/close full screen
        OpenCloseFullScreenTest ocfst = new OpenCloseFullScreenTest();
        ocfst.testOpenCloseFullScreen();	
        
        //date filter
        DateFilterTest dft = new DateFilterTest();
        dft.testStartDate2EndDateFilter();

    }
}