package webclient.DE.Analytic.SalesAnalysisChart.ToT_Gravity.MChartSanity;

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
import webclient.modules.analytic.SalesAnalysisOverview;
import webclient.modules.analytic.ViewType;
import webclient.modules.home.Home;

public class MChartSanity extends BaseWebClientTest{
    @Test
    public void MChartSanity(ITestContext context) throws InterruptedException {
        WebClient tc = new WebClient(context);
        String schemaName = tc.restoreInitialCompany("SBODEMODE", "manager", "manager", "SBODEMODE_UI", "SBODEMODE.tar.gz");
        tc.initialB1A(schemaName);
        tc.open();
          
        Logon logon = new Logon();
        logon.logon("manager", "manager", schemaName);
        
        Home home = new Home();
        home.navigateTo("Analytics->Sales Analysis by Rows Chart");
        SalesAnalysisChart mchart = new SalesAnalysisChart();
        
    // Scenario-1: open default variant
        // check-point-1: check variant name is "Standard"
        Filters filter = new Filters();
        filter.compareDefaultViewName("Standard");
        
        //check-point-2: check global filter
        filter.compareFilterCondition(FilterFields.Analysis.PostingDate, "- 11 Month:Begin...Month:End");
        filter.compareFilterCondition(Analysis.DocumentTypeDisplayName, "A/R Invoice");
        
        //check-point-3: check view type is line and chart is not empty
        // mchart.checkViewShow(ViewType.Line, true);
        
        //check-point-4: check selector
        mchart.compareMeasureAndDimensionSelectors("Net Sales Amount (LC),Gross Profit (LC)", "Posting Year and Month", "All");
        
    // Scenario-2: change filter
        // check-point-1: filter data type: time
        filter.setFilterConditions(FilterFields.Analysis.PostingDate, new String[][]{
            {Operators.EqualTo, "01/01/2016...03/31/2016"}
        });
        filter.go();
        // TODO: check chart data
        mchart.checkViewShow(ViewType.Line, true);
        
    // Scenario-3: save and switch variant
        filter.saveAsView("TestSaveAndSwitchVariant-v1", false, false);
        
        // check point-1: check variant saved ok
        filter.compareDefaultViewName("TestSaveAndSwitchVariant-v1");
        filter.compareFilterCondition(FilterFields.Analysis.PostingDate, "01/01/2016...03/31/2016");
        
        // check point-2: check switch to standard variant ok
        filter.selectView("Standard");
        filter.compareDefaultViewName("Standard");
        filter.compareFilterCondition(FilterFields.Analysis.PostingDate, "- 11 Month:Begin...Month:End");
        
        // check point-3: check switch to custom variant ok
        filter.selectView("TestSaveAndSwitchVariant-v1");
        filter.compareDefaultViewName("TestSaveAndSwitchVariant-v1");
        filter.compareFilterCondition(FilterFields.Analysis.PostingDate, "01/01/2016...03/31/2016");
        
    // Scenario-4: switch default variant
        filter.selectView("Standard");
        filter.setFilterConditions(FilterFields.Analysis.PostingDate, new String[][]{
            {Operators.EqualTo, "01/01/2016...03/31/2016"}
        });
        filter.go();
        filter.saveAsView("TestSwitchDefaultVariant-v1", true, false);
        
        // check point-1: check default variant switched to new variant
        home.backHome();
        home.navigateTo("Analytics->Sales Analysis by Rows Chart");
        filter.compareDefaultViewName("TestSwitchDefaultVariant-v1");
        filter.compareFilterCondition(Analysis.PostingDate, "01/01/2016...03/31/2016");
        mchart.checkViewShow(ViewType.Line, true);
        // check point-2: check default vairant switched back to system variant
        // TODO: wait TA ready
        
    // Scenario-5: back to overview
        home.backHome();
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
        mchart.backToOverview();
        Filters filterInBackOverview = new Filters();
        filterInBackOverview.compareFilterCondition(Analysis.PostingDate, "01/01/2016...03/31/2016");
        filterInChart.compareFilterCondition(Analysis.DocumentTypeDisplayName, "A/R Invoice");
        
    }
}
