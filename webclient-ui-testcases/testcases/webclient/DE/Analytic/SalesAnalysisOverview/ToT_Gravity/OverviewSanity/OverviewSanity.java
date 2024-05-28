package webclient.DE.Analytic.SalesAnalysisOverview.ToT_Gravity.OverviewSanity;

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
import webclient.modules.analytic.SalesAnalysisChart;
import webclient.modules.analytic.SalesAnalysisOverview;
import webclient.modules.analytic.ViewType;
import webclient.modules.home.Home;

public class OverviewSanity extends BaseWebClientTest{
    @Test
    public void OverviewSanity(ITestContext context) throws InterruptedException {
        WebClient tc = new WebClient(context);
        String schemaName = tc.restoreInitialCompany("SBODEMODE", "manager", "manager", "SBODEMODE_UI", "SBODEMODE.tar.gz");
        tc.initialB1A(schemaName);
        tc.open();
      
        Logon logon = new Logon();
        logon.logon("manager", "manager", schemaName);
        
        Home home = new Home();
        home.navigateTo("Analytics->Sales Analysis Overview");
        SalesAnalysisOverview overview = new SalesAnalysisOverview();
        
    // Scenario-1: open standard variant
        // check-point-1: check variant name is "Standard"
        Filters filter = new Filters();
        filter.compareDefaultViewName("Standard");
        
        // check-point-2: check filter settings
        filter.compareFilterCondition(Analysis.PostingDate, "Year:Begin...Today");
        filter.compareFilterCondition(Analysis.DocumentTypeDisplayName, "A/R Invoice");
        
        // check-point-3: current 6 cards are not empty
        int cardTotal = 6;
        for (int cardIndex=0; cardIndex<cardTotal; cardIndex++) {
            if (cardIndex == 3) {
                overview.checkCardContentsShow(cardIndex, CardTypes.Table, true);
            } else {
                overview.checkCardContentsShow(cardIndex, CardTypes.Chart, true);
            }
        }
        
        // check-point-4: calculated value of kpi value of the first card is correct.
        //overview.compareKpiCardHeader(0, "42", "K");
        
     // Scenario-2: change filter
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
        
     // Scenario-3: navigate to chart
//        Filters gfilter = new Filters();
//        // check-point-1: filter data type: time
//        gfilter.setFilterConditions(FilterFields.Analysis.PostingDate, new String[][]{
//            {Operators.EqualTo, "01/01/2016...03/31/2016"}
//        });
//        gfilter.go();
        
        //check-point-1: check global filters, view type, bindings of current card should be carried over to chart when navigate
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
        
     // Scenario-4: save and switch variant
        filter.saveAsView("TestSaveAndSwitchVariant-v1", false, false);
        
        // check point-1: check variant saved ok
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
        
     // Scenario-5: switch default variant
        filter.saveAsView("TestSwitchDefaultVariant-v1", true, false);
        
        // check point-1: check default variant switched to new variant
        home.backHome();
        home.navigateTo("Analytics->Sales Analysis Overview");
        filter.compareDefaultViewName("TestSwitchDefaultVariant-v1");
        overview.compareKpiCardHeader(0, "1.3", "M");
        
        // check point-2: check default variant can be switch back to default variant
        // TODO: waiting for ta ready
//        filter.compareDefaultViewName("Standard");
//        overview.compareKpiCardHeader(0, "2.3", "M");
    }
}
