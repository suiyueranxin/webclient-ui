package webclient.DE.Analytic.SalesAnalysisOverview.ToT_Gravity.SAONavigateToChart;

import java.util.Arrays;
import java.util.List;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import webclient.filters.FilterFields;
import webclient.filters.Filters;
import webclient.filters.Operators;
import webclient.filters.FilterFields.Analysis;
import webclient.general.BaseWebClientTest;
import webclient.general.WebClient;
import webclient.modules.administration.Logon;
import webclient.modules.analytic.CardTypes;
import webclient.modules.analytic.ChartTypes;
import webclient.modules.analytic.SalesAnalysisChart;
import webclient.modules.analytic.SalesAnalysisOverview;
import webclient.modules.analytic.ViewType;
import webclient.modules.home.Home;

public class SAONavigateToChart extends BaseWebClientTest {
    @Test
    public void navigateToChart(ITestContext context) throws InterruptedException {
        WebClient tc = new WebClient(context);
        String schemaName = tc.restoreInitialCompany("SBODEMODE", "manager", "manager", "SBODEMODE_UI", "SBODEMODE.tar.gz");
        tc.initialB1A(schemaName);
        tc.open();
      
        Logon logon = new Logon();
        logon.logon("manager", "manager", schemaName);
        
        Home home = new Home();
        home.navigateTo("Analytics->Sales Analysis Overview");
        SalesAnalysisOverview overview = new SalesAnalysisOverview();
        
        Filters gfilter = new Filters();
        // check-point-1: filter data type: time
        gfilter.setFilterConditions(FilterFields.Analysis.PostingDate, new String[][]{
            {Operators.EqualTo, "01/01/2016...03/31/2016"}
        });
        gfilter.go();
        
        //check-point-1: check global filters, view type, bindings of current card should be carried over to chart when navigate
        int cardTotal = 6;
        List<ViewType> expectedViewTypes = Arrays.asList(
            ViewType.Line,
            ViewType.Line,
            ViewType.StackedColumn,
            ViewType.Table,
            ViewType.Donut,
            ViewType.HeatMap
        );
        List<String> expectedMeasureAndDimensionSelector = Arrays.asList(
            "Net Sales Amount (LC);Posting Year;All",
            "Net Sales Amount (LC),Gross Profit (LC);Posting Year and Month;All",
            "Net Sales Amount (LC);Posting Year and Quarter;Business Partner Group Name",
            "Net Sales Amount (LC);Item Description & Code;All",
            "Net Sales Amount (LC);Sales Employee or Buyer Name",
            "Net Sales Amount (LC);Posting Year and Month;Bill-To Country"
        );   
        for (int cardIndex = 0; cardIndex < cardTotal; cardIndex++) {
            //naviage from card to chart
            overview.openCard(cardIndex);
            
            //check-point-1-1: global filters
            Filters filter = new Filters();
            filter.compareFilterCondition(Analysis.PostingDate, "01/01/2016...03/31/2016");
            filter.compareFilterCondition(Analysis.DocumentTypeDisplayName, "A/R Invoice");
            
            //check-point-1-2: view type
            SalesAnalysisChart mchart = new SalesAnalysisChart();
            mchart.checkViewShow(expectedViewTypes.get(cardIndex), true);

            //check-point-1-3: bindings, it means selected values of selector
            String[] expectedSelectedValues = expectedMeasureAndDimensionSelector.get(cardIndex).split(";");
            String measures = expectedSelectedValues[0];
            String dimension1 = expectedSelectedValues[1];
            String dimension2 = expectedSelectedValues.length > 2? expectedSelectedValues[2] : "";
            mchart.compareMeasureAndDimensionSelectors(measures, dimension1, dimension2);
            
            // back to overview from chart
            mchart.backToOverview();
        }
    }
}
