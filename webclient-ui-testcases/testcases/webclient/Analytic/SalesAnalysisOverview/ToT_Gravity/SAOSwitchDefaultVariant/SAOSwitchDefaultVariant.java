package webclient.Analytic.SalesAnalysisOverview.ToT_Gravity.SAOSwitchDefaultVariant;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import webclient.filters.Filters;
import webclient.filters.Operators;
import webclient.filters.FilterFields.Analysis;
import webclient.general.BaseWebClientTest;
import webclient.general.WebClient;
import webclient.modules.administration.Logon;
import webclient.modules.analytic.SalesAnalysisOverview;
import webclient.modules.home.Home;

public class SAOSwitchDefaultVariant extends BaseWebClientTest{
    @Test
    public void switchDefaultVariant(ITestContext context) throws InterruptedException {
        WebClient tc = new WebClient(context);
        String schemaName = tc.restoreInitialCompany("SBODEMOUS", "manager", "manager", "SBODEMOUS_UI", "SBODEMOUS.tar.gz");
        tc.initialB1A(schemaName);
        tc.open();
        
        Logon logon = new Logon();
        logon.logon("manager", "manager", schemaName);
        
        Home home = new Home();
        home.navigateTo("Analytics->Sales Analysis Overview");
        
        Filters filter = new Filters();
        filter.setFilterConditions(Analysis.PostingDate, new String[][]{
            {Operators.EqualTo, "01/01/2016...03/31/2016"}
        });
        filter.go();
        filter.saveAsView("TestSwitchDefaultVariant-v1", true, false);
        
        // check point-1: check default variant switched to new variant
        home.backHome();
        home.navigateTo("Analytics->Sales Analysis Overview");
        SalesAnalysisOverview overview = new SalesAnalysisOverview();
        filter.compareDefaultViewName("TestSwitchDefaultVariant-v1");
        overview.compareKpiCardHeader(0, "1.3", "M");
        
        // check point-2: check default variant can be switch back to default variant
        // TODO: waiting for ta ready
//        filter.compareDefaultViewName("Standard");
//        overview.compareKpiCardHeader(0, "2.3", "M");
    }
}
