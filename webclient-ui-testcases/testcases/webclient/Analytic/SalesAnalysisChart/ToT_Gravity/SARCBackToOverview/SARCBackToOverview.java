package webclient.Analytic.SalesAnalysisChart.ToT_Gravity.SARCBackToOverview;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import webclient.filters.Filters;
import webclient.filters.Operators;
import webclient.filters.FilterFields.Analysis;
import webclient.general.BaseWebClientTest;
import webclient.general.WebClient;
import webclient.modules.administration.Logon;
import webclient.modules.analytic.SalesAnalysisChart;
import webclient.modules.analytic.SalesAnalysisOverview;
import webclient.modules.home.Home;

public class SARCBackToOverview extends BaseWebClientTest{
    @Test
    public void backToOverview(ITestContext context) throws InterruptedException {
        WebClient tc = new WebClient(context);
        String schemaName = tc.restoreInitialCompany("SBODEMOUS", "manager", "manager", "SBODEMOUS_UI", "SBODEMOUS.tar.gz");
        tc.initialB1A(schemaName);
        tc.open();
        
        Logon logon = new Logon();
        logon.logon("manager", "manager", "US_I073369_2615_05_20");
        
        Home home = new Home();
        home.navigateTo("Analytics->Sales Analysis Overview");
        
        SalesAnalysisOverview overview = new SalesAnalysisOverview();
        
        // change global filter in overview
        Filters filterInOverview = new Filters();
        filterInOverview.setFilterConditions(Analysis.PostingDate, new String[][]{
            {Operators.EqualTo, "01/01/2016...03/31/2016"}
        });
        filterInOverview.go();
        
        // check-point-1: check global filter's changing can be carried to chart from overview
        overview.openCard(0);
        Filters filterInChart = new Filters();
        filterInChart.compareFilterCondition(Analysis.PostingDate, "01/01/2016...03/31/2016");
        filterInChart.compareFilterCondition(Analysis.DocumentTypeDisplayName, "A/R Invoice");
        
        // change global filter in chart
        filterInChart.setFilterConditions(Analysis.PostingDate, new String[][]{
            {Operators.EqualTo, "01/01/2016...06/30/2016"}
        });
        filterInChart.go();
        
        // check-point-2: check global filter's changing cannot be carried to overview from chart
        SalesAnalysisChart chart = new SalesAnalysisChart();
        chart.backToOverview();
        Filters filterInBackOverview = new Filters();
        filterInBackOverview.compareFilterCondition(Analysis.PostingDate, "01/01/2016...03/31/2016");
        filterInChart.compareFilterCondition(Analysis.DocumentTypeDisplayName, "A/R Invoice");
    }
}
