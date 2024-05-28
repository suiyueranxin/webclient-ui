package webclient.DE.Analytic.SalesAnalysisOverview.ToT_Gravity.SAOSaveAndSwitchVariant;

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

public class SAOSaveAndSwitchVariant extends BaseWebClientTest{
    @Test
    public void saveCustomVariant(ITestContext context) throws InterruptedException {
        WebClient tc = new WebClient(context);
        String schemaName = tc.restoreInitialCompany("SBODEMODE", "manager", "manager", "SBODEMODE_UI", "SBODEMODE.tar.gz");
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
        filter.saveAsView("TestSaveAndSwitchVariant-v1", false, false);
        
        // check point-1: check variant saved ok
        SalesAnalysisOverview overview = new SalesAnalysisOverview();
        filter.compareDefaultViewName("TestSaveAndSwitchVariant-v1");
        overview.compareKpiCardHeader(0, "1.3", "M");
        
        // check point-2: check switch to standard variant ok
        filter.selectView("Standard");
        filter.compareDefaultViewName("Standard");
//        overview.compareKpiCardHeader(0, "42", "K");
        
        // check point-3: check switch to custom variant ok
        filter.selectView("TestSaveAndSwitchVariant-v1");
        filter.compareDefaultViewName("TestSaveAndSwitchVariant-v1");
        overview.compareKpiCardHeader(0, "1.3", "M");
        
        // TODO: update custom variant and check variant can save
//        filter.setFilterConditions(Analysis.PostingDate, new String[][]{
//            {Operators.EqualTo, "01/01/2016...06/30/2016"}
//        });
//        filter.go();
//        filter.saveView(); // waiting TA ready
    }
}
