package webclient.Analytic.SalesAnalysisChart.ToT_Gravity.SARCChangeFilter;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import webclient.filters.FilterFields;
import webclient.filters.Filters;
import webclient.filters.Operators;
import webclient.general.BaseWebClientTest;
import webclient.general.WebClient;
import webclient.modules.administration.Logon;
import webclient.modules.analytic.SalesAnalysisChart;
import webclient.modules.analytic.ViewType;
import webclient.modules.home.Home;

public class SARCChangeFilter extends BaseWebClientTest{
    @Test
    public void changeFilter(ITestContext context) throws InterruptedException {
        WebClient tc = new WebClient(context);
        String schemaName = tc.restoreInitialCompany("SBODEMOUS", "manager", "manager", "SBODEMOUS_UI", "SBODEMOUS.tar.gz");
        tc.initialB1A(schemaName);
        tc.open();
      
        Logon logon = new Logon();
        logon.logon("manager", "manager", schemaName);
        
        Home home = new Home();
        home.navigateTo("Analytics->Sales Analysis by Rows Chart");
        SalesAnalysisChart mchart = new SalesAnalysisChart();
        
        Filters filter = new Filters();
        // check-point-1: filter data type: time
        filter.setFilterConditions(FilterFields.Analysis.PostingDate, new String[][]{
            {Operators.EqualTo, "01/01/2016...03/31/2016"}
        });
        filter.go();
        // TODO: check chart data
        mchart.checkViewShow(ViewType.Line, true);
    }
}
