package webclient.Analytic.SalesAnalysisChart.ToT_Gravity.SARCOpenStandardVariant;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import webclient.filters.FilterFields;
import webclient.filters.Filters;
import webclient.filters.FilterFields.Analysis;
import webclient.general.BaseWebClientTest;
import webclient.general.WebClient;
import webclient.modules.administration.Logon;
import webclient.modules.analytic.SalesAnalysisChart;
import webclient.modules.analytic.ViewType;
import webclient.modules.home.Home;

public class SARCOpenStandardVariant extends BaseWebClientTest {
    @Test
    public void openStandardVariant(ITestContext context) throws InterruptedException {
        WebClient tc = new WebClient(context);
        String schemaName = tc.restoreInitialCompany("SBODEMOUS", "manager", "manager", "SBODEMOUS_UI", "SBODEMOUS.tar.gz");
        tc.initialB1A(schemaName);
        tc.open();
      
        Logon logon = new Logon();
        logon.logon("manager", "manager", schemaName);
        
        Home home = new Home();
        home.navigateTo("Analytics->Sales Analysis by Rows Chart");
        SalesAnalysisChart mchart = new SalesAnalysisChart();
        
        // check-point-1: check variant name is "Standard"
        Filters filter = new Filters();
        filter.compareDefaultViewName("Standard");
        
        //check-point-2: check global filter
        filter.compareFilterCondition(FilterFields.Analysis.PostingDate, "- 11 Month:Begin...Month:End");
        filter.compareFilterCondition(Analysis.DocumentTypeDisplayName, "A/R Invoice");
        
        //check-point-3: check view type is line and chart is not empty
//        mchart.checkViewShow(ViewType.Line, true);
        
        //check-point-4: check selector
        mchart.compareMeasureAndDimensionSelectors("Net Sales Amount (LC),Gross Profit (LC)", "Posting Year and Month", "All");
    }
}
