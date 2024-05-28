package webclient.DE.Analytic.SalesAnalysisChart.ToT_Gravity.SARCSwitchDefaultVariant;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import webclient.filters.FilterFields;
import webclient.filters.Filters;
import webclient.filters.Operators;
import webclient.filters.FilterFields.Analysis;
import webclient.general.BaseWebClientTest;
import webclient.general.WebClient;
import webclient.modules.administration.Logon;
import webclient.modules.analytic.SalesAnalysisChart;
import webclient.modules.analytic.ViewType;
import webclient.modules.home.Home;

public class SARCSwitchDefaultVariant extends BaseWebClientTest{
    @Test
    public void switchDefaultVariant(ITestContext context) throws InterruptedException {
        WebClient tc = new WebClient(context);
        String schemaName = tc.restoreInitialCompany("SBODEMODE", "manager", "manager", "SBODEMODE_UI", "SBODEMODE.tar.gz");
        tc.initialB1A(schemaName);
        tc.open();
        
        Logon logon = new Logon();
        logon.logon("manager", "manager", schemaName);
        
        Home home = new Home();
        home.navigateTo("Analytics->Sales Analysis by Rows Chart");
        
        Filters filter = new Filters();
        filter.setFilterConditions(Analysis.PostingDate, new String[][]{
            {Operators.EqualTo, "01/01/2016...03/31/2016"}
        });
        filter.go();
        filter.saveAsView("TestSwitchDefaultVariant-v1", true, false);
        
        // check point-1: check default variant switched to new variant
        home.backHome();
        home.navigateTo("Analytics->Sales Analysis by Rows Chart");
        SalesAnalysisChart chart = new SalesAnalysisChart();
        filter.compareDefaultViewName("TestSwitchDefaultVariant-v1");
        filter.compareFilterCondition(Analysis.PostingDate, "01/01/2016...03/31/2016");
        chart.checkViewShow(ViewType.Line, true);
        
        // check point-2: check default variant can be switch back to default variant
        // TODO: waiting for ta ready
//        filter.compareDefaultViewName("Standard");
//        filter.compareFilterCondition(Analysis.PostingDate, "20160101...20180630");
    }
}
