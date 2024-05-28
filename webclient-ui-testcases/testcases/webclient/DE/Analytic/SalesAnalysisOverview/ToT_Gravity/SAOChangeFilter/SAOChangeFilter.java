package webclient.DE.Analytic.SalesAnalysisOverview.ToT_Gravity.SAOChangeFilter;

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

public class SAOChangeFilter extends BaseWebClientTest{
    @Test
    public void changeFilter(ITestContext context) throws InterruptedException {
        WebClient tc = new WebClient(context);
        String schemaName = tc.restoreInitialCompany("SBODEMODE", "manager", "manager", "SBODEMODE_UI", "SBODEMODE.tar.gz");
        tc.initialB1A(schemaName);
        tc.open();
        
        Logon logon = new Logon();
        logon.logon("manager", "manager", schemaName);
        
        Home home = new Home();
        home.navigateTo("Analytics->Sales Analysis Overview");
        SalesAnalysisOverview overview = new SalesAnalysisOverview();
        
        Filters filter = new Filters();
        // check-point-1: filter data type: time
        filter.setFilterConditions(Analysis.PostingDate, new String[][]{
            {Operators.EqualTo, "01/01/2016...03/31/2016"}
        });
        filter.go();
        overview.compareKpiCardHeader(0, "1.3", "M");
        
        // check-point-2: filter data type: string
        // TODO: waiting framework ready
//        filter.setFilterConditions(Analysis.ItemCode, new String[][] {
//            {Operators.EqualTo, "R00001"},
//            {Operators.EqualTo, "R00002"}
//        });
//        filter.go();
//        overview.compareKpiCardHeader(0, "1.3", "K");
        
        // check-point-3: filter data type: enum and select one more items
        // TODO: waiting framework ready
//        filter.selectFromList(Analysis.DocumentTypeDisplayName, "A/R Invoice,Sales Order");
//        filter.go();
//        overview.compareKpiCardHeader(0, "2.7", "M");
        
    }
}
